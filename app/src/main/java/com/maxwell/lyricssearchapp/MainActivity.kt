package com.maxwell.lyricssearchapp

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels

class MainActivity : AppCompatActivity() {
    private val lyricsViewModel: LyricsViewModel by viewModels()
    private val pbSearching: ProgressBar by lazy { findViewById(R.id.pbSearching) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        lyricsViewModel.searchingSong.observe(this, { searching ->
            if(searching) {
                pbSearching.visibility = View.VISIBLE
            } else {
                pbSearching.visibility = View.GONE
            }
        })
    }
}