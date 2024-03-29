package com.mobileapp.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.mobileapp.controller.WordController
import com.mobileapp.databinding.ActivitySecondBinding
import com.mobileapp.models.Word
import com.mobileapp.services.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONArray
import retrofit2.Response
import retrofit2.Retrofit


class SecondActivity : AppCompatActivity() {

    var values : WordController = WordController()

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivitySecondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var text = ""

        val intent = Intent(this, ThirdActivity::class.java)
        getWords("https://607fd5e3a5be5d00176dc5a8.mockapi.io")

        binding.button.setOnClickListener {
            text = binding.inputText.text.toString()

            if (!text.equals("")) {
                val words = text
                val word = words.split(",", " ", "-")
                values.addToWordsMap(word[0],word[1])
            }

            intent.putExtra("input", values.words.toString())
            startActivity(intent)
        }

        binding.buttonToList.setOnClickListener {
            intent.putExtra("input", values.words.toString())
            startActivity(intent)
        }

        binding.imageButton.setOnClickListener {
            finish()
        }
    }

    fun getWords(url: String): Response<ResponseBody>? {

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
                        val word = Gson().fromJson(item.toString(), Word::class.java)
                        values.addToWordsMap(word.word,word.translatedWord)
                    }
                    Log.d("Pretty Printed JSON :", prettyJson)
                    return@withContext response
                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }
        }
        return null
    }
}