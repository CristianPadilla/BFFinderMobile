package com.cpadilla.bffinder.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cpadilla.bffinder.PetDetailsActivity
import com.cpadilla.bffinder.R
import com.cpadilla.bffinder.databinding.FragmentTodoBinding
import com.cpadilla.bffinder.adapter.AdoptionPostAdapter
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

class TodoFragment : Fragment(R.layout.fragment_todo),
    android.widget.SearchView.OnQueryTextListener,
    AdoptionPostAdapter.OnItemClickListener {


    private var fragmentTodoBinding: FragmentTodoBinding? = null
    private lateinit var binding: FragmentTodoBinding
    private lateinit var adapter: AdoptionPostAdapter
    private val posts = mutableListOf<AdoptionPostResponse>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoBinding.inflate(inflater, container, false)
        fragmentTodoBinding = binding
        binding.svPets.setOnQueryTextListener(this)
        initRecyclerView()
        return binding.root;
    }


    private fun initRecyclerView() {
        adapter = AdoptionPostAdapter(posts, this)
        binding.rvPets?.apply {
            layoutManager = LinearLayoutManager(activity)
        }
        binding.rvPets?.adapter = adapter
        binding.title.text = "Mascotas en adopcion"
        findAllPosts()
    }

    private fun findAllPosts() {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = getRetrofit()
            val retrofitAPI = retrofit.create(AdoptionPostAPIService::class.java)
            val call = retrofitAPI.getPosts("posts")
            activity?.runOnUiThread(java.lang.Runnable {
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
            })
        }
    }

    private fun showError() {
        Toast.makeText(this.context, "ocurrio un error", Toast.LENGTH_SHORT).show()
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://bffinder-api.herokuapp.com/cpadilla-bffinder/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onItemClick(postId: Int) {
        val intent = Intent(this.context, PetDetailsActivity::class.java).apply {
            putExtra("postId", postId)
        }
        startActivity(intent)
    }


}