package work.racka.wallpapergallery

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import work.racka.wallpapergallery.main.WallpaperApiStatus
import work.racka.wallpapergallery.main.WallpaperGridAdapter
import work.racka.wallpapergallery.network.WallpaperProperty

private const val TAG = "BindingAdapters"

@BindingAdapter("imageUrl")
fun bindImage(image: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(image.context)
            .load(imgUri)
            //.transition(DrawableTransitionOptions.withCrossFade())
            .apply(
            RequestOptions()
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image))
            .into(image)
        Log.i(TAG, "Glide accessed")
    }
}

@BindingAdapter("wallpaperList")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<WallpaperProperty>?) {
    val adapter = recyclerView.adapter as WallpaperGridAdapter
    adapter.submitList(data)
}

//Binding adapter for imageView to show No Internet icon when there's no internet
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

//Binding adapter for ProgressBar when loading images
@BindingAdapter("progressVisibility")
fun bindProgressBar(progressBar: ProgressBar, status: WallpaperApiStatus?) {
    when (status) {
        WallpaperApiStatus.LOADING -> progressBar.visibility = ProgressBar.VISIBLE
        WallpaperApiStatus.DONE -> progressBar.visibility = ProgressBar.GONE
        else -> progressBar.visibility = ProgressBar.GONE
    }
}
