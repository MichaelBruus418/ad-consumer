package controllers

import models.Article

import javax.inject._
import play.api._
import play.api.mvc._
import play.twirl.api.Html
import viewModels.ArticlePageViewModel

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class  HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

   def index(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>

     val viewModel = new ArticlePageViewModel(getArticle)

    Ok(views.html.home.article_page(viewModel))
  }


  private def getArticle: Article = {
    val title = "Experimental Article"
    val teaser = "This is an exceotional article_page"
    val body =  Html.apply("""
     <p>
      This is a test paragraph.
     </p>
     <p>
        This is a another test paragraph.
     </p>
      """.replaceAll("\n\t", " "))

    new Article(title, teaser, body)
  }
}
