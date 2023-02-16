package com.example.carauosellnews.di

import android.app.Application
import com.example.carauosellnews.data.repositoryimpl.NewsRepositoryImpl
import com.example.carauosellnews.domain.repository.NewsRepository
import com.example.carauosellnews.domain.usecases.GetNewsDataUsecase
import com.example.carauosellnews.presentation.adapters.NewsListAdapter
import com.example.carauosellnews.presentation.viewmodels.NewsViewModelFactory
import com.example.carauosellnews.data.webservice.Webservice
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://storage.googleapis.com/carousell-interview-assets/android/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Singleton
    @Provides
    fun provideWebService(retrofit: Retrofit): Webservice {
        return retrofit.create(Webservice::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(webservice: Webservice): NewsRepository {
        return NewsRepositoryImpl(webservice)
    }

    @Provides
    @Singleton
    fun provideGetNewsDataUsecase(newsRepository: NewsRepository):GetNewsDataUsecase{
        return GetNewsDataUsecase(newsRepository)
    }

    @Provides
    @Singleton
    fun provideNewsViewModel(getNewsDataUsecase: GetNewsDataUsecase):NewsViewModelFactory{
        return NewsViewModelFactory(getNewsDataUsecase)
    }

    @Singleton
    @Provides
    fun provideNewsAdapter():NewsListAdapter{
        return NewsListAdapter()
    }
}