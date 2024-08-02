package com.piyushprajpti.pyquote

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room

@Composable
fun Navigation(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val db = Room.databaseBuilder(context, QuoteDatabase::class.java, "quotes").build()

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main-feed") {

        composable("main-feed") {
            MainFeed(
                db = db,
                onSavedQuotesClick = { navController.navigate("saved-quotes-screen") }
            )
        }

        composable("saved-quotes-screen") {
            SavedQuotesScreen(
                db = db,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}