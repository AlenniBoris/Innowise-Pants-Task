package com.example.pants.di

import com.example.pants.data.repository.ColorRepositoryImpl
import com.example.pants.data.source.remote.ColorApiService
import com.example.pants.domain.repository.ColorRepository
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val productRepositoryModule = module {
    single {
        ColorRepositoryImpl(
            get<ColorApiService>(),
            get<CoroutineDispatcher>(named(IO_DISPATCHER)),
            get<CoroutineDispatcher>(named(DEFAULT_DISPATCHER))
        )
    } bind(ColorRepository::class)
}
