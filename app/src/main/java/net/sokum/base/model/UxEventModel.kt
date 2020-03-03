package net.sokum.base.model

data class UxEventModel<T>(
    val type : String,
    val data : T? = null
)