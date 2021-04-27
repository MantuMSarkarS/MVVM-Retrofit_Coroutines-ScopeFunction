package com.milkyway.myapplication.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.milkyway.myapplication.model.Data
import com.milkyway.myapplication.model.PostResponse
import com.milkyway.myapplication.network.RetrofitClient
import com.milkyway.myapplication.roomdb.RoomDatabases
import com.milkyway.myapplication.utils.Converters
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PostReposiroty {

    var roomDatabase: RoomDatabases? = null

    var postList: LiveData<List<Data>>? = null
    /*var dataList: ArrayList<Data>? = null
    private var call = RetrofitClient.apiService.getALLPost()*/

    fun initializeDb(context: Context): RoomDatabases {
        return RoomDatabases.getDatabaseClient(context)
    }

    fun getRetrofitInstance(context: Context)  = runBlocking{

       val job:Job= launch{

            RetrofitClient.apiService.getALLPost().enqueue(object : Callback<PostResponse> {
                override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                    if (response.isSuccessful) {
                        roomDatabase = initializeDb(context)
                        launch {
                            for (i in 0 until response.body()?.data!!.size) {
                                roomDatabase!!.postsDao().insert(response.body()?.data!![i])
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    Log.d("msg", t.message.toString())
                    Toast.makeText(context, t.message.toString(), Toast.LENGTH_LONG).show()
                }
            })
        }
        job.join()
    }


    fun getDataFromRoomDb(context: Context): LiveData<List<Data>>? = runBlocking{
        roomDatabase = initializeDb(context)
       val job:Job= launch {
            postList = roomDatabase!!.postsDao().getAllPosts()
        }
        job.join()
        return@runBlocking postList
    }

}
