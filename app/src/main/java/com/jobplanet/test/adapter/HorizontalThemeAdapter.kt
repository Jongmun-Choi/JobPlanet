package com.jobplanet.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jobplanet.test.databinding.ItemHorizontalThemeBinding
import com.jobplanet.test.model.HorizontalThemeData

class HorizontalThemeAdapter : ListAdapter<HorizontalThemeData.HorizontalItem, HorizontalThemeAdapter.HorizontalThemeViewHolder>(Companion) {

    class HorizontalThemeViewHolder(val binding : ItemHorizontalThemeBinding) : RecyclerView.ViewHolder(binding.root)

    companion object: DiffUtil.ItemCallback<HorizontalThemeData.HorizontalItem>() {
        override fun areItemsTheSame(oldItem: HorizontalThemeData.HorizontalItem, newItem: HorizontalThemeData.HorizontalItem
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: HorizontalThemeData.HorizontalItem, newItem: HorizontalThemeData.HorizontalItem
        ): Boolean = oldItem.cover_image == newItem.cover_image && oldItem.title == newItem.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalThemeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHorizontalThemeBinding.inflate(layoutInflater)
        return HorizontalThemeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalThemeViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.item = currentItem
        holder.binding.executePendingBindings()
    }
}