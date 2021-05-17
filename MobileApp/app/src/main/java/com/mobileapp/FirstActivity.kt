package com.mobileapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.mobileapp.databinding.ActivityMainBinding
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView


class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingMain = ActivityMainBinding.inflate(layoutInflater)

        setContentView(bindingMain.root)

        bindingMain.translate?.setOnClickListener {
        val navigation = findNavController(R.id.fragment)
        val bottomNavigation : NavigationView = findViewById(R.id.bottomNavigation)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.firstFragment,R.id.secondFragment,R.id.thirdFragment))
        setupActionBarWithNavController(navigation,appBarConfiguration)

        bottomNavigation.setupWithNavController(navigation)

        val buttonTranslation: Button = findViewById(R.id.translate)
        buttonTranslation.setOnClickListener {
            val intent = Intent(this@FirstActivity, FourthActivity::class.java)
            startActivity(intent)
        }

        bindingMain.add?.setOnClickListener {
            val intent = Intent(this@FirstActivity, SecondActivity::class.java)
            startActivity(intent)
        }

    }

}