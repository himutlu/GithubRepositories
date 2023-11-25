package com.him.githubrepositories.feature.presentation.userDetail

import androidx.lifecycle.ViewModel
import com.him.githubrepositories.feature.domain.usecases.RepositoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private var repositoryUseCases: RepositoryUseCases
) : ViewModel() {

}