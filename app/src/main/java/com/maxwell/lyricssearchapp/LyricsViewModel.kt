package com.maxwell.lyricssearchapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.maxwell.lyricssearchapp.network.LyricsBuilder
import com.maxwell.lyricssearchapp.network.models.Lyric
import com.maxwell.lyricssearchapp.history.SearchHistory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LyricsViewModel: ViewModel() {
    val lyrics = MutableLiveData<Lyric>()
    val searchHistoryList = MutableLiveData<ArrayList<SearchHistory>>(arrayListOf())

    fun searchLyrics(artist: String, title: String) {
        LyricsBuilder.api.search(artist, title).enqueue(
            object: Callback<Lyric> {
                override fun onResponse(call: Call<Lyric>, response: Response<Lyric>) {
                    if(response.isSuccessful) {
                        // fill the lyrics of song
                        lyrics.postValue(response.body())

                        // add the search terms to history
                        val list = searchHistoryList.value
                        list?.add(SearchHistory(artist, title))
                        searchHistoryList.postValue(list!!)
                    } else {
                        val errorLyric = Gson().fromJson(response.errorBody()?.string(), Lyric::class.java)
                        lyrics.postValue(errorLyric)
                    }
                }

                override fun onFailure(call: Call<Lyric>, t: Throwable) {
                    lyrics.postValue(Lyric(null, "Network error, please try again"))
                }
            }
        )
    }
}