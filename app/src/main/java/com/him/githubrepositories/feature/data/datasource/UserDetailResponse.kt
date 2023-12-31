package com.him.githubrepositories.feature.data.datasource

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(
    val email: String?,
    @SerializedName("login")
    val username: String?,
    @SerializedName("avatar_url")
    val avatarLink: String?
)