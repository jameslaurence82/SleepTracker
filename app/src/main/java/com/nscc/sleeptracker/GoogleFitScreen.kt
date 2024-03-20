package com.nscc.sleeptracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@Composable
fun GoogleFitScreen(authViewModel: AuthenticationViewModel, navController: NavController) {
    val isSignedIn by authViewModel.isSignedIn.observeAsState(initial = true)
    Column {
        if (isSignedIn) {
            val imageResource = R.drawable.tempprofile
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = "User Profile Picture",
                modifier = Modifier.clickable {
                    navController.navigate("settings")
                }
            )
        } else {
            // Handle the case when the user is not signed in
        }
    }
}
