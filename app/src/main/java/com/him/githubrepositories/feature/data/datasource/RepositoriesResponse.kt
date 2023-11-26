package com.him.githubrepositories.feature.data.datasource

data class RepositoriesResponse(
    var name: String? = null,
    var owner: Owner? = Owner()
)
