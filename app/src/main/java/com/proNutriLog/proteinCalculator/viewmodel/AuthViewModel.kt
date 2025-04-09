import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var isLoggedIn by mutableStateOf(false)

    // Existing login method
    fun login(email: String, password: String) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                SupabaseRepository.login(email, password)
                isLoggedIn = true
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }

    // Existing register method
    fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                SupabaseRepository.register(username, email, password)
                isLoggedIn = true // or set based on confirmation
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }

    // Sign out method
    fun signOut() {
        viewModelScope.launch {
            try {
                SupabaseRepository.signOut()
                isLoggedIn = false
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }

    // Google Sign-In method
    fun googleSignIn() {
        viewModelScope.launch {
            try {
                // Perform Google sign-in logic
                val result = SupabaseRepository.googleSignIn()
                if (result) {
                    isLoggedIn = true
                } else {
                    errorMessage = "Google Sign-In failed"
                }
            } catch (e: Exception) {
                errorMessage = e.message ?: "Google Sign-In error"
            }
        }
    }
}
