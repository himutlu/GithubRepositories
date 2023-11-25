package com.him.githubrepositories.feature.domain.repository

import com.him.githubrepositories.core.util.Resource
import com.him.githubrepositories.feature.data.datasource.RepositoriesResponse
import com.him.githubrepositories.feature.data.datasource.RepositoryDetailResponse
import com.him.githubrepositories.feature.data.datasource.UserDetailResponse
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    fun getRepositories(): Flow<Resource<List<RepositoriesResponse>>>
    fun getRepositoryDetail(
        userName: String,
        repositoryName: String
    ): Flow<Resource<RepositoryDetailResponse>>

    fun getUserDetail(userName: String): Flow<Resource<UserDetailResponse>>
    fun getRepositoriesOfUser(userName: String): Flow<Resource<List<RepositoriesResponse>>>
}