package com.example.news

import retrofit2.Response
import java.io.IOException
import com.example.news.model.Result

open class BaseRepository {

    protected suspend fun<T : Any> apiCall(call: suspend () -> Response<T>, errorMessage: String) : T? {
        //get the response from the call
        val result = apiResult(call, errorMessage)

        var data: T? = null

        when(result){
            is Result.Success ->
                data = result.data
            is Result.Error ->
                data = null //todo error handling -- TIMBER LOG
        }

        return data
    }

    private suspend  fun<T: Any> apiResult(call: suspend () -> Response<T>, errorMessage: String) : Result<T> {
        val response = call.invoke()
        if(response.isSuccessful) return Result.Success(response.body()!!)

        return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }

}