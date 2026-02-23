package dev.jbelt.seattlemariners.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.jbelt.seattlemariners.repo.ApiService
import dev.jbelt.seattlemariners.repo.EventsService
import dev.jbelt.seattlemariners.repo.NewsService
import dev.jbelt.seattlemariners.repo.StatsService
import dev.jbelt.seattlemariners.retrofit.APIInstance
import dev.jbelt.seattlemariners.retrofit.EventsInstance
import dev.jbelt.seattlemariners.retrofit.NewsInstance
import dev.jbelt.seattlemariners.retrofit.StatsInstance
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiInstance(): APIInstance{
        return APIInstance
    }

    @Provides
    @Singleton
    fun provideNewsInstance(): NewsInstance{
        return NewsInstance
    }

    @Provides
    @Singleton
    fun provideEventsInstance(): EventsInstance{
        return EventsInstance
    }

    @Provides
    @Singleton
    fun provideStatsInstance(): StatsInstance{
        return StatsInstance
    }

    @Provides
    @Singleton
    fun provideApiService(apiInstance: APIInstance): ApiService {
        return apiInstance.api
    }

    @Provides
    @Singleton
    fun provideNewsService(newsInstance: NewsInstance): NewsService {
        return newsInstance.api
    }

    @Provides
    @Singleton
    fun provideEventsService(eventsInstance: EventsInstance): EventsService {
        return eventsInstance.api
    }

    @Provides
    @Singleton
    fun provideStatsService(statsInstance: StatsInstance): StatsService {
        return statsInstance.api
    }
}

