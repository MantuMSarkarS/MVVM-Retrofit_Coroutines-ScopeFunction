package com.milkyway.myapplication.activities

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.milkyway.myapplication.R
import com.milkyway.myapplication.adapter.PostAdapter
import com.milkyway.myapplication.viewmodel.PostViewModel

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: PostViewModel
    lateinit var context: Context
    lateinit var adapter: PostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this@MainActivity
        viewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        loadData.setOnClickListener() {
            findViewById<ProgressBar>(R.id.progress_bar).visibility = View.VISIBLE
            viewModel.loadPostList(context)
            downloadPostDetails()
        }
    }

    private fun downloadPostDetails() {
        viewModel.getPostList(this)?.observe(this, Observer {
            it?.let {
                adapter = PostAdapter(it)
                recyclerview.adapter = adapter
                findViewById<ProgressBar>(R.id.progress_bar).visibility = View.GONE
            }
        })
    }

}