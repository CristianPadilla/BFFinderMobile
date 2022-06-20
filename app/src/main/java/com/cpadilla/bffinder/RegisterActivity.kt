package com.cpadilla.bffinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val back_login = findViewById<TextView>(R.id.idback_login)
        /*back_login.setOnClickListener(

        )*/

        val spinner = findViewById<Spinner>(R.id.idspinner_register);
        val items = resources.getStringArray(R.array.Estratos)

        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        spinner.adapter = adaptador

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@RegisterActivity, items[position], Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }

    }

    fun onBacklogin(view: View) {
        val irLogin = Intent(this, LoginActivity::class.java)
        startActivity(irLogin)
    }
}