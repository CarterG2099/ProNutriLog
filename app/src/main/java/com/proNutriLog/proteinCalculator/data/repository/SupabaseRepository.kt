import com.proNutriLog.proteinCalculator.data.model.User
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest

object SupabaseRepository {
    lateinit var supabase: SupabaseClient
    private const val SUPABASE_URL = "https://aexmdgmfkazcgkkaxfle.supabase.co"
    private const val SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFleG1kZ21ma2F6Y2dra2F4ZmxlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDI0MDA4NTEsImV4cCI6MjA1Nzk3Njg1MX0.3Ag8XGO0QxfrYM4ARfIS8cJM4aj5epCB_orwaQb-moY"  // Make sure the key is correct.

    // Initialize the Supabase client
    fun initializeSupabaseClient() {
        if (!::supabase.isInitialized) {
            supabase = createSupabaseClient(
                supabaseUrl = SUPABASE_URL,
                supabaseKey = SUPABASE_KEY
            ) {
                install(Auth)   // Install the Auth module
                install(Postgrest) // Install the Postgrest module (if needed)
            }
        }
    }

    // Login method (Sign-In)
    suspend fun login(username: String, pass: String): Any {
        return supabase.auth.signInWith(Email) {
            email = username
            password = pass
        }
    }

    // Register method (Sign-Up)
    suspend fun register(username: String, e: String, pass: String) {
        val user = supabase.auth.signUpWith(Email) {
            email = e
            password = pass
        }
        saveUsername(username)
    }

    // Optionally, store the username in a users table
    private suspend fun saveUsername(username: String) {
        val userTable = supabase.postgrest["users"]  // Adjust the table name if needed
        userTable.insert(mapOf("username" to username))
    }

    // Get Current User
//    fun getCurrentUser(): User? {
//        val currentUser = supabase.auth.currentUser
//        return currentUser?.let {
//            // Map Supabase user data to your local User model
//            User(email = it.email ?: "")
//        }
//    }

    // Sign Out method
    suspend fun signOut() {
        supabase.auth.signOut()
    }
}
