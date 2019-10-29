package com.popo.untitledandroidproject.database

import androidx.room.*
import com.popo.untitledandroidproject.model.User

@Dao
interface UserDao {
    @Insert
    fun insert(user: User): Long
    @Delete
    fun delete(user:User)
    @Update
    fun update(user:User)
    @Query("Select *from user where email= :key")
    fun get(key:String): User?
    @Query("Select * from user")
    fun getUsers(): List<User>?

    @Query("select * from user where email= :email and password=:password")
    fun login(email: String,password:String): User?
}