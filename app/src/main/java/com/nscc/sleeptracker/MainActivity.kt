package com.nscc.sleeptracker

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.nscc.sleeptracker.ui.theme.SleepTrackerTheme


// TODO: March 27 2024
// where does this code go to when we want to access the GoogleFit API data
// CODE WAS PROVIDED IN JAVA AND ANDROID CONVERTED TO KOTLIN.... check it!
var gso: GoogleSignInOptions? = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
    .requestScopes(Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE), Scope(Scopes.FITNESS_SLEEP_READ))
    .requestEmail()
    .build()

// TODO: THIS WILL BE USED FOR THE GOOGLE ACCOUNT LOGIN
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
                    // TODO figure out how to initialize google user authorization
                    // Initialize your User object here
                    val user = User()
                    Navigation(authViewModel)
                    class MainActivity : AppCompatActivity() {

                        private lateinit var googleSignInClient: GoogleSignInClient

                        override fun onCreate(savedInstanceState: Bundle?) {
                            super.onCreate(savedInstanceState)
                            setContentView(R.layout.activity_main)

                            // Configure sign-in to request user's ID, email address, and basic profile.
                            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestEmail()
                                .build()

                            googleSignInClient = GoogleSignIn.getClient(this, gso)

                            val signInButton: Button = findViewById(R.id.sign_in_button)
                            signInButton.setOnClickListener {
                                val signInIntent = googleSignInClient.signInIntent
                                startActivityForResult(signInIntent, RC_SIGN_IN)
                            }
                        }

                        public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
                            super.onActivityResult(requestCode, resultCode, data)

                            // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
                            if (requestCode == RC_SIGN_IN) {
                                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                                handleSignInResult(task)
                            }
                        }

                        private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
                            try {
                                val account = completedTask.getResult(ApiException::class.java)

                                // Signed in successfully, show authenticated UI.
                                updateUI(account)
                            } catch (e: ApiException) {
                                Log.w(TAG, "signInResult:failed code=" + e.statusCode)
                                updateUI(null)
                            }
                        }

                        private fun updateUI(account: GoogleSignInAccount?) {
                            // Update your UI here based on user's account
                        }

                        companion object {
                            private const val RC_SIGN_IN = 9001
                            private const val TAG = "GoogleSignInExample"
                        }
                    }

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

