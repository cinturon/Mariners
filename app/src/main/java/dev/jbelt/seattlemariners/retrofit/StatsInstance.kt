package dev.jbelt.seattlemariners.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.jbelt.seattlemariners.repo.StatsService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Singleton
object StatsInstance{
    private const val BASE_URL = "https://statsapi.mlb.com/api/v1/"

    val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val api: StatsService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(StatsService::class.java)
    }
}