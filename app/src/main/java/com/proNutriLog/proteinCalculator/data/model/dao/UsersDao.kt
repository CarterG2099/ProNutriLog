import com.proNutriLog.proteinCalculator.data.model.User
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.from

class UsersDao {

    suspend fun insertUser(user: User) {
        try {
            SupabaseRepository.supabase.postgrest["users"].insert(user)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getUser(userId: String?): User {
        // If userId is null, get the current user ID from SupabaseRepository
        val id = userId ?: SupabaseRepository.getCurrentUser()

        if (id == null) {
            return User("null", "null", "null") // Return null if no userId is available
        }

        return try {
            val response = SupabaseRepository.getUserById(id)
            println("Response: $response")
            val user = response.decodeList<User>().firstOrNull()
            println("UsersDAO user: $user")
            user ?: User("unknown", "unknown", "unknown") // Return null if user is not found
        } catch (e: Exception) {
            e.printStackTrace()
            User("error", "error", "error") // Return null if something goes wrong
        }
    }

    suspend fun updateUser(user: User) {
        try {
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
