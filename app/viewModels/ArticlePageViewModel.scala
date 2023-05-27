package viewModels

import models.{Article, Banner}

case class ArticlePageViewModel (
  article: Article,
  bodyBanners: List[Banner] = List(),
  sidebarLeftBanners: List[Banner] = List(),
  sidebarRightBanners: List[Banner] = List()
)
