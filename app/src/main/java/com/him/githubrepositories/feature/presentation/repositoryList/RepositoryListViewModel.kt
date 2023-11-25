package com.him.githubrepositories.feature.presentation.repositoryList

import androidx.lifecycle.ViewModel
import com.him.githubrepositories.feature.domain.usecases.RepositoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepositoryListViewModel @Inject constructor(
    private var repositoryUseCases: RepositoryUseCases
) : ViewModel() {

}