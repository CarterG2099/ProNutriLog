package com.proNutriLog.proteinCalculator.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String = "",
    val first_name: String = "",
    val last_name: String = "",
    val display_name: String = "",
    val email: String = "",
    val age: Int = 0,
    val height: Int = 0,
    val weight: Int = 0,
    val dietaryPreferences: String = ""
)
