package com.mobileapp

import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.mobileapp.R.layout.activity_third)

        val listView: ListView = findViewById<ListView>(com.mobileapp.R.id.listView)
        // Defined Array values to show in ListView
        val values = arrayOf(
            "Android List View",
            "Adapter implementation",
            "Simple List View In Android",
            "Create List View Android",
            "Android Example",
            "List View Source Code",
            "List View Array Adapter",
            "Android Example List View"
        )

        val adapter = ArrayAdapter(
            this,
            R.layout.simple_list_item_1, R.id.text1, values
        )

        listView.adapter = adapter

        listView.onItemClickListener = OnItemClickListener { _, _, position, _ -> // TODO Auto-generated method stub
            val value = adapter.getItem(position)
            Toast.makeText(applicationContext, value, Toast.LENGTH_SHORT).show()
        }
    }
}