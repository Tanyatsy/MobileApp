package com.mobileapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ThirdActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.mobileapp.R.layout.activity_third)

        val values: Map<String,String> = mapOf<String,String>(
            "entity" to "организация", "work out" to "решить", "congestion" to "затор",
            "vendor" to "продавец", "threat" to "угроза", "engage" to "обручаться",
            "ban" to "запрет", "appreciate" to "ценить", "offend" to "обижать",
            "guilt" to "вина", "hurt" to "ранить", "merry" to "радостный",
            "scared" to "напуганный", "angry" to "злой", "unhappy" to "нестастный",
            "optimism" to "оптимизм", "desire" to "желание", "fear" to "страх",
            "entity" to "организация", "work out" to "решить", "congestion" to "затор",
            "terrible" to "ужасный", "work out" to "решить", "congestion" to "затор",
            "" to "организация", "work out" to "решить", "congestion" to "затор",
            "entity" to "организация", "work out" to "решить", "congestion" to "затор",
            "entity" to "организация", "work out" to "решить", "congestion" to "затор"
        )
        linearLayoutManager = LinearLayoutManager(this)

        val adapter = ListAdapter(values)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = linearLayoutManager

    }
}