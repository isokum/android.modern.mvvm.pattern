package net.sokum.modern.app.ui.main.remote

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
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