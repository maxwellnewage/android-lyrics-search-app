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
    val searchingSong = MutableLiveData(false)
    val currentSearch = MutableLiveData<SearchHistory>()

    fun searchLyrics(artist: String, title: String) {
        searchingSong.postValue(true)

        LyricsBuilder.api.search(artist, title).enqueue(
            object: Callback<Lyric> {
                override fun onResponse(call: Call<Lyric>, response: Response<Lyric>) {
                    searchingSong.postValue(false)

                    if(response.isSuccessful) {
                        // fill the lyrics of song
                        lyrics.postValue(response.body())

                        // add the search terms to history
                        // use bang bang operator because the value never is null
                        val list = searchHistoryList.value!!
                        val searchHistory = SearchHistory(artist, title)
                        currentSearch.postValue(searchHistory)

                        // checking if it's the terms aren't in the history
                        if(!list.contains(searchHistory)) {
                            list.add(SearchHistory(artist, title))
                            searchHistoryList.postValue(list)
                        }
                    } else {
                        val errorLyric = Gson().fromJson(response.errorBody()?.string(), Lyric::class.java)
                        lyrics.postValue(errorLyric)
                    }
                }

                override fun onFailure(call: Call<Lyric>, t: Throwable) {
                    searchingSong.postValue(false)
                    lyrics.postValue(Lyric(null, "Network error, please try again"))
                }
            }
        )
    }
}