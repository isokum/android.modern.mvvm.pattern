package net.sokum.base.list

import kotlin.reflect.KClass

class ViewHolderMapper {
    val items : ArrayList<ViewHolderInfo> = arrayListOf()

    fun findByItem(item : KClass<out ListItem<*>>) : ViewHolderInfo? {
        return items.firstOrNull { it.itemClazz == item }
    }

    fun findByHolder(item : KClass<out BaseViewHolder<*>>) : ViewHolderInfo? {
        return items.firstOrNull { it.itemClazz == item }
    }

    fun findByType(type : Int) : ViewHolderInfo?{
        return items.firstOrNull { it.type == type }
    }
}

data class ViewHolderInfo(
    var type : Int = 0,
    var itemClazz : KClass<ListItem<*>>,
    var holderClazz : KClass<BaseViewHolder<ListItem<*>>>
)