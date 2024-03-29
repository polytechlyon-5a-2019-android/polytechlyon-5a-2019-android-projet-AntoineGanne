package com.popo.untitledandroidproject.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.popo.untitledandroidproject.model.User
import kotlinx.coroutines.Deferred

@Dao
interface UserDao {
    @Insert
    fun insert(user: User): Long
    @Delete
    fun delete(user:User)
    @Update
    fun update(user:User)
    @Query("Select *from user where id= :key")
    fun get(key:Long): User?
    @Query("Select * from user")
    fun getUsers(): List<User>?

    @Query("select * from user where email= :email and password=:password")
    fun login(email: String,password:String): User?
}