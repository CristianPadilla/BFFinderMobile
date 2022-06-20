package com.cpadilla.bffinder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cpadilla.bffinder.R
import com.cpadilla.bffinder.databinding.ItemPetBinding
import com.cpadilla.bffinder.model.AdoptionPostResponse
import com.cpadilla.bffinder.model.PetResponse
import com.squareup.picasso.Picasso

class AdoptionPostAdapter(
    val posts: List<AdoptionPostResponse>,
    val listener: OnItemClickListener
) :
    RecyclerView.Adapter<AdoptionPostAdapter.AdoptionPostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdoptionPostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AdoptionPostViewHolder(layoutInflater.inflate(R.layout.item_pet, parent, false))
    }

    override fun onBindViewHolder(holder: AdoptionPostViewHolder, position: Int) {
        val item = posts[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = posts.size

    inner class AdoptionPostViewHolder(val view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        private val binding = ItemPetBinding.bind(view)
        fun bind(post: AdoptionPostResponse) {
            if (!post.pet.photos.isEmpty()) {

                if (post.pet.photos[0].source != null || !post.pet.photos[0].source.isEmpty()) {
                    Picasso.get().load(post.pet.photos[0].source).into(binding.ivPet)
                }

            }
            binding.tvPetName.text = post.pet.name
            binding.tvPetSex.text = post.pet.sex
            binding.tvPetBreed.text = post.pet.breed.name
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) listener.onItemClick(posts[position].id)

        }


    }

    interface OnItemClickListener {
        fun onItemClick(postId: Int)
    }
}