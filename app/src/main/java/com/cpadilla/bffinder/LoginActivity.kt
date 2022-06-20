package com.cpadilla.bffinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.cpadilla.bffinder.api.AdoptionPostAPIService
import com.cpadilla.bffinder.api.UserApiService
import com.cpadilla.bffinder.databinding.ActivityLoginBinding
import com.cpadilla.bffinder.databinding.ActivityPetDetailsBinding
import com.cpadilla.bffinder.model.AdoptionPostResponse
import com.cpadilla.bffinder.model.UserResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    var user: UserResponse = UserResponse(0, "", "", "", 0, "", "")
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    fun onGoregister(view: View) {
        val irRegistro = Intent(this, RegisterActivity::class.java)
        startActivity(irRegistro)
    }

    fun onLogin(boton: View) {
        var email: String = binding.etEmail.text.toString()
        var password: String = binding.etPassword.text.toString()

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            Toast.makeText(
                this,
                "El correo y la contraseña no pueden estar vacios",
                Toast.LENGTH_LONG
            ).show()

        } else {
            findUser(email)
//            Log.e("TAG", "======================> password " + password + " " + user.password)
//            Log.e("TAG", "======================> email " + email + " " + user.email)
            if (password.equals(user.password)) {
                val nActividad = Intent(this, WelcomeActivity::class.java)
                nActividad.putExtra("userId", user.id)
                startActivity(nActividad)
            } else {
                Toast.makeText(this, "El correo o la contraseña son incorrectas", Toast.LENGTH_LONG)
                    .show()
            }
        }


    }

    private fun findUser(userEmail: String) {
//        CoroutineScope(Dispatchers.IO).launch {

        val retrofit = getRetrofit()
        val retrofitAPI = retrofit.create(UserApiService::class.java)
        val call = retrofitAPI.getUser("users/email/$userEmail")

//            runOnUiThread {

        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()
                    if (result != null) {
                        user = result
                        Log.e("TAG", "========================> entre " + user.toString())
                    }
                } else Log.e("TAG", "========================> no entre ")
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("TAG", "No se pudo relizar la peticion")
                throw t;
            }
        })


    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://bffinder-api.herokuapp.com/cpadilla-bffinder/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}