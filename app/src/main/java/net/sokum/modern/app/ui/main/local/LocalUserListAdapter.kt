package net.sokum.modern.app.ui.main.local

import android.view.ViewGroup
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.main_cell_user_item.view.*
import net.sokum.base.list.ViewHolderListAdapter
import net.sokum.base.list.BaseViewHolder
import net.sokum.modern.app.GlideApp
import net.sokum.modern.app.R
import net.sokum.modern.app.data.UserItem
import net.sokum.modern.app.ui.main.UserActionViewModel

class LocalUserListAdapter(viewModel : UserActionViewModel) : ViewHolderListAdapter<UserActionViewModel>(viewModel) {
    init {
        addViewType(UserItem::class, LocalUserItemViewHolder::class)
    }
}

class LocalUserItemViewHolder(parent: ViewGroup) : BaseViewHolder<UserItem, UserActionViewModel>(parent, R.layout.main_cell_user_item) {
    override fun bind(data: UserItem, viewModel: UserActionViewModel) {
        GlideApp.with(itemView.context)
            .load(data.avatarUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
            .into(itemView.avatar)

        itemView.userName.text = data.login

        itemView.likeBtn.isChecked = true
        itemView.likeBtn.setOnClickListener {
            viewModel.unLikeUser(data)
        }
    }
}