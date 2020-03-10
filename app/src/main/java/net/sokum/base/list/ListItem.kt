package net.sokum.base.list

interface ListItem {
    fun isItemsSame(target : ListItem): Boolean {
        return this == target
    }

    fun areContentsTheSame(target : ListItem): Boolean {
        return false
    }
}