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

    fun getUserDetail(userName: String) {
        viewModelScope.launch {
            repositoryUseCases.getUserDetailUseCases(userName).collect { response ->
                if (response.status == Status.SUCCESS) {
                    getUserRepositories(userName)
                    _userDetailLiveData.value = response.data!!
                }
            }
        }
    }

    private fun getUserRepositories(userName: String) {
        viewModelScope.launch {
            repositoryUseCases.getUserRepositoriesUseCases(userName).collect { response ->
                if (response.status == Status.SUCCESS) {
                    _userRepositoriesLiveData.value = response.data!!
                }
            }
        }
    }
}