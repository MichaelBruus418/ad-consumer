package controllers

import play.api.libs.ws.BodyWritable
import play.api.mvc._
import play.libs.ws._

import java.util.Base64
import javax.inject._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json._
import play.api.libs.ws.JsonBodyReadables._
import play.api.libs.ws.JsonBodyWritables._

@Singleton
class TestController @Inject()(ws: WSClient, val controllerComponents: ControllerComponents) extends BaseController {

  private val authUser = "Jyllands-Posten"
  private val authPassword = "1234"
  private val authScheme = play.api.libs.ws.WSAuthScheme.BASIC

  def index(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val data = Json.obj("publisher" -> "Jyllands-Posten", "zone" -> "sidebar")

    try {
      val eventualResult = ws.asScala().url("http://localhost:9100/api/creative/ping")
        .withAuth(authUser, authPassword, authScheme)
        .post(data)
        .map(response => {
          Ok(response.toString)
        })
        .recover(e => InternalServerError(e.toString))

      eventualResult
    }
    catch {
      case e: Throwable => Future.successful(InternalServerError(e.toString))
    }
  }
}
