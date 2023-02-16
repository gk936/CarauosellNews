package com.example.carauosellnews.data.webservice

import com.example.carauosellnews.data.model.NewsItem
import retrofit2.Response
import retrofit2.http.GET

interface Webservice {

    @GET("carousell_news.json")
    suspend fun getData() : Response<List<NewsItem>>

}