package com.him.githubrepositories.feature.presentation.repositoryList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.him.githubrepositories.core.util.Status
import com.him.githubrepositories.feature.data.datasource.RepositoriesResponse
import com.him.githubrepositories.feature.domain.usecases.RepositoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryListViewModel @Inject constructor(
    private var repositoryUseCases: RepositoryUseCases
) : ViewModel() {
    private val _repositoriesLiveData = MutableLiveData<List<RepositoriesResponse>>()
    val repositoriesLiveData: LiveData<List<RepositoriesResponse>>
        get() = _repositoriesLiveData

    private val _dataLoading = MutableLiveData(false)
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun getRepositories() {
        viewModelScope.launch {
            repositoryUseCases.getRepositoriesUseCases().collect { repositoriesResponse ->
                when (repositoriesResponse.status) {
                    Status.SUCCESS -> {
                        _repositoriesLiveData.value = repositoriesResponse.data!!
                        _dataLoading.value = false
                    }
                    Status.ERROR -> {
                        _error.value = repositoriesResponse.message
                    }
                    Status.LOADING -> {
                        _dataLoading.value = true
                    }
                }
            }
        }
    }
}