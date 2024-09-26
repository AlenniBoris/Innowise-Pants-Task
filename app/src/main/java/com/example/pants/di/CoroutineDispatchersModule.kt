package com.example.pants.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

const val DEFAULT_DISPATCHER = "DEFAULT_DISPATCHER"

const val IO_DISPATCHER = "IO_DISPATCHER"

val coroutineDispatchersModule = module {

    single(named(DEFAULT_DISPATCHER)) { Dispatchers.Default } bind(CoroutineDispatcher::class)

    single(named(IO_DISPATCHER)) { Dispatchers.IO } bind(CoroutineDispatcher::class)

}