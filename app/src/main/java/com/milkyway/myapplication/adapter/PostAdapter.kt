package com.milkyway.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.milkyway.myapplication.R
import com.milkyway.myapplication.model.Data
import com.squareup.picasso.Picasso

class PostAdapter(var data: List<Data>) : RecyclerView.Adapter<PostAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.id.setText(data.get(position).id)
        holder.email.setText(data.get(position).email)
        holder.firstname.setText(data.get(position).firstName)
        holder.lastname.setText(data.get(position).lastName)
        Picasso.get().load(data.get(position).avatar).into(holder.image)
    }

    override fun getItemCount(): Int {
        return 1
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id = itemView.findViewById<TextView>(R.id.id)
        val email = itemView.findViewById<TextView>(R.id.email)
        val firstname = itemView.findViewById<TextView>(R.id.firstname)

        val lastname = itemView.findViewById<TextView>(R.id.lastname)
        val image = itemView.findViewById<ImageView>(R.id.image)

    }
}