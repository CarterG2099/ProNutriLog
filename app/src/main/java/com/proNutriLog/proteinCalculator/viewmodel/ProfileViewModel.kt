import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proNutriLog.proteinCalculator.data.model.User
import io.github.jan.supabase.postgrest.postgrest

class ProfileViewModel : ViewModel() {

    private val userTable = SupabaseRepository.supabase.postgrest["users"]

    private val _userProfileLiveData = MutableLiveData<User>()
    val userProfileLiveData: LiveData<User> = _userProfileLiveData

    fun loadUserProfile() {
        // Fetch user profile data from a repository or local database
        _userProfileLiveData.value = User("John Doe", "johndoe@example.com", "john", "john@test.com", 25, 175, 70, "Vegetarian")
    }

    fun updateUserProfile(updatedProfile: User) {
        // Update the user profile data in your repository or local database
        _userProfileLiveData.value = updatedProfile
    }
}
