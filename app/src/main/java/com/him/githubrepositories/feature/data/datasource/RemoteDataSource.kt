package com.him.githubrepositories.feature.data.datasource

import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: GithubApi
) {
    suspend fun getRepositories(): Response<List<RepositoriesResponse>> = api.getRepositories()
    suspend fun getRepositoryDetail(
        username: String,
        repositoryName: String
    ): Response<RepositoryDetailResponse> = api.getRepositoryDetail(username, repositoryName)

    suspend fun getUserDetail(username: String): Response<UserDetailResponse> =
        api.getUserDetail(username)

    suspend fun getRepositoriesOfUser(username: String): Response<List<RepositoriesResponse>> =
        api.getRepositoriesOfUser(username)
}