"use strict";

class BannerUtil {

    static setBasePath(elm, basePath) {
        if (elm instanceof HTMLIFrameElement && elm.dataset?.basepathSet === "0" && elm.srcdoc?.length > 0) {
            // Prevent recursive onload loop
            elm.dataset.basepathSet = "1"
            if (typeof (basePath) !== "string" || basePath.length === 0 &&
                (typeof (elm.dataset.src) === "string" && elm.dataset.src.length > 0)) {
                basePath = elm.dataset.src
            }
            if (typeof (basePath) !== "string" || basePath.length === 0) return;
            basePath = basePath.replace(/\w+\.html$/gi, "");
            const parser = new DOMParser();
            const parsedSrcDoc = parser.parseFromString(elm.srcdoc.toString(), "text/html");
            let base = document.createElement("base")
            base.href = basePath
            parsedSrcDoc.head.prepend(base)
            elm.srcdoc = parsedSrcDoc.documentElement.innerHTML
        }
    }
}
