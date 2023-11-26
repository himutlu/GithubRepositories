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

    fun getRepositoryDetail(username: String, repositoryName: String) {
        viewModelScope.launch {
            repositoryUseCases.getRepositoryDetailUseCases(username, repositoryName)
                .collect { response ->
                    if (response.status == Status.SUCCESS) {
                        _repositoryDetailLiveData.value = response.data!!
                    }
                }
        }
    }
}