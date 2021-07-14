package work.racka.wallpapergallery.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import work.racka.wallpapergallery.databinding.MainFragmentBinding
import work.racka.wallpapergallery.util.onQueryTextChanged
import work.racka.wallpapergallery.viewmodels.MainViewModel
import work.racka.wallpapergallery.viewmodels.MainViewModelFactory
import work.racka.wallpapergallery.viewmodels.WallpaperApiStatus

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        val application = requireNotNull(activity).application
        val viewModelFactory = MainViewModelFactory(application)
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = MainFragmentBinding.inflate(inflater)
        //binding.toolbar.inflateMenu(R.menu.main_menu)


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
        viewModel.navigateToDetailsFragment.observe(viewLifecycleOwner) { wallpaperProperty ->
            wallpaperProperty?.let {
                this.findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToDetailsFragment(it)
                )
                viewModel.displayWallpaperDetailsCompleted()
            }
        }

        //Observe the network call status and show a snackbar for the appropriate status
        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
            val responseText: String = when (status) {
                WallpaperApiStatus.LOADING -> "Fetching Wallpapers..."
                WallpaperApiStatus.ERROR -> "No Internet Connection!"
                else -> "Latest wallpapers fetched!"
            }
            //Making the snackbar
            status?.let {
                val snackbar = Snackbar.make(
                    binding.root,
                    responseText,
                    Snackbar.LENGTH_SHORT
                )
                snackbar.apply {
                    animationMode = Snackbar.ANIMATION_MODE_SLIDE
                    show()
                }
                viewModel.statusCheckCompleted()
                Log.i("MainFragment", "Snackbar called")
            }
        })

        binding.searchView.onQueryTextChanged {
            Log.i("MainFragment", "onQueryTextChanged accessed")
            viewModel.searchQuery.value = it
        }

        //Using System bar insets to fix custom toolbar overlapping to the status bar
        ViewCompat.setOnApplyWindowInsetsListener(binding.appBarItems) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Apply the insets as a margin to the view. Here the system is setting
            // only the bottom, left, and right dimensions, but apply whichever insets are
            // appropriate to your layout. You can also update the view padding
            // if that's more appropriate.
            view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                updateMargins(top = insets.top)
            }

            // Return CONSUMED if you don't want want the window insets to keep being
            // passed down to descendant views.
            WindowInsetsCompat.CONSUMED
        }

        //setHasOptionsMenu(true)
        return binding.root
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.main_menu, menu)
////        val search = menu.findItem(R.id.action_search)
////        val searchView = search.actionView as SearchView
////        Log.i("MainFragment", "onCreateOptionMenu called")
////        //searchView.isSubmitButtonEnabled = true
////        searchView.onQueryTextChanged {
////            Log.i("MainFragment", "onQueryTextChanged accessed")
////            viewModel.searchQuery.value = it
////        }
//        super.onCreateOptionsMenu(menu, inflater)
//    }

//    override fun onQueryTextSubmit(query: String?): Boolean {
//        query?.let {
//            search(query)
//        }
//        Log.i("MainFragment", "onQueryTextChange")
//        return true
//    }
//
//    override fun onQueryTextChange(newText: String?): Boolean {
//        newText?.let {
//            search(newText)
//        }
//        Log.i("MainFragment", "onQueryTextChange")
//        return true
//    }
//    fun search(query: String?) {
//        viewModel.searchDatabase(query).observe(viewLifecycleOwner, Observer {
//            it?.let {
//                wallpaperGridAdapter.submitList(it)
//
//            }
//        })
//        Log.i("MainFragment", "SearchQuery called")
//    }
}
