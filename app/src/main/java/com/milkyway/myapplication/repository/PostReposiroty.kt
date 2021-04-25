package com.milkyway.myapplication.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.milkyway.myapplication.model.Data
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

    var roomDatabase: RoomDatabases? = null

    var postList = MutableLiveData<List<Data>>()
    var dataList : LiveData<List<Data>>? =null

    private var call = RetrofitClient.apiService.getALLPost()

    fun initializeDb(context: Context): RoomDatabases {
        return RoomDatabases.getDatabaseClient(context)
    }

    fun getRetrofitInstance(context: Context) {
        roomDatabase = initializeDb(context)
        GlobalScope.launch {

            call.enqueue(object : Callback<PostResponse> {

                override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                    response.body()?.data!!.let {
                        roomDatabase!!.postsDao().insert(response.body()!!.data)
                    }
                }

                override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    Log.d("msg", t.message.toString())
                    Toast.makeText(context, t.message.toString(),Toast.LENGTH_LONG).show()
                }
            })
        }
    }

     fun getDataFromRoomDb(): LiveData<List<Data>>? {
         GlobalScope.launch {
             dataList = roomDatabase!!.postDao().getAllPosts()
             delay(100)
         }
       return dataList
    }

}
