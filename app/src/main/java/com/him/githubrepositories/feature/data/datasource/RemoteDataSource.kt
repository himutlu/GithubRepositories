package com.him.githubrepositories.feature.data.datasource

import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: GithubApi
) {
    suspend fun getRepositories(): Response<List<RepositoriesResponse>> = api.getRepositories()
    suspend fun getRepositoryDetail(
        userName: String,
        repositoryName: String
    ): Response<RepositoryDetailResponse> = api.getRepositoryDetail(userName, repositoryName)

    suspend fun getUserDetail(userName: String): Response<UserDetailResponse> =
        api.getUserDetail(userName)

    suspend fun getRepositoriesOfUser(userName: String): Response<List<RepositoriesResponse>> =
        api.getRepositoriesOfUser(userName)
}