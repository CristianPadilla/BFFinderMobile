package com.cpadilla.bffinder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cpadilla.bffinder.R
import com.cpadilla.bffinder.databinding.FragmentCreatePostBinding

class CreatePostFragment : Fragment(R.layout.fragment_create_post) {

    private var fragmentCreatePostBinding: FragmentCreatePostBinding? = null
    private lateinit var binding: FragmentCreatePostBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreatePostBinding.inflate(inflater, container, false)
        fragmentCreatePostBinding = binding
        return binding.root;
    }

    fun onCreatePost(view: View) {}
//    fun onCheckboxClickedDangerous(view: View) {}
//    fun onCheckboxClickedSterilized(view: View) {}
//    fun onRadioButtonClickedMale(view: View) {}
//    fun onRadioButtonClickedFemale(view: View) {}
//    fun onRadioButtonClickedUnknown(view: View) {}
}