package com.omaestre.marvel.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omaestre.marvel.R
import com.omaestre.marvel.base.extension.loadImage
import com.omaestre.marvel.databinding.ListItemBinding
import com.omaestre.marvel.domain.model.Hero

class ItemAdapter(private val clickItem: ClickIntoView) : RecyclerView.Adapter<ItemViewHolder>() {

    private var listItems: List<Hero> = emptyList()

    //region override methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(listItems[position], clickItem)

    }

    override fun getItemCount() = listItems.size
    //endregion

    //region public methods
    fun updateItems(items: List<Hero>) {
        listItems = items
        notifyDataSetChanged()
    }
    //endregion
}

interface ClickIntoView {
    fun itemClicked(item: Hero)
}

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ListItemBinding.bind(view)

    fun bind(item: Hero, listener: ClickIntoView) {
        with(binding) {
            itemName.text = item.name
            itemImage.loadImage(item.thumbnail.path + "." + item.thumbnail.extension)

            itemView.setOnClickListener {
                listener.itemClicked(item)
            }
        }
    }
}