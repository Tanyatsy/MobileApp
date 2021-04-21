package com.mobileapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val inputText: EditText = findViewById(R.id.inputText)
        val button: Button = findViewById(R.id.button)
        val buttonToList: Button = findViewById(R.id.buttonToList)
        val buttonBack: ImageButton = findViewById(R.id.imageButton)

        var text = ""

        val intent = Intent(this, ThirdActivity::class.java)

        val values = mutableMapOf<String, String>(
            "entity" to "организация", "work out" to "решить", "congestion" to "затор",
            "vendor" to "продавец", "threat" to "угроза", "engage" to "обручаться",
            "ban" to "запрет", "appreciate" to "ценить", "offend" to "обижать",
            "guilt" to "вина", "hurt" to "ранить", "merry" to "радостный",
            "scared" to "напуганный", "angry" to "злой", "unhappy" to "несчастный",
            "optimism" to "оптимизм", "desire" to "желание", "fear" to "страх",
            "gift" to "подарок", "cake" to "торт", "delivery" to "доставка",
            "entity" to "организация", "terrible" to "ужасный", "be" to "быть",
            "occasion" to "возможность", "pie" to "пирог", "vase" to "ваза",
            "suit" to "костюм", "candy" to "конфета", "sweet" to "сладкий",
            "earning" to "серьга", "ring" to "кольцо", "tie" to "галстук"
        )


        button.setOnClickListener {
            text = inputText.text.toString()

            if (!text.equals("")) {
                val words = text
                val word = words.split(",", " ", "-")
                values[word[0]] = word[1]
            }

            intent.putExtra("input", values.toString())
            startActivity(intent)
        }

        buttonToList.setOnClickListener {
            intent.putExtra("input", values.toString())
            startActivity(intent)
        }

        buttonBack.setOnClickListener {
            finish()
        }

    }


}