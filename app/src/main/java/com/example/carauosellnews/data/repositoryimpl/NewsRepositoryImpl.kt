package com.example.carauosellnews.data.repositoryimpl

import com.example.carauosellnews.data.model.NewsItem
import com.example.carauosellnews.domain.repository.NewsRepository
import com.example.carauosellnews.utils.resource.Resource
import com.example.carauosellnews.data.webservice.Webservice
import retrofit2.Response

class NewsRepositoryImpl(val webservice: Webservice): NewsRepository {
    override suspend fun getNewsData(): Resource<List<NewsItem>>{
        return responseToResource(webservice.getData())
    }

    private fun responseToResource(response: Response<List<NewsItem>>): Resource<List<NewsItem>> {
        if(response.isSuccessful){
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}