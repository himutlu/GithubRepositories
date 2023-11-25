package com.him.githubrepositories.feature.domain.usecases

class RepositoryUseCases(
    val getRepositoriesUseCases: GetRepositoryUseCases,
    val getRepositoryDetailUseCases: GetRepositoryDetailUseCases,
    val getUserDetailUseCases: GetUserDetailUseCases,
    val getUserRepositoriesUseCases: GetUserRepositoriesUseCases
)