package com.capstone.h_buddy.ui.profil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.h_buddy.data.preference.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
): ViewModel() {

    //data store (dark mode)
    val darkModeFlow: Flow<Boolean> = dataStoreManager.getDarkModeFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, false)
    fun startDarkMode(isDarkMode: Boolean) {
        viewModelScope.launch {
            dataStoreManager.startDarkMode(isDarkMode)
        }

    }
}