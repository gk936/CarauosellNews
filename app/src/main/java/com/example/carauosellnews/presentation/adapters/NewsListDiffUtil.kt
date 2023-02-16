package com.example.carauosellnews.presentation.adapters

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.example.carauosellnews.data.model.NewsItem

class NewsListDiffUtil(private val oldList : MutableList<NewsItem>, private val newList : MutableList<NewsItem>):DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id === newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].title == newList[newItemPosition].title -> false
            oldList[oldItemPosition].bannerUrl == newList[newItemPosition].bannerUrl ->false
            else -> {
                true
            }
        }
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }

}