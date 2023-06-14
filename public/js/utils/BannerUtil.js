export class BannerUtil {
    // Banner iframes is expected to be members of this css class
    static #className = "banner"
    // Define if ads displayed multiple times will be counted as one
    static #counterIgnoresDuplicates = false
    static #bannerData
    static #authEnc = btoa("jyllands-posten:1234")

    static attachListeners(bannerData) {
        if (!bannerData instanceof Map) throw new TypeError("Map expected.")
        this.#bannerData = bannerData
        const elms = document.querySelectorAll("." + this.#className);

        elms.forEach(elm => {
            elm.addEventListener("load", (event) => this.#countImpression(event, elm))
        })
    }

    static #countImpression(event, elm) {
        if (!elm instanceof HTMLIFrameElement) new TypeError("HTMLIFrameElement expected.")
        console.log("MetricCallback: " + elm.dataset.hash)
        const downloadedUrl = this.#bannerData.get(elm.dataset.hash)?.downloaded
        if (downloadedUrl !== "undefined") {
            this.#dispatch(downloadedUrl)
                .then(data => {
                    console.log("response: " + data.toString())
                })
                .catch(e => {
                    console.error(e)
                })

        }
    }

    static async #dispatch(url) {
        // Create and dispatch fetch.
        const response = await fetch(url, {
            method: 'PATCH',
            mode: 'no-cors',
            credentials: 'same-origin',
            cache: 'no-cache',
            headers: {
                'Authorization': 'Basic ' + this.#authEnc,
                'Content-Type': 'application/json',
            },
            body: '{}',
        });

        if (!response.ok) {
            // Compose error message
            let msg = response.status + " " + response.statusText;
            // Include text/plain content if present.
            if (response.headers.get("content-type")?.includes("text/plain")) msg += "\n" + await (response.text())
            // Either reject promise or throw Error.
            return Promise.reject(msg)
            // throw new Error(msg)
        }

        // return response.json();

    }
}
