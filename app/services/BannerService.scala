package services

import enums.Zone.Zone
import models.Banner
import play.libs.ws._

import javax.inject._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json._
// import play.api.libs.ws.JsonBodyReadables._
// import play.api.libs.ws.JsonBodyWritables._

@Singleton
class BannerService @Inject() (ws: WSClient) {

  private val authUser     = "Jyllands-Posten"
  private val authPassword = "1234"
  private val authScheme   = play.api.libs.ws.WSAuthScheme.BASIC

  def getBanner(zone: Zone): Future[Option[Banner]] = {

    val data = Json.obj(
      "publisher"   -> "Jyllands-Posten",
      "zone"        -> zone.toString.toLowerCase(),
      "includeHtml" -> true,
    )

    val eventualBannerOpt = ws
      .asScala()
      .url("http://localhost:9100/api/creative/request")
      .withAuth(authUser, authPassword, authScheme)
      .post(data)
      .map(response => {
        Json.fromJson[Banner](response.body[JsValue]).asOpt
      })

    eventualBannerOpt
  }
}
