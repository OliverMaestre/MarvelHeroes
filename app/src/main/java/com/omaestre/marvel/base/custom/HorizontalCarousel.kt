package com.omaestre.marvel.base.custom

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HorizontalCarousel(
    context: Context,
    attrs: AttributeSet
) : RecyclerView(context, attrs) {

    fun <T : ViewHolder> initialize(newAdapter: Adapter<T>?) {
        layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        newAdapter?.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                post {
                    val sidePadding = if(getChildAt(0)!=null)
                        (width / 2) - (getChildAt(0).width / 2)
                    else{
                        (width / 2)
                    }
                    setPadding(sidePadding, 0, sidePadding, 0)
                    scrollToPosition(0)
                }
            }
        })
        adapter = newAdapter
    }



}