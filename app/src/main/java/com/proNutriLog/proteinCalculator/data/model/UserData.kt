package com.proNutriLog.proteinCalculator.data.model

data class User(
    val name: String = "",
    val email: String = "",
    val age: Int = 0,
    val height: Int = 0,
    val weight: Int = 0,
    val dietaryPreferences: String = ""
)
