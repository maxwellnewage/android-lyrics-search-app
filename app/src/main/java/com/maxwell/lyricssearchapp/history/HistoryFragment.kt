package com.maxwell.lyricssearchapp.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maxwell.lyricssearchapp.LyricsViewModel
import com.maxwell.lyricssearchapp.R

class HistoryFragment : Fragment() {
    private val lyricsViewModel: LyricsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)

        val rvHistory: RecyclerView = v.findViewById(R.id.rvHistory)
        val adapter = HistoryAdapter(arrayListOf(), lyricsViewModel)
        rvHistory.adapter = adapter
        rvHistory.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        lyricsViewModel.searchHistoryList.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })
    }
}