package com.him.githubrepositories.feature.domain.usecases

import com.him.githubrepositories.core.util.Resource
import com.him.githubrepositories.feature.data.datasource.RepositoryDetailResponse
import com.him.githubrepositories.feature.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow

class GetRepositoryDetailUseCases(private val githubRepository: GithubRepository) {
    operator fun invoke(
        username: String,
        repositoryName: String
    ): Flow<Resource<RepositoryDetailResponse>> {
        return githubRepository.getRepositoryDetail(username, repositoryName)
    }
}