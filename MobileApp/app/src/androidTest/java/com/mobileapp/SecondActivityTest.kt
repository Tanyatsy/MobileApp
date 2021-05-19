package com.mobileapp

import android.os.Looper
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Response
import retrofit2.Retrofit

@RunWith(AndroidJUnit4::class)
class SecondActivityTest : TestCase() {
    @Test
    fun test() {
        Looper.prepare()
        val testSample: SecondActivity = SecondActivity()
        assertTrue(testSample.values.javaClass.toString() == "class java.util.LinkedHashMap")
    }

    @Test
    fun integrationTestGet() {
        Looper.prepare()
        var response: Response<ResponseBody>
        val api = create("https://607fd5e3a5be5d00176dc5a8.mockapi.io")
        CoroutineScope(Dispatchers.IO).launch {
            response = api.getWords()
            if (response.isSuccessful) {
                assertTrue("The response is OK", response.code() == (200))
            }
        }
    }

    @Test
    fun integrationTestPost() {
        Looper.prepare()
        var response: Response<ResponseBody>

        val jsonObject = JSONObject()
        jsonObject.put("word", "Jack")
        jsonObject.put("translatedWord", "John")

        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        val api = create("https://607fd5e3a5be5d00176dc5a8.mockapi.io")
        CoroutineScope(Dispatchers.IO).launch {
            response = api.addWord(requestBody)
            if (response.isSuccessful) {
                assertTrue("The response is OK", response.code() == (201))
            }
        }
    }

    private fun create(baseUrl: String): APIService {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .build()
        return retrofit.create(APIService::class.java)
    }
}