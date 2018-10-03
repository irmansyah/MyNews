package com.irmansyah.training.mynews.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.irmansyah.training.mynews.BaseViewHolder
import com.irmansyah.training.mynews.R
import com.irmansyah.training.mynews.data.model.Source
import kotlinx.android.synthetic.main.item_source.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class SourceAdapter(private val sources: ArrayList<Source>) : RecyclerView.Adapter<SourceAdapter.SourceViewHolder>() {

    private lateinit var mListener: (Source) -> Unit

    fun setOnItemCLickListener(listener: (Source) -> Unit) {
        this.mListener = listener
    }

    fun addSourceToList(sources: List<Source>?) {
        this.sources.clear()
        sources?.let { this.sources.addAll(it) }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = sources.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceAdapter.SourceViewHolder =
            SourceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_source, parent, false))

    override fun onBindViewHolder(holder: SourceAdapter.SourceViewHolder, position: Int) {
        holder.clear()
        holder.onBind(position)
    }

    inner class SourceViewHolder(view: View) : BaseViewHolder(view) {

        override fun clear() {
            itemView.title_tv.text = ""
            itemView.desc_tv.text = ""
        }

        override fun onBind(position: Int) {
            sources[position].let {
                itemView.title_tv.text = it.name
                itemView.desc_tv.text = it.description
            }
            itemView.onClick { mListener(sources[position]) }
        }
    }
}