package com.him.githubrepositories.feature.data.datasource

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("repositories")
    suspend fun getRepositories(): Response<List<RepositoriesResponse>>

    @GET("/repos/{username}/{repository_name}")
    suspend fun getRepositoryDetail(
        @Path("username") username: String,
        @Path("repository_name") repositoryName: String
    ): Response<RepositoryDetailResponse>

    @GET("users/{username}")
    suspend fun getUserDetail(
        @Path("username") username: String
    ): Response<UserDetailResponse>

    @GET("users/{username}/repos")
    suspend fun getRepositoriesOfUser(
        @Path("username") username: String
    ): Response<List<RepositoriesResponse>>
}