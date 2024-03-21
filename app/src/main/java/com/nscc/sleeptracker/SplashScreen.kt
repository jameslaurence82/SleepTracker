package com.nscc.sleeptracker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun SplashScreen(authViewModel: AuthenticationViewModel, navController: NavController) {
    // Observe the sign-in state from the ViewModel
    val isSignedIn by authViewModel.isSignedIn.observeAsState(initial = false)

    // If the user is signed in, navigate to the main screen
    if (isSignedIn) {
        navController.navigate("googleFit")
    } else {
        // If the user is not signed in, show the login button
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Sleep Tracker", style = MaterialTheme.typography.bodyMedium)
            Button(onClick = {
                // TODO: Implement sign-in logic here
                // After successful sign-in, navigate to the googleFitScreen
                navController.navigate("googleFit")
            }) {
                Text(text = "LOGIN")
            }
        }
    }
}
