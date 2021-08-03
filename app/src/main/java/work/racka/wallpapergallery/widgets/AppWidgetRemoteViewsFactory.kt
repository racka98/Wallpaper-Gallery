package work.racka.wallpapergallery.widgets

import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import work.racka.wallpapergallery.R
import work.racka.wallpapergallery.database.WallpapersDatabase
import work.racka.wallpapergallery.repository.WallpaperRepository

class AppWidgetRemoteViewsFactory(val context: Context) :
    RemoteViewsService.RemoteViewsFactory {

    private val db: WallpapersDatabase = WallpapersDatabase.getDatabase(context)
    private val repo = WallpaperRepository(db)
    private val wallpapersList: ArrayList<String> =
        arrayListOf("This", "That", "You", "When", "Fun")

    //private val job = SupervisorJob()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    override fun onCreate() {
//        coroutineScope.launch {
//            wallpapersList = repo.getWidgetWallsList()
//        }
    }

    override fun onDataSetChanged() {
//        coroutineScope.launch {
//            wallpapersList = repo.getWidgetWallsList()
//        }
    }

    override fun onDestroy() {

    }

    override fun getCount(): Int {
        Log.d("RemoteView", "Wallpaper size : ${wallpapersList.size}")
        return wallpapersList.size
    }

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(context.packageName, R.layout.wallpaper_names_widget)
        rv.setTextViewText(android.R.id.text1, wallpapersList[position])
        return rv
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

}