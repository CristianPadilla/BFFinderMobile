package com.cpadilla.bffinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.cpadilla.bffinder.adapter.AdoptionPostAdapter
import com.cpadilla.bffinder.api.AdoptionPostAPIService
import com.cpadilla.bffinder.databinding.ActivityPetDetailsBinding
import com.cpadilla.bffinder.model.AdoptionPostResponse
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PetDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPetDetailsBinding
    var postId: Int = 0
    private lateinit var post: AdoptionPostResponse
    private lateinit var adapter: AdoptionPostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        postId = intent.getIntExtra("postId", 0)


        findPost(postId)


//        binding.tvPostId.text = post.pet.id.toString()
//        val textView = binding.tvPostId.apply {
//            text = intent.getIntExtra("postId", 0).toString()
//        }

        //Slider de las mascotas
//        val imageList = ArrayList<SlideModel>() // Create image list
//
//        imageList.add(SlideModel("https://bit.ly/2YoJ77H"))
//        imageList.add(SlideModel("https://bit.ly/2BteuF2"))
//        imageList.add(SlideModel("https://bit.ly/3fLJf72"))
//
//        val imageSlider = findViewById<ImageSlider>(R.id.idimgslider)
//        imageSlider.setImageList(imageList)
    }


    private fun findPost(postId: Int) {
        CoroutineScope(Dispatchers.IO).launch {

            val retrofit = getRetrofit()
            val retrofitAPI = retrofit.create(AdoptionPostAPIService::class.java)
            val call = retrofitAPI.getPost("posts/$postId")

            runOnUiThread {
                call.enqueue(object : Callback<AdoptionPostResponse> {
                    override fun onResponse(
                        call: Call<AdoptionPostResponse>,
                        response: Response<AdoptionPostResponse>
                    ) {
                        if (response.isSuccessful) {

                            val result = response.body()
                            if (result != null) {
                                post = result
                            }
                            binding.tvPostId.text = post.id.toString()
                            if (!post.pet.photos.isEmpty()) {
                                var images: ArrayList<SlideModel> = ArrayList()
                                var i = 0
                                for (photo in post.pet.photos) {
                                    images.add(SlideModel(photo.source, null, null))
                                }
                                binding.isPetPhotos.setImageList(images)
                                binding.tvPetName.text = post.pet.name
                                binding.tvPetNameTitle.text = post.pet.name
                                binding.tvPetBreed.text = post.pet.breed.name
                                binding.tvPetAge.text = post.pet.age.toString() + " years old"

                                if (post.pet.size.equals('s')) binding.tvPetSize.text = "Small"
                                else if (post.pet.size.equals('m')) binding.tvPetSize.text =
                                    "Medium"
                                else binding.tvPetSize.text = "Large"

                                binding.tvPetWeight.text = post.pet.weight.toString() + " Kg"
                                binding.tvSterilized.text =
                                    if (post.pet.sterilized) "Sterilized" else "Not Sterilized"
                                binding.tvPetSex.text = post.pet.sex
                                binding.tvDangerous.text = if (post.pet.dangerous) "Yes" else "No"
                                binding.tvVaccinated.text = if (post.pet.vaccinated) "Yes" else "No"
                                binding.idtextDescription.text = post.description
                                binding.tvOwnerName.text =
                                    post.pet.owner.name + " " + post.pet.owner.surname


                            }
                        }
                    }

                    override fun onFailure(call: Call<AdoptionPostResponse>, t: Throwable) {
                        Log.e("TAG", "No se pudo relizar la peticion")
                        throw t;
                    }
                })
            }
        }
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://bffinder-api.herokuapp.com/cpadilla-bffinder/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun onCreateAdopt(view: View) {}
    fun onAddComment(view: View) {}
}