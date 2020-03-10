package net.sokum.modern.app.ui.main.remote

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.main_cell_user_item.view.*
import net.sokum.modern.app.GlideApp
import net.sokum.modern.app.R
import net.sokum.modern.app.data.UserItem
import net.sokum.modern.app.ui.main.UserActionViewModel

open class UserPageListAdapter(val context : Context, private val actionViewModel : UserActionViewModel)
    : PagedListAdapter<UserItem, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    var likeUsersMap = mapOf<Long, UserItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.main_cell_user_item, parent, false)
        return UserItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var data = getItem(position)

        if ( data != null ) {
            when (holder) {
                is UserItemViewHolder -> holder.bind(data, isLikeUser(data), actionViewModel)
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



    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserItem>() {
            override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class UserItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(data : UserItem, isLike : Boolean, actionViewModel : UserActionViewModel) {
        GlideApp.with(itemView.context)
            .load(data.avatarUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
            .into(itemView.avatar)

        itemView.userName.text = data?.login

        itemView.likeBtn.isChecked = isLike
        itemView.likeBtn.setOnClickListener {
            if ( isLike ) {
                actionViewModel.unLikeUser(data)
            } else {
                actionViewModel.likeUser(data)
            }
        }
    }
}