import android.content.Context
import android.preference.PreferenceManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

class ThemePreferenceManager(context: Context) {

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    companion object {
        private const val THEME_PREFERENCE_KEY = "theme_preference"
        private const val DEFAULT_THEME = "system"  // Default is system theme
    }

    // Save the theme preference
    fun saveThemePreference(theme: String) {
        sharedPreferences.edit().putString(THEME_PREFERENCE_KEY, theme).apply()
    }

    // Get the theme preference
    fun getThemePreference(): String {
        return sharedPreferences.getString(THEME_PREFERENCE_KEY, DEFAULT_THEME) ?: DEFAULT_THEME
    }
}
