package com.him.githubrepositories.feature.data.datasource

import com.google.gson.annotations.SerializedName

data class RepositoriesResponse(
    var name: String? = null,
    var owner: Owner? = Owner()
)

data class Owner(
    var login: String? = null,
    @SerializedName("avatar_url") var
    avatarUrl: String? = null
)