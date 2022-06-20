package com.cpadilla.bffinder.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cpadilla.bffinder.databinding.ItemPetBinding
import com.cpadilla.bffinder.model.AdoptionPostResponse
import com.squareup.picasso.Picasso

class AdoptionPostDetailsAdapter(
    val post: AdoptionPostResponse,
    val listener: OnItemClickListener
) :
    RecyclerView.Adapter<AdoptionPostDetailsAdapter.AdoptionPostDetailsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdoptionPostDetailsAdapter.AdoptionPostDetailsViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(
        holder: AdoptionPostDetailsAdapter.AdoptionPostDetailsViewHolder,
        position: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


    interface OnItemClickListener {
        fun onItemClick(postId: Int)
    }

    inner class AdoptionPostDetailsViewHolder(val view: View) : RecyclerView.ViewHolder(view),
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
//            val position = adapterPosition
//            if (position != RecyclerView.NO_POSITION) listener.onItemClick(posts[position].id)

        }


    }
}