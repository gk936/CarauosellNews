package com.example.carauosellnews.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.carauosellnews.domain.usecases.GetNewsDataUsecase

class NewsViewModelFactory(
    private val getNewsDataUsecase: GetNewsDataUsecase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(
                getNewsDataUsecase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}