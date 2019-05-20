package com.example.domain.params

data class RepoParams(
    val refreshFromApi: Boolean,
    val liveUpdate: Boolean = false
)