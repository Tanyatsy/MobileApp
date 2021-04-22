package com.mobileapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import java.util.*

class FourthActivity : AppCompatActivity(){

    val availableModels = MutableLiveData<List<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        val inputTextWord: EditText = findViewById(R.id.inputTextWord)
        val inputTextTranslated: EditText = findViewById(R.id.inputTextTranslated)
        val translateButton: Button = findViewById(R.id.translateButton)
        val saveButton: Button = findViewById(R.id.saveButton)
        val buttonBack: ImageButton = findViewById(R.id.imageButton)

        buttonBack.setOnClickListener {
            finish()
        }

        saveButton.setOnClickListener {
            // Create JSON using JSONObject
            val jsonObject = JSONObject()
            jsonObject.put("word", "Jack")
            jsonObject.put("translatedWord", "John")

            rawJSON("https://607fd5e3a5be5d00176dc5a8.mockapi.io", jsonObject)
        }

        translateButton.setOnClickListener{
            var word = inputTextWord.text.toString()

            val options = TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH)
                .setTargetLanguage(TranslateLanguage.GERMAN)
                .build()

            val englishGermanTranslator = Translation.getClient(options)
            englishGermanTranslator.translate(word)
                .addOnSuccessListener { translatedText ->
                    inputTextTranslated.setText(translatedText)
                }
                .addOnFailureListener { exception ->
                    println(exception.message)
                }
        }
    }

    fun rawJSON(url: String, jsonObject: JSONObject) {

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

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string()
                        )
                    )

                    Log.d("Pretty Printed JSON :", prettyJson)

                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }
}