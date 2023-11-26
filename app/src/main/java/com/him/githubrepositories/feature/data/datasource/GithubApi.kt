package com.him.githubrepositories.feature.data.datasource

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("repositories")
    suspend fun getRepositories(): Response<List<RepositoriesResponse>>

    @GET("/repos/{user_name}/{repository_name}")
    suspend fun getRepositoryDetail(
        @Path("user_name") userName: String,
        @Path("repository_name") repositoryName: String
    ): Response<RepositoryDetailResponse>

    @GET("users/{user_name}")
    suspend fun getUserDetail(
        @Path("user_name") userName: String
    ): Response<UserDetailResponse>

    @GET("users/{user_name}/repos")
    suspend fun getRepositoriesOfUser(
        @Path("user_name") userName: String
    ): Response<List<RepositoriesResponse>>
}