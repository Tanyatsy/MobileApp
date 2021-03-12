package com.mobileapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: ImageButton = findViewById(R.id.imageButton)
        button.setOnClickListener {
            val intent = Intent(this@FirstActivity, SecondActivity::class.java)
            startActivity(intent)
        }

    }

}