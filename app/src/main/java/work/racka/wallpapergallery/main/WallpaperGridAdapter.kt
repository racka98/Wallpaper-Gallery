package work.racka.wallpapergallery.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import work.racka.wallpapergallery.databinding.WallpaperGroupBinding
import work.racka.wallpapergallery.network.WallpaperProperty

class WallpaperGridAdapter(private val onClickListener: OnClickListener): ListAdapter<WallpaperProperty, WallpaperGridAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private var binding: WallpaperGroupBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(wallpaperProperty: WallpaperProperty) {
            binding.wallpaper = wallpaperProperty
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = WallpaperGroupBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<WallpaperProperty>() {
        override fun areItemsTheSame(oldItem: WallpaperProperty, newItem: WallpaperProperty): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: WallpaperProperty, newItem: WallpaperProperty): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wallpaperProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(wallpaperProperty)
        }
        holder.bind(wallpaperProperty)
    }

    class OnClickListener(val clickListener: (wallpaperProperty: WallpaperProperty) -> Unit) {
        fun onClick(wallpaperProperty: WallpaperProperty) = clickListener(wallpaperProperty)
    }
}