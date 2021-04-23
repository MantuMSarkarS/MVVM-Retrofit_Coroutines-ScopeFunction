package com.milkyway.myapplication.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.milkyway.myapplication.model.PostResponse

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(posts : MutableLiveData<PostResponse>)

    @Transaction
    @Query("select * from posts")
    suspend fun getAllPosts(): MutableLiveData<PostResponse>
}