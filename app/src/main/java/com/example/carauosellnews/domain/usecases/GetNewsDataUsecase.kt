package com.example.carauosellnews.domain.usecases

import android.util.Log
import com.example.carauosellnews.data.model.NewsItem
import com.example.carauosellnews.domain.repository.NewsRepository
import com.example.carauosellnews.utils.resource.Resource

class GetNewsDataUsecase(private val newsRepository: NewsRepository) {

    suspend fun execute() : Resource<List<NewsItem>>{
        Log.e("@@@","Called from Webservice")
        return newsRepository.getNewsData()
    }
}