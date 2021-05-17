package com.mobileapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.mobileapp.databinding.ActivityMainBinding


class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingMain = ActivityMainBinding.inflate(layoutInflater)

        setContentView(bindingMain.root)

        bindingMain.translate?.setOnClickListener {
            val intent = Intent(this@FirstActivity, FourthActivity::class.java)
            startActivity(intent)
        }

        bindingMain.add?.setOnClickListener {
            val intent = Intent(this@FirstActivity, SecondActivity::class.java)
            startActivity(intent)
        }

    }

}