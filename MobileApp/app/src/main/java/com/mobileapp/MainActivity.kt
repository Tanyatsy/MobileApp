package com.mobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        val inputText: EditText = findViewById(R.id.inputText)
        val button: Button = findViewById(R.id.button)
        var text = ""

            val messageTextView: TextView = findViewById(R.id.textView)
            if( !intent.getStringExtra("changedName").equals(null)){
                messageTextView.text = "Hello, " + intent.getStringExtra("changedName")
            }

        button.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            text = inputText.text.toString()
            intent.putExtra("input", text)
            startActivity(intent)
        }

    }


}