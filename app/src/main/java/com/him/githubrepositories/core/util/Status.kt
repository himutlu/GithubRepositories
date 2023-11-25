package com.him.githubrepositories.core.util

sealed class Status {
    object SUCCESS : Status()
    object ERROR : Status()
    object LOADING : Status()
}