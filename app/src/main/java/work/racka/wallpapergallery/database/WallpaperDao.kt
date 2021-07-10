package work.racka.wallpapergallery.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


//Dao writing and reading to the room database
@Dao
interface WallpaperDao {
    //Get all the wallpapers
    @Query("SELECT * FROM wallpapers_collection")
    fun getAllWallpapers(): LiveData<List<WallpaperDatabase>>

    //Insert network data into the database upon update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllWallpapers(vararg wallpapers: WallpaperDatabase)

}