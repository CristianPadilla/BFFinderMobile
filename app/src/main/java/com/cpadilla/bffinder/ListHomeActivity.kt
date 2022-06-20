package com.cpadilla.bffinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cpadilla.bffinder.adapter.AdoptionPostAdapter
import com.cpadilla.bffinder.databinding.ActivityListhomeBinding
import com.cpadilla.bffinder.api.AdoptionPostAPIService
import com.cpadilla.bffinder.model.AdoptionPostResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListHomeActivity : AppCompatActivity(), android.widget.SearchView.OnQueryTextListener,
    AdoptionPostAdapter.OnItemClickListener {
    private lateinit var binding: ActivityListhomeBinding
    private lateinit var adapter: AdoptionPostAdapter
    private val posts = mutableListOf<AdoptionPostResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListhomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.svPets.setOnQueryTextListener(this)
        initRecyclerView()

    }

    private fun initRecyclerView() {
        adapter = AdoptionPostAdapter(posts, this)
        binding.rvPets?.layoutManager = LinearLayoutManager(this)
        binding.rvPets?.adapter = adapter

        findAllPosts()

    }

    private fun findAllPosts() {
        CoroutineScope(Dispatchers.IO).launch {

            val retrofit = getRetrofit()
            val retrofitAPI = retrofit.create(AdoptionPostAPIService::class.java)
            val call = retrofitAPI.getPosts("posts")

            runOnUiThread {
                call.enqueue(object : Callback<List<AdoptionPostResponse>> {
                    override fun onResponse(
                        call: Call<List<AdoptionPostResponse>>,
                        response: Response<List<AdoptionPostResponse>>
                    ) {
                        if (response.isSuccessful) {
                            val results = response.body()
                            posts.clear()
                            if (results != null) {
                                posts.addAll(results)
                            }
                            adapter.notifyDataSetChanged()
                        }
                    }

                    override fun onFailure(call: Call<List<AdoptionPostResponse>>, t: Throwable) {
                        Log.e("TAG", "No se pudo relizar la peticion")
                        throw t;
                    }
                })
//                call.enqueue(object : Callback<List<AdoptionPostResponse>>{})
            }


        }
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://bffinder-api.herokuapp.com/cpadilla-bffinder/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun showError() {
        Toast.makeText(this, "ocurrio un error", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {

//            findAllPets()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onItemClick(postId: Int) {

        val intent = Intent(this, PetDetailsActivity::class.java).apply {
            putExtra("postId", postId)
        }
        startActivity(intent)

    }
}