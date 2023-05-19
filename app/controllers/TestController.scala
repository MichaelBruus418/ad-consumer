package controllers

import play.api.mvc._
import play.libs.ws._

import java.util.Base64
import javax.inject._

@Singleton
class TestController @Inject()(ws: WSClient, val controllerComponents: ControllerComponents) extends BaseController {

  private val authUser = "Jyllands-Posten"
  private val authPassword = "1234"
  private val authScheme = WSAuthScheme.BASIC

  def index(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>

    // val credentials = "jyllands-posten:1234"
    // val encoded = new String(Base64.getEncoder.encode(credentials.getBytes("utf-8")))
    val wsReq = ws.url("http://localhost:9100/api/banner/ping").setAuth(authUser, authPassword, authScheme)

    val eventualResponse = wsReq.get().toCompletableFuture
    // Wait for respsone
    val response = eventualResponse.get()
    val status = response.getStatus

    Ok("I'm TestController.\nStatus code: " + status + "\nBody: " + response.getBody)
  }
}
