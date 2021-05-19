package com.mobileapp.views

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.mobileapp.R


class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigation = findNavController(R.id.fragment)
        val bottomNavigation : NavigationView = findViewById(R.id.bottomNavigation)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.firstFragment,
            R.id.secondFragment,
            R.id.thirdFragment
        ))
        setupActionBarWithNavController(navigation,appBarConfiguration)

        bottomNavigation.setupWithNavController(navigation)

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