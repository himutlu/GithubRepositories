package com.him.githubrepositories.feature.data.datasource

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("login")
    var username: String? = null,
    @SerializedName("avatar_url")
    var avatarUrl: String? = null
)