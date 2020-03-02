package net.sokum.mordern.app.base.model

data class UxEvent<T>(
    val type : String,
    val data : T? = null
)