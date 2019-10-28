package com.popo.untitledandroidproject.database

import androidx.room.*
import com.popo.untitledandroidproject.model.User

@Dao
interface UserDao {
    @Insert
    fun insert(user: User): String
    @Delete
    fun delete(user:User)
    @Update
    fun update(user:User)
    @Query("Select *from user where email= :key")
    fun get(key:String): User?
    @Query("Select * from user")
    fun getUsers(): List<User>?
}