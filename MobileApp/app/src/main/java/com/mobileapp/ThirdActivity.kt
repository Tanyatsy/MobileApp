package com.mobileapp

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ThirdActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        val s = intent.getStringExtra("input")!!.replace("}", "").replace("{", "")
        val values = s.split(",").associate {
            val (left, right) = it.split("=")
            left to right
        }

        linearLayoutManager = LinearLayoutManager(this)

        val adapter = ListAdapter(values)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = linearLayoutManager

        val buttonBack: ImageButton = findViewById(R.id.imageButton)

        buttonBack.setOnClickListener {
            finish()
        }
    }
}