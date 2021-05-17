package com.mobileapp

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobileapp.databinding.ActivityThirdBinding


class ThirdActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val s = intent.getStringExtra("input")!!.replace("}", "").replace("{", "")
        val values = s.split(",").associate {
            val (left, right) = it.split("=")
            left to right
        }

        linearLayoutManager = LinearLayoutManager(this)

        val adapter = ListAdapter(values)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = linearLayoutManager

        binding.imageButton.setOnClickListener {
            finish()
        }
    }
}