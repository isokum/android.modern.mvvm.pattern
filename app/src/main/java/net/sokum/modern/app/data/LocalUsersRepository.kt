package net.sokum.modern.app.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import net.sokum.modern.app.db.UserDao
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

    fun search(query : String) : LiveData<List<UserItem>> {
        return if ( query?.isBlank() ===  false ) {
            userDao.findByLogin("%$query%")
        } else {
            userDao.findAll()
        }
    }
}