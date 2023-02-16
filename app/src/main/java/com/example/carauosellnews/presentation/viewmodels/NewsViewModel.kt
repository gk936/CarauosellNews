package com.example.carauosellnews.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carauosellnews.data.model.NewsItem
import com.example.carauosellnews.domain.usecases.GetNewsDataUsecase
import com.example.carauosellnews.utils.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(private val getNewsDataUsecase: GetNewsDataUsecase) : ViewModel() {
    val newsLiveData: MutableLiveData<Resource<List<NewsItem>>> = MutableLiveData()

    fun getNewsData() = viewModelScope.launch(Dispatchers.IO) {
        newsLiveData.postValue(Resource.Loading())
        val apiResult = getNewsDataUsecase.execute()
        newsLiveData.postValue(apiResult)
    }
}
