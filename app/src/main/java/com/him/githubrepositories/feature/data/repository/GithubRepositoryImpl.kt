package com.him.githubrepositories.feature.data.repository

import com.him.githubrepositories.core.util.Resource
import com.him.githubrepositories.feature.data.datasource.RemoteDataSource
import com.him.githubrepositories.feature.data.datasource.RepositoriesResponse
import com.him.githubrepositories.feature.data.datasource.RepositoryDetailResponse
import com.him.githubrepositories.feature.data.datasource.UserDetailResponse
import com.him.githubrepositories.feature.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GithubRepositoryImpl(private val dataSource: RemoteDataSource) : GithubRepository {

    private val errorMessage = "An error occurred while fetching %s!"
    override fun getRepositories(): Flow<Resource<List<RepositoriesResponse>>> = flow {
        emit(Resource.loading())
        val response = dataSource.getRepositories()
        if (response.isSuccessful) {
            response.body()?.let { repositoriesResponse ->
                emit(Resource.success(repositoriesResponse))
            } ?: emit(Resource.error(String.format(errorMessage,  "repositories")))
        } else {
            emit(Resource.error(String.format(errorMessage,  "repositories")))
        }
    }

    override fun getRepositoryDetail(
        userName: String,
        repositoryName: String
    ): Flow<Resource<RepositoryDetailResponse>> = flow {
        emit(Resource.loading())
        val response = dataSource.getRepositoryDetail(userName, repositoryName)
        if (response.isSuccessful) {
            response.body()?.let { repositoryDetailResponse ->
                emit(Resource.success(repositoryDetailResponse))
            } ?: emit(Resource.error(String.format(errorMessage,  "repository detail")))
        } else {
            emit(Resource.error(String.format(errorMessage,  "repository detail")))
        }
    }

    override fun getUserDetail(userName: String): Flow<Resource<UserDetailResponse>> = flow {
        emit(Resource.loading())
        val response = dataSource.getUserDetail(userName)
        if (response.isSuccessful) {
            response.body()?.let { userDetailResponse ->
                emit(Resource.success(userDetailResponse))
            } ?: emit(Resource.error(String.format(errorMessage,  "user detail")))
        } else {
            emit(Resource.error(String.format(errorMessage,  "user detail")))
        }
    }

    override fun getRepositoriesOfUser(userName: String): Flow<Resource<List<RepositoriesResponse>>> =
        flow {
            emit(Resource.loading())
            val response = dataSource.getRepositoriesOfUser(userName)
            if (response.isSuccessful) {
                response.body()?.let { repositoriesResponse ->
                    emit(Resource.success(repositoriesResponse))
                } ?: emit(Resource.error(String.format(errorMessage,  "user repositories")))
            } else {
                emit(Resource.error(String.format(errorMessage,  "user repositories")))
            }
        }


}