package com.maxwell.lyricssearchapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider

class LyricsFragment : Fragment() {
    private val lyricsViewModel: LyricsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lyric, container, false)
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)

        val tvLyric: TextView = v.findViewById(R.id.tvLyric)

        lyricsViewModel.lyrics.observe(viewLifecycleOwner, {
            // if the lyric wasn't found, you will receive an error string property
            if(it.lyrics.isNullOrEmpty()) {
                tvLyric.text = it.error
            } else {
                tvLyric.text = it.lyrics
            }
        })
    }
}