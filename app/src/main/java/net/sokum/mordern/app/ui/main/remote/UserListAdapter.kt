package net.sokum.mordern.app.ui.main.remote

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.main_cell_user_item.view.*
import net.sokum.mordern.app.GlideApp
import net.sokum.mordern.app.R
import net.sokum.mordern.app.data.UserItem
import net.sokum.mordern.app.ui.main.UserActionViewModel

open class UserListAdapter(val context : Context, private val actionViewModel : UserActionViewModel)
    : ListAdapter<UserItem, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<UserItem>() {
    override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
        return oldItem == newItem
    }
}) {
    var likeUsersMap = mapOf<Long, UserItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.main_cell_user_item, parent, false)
        return UserItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var data = getItem(position)
        GlideApp.with(context)
            .load(data.avatarUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
            .into(holder.itemView.avatar)

        holder.itemView.userName.text = data.login

        holder.itemView.likeBtn.isChecked = isLikeUser(data)
        holder.itemView.likeBtn.setOnClickListener {
            if ( isLikeUser(data) ) {
                actionViewModel.unLikeUser(data)
            } else {
                actionViewModel.likeUser(data)
            }
        }
    }

    open fun isLikeUser(user : UserItem) : Boolean {
        return likeUsersMap.containsKey(user.id)
    }

    fun submitLikeMap(map : Map<Long, UserItem>) {
        likeUsersMap = map
        notifyDataSetChanged()
    }
}

class UserItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)