package dev.jbelt.seattlemariners.screens.navbars

import android.R.attr.onClick
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.jbelt.seattlemariners.screens.navigation.Screens


@Composable
fun BottomNav(
    navController: NavController
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.News,
        BottomNavItem.Roster,
        BottomNavItem.Events,
    )


    NavigationBar(
        modifier = Modifier.height(100.dp),
        containerColor = Color.Black,
        contentColor = Color.White,
        tonalElevation = 8.dp,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                ,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route){
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                alwaysShowLabel = true
            )
        }
    }
}

data class BottomNavItem (
    val title: String,
    val icon: ImageVector,
    val route: String,
)
{
    companion object {
        val Home = BottomNavItem(
            title = "Home",
            icon = Icons.Default.Home,
            route = Screens.Home.route
        )

        val News = BottomNavItem(
            title = "News",
            icon = Icons.Default.Newspaper,
            route = Screens.News.route,
        )

        val Roster = BottomNavItem(
            title = "Roster",
            icon = Icons.Default.Person,
            route = Screens.Roster.route
        )

        val Events = BottomNavItem(
            title = "Events",
            icon = Icons.Default.ShoppingCart,
            route = Screens.Events.route
        )
    }
}
