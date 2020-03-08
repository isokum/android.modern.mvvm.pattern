package net.sokum.base.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.primaryConstructor

abstract class BaseListAdapter : ListAdapter<ListItem<*>, BaseViewHolder<ListItem<*>>>(DIFF_CALLBACK) {
    val mapper : ViewHolderMapper = ViewHolderMapper()

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListItem<*>>() {
            override fun areItemsTheSame(oldItem: ListItem<*>, newItem: ListItem<*>): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ListItem<*>, newItem: ListItem<*>): Boolean {
                return false
            }
        }
    }

    fun addViewType(modelClazz : KClass<ListItem<*>> , viewHolderClazz : KClass<BaseViewHolder<ListItem<*>>>) {
        mapper.items.add(
            ViewHolderInfo(
                mapper.items.size,
                modelClazz,
                viewHolderClazz
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        var item = getItem(position)

        var viewHolderInfo = mapper.findByItem(item::class)

        return viewHolderInfo?.type ?: -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ListItem<*>> {
        var info = mapper.findByType(viewType)

        if (viewType >= 0 && info != null) {
            val constructor = info.holderClazz.primaryConstructor
            val args = constructor?.parameters?.map {
                it to parent.context
            }?.toMap() ?: mapOf<KParameter, Any>()

            return constructor!!.callBy(args)
        } else {
            return UnSupportViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ListItem<*>>, position: Int) {
        holder.bind(getItem(position))
    }
}