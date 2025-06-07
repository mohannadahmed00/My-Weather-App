package com.giraffe.myweatherapp.di

import com.giraffe.myweatherapp.data.DataSource
import com.giraffe.myweatherapp.data.IDataSource
import com.giraffe.myweatherapp.data.location.GoogleLocationProvider
import com.giraffe.myweatherapp.data.location.LocationProvider
import com.giraffe.myweatherapp.data.utils.HttpClientFactory
import com.giraffe.myweatherapp.presentation.HomeViewModel
import io.ktor.client.engine.android.Android
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { GoogleLocationProvider(androidContext()) }.bind<LocationProvider>()
    single { HttpClientFactory.create(Android.create()) }
    single { DataSource(get(),get()) }.bind<IDataSource>()
    viewModel { HomeViewModel(get()) }
}