package com.him.githubrepositories.feature.domain.usecases

import com.him.githubrepositories.core.util.Resource
import com.him.githubrepositories.feature.data.datasource.RepositoriesResponse
import com.him.githubrepositories.feature.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow

class GetRepositoryUseCases(private val githubRepository: GithubRepository) {
    operator fun invoke(): Flow<Resource<List<RepositoriesResponse>>> {
        return githubRepository.getRepositories()
    }
}
