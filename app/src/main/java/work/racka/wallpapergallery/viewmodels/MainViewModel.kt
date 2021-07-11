package work.racka.wallpapergallery.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import work.racka.wallpapergallery.database.WallpapersDatabase
import work.racka.wallpapergallery.domain.Wallpaper
import work.racka.wallpapergallery.repository.WallpaperRepository

enum class WallpaperApiStatus {
    LOADING,
    ERROR,
    DONE
}

const val TAG = "MainViewModel"

class MainViewModel(app: Application) : AndroidViewModel(app) {

    //Network status from the repository
    //Used to show status of the network call to the user in MainFragment
    private val _status = MutableLiveData<WallpaperApiStatus?>()
    val status: LiveData<WallpaperApiStatus?> = _status

    private val _navigateToDetailsFragment = MutableLiveData<Wallpaper?>()
    val navigateToDetailsFragment: LiveData<Wallpaper?>
        get() = _navigateToDetailsFragment

    //Get an instance of the database
    val database = WallpapersDatabase.getDatabase(app)

    //Retrieve the repository to get the list of wallpapers
    private val wallpaperRepository = WallpaperRepository(database)

    //Query LiveData to be set in the fragment and update the query
    val searchQuery = MutableLiveData("")

    //
    private val updatedWallpaperList = searchQuery.switchMap {
        val sqlQuery = "%$it%"
        wallpaperRepository.searchDatabase(sqlQuery)
    }

    //Get videos Live data from the repository and assign to a wallpaper list
    val wallpaperList = updatedWallpaperList

    init {
        refreshWallpapersDatabase()
    }

    private fun refreshWallpapersDatabase() {
        viewModelScope.launch {
            try {
                _status.value = WallpaperApiStatus.LOADING
                wallpaperRepository.refreshWallpapers()
                _status.value = WallpaperApiStatus.DONE
            } catch (e: Exception) {
                _status.value = WallpaperApiStatus.ERROR
                Log.e("WallpaperRepository", "Possibly no internet connection", e)
            }
        }
    }

    fun displayWallpaperDetails(wallpaper: Wallpaper) {
        _navigateToDetailsFragment.value = wallpaper
        Log.i(TAG, "Displayed wallpapers")
    }

    fun displayWallpaperDetailsCompleted() {
        _navigateToDetailsFragment.value = null
    }

    fun statusCheckCompleted() {
        _status.value = null
    }
}
