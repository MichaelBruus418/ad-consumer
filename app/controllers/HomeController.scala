package controllers

import enums.Zone
import models.Banner

import javax.inject._
import play.api.mvc._
import services.BannerService
import utils.ArticleUtil
import viewModels.ArticlePageViewModel

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

@Singleton
class HomeController @Inject() (
  val controllerComponents: ControllerComponents,
  val bannerService: BannerService,
) extends BaseController {

  def indexOFF(): Action[AnyContent] = Action {
    println("Ad-Consumer: HomeController.index() called.")
    Ok("Loop this")
  }

  def index(): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      println("Ad-Consumer: HomeController.index() called.")

      val eventuals: List[Future[Option[Banner]]] =
        List(
          bannerService.getBanner(Zone.BODY),
          bannerService.getBanner(Zone.SIDEBAR),
          bannerService.getBanner(Zone.SIDEBAR),
          // bannerService.getBanner(Zone.SIDEBAR),
          // bannerService.getBanner(Zone.SIDEBAR),
        )

      // Transform successful eventuals to a list in a single future.
      val eventual = Future.sequence(eventuals.map(_.transform(Success(_))))
      // Split into successes and failures
      val eventualBannerOpts = eventual.map(_.collect{case Success(x) => x})
      val eventualFailures = eventual.map(_.collect{case Failure(x) => x })

      // Error handling of failed futures
      for {
        failures <- eventualFailures
      } {
        for (e <- failures) {
          println(e.toString)
        }
      }

      // Work successes
      val eventualViewModel = eventualBannerOpts
        .map(bannerOpts => {
          val sidebarBanners                            =
            bannerOpts.flatMap(v => v.filter(_.zone == "sidebar"))
          val (sidebarRightBanners, sidebarLeftBanners) =
            sidebarBanners.splitAt(sidebarBanners.length / 2)
          val bodyBanners                               =
            bannerOpts.flatMap(v => v.filter(_.zone == "body"))
          ArticlePageViewModel(
            ArticleUtil.createArticle,
            bodyBanners,
            sidebarLeftBanners,
            sidebarRightBanners,
          )
        })
        .recover(_ => ArticlePageViewModel(ArticleUtil.createArticle))

      eventualViewModel
        .map(viewModel => Ok(views.html.home.article_page(viewModel)))
        .recover(e => InternalServerError(e.toString))

  }

}
