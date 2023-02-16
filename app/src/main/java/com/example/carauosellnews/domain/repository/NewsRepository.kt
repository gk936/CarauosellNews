package com.example.carauosellnews.domain.repository

import com.example.carauosellnews.data.model.NewsItem
import com.example.carauosellnews.utils.resource.Resource

interface NewsRepository {

    suspend fun getNewsData() : Resource<List<NewsItem>>

}