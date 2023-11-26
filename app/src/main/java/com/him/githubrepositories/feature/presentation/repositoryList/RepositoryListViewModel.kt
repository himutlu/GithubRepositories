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

    fun getRepositories() {
        viewModelScope.launch {
            repositoryUseCases.getRepositoriesUseCases().collect { repositoriesResponse ->
                if (repositoriesResponse.status == Status.SUCCESS) {
                    _repositoriesLiveData.value = repositoriesResponse.data!!
                }
            }
        }
    }
}