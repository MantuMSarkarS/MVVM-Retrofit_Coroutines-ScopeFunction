package com.milkyway.myapplication.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.milkyway.myapplication.model.PostResponse
import com.milkyway.myapplication.network.PostsDao
import com.milkyway.myapplication.network.RetrofitClient
import com.milkyway.myapplication.roomdb.RoomDatabases
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PostReposiroty {

    lateinit var roomDao: PostsDao

    var roomDatabase: RoomDatabases? = null

    var postList = MutableLiveData<PostResponse>()

    private var call = RetrofitClient.apiService.getALLPost()

    fun getRetrofitInstance(context: Context) {

        GlobalScope.launch {

            call.enqueue(object : Callback<PostResponse> {

                override fun onResponse(
                    call: Call<PostResponse>,
                    response: Response<PostResponse>
                ) {

                    val body = response.body()

                    body?.apply {
                        postList.value = PostResponse(data, support)
                    }
                }

                override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    Log.d("msg", t.message.toString())
                }
            })
            savePostToRoom(context, postList)
        }
    }

    fun initializeDb(context: Context): RoomDatabases {
        return RoomDatabases.getDataseClient(context)
    }

    private suspend fun savePostToRoom(context: Context, postList: MutableLiveData<PostResponse>) {
        roomDatabase = initializeDb(context)
        roomDatabase!!.postDao().insert(postList)
        getDataFromRoomDb(context)
    }

     fun getDataFromRoomDb(context: Context): MutableLiveData<PostResponse> {
        roomDatabase = initializeDb(context)
         GlobalScope.launch {
             postList = roomDatabase!!.postDao().getAllPosts()
             delay(1000)
         }
       return postList
    }

}