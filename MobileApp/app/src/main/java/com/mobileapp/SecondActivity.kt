package com.mobileapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        displayName(intent.getStringExtra("input"))

        var textChangedName = ""
        val buttonConfig: Button = findViewById(R.id.buttonChangeName)
        val inputText: EditText = findViewById(R.id.editTextName)

        buttonConfig.setOnClickListener {
            textChangedName = inputText.text.toString()
            displayName(textChangedName)
            Toast.makeText(
                this@SecondActivity, "Name is changed!",
                Toast.LENGTH_LONG
            ).show()
        }



        val button: ImageButton = findViewById(R.id.imageButton)
        button.setOnClickListener {
            val intent = Intent(this@SecondActivity, MainActivity::class.java)
            intent.putExtra("changedName", textChangedName)
            startActivity(intent)
        }

    }

    private fun displayName(text: String?){
        val messageTextView: TextView = findViewById(R.id.textView)
        messageTextView.text = "Your name: $text"
    }

}