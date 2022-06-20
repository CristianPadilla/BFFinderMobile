package com.cpadilla.bffinder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cpadilla.bffinder.R

import com.google.android.material.snackbar.Snackbar

class MyPostsFragment : Fragment(R.layout.fragment_myposts) {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*val fragmento = inflater.inflate(R.layout.fragment_myposts, container, false)
        return fragmento*/

        /*val fab: View = view?.findViewById(R.id.fab_createpost) ?:

        fab.setOnClickListener{ fab_button ->
            Snackbar.make(fab_button,"Fab", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }*/

        val fab: View

        val view: View = inflater.inflate(R.layout.fragment_myposts, container, false)
        fab = view.findViewById(R.id.fab_createpost)
        fab.setOnClickListener { fab_button ->
            Snackbar.make(fab_button,"Fab...", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
            /*supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView, MyPostsFragment())
                commit()
            }*/
        }
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}