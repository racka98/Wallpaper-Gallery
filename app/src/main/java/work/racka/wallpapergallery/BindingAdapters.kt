package work.racka.wallpapergallery

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import work.racka.wallpapergallery.main.WallpaperGridAdapter
import work.racka.wallpapergallery.network.WallpaperProperty

@BindingAdapter("imageUrl")
fun bindImage(image: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(image.context)
            .load(imgUri)
            .transition(DrawableTransitionOptions.withCrossFade())
            /**.apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )**/
            .into(image)
    }
}

@BindingAdapter("wallpaperList")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<WallpaperProperty>?) {
    val adapter = recyclerView.adapter as WallpaperGridAdapter
    adapter.submitList(data)
}