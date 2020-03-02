package net.sokum.mordern.app.module

import net.sokum.mordern.app.data.UserItem

class LikeUserStorage {
    private var likeUsers = hashMapOf<Long, UserItem>()

    fun isLike(user : UserItem) : Boolean {
        return likeUsers.containsKey(user.id)
    }

    fun like(user : UserItem) {
        likeUsers[user.id] = user
    }

    fun unlike(user : UserItem) {
        likeUsers.remove(user.id)
    }

    fun getUsers() : List<UserItem> {
        return likeUsers.values.toList()
    }

    fun searchUsers(q : String) : List<UserItem> {
        return if (q.isBlank()) {
            getUsers()
        } else {
            likeUsers.values.filter { it.login.contains(q) }.toList()
        }
    }
}