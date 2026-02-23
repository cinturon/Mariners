package dev.jbelt.seattlemariners.screens.navigation

sealed class Screens(val route: String){

    object Home: Screens("Home")

    object Roster: Screens("Roster")

    object News: Screens("News")

    object Events: Screens("Events")

    object ArticleDetail: Screens("ArticleDetail/{articleId}") {
        fun createRoute(articleId: Long) = "ArticleDetail/$articleId"
    }

}