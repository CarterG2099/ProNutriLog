package com.proNutriLog.proteinCalculator.data.repository

import android.content.Context
import android.os.Build
import android.util.Base64
import androidx.annotation.RequiresApi
import com.proNutriLog.proteinCalculator.BuildConfig
import com.proNutriLog.proteinCalculator.data.remote.KrogerApiService
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDateTime
import javax.inject.Inject

class TokenManager @Inject constructor(
    @ApplicationContext context: Context,
    private val apiService: KrogerApiService
) {
    private val prefs = context.getSharedPreferences("kroger_prefs", Context.MODE_PRIVATE)

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getValidProductToken(): String {
        val token = prefs.getString("KROGER_PRODUCT_TOKEN", null)
        val expiry = prefs.getString("KROGER_PRODUCT_TOKEN_EXPIRY", null)

        if (token != null && expiry != null) {
            val expiryTime = LocalDateTime.parse(expiry)
            if (expiryTime.isAfter(LocalDateTime.now())) {
                return token
            }
        }

        // Token expired or missing
        return fetchAndStoreNewToken()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun fetchAndStoreNewToken(): String {
        println("CLIENTID: ${BuildConfig.KROGER_CLIENT_ID}")
        println("SECRET: ${BuildConfig.KROGER_CLIENT_SECRET}")
        val basicAuth = "Basic " + Base64.encodeToString(
            "${BuildConfig.KROGER_CLIENT_ID}:${BuildConfig.KROGER_CLIENT_SECRET}".toByteArray(),
            Base64.NO_WRAP
        )

        println("Getting access token... with auth: $basicAuth")
        val response = apiService.getProductAccessToken(basicAuth)
        if (response.isSuccessful) {
            val body = response.body() ?: throw Exception("Token response is empty")
            val expiryTime = LocalDateTime.now().plusSeconds(body.expiresIn.toLong())

            prefs.edit()
                .putString("KROGER_PRODUCT_TOKEN", body.accessToken)
                .putString("KROGER_PRODUCT_TOKEN_EXPIRY", expiryTime.toString())
                .apply()

            return body.accessToken
        } else {
            throw Exception("Failed to fetch token: ${response.errorBody()?.string()}")
        }
    }
}
