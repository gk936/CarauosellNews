package com.example.carauosellnews.presentation.adapters

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carauosellnews.data.model.NewsItem
import com.example.carauosellnews.databinding.ItemNewsBinding
import com.example.carauosellnews.utils.Utility.getTextCreatedAt


class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {
    inner class NewsViewHolder(val binding : ItemNewsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(newsItem : NewsItem){
            binding.tvTitle.text = newsItem.title
            binding.tvDesc.text = newsItem.description
            binding.tvTime.text = getTextCreatedAt(newsItem.timeCreated!!)
            Glide.with(binding.ivArticle.context)
                .load(newsItem.bannerUrl)
                .into(binding.ivArticle)
        }
    }

    private val callback = object : DiffUtil.ItemCallback<NewsItem>(){
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = differ.currentList[position]
        Log.d("@@@","Position" + position + " " + item.toString())
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}