package net.sokum.base.list

import android.view.ViewGroup
import net.sokum.base.model.BaseViewModel
import net.sokum.modern.app.R

class UnSupportViewHolder(parent : ViewGroup) : BaseViewHolder<ListItem, BaseViewModel>(parent, R.layout.unsupport_cell) {
    override fun bind(data: ListItem, viewModel: BaseViewModel) {

    }
}