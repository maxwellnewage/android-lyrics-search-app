package com.maxwell.lyricssearchapp.network

import com.maxwell.lyricssearchapp.network.models.Lyric
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LyricsAPI {
    @GET("{artist}/{title}")
    fun search(
        @Path("artist") artist: String,
        @Path("title") title: String
    ): Call<Lyric>
}