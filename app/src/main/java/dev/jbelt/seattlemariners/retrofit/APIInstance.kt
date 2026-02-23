package dev.jbelt.seattlemariners.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.jbelt.seattlemariners.repo.ApiService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Singleton
object APIInstance{
    private const val BASE_URL = "https://site.api.espn.com/apis/site/v2/sports/baseball/mlb/"

    val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)
    }
}