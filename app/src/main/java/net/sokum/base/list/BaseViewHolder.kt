package net.sokum.base.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T : ListItem<*>> : RecyclerView.ViewHolder {
    constructor(itemView: View) : super(itemView)
    constructor(parent: ViewGroup, @LayoutRes layout: Int) : this(LayoutInflater.from(parent.context).inflate(layout, parent, false))

    abstract fun bind(data : T)
}