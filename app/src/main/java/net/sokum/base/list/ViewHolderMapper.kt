package net.sokum.base.list

import android.view.ViewGroup

class ViewHolderMapper {
    private val items : ArrayList<ViewHolderInfo> = arrayListOf()

    fun findByItem(item : ListItem) : ViewHolderInfo {
        var info = items.firstOrNull { it.condition(item) }
        return info ?: ViewHolderInfo(items.size, { true }, { UnSupportViewHolder(it) })
    }

    fun findByViewType(type : Int) : ViewHolderInfo{
        var info = items.firstOrNull { it.type == type }
        return info ?: ViewHolderInfo(items.size, { true }, { UnSupportViewHolder(it) })
    }

    fun map(itemType: (ListItem) -> Boolean = { true }, viewHolder : (ViewGroup) -> BaseViewHolder<*, *>) {
        items.add(ViewHolderInfo(items.size, itemType, viewHolder))
    }
}

data class ViewHolderInfo(
    val type : Int = 0,
    val condition: (ListItem) -> Boolean,
    val factory : (ViewGroup) -> BaseViewHolder<*, *>
)

inline fun viewHolderMapper(buildFactory: ViewHolderMapper.() -> Unit): ViewHolderMapper {
    return ViewHolderMapper().apply(buildFactory)
}