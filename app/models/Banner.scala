package models

import play.api.libs.json.{Format, Json}

case class Banner(
  serve: String,
  downloadedImpression: String,
  viewableImpression: String,
  zone: String,
  width: Int,
  height: Int,
  html: Option[String],
  basepath: Option[String]
)

object Banner {
  // --- Combined read/write ---
  implicit val format: Format[Banner] = Json.format[Banner]
}
