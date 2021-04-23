package com.milkyway.myapplication.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.milkyway.myapplication.model.PostResponse
import com.milkyway.myapplication.network.PostsDao

@Database(entities = arrayOf(PostResponse::class), version = 1, exportSchema = false)
abstract class RoomDatabases : RoomDatabase() {

    abstract fun postDao(): PostsDao

    companion object {

        @Volatile
        private var INSTANCE: RoomDatabases? = null

        fun getDataseClient(context: Context): RoomDatabases {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, RoomDatabases::class.java, "DATA_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }
}