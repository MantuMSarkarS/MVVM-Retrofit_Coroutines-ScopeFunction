package com.milkyway.myapplication.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.milkyway.myapplication.model.Data
import com.milkyway.myapplication.model.PostResponse
import com.milkyway.myapplication.repository.PostReposiroty

class PostViewModel : ViewModel() {

    var postLiveData: LiveData<List<Data>>? = null
    var postData: ArrayList<Data>? = null

    fun loadPostList(context: Context) {
      return PostReposiroty.getRetrofitInstance(context)
    }

     fun getPostList(context: Context): LiveData<List<Data>>? {
        postLiveData = PostReposiroty.getDataFromRoomDb(context)
        return postLiveData
    }

}