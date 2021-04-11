package com.maxwell.lyricssearchapp

import androidx.lifecycle.ViewModel
import com.maxwell.lyricssearchapp.network.LyricsBuilder
import com.maxwell.lyricssearchapp.network.models.Lyric
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LyricsViewModel: ViewModel() {

    fun searchLyrics(artist: String, title: String) {
        LyricsBuilder.api.search(artist, title).enqueue(
            object: Callback<Lyric> {
                override fun onResponse(call: Call<Lyric>, response: Response<Lyric>) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<Lyric>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            }
        )
    }
}