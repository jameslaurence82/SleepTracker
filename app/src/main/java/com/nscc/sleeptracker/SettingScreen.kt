package com.nscc.sleeptracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable  // Annotation indicating a composable function
fun SettingsScreen(authViewModel: AuthenticationViewModel, navController: NavController) {
    val isSignedIn by authViewModel.isSignedIn.observeAsState(initial = true)

    if (!isSignedIn) {
        // This will only work if you have a NavHost with a composable that has the route "splash_screen"
        navController.navigate("splash")
    }
    // Column composable for vertical arrangement of elements
    Column(
        modifier = Modifier.padding(40.dp),  // Add padding around the column
        verticalArrangement = Arrangement.spacedBy(5.dp)  // Space between elements in the column
    ) {
        Spacer(modifier = Modifier.height(25.dp))  // Spacer for additional vertical space
        // Load and display user's profile picture
        val imageResource = R.drawable.tempprofile  // Image resource ID
        Image(
            painter = painterResource(id = imageResource),  // Load image from resource
            contentDescription = "User Profile Picture",  // Description for accessibility
            modifier = Modifier  // Modifier for styling and layout
                .size(150.dp)  // Set size of image
                .clip(CircleShape)  // Clip image into circle shape
                .border(2.dp, Color.Gray, CircleShape)  // Add border around clipped image
                .align(Alignment.CenterHorizontally),  // Center align horizontally within parent column
//                .padding(top = 50.dp), // Add padding at top for spacing of image and border
            contentScale = ContentScale.Crop  // Crop the image to fill the size of the Image composable
        )

        Spacer(modifier = Modifier.height(25.dp))  // Spacer for additional vertical space

        // Display user's account name centered with specified font size
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {  // Box for alignment
            Text(text = "John Doe", textAlign = TextAlign.Center, fontSize = 20.sp)  // Text composable for displaying name
            // TODO: Display the user's account name when user has been set up
            //Text(text = user.accountName, textAlign = TextAlign.Center, fontSize = 20.sp)
        }

        // Display user's email centered with specified font size
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {  // Box for alignment
            Text(text = "nobody@fakeemail.com", textAlign = TextAlign.Center, fontSize = 20.sp)  // Text composable for displaying email
            // TODO: Display the user's email when user has been set up
            //Text(text = user.email, textAlign = TextAlign.Center, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(50.dp))  // Spacer for additional vertical space

        // Sign out button triggering associated action on click
        Button(onClick={ /* handle sign out */ }) {
            Text("Sign Out", fontSize=18.sp)  // Label of button with specified font size
        }

        Spacer(modifier=Modifier.height(10.dp))  // Spacer for additional vertical space

        // Mutable state holding notification setting status
        var notificationsEnabled by remember { mutableStateOf(false)}
        // Setting item for notifications with a switch
        SettingItem(name="Notifications", checked=notificationsEnabled,
            onCheckedChange={notificationsEnabled=it})  // Update state when switch is toggled

        Spacer(modifier=Modifier.height(10.dp))  // Spacer for additional vertical space

        // Mutable state holding dark mode setting status
        var darkModeEnabled by remember { mutableStateOf(false) }
        // Setting item for dark mode with a switch
        SettingItem(name="Dark Mode", checked=darkModeEnabled,
            onCheckedChange={darkModeEnabled=it})  // Update state when switch is toggled

        Spacer(modifier=Modifier.height(10.dp))  // Spacer for additional vertical space

        // Privacy Policy button triggering associated action on click
        Button(onClick = { /* open privacy policy */ }) {
            Text("Privacy Policy", fontSize = 18.sp)  // Label of button with specified font size
        }
    }
}

@Composable
fun SettingItem(name: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name, fontSize = 20.sp)
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}
