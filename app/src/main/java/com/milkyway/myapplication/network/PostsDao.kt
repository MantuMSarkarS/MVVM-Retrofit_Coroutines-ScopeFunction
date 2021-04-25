package com.milkyway.myapplication.network

import androidx.lifecycle.LiveData
import androidx.room.*
import com.milkyway.myapplication.model.Data


/*
@Dao
abstract class PostsDao<Data> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   abstract fun insert(posts : MutableLiveData<List<Data>>)
}
*/

@Dao
abstract class PostsDao<Data> {
    //Base Class
    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: Data)*/

}
@Dao
abstract class PostDao : PostsDao<Data>() {

    /* @Transaction
     @Query("select * from data")
     abstract fun getAllPosts(): LiveData<List<Data>>*/

}