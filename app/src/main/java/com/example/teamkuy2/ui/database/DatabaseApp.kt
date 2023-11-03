//package com.example.teamkuy2.ui.database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
////buat database
//@Database(entities = [User::class], version = 1)
//abstract class DatabaseApp : RoomDatabase(){
//    abstract fun UserDao(): UserDao
//
//    companion object{
//        @Volatile
//        private var INSTANCE: DatabaseApp? = null
//
//        @JvmStatic
//        fun getDatabase(context: Context): DatabaseApp {
//            if (INSTANCE == null) {
//                synchronized(DatabaseApp::class.java) {
//                    INSTANCE = Room.databaseBuilder(
//                        context.applicationContext,
//                        DatabaseApp::class.java,
//                        "note_database"
//                    ).build()
//                }
//            }
//            return INSTANCE as DatabaseApp
//        }
//    }
//}
