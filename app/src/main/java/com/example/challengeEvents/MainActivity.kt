package com.example.challengeEvents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.challengeEvents.data.api.RetrofitClient
import com.example.challengeEvents.data.api.EventApi
import com.example.challengeEvents.repository.EventRepository
import com.example.challengeEvents.ui.event.EventDetailScreen
import com.example.challengeEvents.ui.event.EventListScreen
import com.example.challengeEvents.ui.theme.ChallengeEventsTheme
import com.example.challengeEvents.viewmodel.EventViewModel
import com.example.challengeEvents.viewmodel.EventViewModelFactory

class MainActivity : ComponentActivity() {

    private val eventViewModel: EventViewModel by lazy {
        EventViewModelFactory(
            EventRepository(
                RetrofitClient.retrofit.create(EventApi::class.java)
            )
        ).create(EventViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ChallengeEventsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "eventList") {
                        composable("eventList") {
                            EventListScreen(eventViewModel = eventViewModel, navController = navController)
                        }
                        composable("eventDetail/{eventId}") { backStackEntry ->
                            val eventId = backStackEntry.arguments?.getString("eventId")
                            val event = eventViewModel.events.value.find { it.id == eventId }
                            event?.let { EventDetailScreen(event = it, navController = navController) }
                        }
                    }
                }
            }
        }
    }
}
