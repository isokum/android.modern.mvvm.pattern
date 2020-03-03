package net.sokum.mordern.app.ui.main.local

import android.content.Context
import net.sokum.mordern.app.data.UserItem
import net.sokum.mordern.app.ui.main.UserActionViewModel
import net.sokum.mordern.app.ui.main.remote.UserListAdapter

class LocalUserListAdapter(context : Context, actionViewModel : UserActionViewModel)
    : UserListAdapter(context, actionViewModel)
{
    override fun isLikeUser(user: UserItem): Boolean {
        return true
    }
}