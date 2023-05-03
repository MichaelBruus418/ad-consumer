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
    val description = "What happens when you write an experimental article?"
    val body =  Html("""
      <p>
        På et institut for cellebiologi i New York var professor Mayumi Ito og 13 andre forskere i gang med at eksaminere årsagen til,
        at hår bliver gråt på bittesmå mus, da de fandt noget overraskende.
      </p>
      <p>
        Indtil nu var stamceller tænkt som noget, der kun befandt sig i stamcelleafdelingen af hårsækken.
      </p>
      <p>
    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ultricies nibh ornare tincidunt porta. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque et venenatis magna. Phasellus et dapibus nisi, ac porta dolor. Nulla dapibus at metus vitae cursus. Cras orci nisi, semper at accumsan vitae, faucibus molestie libero. Donec pellentesque sem ac varius dignissim. Vestibulum turpis nisi, molestie nec ullamcorper in, pulvinar non enim. Curabitur fermentum ipsum quis sem consectetur condimentum.
    </p>
    <p>
    Phasellus varius aliquet turpis finibus vulputate. Aenean aliquam ornare velit, at suscipit magna. Ut tortor est, malesuada sed mauris quis, commodo lacinia velit. Etiam vulputate nunc vitae lorem dignissim faucibus. Vestibulum tincidunt nulla ipsum, sed varius ipsum tristique a. Fusce sed nulla a turpis convallis euismod. Proin hendrerit libero a ex hendrerit interdum. Proin cursus blandit magna, id interdum dui cursus in. Ut porta sit amet sem faucibus bibendum. Proin non congue tellus. Etiam dictum, magna quis pulvinar viverra, dui lacus facilisis lorem, a euismod libero nisi faucibus leo. Ut tincidunt feugiat arcu. Nam porttitor velit dui, vel eleifend mauris tincidunt ut. Sed ornare ac elit vitae condimentum.
    </p>
    <p>
    Donec ut magna eleifend, faucibus augue non, suscipit turpis. Proin metus magna, dictum luctus risus at, feugiat iaculis dolor. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Praesent velit urna, fringilla nec hendrerit at, dapibus quis elit. Vestibulum hendrerit, augue gravida porttitor tempus, magna ipsum tristique lacus, a porta eros leo ut nulla. Suspendisse tristique nunc et tortor bibendum fringilla. Aenean sit amet odio et nunc aliquam blandit. Nam iaculis laoreet mauris, eu fermentum quam consectetur a. Donec molestie libero massa, id lacinia elit vestibulum vitae.
    </p>
    <p>
    Ut nulla libero, viverra eu ligula at, porta rutrum lorem. Vivamus vestibulum luctus lectus, vel commodo turpis elementum non. Pellentesque tincidunt lorem sed magna convallis porttitor. Curabitur porta vehicula tempor. Ut aliquet ligula mi. Donec semper semper nunc ac mattis. Nunc neque turpis, posuere vitae pulvinar et, malesuada et risus. Curabitur eu augue enim. Quisque a justo id quam suscipit tincidunt. In fermentum dapibus nulla vel rutrum. Duis mollis ac ligula ut mattis. Donec ac ipsum nec enim fermentum mattis. Ut cursus enim ut nisl facilisis pharetra.
    </p>
    <p>
    Nunc elit nunc, facilisis non pulvinar nec, rhoncus eget tortor. Praesent dictum leo neque, eget faucibus erat lacinia quis. Suspendisse eget pretium ex, non ornare turpis. Pellentesque elit arcu, tempor non blandit ac, feugiat ac purus. Sed in dui pulvinar, ornare tellus mattis, condimentum tortor. Phasellus imperdiet fringilla arcu, a ultrices nibh accumsan sed. Vestibulum tincidunt urna sed orci imperdiet, at cursus sapien fringilla. Nulla ut mollis sapien, ac volutpat ex. Proin pretium accumsan nulla non elementum.
    </p>
      """)


    new Article(title, teaser, description, body)
  }
}
