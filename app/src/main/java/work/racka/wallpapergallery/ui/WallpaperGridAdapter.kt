package work.racka.wallpapergallery.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import work.racka.wallpapergallery.databinding.WallpaperGroupBinding
import work.racka.wallpapergallery.domain.Wallpaper

class WallpaperGridAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Wallpaper, WallpaperGridAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private var binding: WallpaperGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(wallpaper: Wallpaper) {
            binding.wallpaper = wallpaper
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

    companion object DiffCallback : DiffUtil.ItemCallback<Wallpaper>() {
        override fun areItemsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wallpaper = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(wallpaper)
        }
        holder.bind(wallpaper)
    }

    class OnClickListener(val clickListener: (wallpaper: Wallpaper) -> Unit) {
        fun onClick(wallpaper: Wallpaper) = clickListener(wallpaper)
    }
}