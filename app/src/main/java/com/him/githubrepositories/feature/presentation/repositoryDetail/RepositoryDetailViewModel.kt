package com.him.githubrepositories.feature.presentation.repositoryDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.him.githubrepositories.core.util.Status
import com.him.githubrepositories.feature.data.datasource.RepositoryDetailResponse
import com.him.githubrepositories.feature.domain.usecases.RepositoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryDetailViewModel @Inject constructor(
    private var repositoryUseCases: RepositoryUseCases
) : ViewModel() {
    private val _repositoryDetailLiveData = MutableLiveData<RepositoryDetailResponse>()
    val repositoryDetailLiveData: LiveData<RepositoryDetailResponse>
        get() = _repositoryDetailLiveData

    private val _dataLoading = MutableLiveData(false)
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun getRepositoryDetail(username: String, repositoryName: String) {
        viewModelScope.launch {
            repositoryUseCases.getRepositoryDetailUseCases(username, repositoryName)
                .collect { response ->
                    when (response.status) {
                        Status.SUCCESS -> {
                            _repositoryDetailLiveData.value = response.data!!
                            _dataLoading.value = false
                        }
                        Status.ERROR -> {
                            _error.value = response.message
                        }
                        Status.LOADING -> {
                            _dataLoading.value = true
                        }
                    }
                }
        }
    }
}