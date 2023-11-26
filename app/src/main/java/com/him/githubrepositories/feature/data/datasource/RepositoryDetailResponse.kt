package com.him.githubrepositories.feature.data.datasource

import com.google.gson.annotations.SerializedName

data class RepositoryDetailResponse(
    @SerializedName("name")
    val repositoryName: String? = null,
    val owner: Owner? = null,
    val language: String? = null,
    @SerializedName("default_branch")
    val defaultBranchName: String? = null,
    @SerializedName("forks_count")
    val forkCount: String? = null
)