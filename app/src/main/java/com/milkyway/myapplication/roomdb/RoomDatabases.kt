package com.milkyway.myapplication.roomdb

import android.content.Context
import androidx.room.*
import com.milkyway.myapplication.model.Data
import com.milkyway.myapplication.network.PostsDao

@Database(entities = arrayOf(Data::class), version = 1, exportSchema = false)
abstract class RoomDatabases : RoomDatabase() {

    abstract fun postsDao(): PostsDao

    companion object {

        @Volatile
        private var INSTANCE: RoomDatabases? = null

        fun getDatabaseClient(context: Context): RoomDatabases {

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