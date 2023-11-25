package com.him.githubrepositories.feature.presentation.repositoryDetail

import androidx.lifecycle.ViewModel
import com.him.githubrepositories.feature.domain.usecases.RepositoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepositoryDetailViewModel @Inject constructor(
    private var repositoryUseCases: RepositoryUseCases
) : ViewModel() {

}