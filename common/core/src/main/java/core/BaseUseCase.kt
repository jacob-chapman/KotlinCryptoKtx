package core

import kotlinx.coroutines.*

//todo test viewModelScope with println
abstract class BaseUseCase<out T, in Params> where T: Any {

    private var scope = CoroutineScope(Dispatchers.IO)

    abstract suspend fun execute(params: Params): Either<Failure, T>

    operator fun invoke(params: Params, onResult: (Either<Failure, T>) -> Unit = {}) {
        //we are launching this scope with the view model context
        //viewModelScope will handle the cancellation based on its lifecycle
        scope.launch {
            val result = withContext(Dispatchers.IO) {
                execute(params)
            }

            onResult(result)
        }
    }

    //extra helper to explicitly cancel jobs in context
    fun cancelJobs() {
        scope.coroutineContext.cancelChildren()
    }


    //dumb class for no params needed for execution of use case call
    class None
}
