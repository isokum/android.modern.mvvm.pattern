package net.sokum.modern.app.ui.main.local

import android.content.Context
import net.sokum.modern.app.data.UserItem
import net.sokum.modern.app.ui.main.UserActionViewModel
import net.sokum.modern.app.ui.main.remote.UserListAdapter

class LocalUserListAdapter(context : Context, actionViewModel : UserActionViewModel)
    : UserListAdapter(context, actionViewModel)
{
    override fun isLikeUser(user: UserItem): Boolean {
        return true
    }
}