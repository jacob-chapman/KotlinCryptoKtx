package core

import kotlinx.coroutines.CoroutineScope

data class RepoParams(
    val refreshFromApi: Boolean,
    val scope: CoroutineScope? = null,
    val liveUpdate: Boolean = false
)