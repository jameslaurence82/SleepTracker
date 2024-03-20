package com.nscc.sleeptracker

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun SettingsScreen(user: User) {
    Column {
        // Display the user's email
        Text(text = user.email)

        // Sign Out button
        Button(onClick = { /* handle sign out */ }) {
            Text("Sign Out")
        }

        // Notifications switch
        var notificationsEnabled by remember { mutableStateOf(true) }
        Switch(
            checked = notificationsEnabled,
            onCheckedChange = { notificationsEnabled = it }
        )
        Text("Notifications")

        // Dark Mode switch
        var darkModeEnabled by remember { mutableStateOf(false) }
        Switch(
            checked = darkModeEnabled,
            onCheckedChange = { darkModeEnabled = it }
        )
        Text("Dark Mode")

        // Privacy Policy
        Button(onClick = { /* open privacy policy */ }) {
            Text("Privacy Policy")
        }
    }
}