package com.him.githubrepositories.feature.data.datasource

data class RepositoryDto(
    val repoName: String? = null,
    val ownerUserName: String? = null,
    val avatarLink: String?,
    val forkCount: String? = null,
    val language: String? = null,
    val defaultBranchName: String? = null
)