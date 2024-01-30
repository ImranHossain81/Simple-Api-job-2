package com.example.imran_47129083_job2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        userAdapter = UserAdapter(emptyList())
        recyclerView.adapter = userAdapter

        fetchData()
    }

    private fun fetchData() {
        val call = ApiClient.apiService.getUsers()
        call.enqueue(object: retrofit2.Callback<List<User>> {
            override fun onResponse(call: retrofit2.Call<List<User>>, response: retrofit2.Response<List<User>>) {
                if(response.isSuccessful){
                    val users = response.body()?: emptyList()
                    userAdapter = UserAdapter(users)
                    recyclerView.adapter = userAdapter
                }
            }

            override fun onFailure(call: retrofit2.Call<List<User>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Data Fetehing failed", Toast.LENGTH_SHORT).show()
            }

        })

        }
    }
