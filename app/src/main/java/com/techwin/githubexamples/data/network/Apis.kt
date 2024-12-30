package com.techwin.githubexamples.data.network

import com.google.gson.GsonBuilder
import com.techwin.githubexamples.data.network.responses.GetGalleryResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface Apis {

    //&mature=true
    @GET("hot/viral/1?showViral=true")
    suspend fun getGallery(
        @HeaderMap map: HashMap<String, String>
    ): Response<GetGalleryResponse>

    //&mature=true
    @GET("search/1")
    suspend fun searchGallery(
        @HeaderMap map: HashMap<String, String>,
        @Query("q") query: String
    ): Response<GetGalleryResponse>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): Apis {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .create()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.imgur.com/3/gallery/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(Apis::class.java)
        }
    }
}