package work.racka.wallpapergallery.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import work.racka.wallpapergallery.R
import work.racka.wallpapergallery.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        val binding = MainFragmentBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the photosGrid RecyclerView
        //Add click listener to this adapter
        //binding.wallpaperGrid.adapter = WallpaperGridAdapter()
        binding.wallpaperGrid.adapter = WallpaperGridAdapter(
            WallpaperGridAdapter.OnClickListener { wallpaperProperty ->
                viewModel.displayWallpaperDetails(wallpaperProperty)
            }
        )

        //Observe navigateToDetailsFragment from MainViewModel to trigger navigation
        viewModel.navigateToDetailsFragment.observe(viewLifecycleOwner, Observer { wallpaperProperty ->
            wallpaperProperty?.let {
                this.findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToDetailsFragment(it))
                viewModel.displayWallpaperDetailsCompleted()
            }
        })

        return binding.root
    }


}