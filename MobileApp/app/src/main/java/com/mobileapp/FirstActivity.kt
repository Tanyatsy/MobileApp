package com.mobileapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonTranslation: Button = findViewById(R.id.translate)
        buttonTranslation.setOnClickListener {
            val intent = Intent(this@FirstActivity, FourthActivity::class.java)
            startActivity(intent)
        }

        val buttonAdd: Button = findViewById(R.id.add)
        buttonAdd.setOnClickListener {
            val intent = Intent(this@FirstActivity, SecondActivity::class.java)
            startActivity(intent)
        }

    }

}