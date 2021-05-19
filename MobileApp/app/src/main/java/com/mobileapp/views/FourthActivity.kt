package com.mobileapp.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.mobileapp.services.APIService
import com.mobileapp.databinding.ActivityFourthBinding
import com.mobileapp.services.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit

class FourthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButton.setOnClickListener {
            finish()
        }

        binding.saveButton.setOnClickListener {
            // Create JSON using JSONObject
            val jsonObject = JSONObject()
            jsonObject.put("word", "Jack")
            jsonObject.put("translatedWord", "John")

            postJSON("https://607fd5e3a5be5d00176dc5a8.mockapi.io", jsonObject)
        }

        binding.translateButton.setOnClickListener {
            var word = binding.inputTextWord.text.toString()

            val options = TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH)
                .setTargetLanguage(TranslateLanguage.GERMAN)
                .build()

            val englishGermanTranslator = Translation.getClient(options)
            englishGermanTranslator.translate(word)
                .addOnSuccessListener { translatedText ->
                    binding.inputTextTranslated.setText(translatedText)
                }
                .addOnFailureListener { exception ->
                    println(exception.message)
                }
        }
    }

    fun postJSON(url: String, jsonObject: JSONObject) {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .build()

        // Create Service
        val service = retrofit.create(APIService::class.java)

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()

        // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            val response = service.addWord(requestBody)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(response.body()?.string())
                    )
                    Log.d("Pretty Printed JSON :", prettyJson)
                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }
}