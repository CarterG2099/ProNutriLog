import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proNutriLog.proteinCalculator.data.model.User
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ProfileViewModel : ViewModel() {

    private val usersDao = UsersDao()
    private val _userProfileLiveData = MutableLiveData<User>()
    val userProfileLiveData: LiveData<User> = _userProfileLiveData

    fun loadUserProfile() {
        viewModelScope.launch {
            try {
                var user = usersDao.getUser(null)
                _userProfileLiveData.value = user ?: User() // Handle null case
            } catch (e: Exception) {
                e.printStackTrace()
                // Optionally set error state
            }
        }
    }

    fun updateUserProfile(updatedProfile: User) {
        viewModelScope.launch {
            try {
                usersDao.updateUser(updatedProfile)
//                val userId = SupabaseRepository.supabase.auth.currentUserOrNull()?.id
//                if (userId != null) {
//                    SupabaseRepository.supabase
//                        .postgrest["users"]
//                        .update(updatedProfile) {
//                            filter { eq("id", userId) }
//                        }
//                    _userProfileLiveData.value = updatedProfile
//                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
