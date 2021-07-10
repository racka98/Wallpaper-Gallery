package work.racka.wallpapergallery.util

import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import work.racka.wallpapergallery.R
import work.racka.wallpapergallery.domain.Wallpaper
import work.racka.wallpapergallery.ui.WallpaperGridAdapter
import work.racka.wallpapergallery.viewmodels.WallpaperApiStatus

private const val TAG = "BindingAdapters"

@BindingAdapter("imageUrl")
fun bindImage(image: ImageView, imgUrl: String?) {
    val drawable = CircularProgressDrawable(image.context)
    drawable.apply {
        centerRadius = 50f
        strokeWidth = 15f
        setColorSchemeColors(R.color.teal_200)
        start()
    }
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(image.context)
            .load(imgUri)
            //.transition(DrawableTransitionOptions.withCrossFade())
            .apply(
            RequestOptions()
            .placeholder(drawable)
            .error(R.drawable.ic_broken_image))
            .into(image)
        Log.i(TAG, "Glide accessed")
    }
}

@BindingAdapter("wallpaperList")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Wallpaper>?) {
    val adapter = recyclerView.adapter as WallpaperGridAdapter
    adapter.submitList(data)
}

/**
//Binding adapter for imageView to show No Internet icon when there's no internet
//Use Snackbar instead of this inside the MainFragment
@BindingAdapter("networkIcon")
fun bindNetworkIcon(networkImageView: ImageView, status: WallpaperApiStatus?) {
when (status) {
WallpaperApiStatus.ERROR -> {
networkImageView.visibility = View.VISIBLE
networkImageView.setImageResource(R.drawable.ic_connection_error)
}
else -> networkImageView.visibility = View.GONE
}
}
 */

//Binding adapter for ProgressBar when loading images
@BindingAdapter("progressVisibility")
fun bindProgressBar(progressBar: ProgressBar, status: WallpaperApiStatus?) {
    when (status) {
        WallpaperApiStatus.LOADING -> progressBar.visibility = ProgressBar.VISIBLE
        WallpaperApiStatus.DONE -> progressBar.visibility = ProgressBar.GONE
        else -> progressBar.visibility = ProgressBar.GONE
    }
}
