package com.capstone.h_buddy.ui.beranda

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.h_buddy.data.api.article.ArticlesItem
import com.capstone.h_buddy.data.api.article.ArticlesResponse
import com.capstone.h_buddy.data.preference.DataStoreManager
import com.capstone.h_buddy.data.repository.ArticlesRepository
import com.capstone.h_buddy.data.repository.MainRepository
import com.capstone.h_buddy.utils.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MainRepository,
    private val dataStoreManager: DataStoreManager,
    private val articlesRepository: ArticlesRepository
) : ViewModel() {
    //article
    private val _articleData = MutableLiveData<MyResponse<ArticlesResponse>>()
    val articleData: LiveData<MyResponse<ArticlesResponse>> get() = _articleData
    private var isDataLoaded = false
    fun getAllArticle() {
        if (isDataLoaded) return
        viewModelScope.launch {
            try {
                repository.getArticles().collect {
                    _articleData.value = it
                    isDataLoaded = true
                }
            } catch (e: Exception) {
                _articleData.value = MyResponse.error(e.message ?: "Error loading data")
            }
        }
    }

    //data store (dark mode)
    val darkModeFlow: Flow<Boolean> = dataStoreManager.getDarkModeFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, false)
    fun startDarkMode(isDarkMode: Boolean) {
        viewModelScope.launch {
            dataStoreManager.startDarkMode(isDarkMode)
        }

    }

    //favorite button

    val allArticles: LiveData<List<ArticlesItem>> = articlesRepository.allArticles

    fun updateFavoriteStatus(articleId: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            articlesRepository.updateFavoriteStatus(articleId, isFavorite)
        }
    }

}
