package com.cpadilla.bffinder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cpadilla.bffinder.model.PetResponse
import com.cpadilla.bffinder.R
import com.cpadilla.bffinder.databinding.ItemPetBinding
import com.squareup.picasso.Picasso

class PetAdapter(val pets: List<PetResponse>) : RecyclerView.Adapter<PetAdapter.PetViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PetViewHolder(layoutInflater.inflate(R.layout.item_pet, parent, false))
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val item = pets[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = pets.size


    class PetViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemPetBinding.bind(view)
        fun bind(pet: PetResponse) {
            if (!pet.photos.isEmpty()) {
                if (pet.photos[0].source != null || !pet.photos[0].source.isEmpty()) {
                    Picasso.get().load(pet.photos[0].source).into(binding.ivPet)
                }

            }

//            binding.tvPetName.text = pet.name
//            binding.tvPetId.text = pet.id.toString()
//            if (pet.size.equals('l')) {
//                binding.tvPetSize.text = "large"
//            } else if (pet.size.equals('m')) {
//                binding.tvPetSize.text = "medium"
//            } else {
//                binding.tvPetSize.text = "small"
//            }
//
//            binding.tvPetVaccinated.text = if (pet.vaccinated) "vaccinated" else "not vaccinated"

        }
    }
}