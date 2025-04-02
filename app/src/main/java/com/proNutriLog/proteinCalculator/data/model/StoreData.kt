package com.proNutriLog.proteinCalculator.data.model

data class Store(
    val store_id: Long,
    val name: String,
    val location: String,
    val website: String? = null // Optional, since the website can be null
)
