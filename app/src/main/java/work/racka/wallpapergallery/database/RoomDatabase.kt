package work.racka.wallpapergallery.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WallpaperDatabase::class], version = 1)
abstract class WallpapersDatabase : RoomDatabase() {
    abstract val wallpaperDao: WallpaperDao

    companion object {
        @Volatile
        private var INSTANCE: WallpapersDatabase? = null

        fun getDatabase(context: Context): WallpapersDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WallpapersDatabase::class.java,
                        "wallpapers_collection"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}