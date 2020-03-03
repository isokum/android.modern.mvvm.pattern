package net.sokum.mordern.app.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import net.sokum.mordern.app.data.UserItem

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user : UserItem)

    @Delete
    suspend fun delete(user : UserItem)

    @Query("SELECT * from users")
    fun findAll() : LiveData<List<UserItem>>

    @Query("SELECT * from users WHERE id = :id")
    suspend fun findById(id : Long) : UserItem?

    @Query("SELECT * from users WHERE login like :login")
    fun findByLogin(login : String) : LiveData<List<UserItem>>
}