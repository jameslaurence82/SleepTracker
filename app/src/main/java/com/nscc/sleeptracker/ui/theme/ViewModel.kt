package com.nscc.sleeptracker.ui.theme

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthenticationViewModel : ViewModel() {

    // LiveData for sign-in state
    val isSignedIn = MutableLiveData<Boolean>()

    // LiveData for dark mode state
    val isDarkMode = MutableLiveData<Boolean>()

    // LiveData for notifications state
    val isNotificationsEnabled = MutableLiveData<Boolean>()

    fun signOut() {
        isSignedIn.value = false
    }

    fun signIn() {
        isSignedIn.value = true
    }

    fun toggleDarkMode() {
        isDarkMode.value = !(isDarkMode.value ?: false)
    }

    fun toggleNotifications() {
        isNotificationsEnabled.value = !(isNotificationsEnabled.value ?: false)
    }
}
