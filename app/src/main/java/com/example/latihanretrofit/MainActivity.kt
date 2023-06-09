package com.example.latihanretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//const val BASE_URL = "https://jsonplaceholder.typicode.com/"
class MainActivity : AppCompatActivity() {
    lateinit var rvMain : RecyclerView
    lateinit var myAdapter: MyAdapter

    var BASE_URL ="https://jsonplaceholder.typicode.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain = findViewById(R.id.recycle_view)

        rvMain.layoutManager = LinearLayoutManager(this)

        getAllData()

    }

    private fun getAllData() {

        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlbumService::class.java)

        var retroData = retrofit.getAlbums()

        retroData.enqueue(object : Callback<List<AlbumsItem>> {
            override fun onResponse(
                call: Call<List<AlbumsItem>>,
                response: Response<List<AlbumsItem>>
            ) {
                var data = response.body()!!

                myAdapter = MyAdapter(baseContext,data)

                rvMain.adapter = myAdapter

                Log.d("data",data.toString())
            }

            override fun onFailure(call: Call<List<AlbumsItem>>, t: Throwable) {

            }

        })
    }
}