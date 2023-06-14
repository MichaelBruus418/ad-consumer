package models

import play.api.libs.json.{Format, Json}

case class Banner(
  serve: String,
  downloadedImpression: String,
  viewableImpression: String,
  hash: String,
  zone: String,
  width: Int,
  height: Int,
  html: Option[String]
)

object Banner {
  implicit val format: Format[Banner] = Json.format[Banner]
}
