package com.example.teamkuy2.ui.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.teamkuy2.ui.model.ResponseGithub

@Database(entities = [ResponseGithub.item::class], version = 1,
    exportSchema = false)
abstract class DatabaseApp : RoomDatabase(){
    abstract fun userDao(): UserDao
}