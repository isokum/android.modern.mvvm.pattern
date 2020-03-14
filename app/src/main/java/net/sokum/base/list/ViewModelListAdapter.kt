package net.sokum.base.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import net.sokum.base.model.BaseViewModel

open class ViewModelListAdapter<VM : BaseViewModel> :
    ListAdapter<ListItem, BaseViewHolder<ListItem, BaseViewModel>> {

    private var viewModel : VM
    private val mapper : ViewHolderMapper

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

    constructor(actionViewModel: VM, mapper : ViewHolderMapper) : super(DIFF_CALLBACK) {
        this.mapper = mapper
        this.viewModel = actionViewModel
    }

    override fun getItemViewType(position: Int): Int {
        return mapper.findByItem(getItem(position)).type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ListItem, BaseViewModel> {
        return mapper.findByViewType(viewType).factory(parent) as BaseViewHolder<ListItem, BaseViewModel>
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ListItem, BaseViewModel>, position: Int) {
        holder.bind(getItem(position), viewModel)
    }
}