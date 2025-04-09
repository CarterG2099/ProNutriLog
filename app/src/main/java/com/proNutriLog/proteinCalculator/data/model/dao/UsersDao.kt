// UsersDao.kt
package com.proNutriLog.proteinCalculator.data

import com.proNutriLog.proteinCalculator.data.model.User
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns


class UsersDao() {

    suspend fun insertUser(user: User) {
        SupabaseRepository.supabase.postgrest["users"].insert(user)
    }

    suspend fun getUser(userId: String): User? {
        val result = SupabaseRepository.supabase.postgrest.from("users")
            .select {
                filter {
                    eq("user_id", userId)
                }
                single()
            }

        val data = result.data as? Map<String, Any?> ?: return null
        println("getUser: $data")
        return User(
            first_name = data["first_name"] as? String ?: "",
            last_name = data["last_name"] as? String ?: ""
        )
    }
}
