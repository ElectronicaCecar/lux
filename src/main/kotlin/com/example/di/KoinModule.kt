package com.example.di

import com.example.domain.voltaje.repocitory.ReleDataSource
import com.example.domain.voltaje.repocitory.VoltajeDataSource
import com.example.infrastructure.dataSource.ReleDataSourceImpl
import com.example.infrastructure.dataSource.VoltajeDataSourceImpl
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val KoinModule = module {
    single {
        KMongo.createClient(System.getenv("MONGODB_URI"))
            .coroutine
            .getDatabase("LUXIL")
    }
    single<VoltajeDataSource> { VoltajeDataSourceImpl(get()) }
    single<ReleDataSource> { ReleDataSourceImpl(get()) }
}