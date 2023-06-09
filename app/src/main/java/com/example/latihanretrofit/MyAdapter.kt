package com.example.latihanretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var  con : Context, var list: List<AlbumsItem>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(v : View) : RecyclerView.ViewHolder(v)
    {
        var userID = v.findViewById<TextView>(R.id.tv_userID)
        var id = v.findViewById<TextView>(R.id.tv_ID)
        var title = v.findViewById<TextView>(R.id.tv_Title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(con).inflate(R.layout.list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userID.text = list[position].userId.toString()
        holder.id.text = list[position].id.toString()
        holder.title.text = list[position].title.toString()
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}