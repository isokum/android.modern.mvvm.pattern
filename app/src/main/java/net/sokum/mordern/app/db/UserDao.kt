package net.sokum.mordern.app.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import net.sokum.mordern.app.data.UserItem

@Dao
interface UserDao {
    @Query("SELECT * from users")
    fun getAll() : List<UserItem>

    @Insert
    suspend fun insert(user : UserItem)

    @Delete
    suspend fun delete(user : UserItem)

    @Query("SELECT * from users WHERE id = :id")
    fun findById(id : Long) : UserItem
}