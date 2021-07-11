package work.racka.wallpapergallery.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import work.racka.wallpapergallery.database.WallpapersDatabase
import work.racka.wallpapergallery.database.asDomainModel
import work.racka.wallpapergallery.domain.Wallpaper
import work.racka.wallpapergallery.network.WallpaperApi
import work.racka.wallpapergallery.network.asDatabaseModel

class WallpaperRepository(val database: WallpapersDatabase) {
    //A collection of wallpapers that can be shown on the screen
    val wallpapers: LiveData<List<Wallpaper>> =
        Transformations.map(database.wallpaperDao.getAllWallpapers()) {
            it.asDomainModel()
        }

    /**
     * Refresh wallpapers stored in offline cache
     * Using the IO dispatcher for Disk IO activities
     * This makes the function safe to call from the Main thread and not block it
     **/
    suspend fun refreshWallpapers() {
        val wallpaperList = WallpaperApi.wallpaperService.getWallpapers()
        withContext(Dispatchers.IO) {
            database.wallpaperDao.insertAllWallpapers(*wallpaperList.asDatabaseModel())
        }
    }

    fun searchDatabase(query: String): LiveData<List<Wallpaper>> {
        return Transformations
            .map(database.wallpaperDao.searchDatabase(query)) {
                it.asDomainModel()
            }
    }
}