package net.sokum.base.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import net.sokum.base.model.BaseViewModel

abstract class BaseViewHolder<T : ListItem, VM : BaseViewModel> : RecyclerView.ViewHolder {
    constructor(itemView: View) : super(itemView)
    constructor(parent: ViewGroup, @LayoutRes layout: Int) : this(LayoutInflater.from(parent.context).inflate(layout, parent, false))

    abstract fun bind(data: T, viewModel: VM)
}