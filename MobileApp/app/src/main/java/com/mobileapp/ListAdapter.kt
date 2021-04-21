package com.mobileapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(var wordList: Map<String, String>) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var wordTextView: TextView = itemView.findViewById(R.id.word)
        private var translationTextView: TextView = itemView.findViewById(R.id.wordTranslation)

        fun bind(word: String, translation: String) {
            wordTextView.text = word
            translationTextView.text = translation
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val keySet: Set<String> = wordList.keys
        val keyArray = keySet.toTypedArray<String>()
        val key: String = keyArray[position]
        holder.bind(key, wordList[key].toString())
    }

    override fun getItemCount(): Int {
        return wordList.size
    }
}