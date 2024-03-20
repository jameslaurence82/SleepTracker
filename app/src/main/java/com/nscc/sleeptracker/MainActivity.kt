package com.nscc.sleeptracker

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nscc.sleeptracker.ui.theme.SleepTrackerTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.ui.res.painterResource
import com.nscc.sleeptracker.ui.theme.AuthenticationViewModel


// TODO: THIS WILL HOPEFULLY BE USED FOR THE GOOGLE ACCOUNT PROFILE
//    Fetch Google Fit sleep data here (implement your logic)
// Define your User class here or import it if it's defined elsewhere
class User {
    // User's email
    var email: String = ""
    // User's profile picture
    var profilePicture: Bitmap? = null
    // User's email account name
    var accountName: String = ""
}

class MainActivity : ComponentActivity() {
    private val authViewModel: AuthenticationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SleepTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // TODO figure out how to initialize google user object
                    // Initialize your User object here
                    val user = User()
                    Navigation(authViewModel)
                }
            }
        }
    }
}

@Composable
fun SleepTrackerTheme(content: @Composable () -> Unit) {
    // Define the color scheme for the theme
    val colorScheme = darkColorScheme().copy(
        primary = Color(0xFF1A237E), // Indigo 900
        onPrimary = Color.White,
        primaryContainer = Color(0xFF000051), // Indigo 900 variant
        secondary = Color(0xFF206EE2), // Light Blue 300
        onSecondary = Color.White
    )
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        shapes = Shapes(),
        content = content
    )
}

// This composable is kept within MainActivity.kt
@Composable
fun Navigation(authViewModel: AuthenticationViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(authViewModel, navController) }
        composable("googleFit") { GoogleFitScreen(authViewModel, navController) }
        composable("settings") { SettingsScreen(authViewModel, navController) }
    }
}




//    Display the data in your desired format
//        Image(
//            bitmap = user.profilePicture,
//            contentDescription = "User Profile Picture",
//            modifier = Modifier.clickable {
//            // Navigate to Settings screen when profile picture is clicked
//            navController.navigate("settings")
//            }
//        )
//    }
//}

