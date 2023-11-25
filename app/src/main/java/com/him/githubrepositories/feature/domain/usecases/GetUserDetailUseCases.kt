package com.him.githubrepositories.feature.domain.usecases

import com.him.githubrepositories.core.util.Resource
import com.him.githubrepositories.feature.data.datasource.UserDetailResponse
import com.him.githubrepositories.feature.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow

class GetUserDetailUseCases(private val githubRepository: GithubRepository) {
    operator fun invoke(userName: String): Flow<Resource<UserDetailResponse>> {
        return githubRepository.getUserDetail(userName)
    }
}