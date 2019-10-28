package com.popo.untitledandroidproject.database

import android.content.Context
import androidx.core.content.contentValuesOf
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.popo.untitledandroidproject.model.User

@Database(entities = [User::class], version = 1,exportSchema = false)
abstract class LocalDatabase:RoomDatabase() {
    abstract val userDao:UserDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase?=null

        fun getInstance(context: Context): LocalDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance=Room.databaseBuilder(
                        context.applicationContext,
                        LocalDatabase::class.java,
                        "my_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE=instance
                }
                return instance
            }
        }
    }
}