package dev.jbelt.seattlemariners

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import coil.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint
import dev.jbelt.seattlemariners.screens.events.EventScreen
import dev.jbelt.seattlemariners.screens.home.HomeScreen
import dev.jbelt.seattlemariners.screens.navbars.BottomNav
import dev.jbelt.seattlemariners.screens.navigation.Screens
import dev.jbelt.seattlemariners.screens.news.NewsArticle
import dev.jbelt.seattlemariners.screens.news.NewsScreen
import dev.jbelt.seattlemariners.screens.roster.RosterScreen
import dev.jbelt.seattlemariners.ui.theme.SeattleMarinersTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = false
        setContent {
            SeattleMarinersTheme {
                val navController = rememberNavController()

                // Content layer with navigation
                Scaffold(
                    bottomBar = { BottomNav(navController) },
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Transparent
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        // Background image layer - only fills content area above bottom nav
                        AsyncImage(
                            model = R.drawable.stadium_bg,
                            contentDescription = "Stadium Background",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        // Navigation content on top
                        NavHost(
                            navController = navController,
                            startDestination = Screens.Home.route
                        ) {
                            composable(Screens.News.route) {
                                NewsScreen()
                            }
                            composable(Screens.Roster.route) {
                                RosterScreen()
                            }
                            composable(Screens.Home.route) {
                                HomeScreen(
                                    onArticleClick = { articleId ->
                                        navController.navigate(Screens.ArticleDetail.createRoute(articleId))
                                    }
                                )
                            }
                            composable(Screens.Events.route) {
                                EventScreen()
                            }
                            composable(
                                route = Screens.ArticleDetail.route,
                                arguments = listOf(navArgument("articleId") { type = NavType.LongType })
                            ) { backStackEntry ->
                                val articleId = backStackEntry.arguments?.getLong("articleId") ?: 0L
                                NewsArticle(
                                    articleId = articleId,
                                    onBackClick = { navController.popBackStack() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
