package com.milkyway.myapplication.network

import androidx.lifecycle.LiveData
import androidx.room.*
import com.milkyway.myapplication.model.Data

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: Data)

    @Transaction
    @Query("select * from data")
    fun getAllPosts(): LiveData<List<Data>>?
}