package net.sokum.mordern.app.base.network

import com.google.gson.Gson
import okhttp3.Headers
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketException

enum class ErrorCodes(val code:Int) {
    SocketItemOut(-1)
}

data class ErrorBody(
    val message : String
)

object ResponseHandler {
    private var gson = Gson()

    fun <T: Any> handleSuccess(data : T, headers : Headers) : Resource<T> {
        return Resource.Success(data, headers.toMultimap())
    }

    fun <T:Any> handleFailure(code : Int, errorBody : ResponseBody?) : Resource<T> {
        if ( errorBody != null ) {
            try {
                val errorBody = gson.fromJson(errorBody.string(), ErrorBody::class.java)
                return Resource.Error(code, errorBody.message)
            } catch (e: Exception) {
            }
        }
        return Resource.Error(code, getErrorMessage(Int.MAX_VALUE))
    }

    fun <T: Any> handleExecption(e : Exception) : Resource<T> {
        return when(e) {
            is HttpException -> Resource.Error(e.code(), getErrorMessage(e.code()))
            is SocketException -> Resource.Error(ErrorCodes.SocketItemOut.code, getErrorMessage(ErrorCodes.SocketItemOut.code))
            else -> Resource.Error(ErrorCodes.SocketItemOut.code, getErrorMessage(Int.MAX_VALUE))
        }
    }

    private fun getErrorMessage(code : Int) : String {
        return when(code) {
            ErrorCodes.SocketItemOut.code -> "Timeout"
            401 -> "Unauthorised"
            404 -> "Not Found"
            else -> "Something went wrong"
        }
    }
}