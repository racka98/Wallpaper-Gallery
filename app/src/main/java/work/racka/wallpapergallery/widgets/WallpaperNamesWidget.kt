package work.racka.wallpapergallery.widgets

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import work.racka.wallpapergallery.R

/**
 * Implementation of App Widget functionality.
 */
class WallpaperNamesWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = "WALLPAPER NAMES"
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.wallpaper_names_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)
    views.setRemoteAdapter(
        R.id.wallpaper_name_list,
        Intent(context, AppWidgetRemoteViewsService::class.java)
    )

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}