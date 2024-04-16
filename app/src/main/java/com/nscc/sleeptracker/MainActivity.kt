import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nscc.sleeptracker.R
import com.nscc.sleeptracker.ui.theme.SleepTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SleepTrackerTheme {
                // Set the content of the activity
                MainActivityContent()
            }
        }
    }

    @Composable
    fun MainActivityContent() {
        // Load the background image
        val backgroundImage: Painter = painterResource(id = R.drawable.banana)

        // Display the background image and other content
        MaterialTheme(
            colorScheme = darkColorScheme(),
            content = {
                Image(
                    painter = backgroundImage,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
                // Other composables can be added here or below the Image
                // Example: Navigation setup
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash") {
                    composable("splash") { SplashScreen(navController) }
                    composable("googleFit") { GoogleFitScreen(navController) }
                    composable("settings") { SettingsScreen(navController) }
                }
            }
        )
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    // You can add your splash screen content here
}

@Composable
fun GoogleFitScreen(navController: NavController) {
    // You can add your Google Fit screen content here
}

@Composable
fun SettingsScreen(navController: NavController) {
    // You can add your settings screen content here
}
