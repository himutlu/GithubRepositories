package com.him.githubrepositories.feature.presentation.userDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.him.githubrepositories.core.util.Status
import com.him.githubrepositories.feature.data.datasource.RepositoriesResponse
import com.him.githubrepositories.feature.data.datasource.UserDetailResponse
import com.him.githubrepositories.feature.domain.usecases.RepositoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private var repositoryUseCases: RepositoryUseCases
) : ViewModel() {
    private val _userDetailLiveData = MutableLiveData<UserDetailResponse>()
    val userDetailLiveData: LiveData<UserDetailResponse>
        get() = _userDetailLiveData

    private val _userRepositoriesLiveData = MutableLiveData<List<RepositoriesResponse>>()
    val userRepositoriesLiveData: LiveData<List<RepositoriesResponse>>
        get() = _userRepositoriesLiveData

    private val _dataLoading = MutableLiveData(false)
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading
    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun getUserDetail(username: String) {
        viewModelScope.launch {
            repositoryUseCases.getUserDetailUseCases(username).collect { response ->
                when (response.status) {
                    Status.SUCCESS -> {
                        _userDetailLiveData.value = response.data!!
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

    fun getUserRepositories(username: String) {
        viewModelScope.launch {
            repositoryUseCases.getUserRepositoriesUseCases(username).collect { response ->
                when (response.status) {
                    Status.SUCCESS -> {
                        _userRepositoriesLiveData.value = response.data!!
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