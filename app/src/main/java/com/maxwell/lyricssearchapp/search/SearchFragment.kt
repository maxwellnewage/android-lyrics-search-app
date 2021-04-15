package com.maxwell.lyricssearchapp.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.maxwell.lyricssearchapp.LyricsViewModel
import com.maxwell.lyricssearchapp.R
import com.maxwell.lyricssearchapp.hideKeyboard

class SearchFragment : Fragment() {
    private val lyricsViewModel: LyricsViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)

        val etArtist: EditText = v.findViewById(R.id.etArtist)
        val etSongTitle: EditText = v.findViewById(R.id.etSongTitle)
        val ibSearch: ImageButton = v.findViewById(R.id.ibSearch)

        ibSearch.setOnClickListener {
            hideKeyboard()

            val artist = etArtist.text.toString()
            val songTitle = etSongTitle.text.toString()

            if(artist.isEmpty() || songTitle.isEmpty()) {
                Toast.makeText(activity, "The song or artist cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lyricsViewModel.searchLyrics(artist, songTitle)
        }

        lyricsViewModel.currentSearch.observe(viewLifecycleOwner, {
            etArtist.setText(it.artist)
            etSongTitle.setText(it.title)
        })
    }
}