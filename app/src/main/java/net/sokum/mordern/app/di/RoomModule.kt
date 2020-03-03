package net.sokum.mordern.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import net.sokum.mordern.app.db.UserDao
import net.sokum.mordern.app.db.UserDatabase
import javax.inject.Singleton

@Module
class RoomModule {
    @Singleton
    @Provides
    fun provideRoomDatabase(context : Context) : UserDatabase {
        return UserDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideUserDao(database : UserDatabase) : UserDao {
        return database.userDao()
    }

}