package utils

object BannerUtil {
  def getBasePathFromUrl(url: String): String = {
    val regex = "\\w+\\.html$".r
    regex.replaceAllIn(url, "")
  }
}
