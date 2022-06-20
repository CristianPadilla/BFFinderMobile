package com.cpadilla.bffinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.cpadilla.bffinder.fragment.CreatePostFragment
import com.cpadilla.bffinder.fragment.MyPostsFragment
import com.cpadilla.bffinder.fragment.TodoFragment
import com.google.android.material.navigation.NavigationView

class WelcomeActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_menu)
        val navView: NavigationView = findViewById(R.id.idnav_menu)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView, TodoFragment())
                        commit()
                    }
                }
                R.id.item_posts -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView, MyPostsFragment())
                        commit()
                    }
                }

                R.id.item_create_post -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView, CreatePostFragment())
                        commit()
                    }
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (toggle.onOptionsItemSelected(item)) {
                return true
            }
            return super.onOptionsItemSelected(item)
        }


    }