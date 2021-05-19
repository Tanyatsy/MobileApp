package com.mobileapp.services



import com.google.android.gms.common.api.Api
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {

    @POST("/ed")
    suspend fun addWord(@Body requestBody: RequestBody): Response<ResponseBody>

    @GET("/word")
    suspend fun getWords(): Response<ResponseBody>

}