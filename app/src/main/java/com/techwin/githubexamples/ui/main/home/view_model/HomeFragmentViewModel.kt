package com.techwin.githubexamples.ui.main.home.view_model

import androidx.lifecycle.ViewModel
import com.techwin.githubexamples.data.respositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeFragmentViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    suspend fun getGallery() = withContext(Dispatchers.IO) {
        userRepository.getGallery()
    }

    suspend fun searchGallery(query: String) = withContext(Dispatchers.IO) {
        userRepository.searchGallery(query)
    }
}