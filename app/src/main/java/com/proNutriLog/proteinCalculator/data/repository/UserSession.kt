package com.proNutriLog.proteinCalculator.data.repository

import com.proNutriLog.proteinCalculator.data.model.User

object UserSession {
    var currentUser: User? = null
    private set

    fun setCurrentUser(user: User) {
        currentUser = user
    }

    fun clearCurrentUser() {
        currentUser = null
    }
}