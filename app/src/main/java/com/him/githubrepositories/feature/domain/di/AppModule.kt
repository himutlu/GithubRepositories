package com.him.githubrepositories.feature.domain.di

import com.him.githubrepositories.feature.data.datasource.GithubApi
import com.him.githubrepositories.feature.data.datasource.RemoteDataSource
import com.him.githubrepositories.feature.data.repository.GithubRepositoryImpl
import com.him.githubrepositories.feature.domain.repository.GithubRepository
import com.him.githubrepositories.feature.domain.usecases.GetRepositoryDetailUseCases
import com.him.githubrepositories.feature.domain.usecases.GetRepositoryUseCases
import com.him.githubrepositories.feature.domain.usecases.GetUserDetailUseCases
import com.him.githubrepositories.feature.domain.usecases.GetUserRepositoriesUseCases
import com.him.githubrepositories.feature.domain.usecases.RepositoryUseCases
import com.him.githubrepositories.feature.domain.util.Constants
import com.him.githubrepositories.feature.domain.util.Constants.BASE_URL
import com.him.githubrepositories.feature.domain.util.DefaultRequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGithubRepository(
        remoteDataSource: RemoteDataSource,
    ): GithubRepository {
        return GithubRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        requestInterceptor: DefaultRequestInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        with(OkHttpClient.Builder()) {
            addInterceptor(requestInterceptor)
//            if (BuildConfig.DEBUG)
            addInterceptor(loggingInterceptor)
            connectTimeout(Constants.TIMEOUT_MILIS, TimeUnit.MILLISECONDS)
            build()
        }

    @Provides
    @Singleton
    fun provideGithubApi(
        client: OkHttpClient
    ): GithubApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(GithubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepositoriesUseCases(githubRepository: GithubRepository): RepositoryUseCases =
        RepositoryUseCases(
            GetRepositoryUseCases(githubRepository),
            GetRepositoryDetailUseCases(githubRepository),
            GetUserDetailUseCases(githubRepository),
            GetUserRepositoriesUseCases(githubRepository)
        )
}