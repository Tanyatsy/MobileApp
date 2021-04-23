package com.mobileapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.mobileapp.databinding.ActivitySecondBinding
import com.mobileapp.models.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import retrofit2.Retrofit


class SecondActivity : AppCompatActivity() {

    lateinit var wordMap: WordController

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivitySecondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var text = ""

        val intent = Intent(this, ThirdActivity::class.java)
        getWords("https://607fd5e3a5be5d00176dc5a8.mockapi.io")

        binding.button.setOnClickListener {
            text = binding.inputText.text.toString()
        button.setOnClickListener {
            text = inputText.text.toString()

            if (!text.equals("")) {
                val words = text
                val word = words.split(",", " ", "-")
                wordMap.addToWordsMap(word[0] ,word[1])
            }

            intent.putExtra("input", wordMap.words.toString())
            startActivity(intent)
        }

            binding.buttonToList.setOnClickListener {
            intent.putExtra("input",  wordMap.words.toString())
            startActivity(intent)
        }

        binding.imageButton.setOnClickListener {
            finish()
        }
    }

    fun getWords(url: String) {

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
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(JsonParser.parseString(response.body()?.string()))
                    val json = JSONArray(prettyJson)
                    for (i in 0 until json.length()) {
                        val item = json.getJSONObject(i)
                        var word: Word =
                            Gson().fromJson<Word>(json.getJSONObject(i).toString(), Word::class.java)
                        println(word.translatedWord)
                        wordMap.addToWordsMap(item.get("word") as String, item.get("translatedWord") as String)
                    }
                    Log.d("Pretty Printed JSON :", prettyJson)
                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }
        }
    }
}

