package controllers

import models.Article
import javax.inject._
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

     val viewModel = ArticlePageViewModel(createArticle)

    Ok(views.html.home.article_page(viewModel))
  }


  private def createArticle: Article = {
    val title = "Experimental Article"
    val teaser = "...and then this happened!"
    val description = "What happens when you write an expereimental article?"
    val body =  Html("""
      <p>
        På et institut for cellebiologi i New York var professor Mayumi Ito og 13 andre forskere i gang med at eksaminere årsagen til,
        at hår bliver gråt på bittesmå mus, da de fandt noget overraskende.
      </p>
      <p>
        Indtil nu var stamceller tænkt som noget, der kun befandt sig i stamcelleafdelingen af hårsækken.
      </p>
      """)
   /* val body = Html("") */

    new Article(title, teaser, description, body)
  }
}
