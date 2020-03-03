package net.sokum.base.network

sealed class Resource<out T : Any> {
    data class Success<out T: Any>(val data: T, val headers : Map<String, List<String>>) : Resource<T>()
    data class Error(val errorCode: Int, val errorMessage : String) : Resource<Nothing>()
    data class Loading(val message: String) : Resource<Nothing>()
}