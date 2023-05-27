package utils

import models.Article
import play.twirl.api.Html

object ArticleUtil {

  def createArticle: Article = {
    val title = "Hjemmelavet ketchup til grillpølsen"
    val teaser = "...and then this happened!"
    val description = "Jyllands-Postens chefkok giver dig en opskrift på ketchup og anbefaler tilbehør til sommerens grillaftener."
    val image = """<img class="article" src="/assets/images/articles/hjemmelavet-ketchup.jpg">"""
    val body =
      """
      <p>
      Ketchuppen er næsten uundværligt tilbehør, når pølserne har forladt grillen en dejlig sommeraften.
      </p>
      <p>
      Jyllands-Postens egen chefkok, Karsten Mikkelsen, sværger selv til både sennep og ketchup som supplement til grillmaden, og han giver her en opskrift på en hjemmerørt udgave af den røde af de to dyppelser.
      </p>
      <p>
      Det hele starter med en nogenlunde begrænset ingrediensliste, hvor mange af tingene nok i forvejen står i de flestes køkkenskabe: tomater, æbler, hvidløg, løg, honning, æbleeddike, kanel, spidskommen, farin og salt.
      </p>
      <p>
      Tilberedningen tager ikke lang tid, og Karsten Mikkelsen mener bestemt, at det er den lille umage værd:
       </p>
       <p>
      »Den ser ikke ud som en Heinz-ketchup, men her ved man, hvad der er i. Der er heller ikke nogen E-numre i spil, så den holder ikke så lang tid, men den smager til gengæld rigtigt godt.«
      </p>
      <p>
      Når man har fundet ingredienserne frem, skal de største af råvarerne hakkes groft, inden man smider det hele i en gryde og lader det koge i en halv time med jævnlig omrøring. Derefter skal det blot blendes og køles ned.
      Syre, creme og krydder
      </p>
      <p>
      Hvis man har egne favoritkrydderier, er en hjemmelavet ketchup en god måde at få dem i spil.
      </p>
      <p>
      »Ketchup skal selvfølgelig smage af tomat, men man kan bruge alle de krydderier, man har lyst til,« forsikrer Karsten Mikkelsen, der også kommer med en anbefaling til yderligere tilbehør til grillpølsen:
      </p>
      <p>
      »Hvis du både har ketchup, sennep, kartoffelsalat og en frankfurter, skal der noget neutralt ind. Det kunne være noget spidskål eller nogle asparges. Der mangler ikke syre, for det har du i både ketchup og sennep, og så har du den cremede kartoffelsalat og krydrede frankfurter ved siden af. Allerhøjest kan man lige vende salaten i lidt olie og citron.«
      </p>
      """


    new Article(title, teaser, description, Html(image + body))

  }

}
