package net.sokum.mordern.app.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import net.sokum.mordern.app.db.UserDao
import javax.inject.Inject

class LocalUsersRepository @Inject constructor(
    private val userDao : UserDao
) {
    val likeUsers : LiveData<List<UserItem>> = userDao.findAll()
    var likeUserMap : LiveData<Map<Long, UserItem>> = Transformations.map(likeUsers) { it.map { user -> user.id to user}.toMap() }

    suspend fun insert(user : UserItem) {
        userDao.insert(user)
    }

    suspend fun delete(user : UserItem) {
        userDao.delete(user)
    }

    fun isExists(id : Long) : Boolean {
        return likeUserMap.value?.containsKey(id) ?: false
    }

    fun search(query : String) : LiveData<List<UserItem>> {
        return userDao.findByLogin(query)
    }
}