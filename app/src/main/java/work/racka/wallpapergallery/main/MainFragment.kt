package work.racka.wallpapergallery.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.core.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
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
        binding.toolbar.inflateMenu(R.menu.main_menu)

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

        //Using System bar insets to fix custom toolbar overlapping to the status bar
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainFragmentContainer) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Apply the insets as padding to the view. Here the system is setting
            // only the bottom, left, and right dimensions, but apply whichever insets are
            // appropriate to your layout. You can also update the view padding
            // if that's more appropriate.
            view.updatePadding(
                top = insets.top
            )

            // Return CONSUMED if you don't want want the window insets to keep being
            // passed down to descendant views.
            WindowInsetsCompat.CONSUMED
        }


        return binding.root
    }


}