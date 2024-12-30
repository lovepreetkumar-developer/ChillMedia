package com.techwin.githubexamples.ui.main.home.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.techwin.githubexamples.data.respositories.UserRepository

@Suppress("UNCHECKED_CAST")
class HomeFragmentViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeFragmentViewModel(repository) as T
    }
}