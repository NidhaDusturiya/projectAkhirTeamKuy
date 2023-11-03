package com.example.teamkuy2.ui.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE username_app = :username AND password = :password")
    fun checkUserPass(username: String, password: String): List<User>

    @Insert
    fun insert(vararg user: User): List<Long>

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user WHERE username_app = :username")
    suspend fun getUserByUsername(username: String): User?
}