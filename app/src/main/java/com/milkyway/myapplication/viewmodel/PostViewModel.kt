package com.milkyway.myapplication.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.milkyway.myapplication.model.PostResponse
import com.milkyway.myapplication.repository.PostReposiroty

class PostViewModel : ViewModel() {

    var postLiveData: MutableLiveData<PostResponse>? = null

    fun loadPostList(context: Context) {
        PostReposiroty.getRetrofitInstance(context)
    }

     fun getPostList(context: Context): MutableLiveData<PostResponse>? {
        postLiveData = PostReposiroty.getDataFromRoomDb(context)
        return postLiveData
    }

}