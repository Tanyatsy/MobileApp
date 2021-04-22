package com.mobileapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import retrofit2.Retrofit


class SecondActivity : AppCompatActivity() {

    var values = mutableMapOf<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val inputText: EditText = findViewById(R.id.inputText)
        val button: Button = findViewById(R.id.button)
        val buttonToList: Button = findViewById(R.id.buttonToList)
        val buttonBack: ImageButton = findViewById(R.id.imageButton)

        var text = ""

        val intent = Intent(this, ThirdActivity::class.java)
       getWords("https://607fd5e3a5be5d00176dc5a8.mockapi.io")

        values = mutableMapOf<String, String>(
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

    fun getWords(url: String){

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .build()

        // Create Service
        val service = retrofit.create(APIService::class.java)

        CoroutineScope(Dispatchers.IO).launch {

            // Do the GET request and get response
            val response = service.getWords()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string()
                        )
                    )

                    val json = JSONArray(prettyJson)
                    for (i in 0 until json.length()) {
                        val item = json.getJSONObject(i)
                       values.put(item.get("word") as String, item.get("translatedWord") as String)
                    }
                          Log.d("Pretty Printed JSON :", prettyJson)
                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }
        }
    }
}

