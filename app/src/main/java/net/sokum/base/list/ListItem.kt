package net.sokum.base.list

interface ListItem<in T : Any> {
    fun isItemsSame(target: T): Boolean {
        return this == target
    }

    fun areContentsTheSame(target : T): Boolean {
        return false
    }
}