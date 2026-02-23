package dev.jbelt.seattlemariners.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.jbelt.seattlemariners.repo.NewsService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Singleton
object NewsInstance{
    private const val BASE_URL = "https://content.core.api.espn.com/v1/sports/news/"

    val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val api: NewsService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(NewsService::class.java)
    }
}