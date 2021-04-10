package com.maxwell.lyricssearchapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.maxwell.lyricssearchapp.network.LyricsBuilder
import com.maxwell.lyricssearchapp.network.models.Lyric
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LyricsBuilder.api.search("Coldplay", "Adventure of a Lifetime").enqueue(
            object: Callback<Lyric> {
                override fun onResponse(call: Call<Lyric>, response: Response<Lyric>) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<Lyric>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            }
        )

//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }
}