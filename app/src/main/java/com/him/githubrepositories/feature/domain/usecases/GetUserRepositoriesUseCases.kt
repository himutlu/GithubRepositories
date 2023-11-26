package com.him.githubrepositories.feature.domain.usecases

import com.him.githubrepositories.core.util.Resource
import com.him.githubrepositories.feature.data.datasource.RepositoriesResponse
import com.him.githubrepositories.feature.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow

class GetUserRepositoriesUseCases(private val githubRepository: GithubRepository) {
    operator fun invoke(username: String): Flow<Resource<List<RepositoriesResponse>>> {
        return githubRepository.getRepositoriesOfUser(username)
    }
}