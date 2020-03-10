package net.sokum.base.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import net.sokum.base.model.BaseViewModel
import java.lang.IllegalArgumentException
import kotlin.reflect.KClass
import kotlin.reflect.KParameter

abstract class ViewHolderListAdapter<VM : BaseViewModel> :
    ListAdapter<ListItem, BaseViewHolder<ListItem, BaseViewModel>> {
    private val mapper : ViewHolderMapper
    private var viewModel : VM

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListItem>() {
            override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
                return oldItem.isItemsSame(newItem)
            }

            override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
                return oldItem.areContentsTheSame(newItem)
            }
        }
    }

    constructor(actionViewModel: VM) : super(DIFF_CALLBACK) {
        this.viewModel = actionViewModel
        this.mapper = ViewHolderMapper()
    }

    constructor(actionViewModel: VM, mapItems : ArrayList<ViewHolderInfo>) : super(DIFF_CALLBACK) {
        this.mapper = ViewHolderMapper()
        this.viewModel = actionViewModel
    }

    fun addViewType(modelClazz : KClass<*>, viewHolderClazz : KClass<*>) {
        try {
            mapper.items.add(
                ViewHolderInfo(
                    mapper.items.size,
                    modelClazz as KClass<ListItem>,
                    viewHolderClazz as KClass<BaseViewHolder<ListItem, BaseViewModel>>
                )
            )
        } catch (e : Throwable) {
            throw IllegalArgumentException("Model 타입과 ViewHolder 타입을 확인해 주세요.")
        }
    }

    override fun getItemViewType(position: Int): Int {
        var item = getItem(position)

        var viewHolderInfo = mapper.findByItem(item::class)

        return viewHolderInfo?.type ?: -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ListItem, BaseViewModel> {
        var info = mapper.findByType(viewType)

        if ( viewType >= 0 && info != null ) {
            val constructor = info.holderClazz.constructors.firstOrNull { it.parameters.size == 1 }
            val args = constructor?.parameters?.map { it to parent }?.toMap() ?: mapOf<KParameter, Any>()

            return constructor!!.callBy(args)
        } else {
            return UnSupportViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ListItem, BaseViewModel>, position: Int) {
        holder.bind(getItem(position), viewModel)
    }
}