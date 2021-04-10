package com.maxwell.lyricssearchapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LyricsBuilder {
    private const val BASE_URL = "https://private-anon-ffa31e7822-lyricsovh.apiary-proxy.com/v1/"

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val api: LyricsAPI by lazy { retrofit().create(LyricsAPI::class.java) }
}