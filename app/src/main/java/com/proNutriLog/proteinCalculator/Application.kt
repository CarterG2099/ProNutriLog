package com.proNutriLog.proteinCalculator

import SupabaseRepository
import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.ktor.client.*
import io.ktor.client.engine.android.*

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize Supabase client here
        SupabaseRepository.initializeSupabaseClient()
    }
}

val client = HttpClient(Android)
