package com.milkyway.myapplication.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.milkyway.myapplication.model.Data
import com.milkyway.myapplication.model.PostResponse
import com.milkyway.myapplication.repository.PostReposiroty

class PostViewModel : ViewModel() {

    var postLiveData: MutableLiveData<List<Data>>? = null

    fun loadPostList(context: Context) {
        PostReposiroty.getRetrofitInstance(context)
    }

     fun getPostList(): LiveData<List<Data>>? {
        postLiveData = PostReposiroty.getDataFromRoomDb()
        return postLiveData
    }

}