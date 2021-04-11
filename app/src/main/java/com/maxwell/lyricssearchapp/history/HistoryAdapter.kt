package com.maxwell.lyricssearchapp.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maxwell.lyricssearchapp.R

class HistoryVH(v: View): RecyclerView.ViewHolder(v) {
    val tvTitle: TextView = v.findViewById(R.id.tvTitle)
    val tvArtist: TextView = v.findViewById(R.id.tvArtist)
}

class HistoryAdapter(private var searchHistoryList: ArrayList<SearchHistory>): RecyclerView.Adapter<HistoryVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH {
        return HistoryVH(LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false))
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        val searchHistory = searchHistoryList[position]

        holder.tvTitle.text = searchHistory.title
        holder.tvArtist.text = searchHistory.artist

        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return searchHistoryList.size
    }

    fun setData(searchHistoryList: ArrayList<SearchHistory>) {
        this.searchHistoryList = searchHistoryList
        notifyDataSetChanged()
    }
}