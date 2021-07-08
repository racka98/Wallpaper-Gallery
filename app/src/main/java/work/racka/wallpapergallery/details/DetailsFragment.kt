package work.racka.wallpapergallery.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import work.racka.wallpapergallery.R
import work.racka.wallpapergallery.databinding.DetailsFragmentBinding

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = DetailsFragmentBinding.inflate(inflater)

        //Using navigation bar insets to prevent content being cut off at the navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(binding.detailsContainer) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.navigationBars())
            // Apply the insets as margin to the view. Here the system is setting
            // only the bottom, left, and right dimensions, but apply whichever insets are
            // appropriate to your layout. You can also update the view padding
            // if that's more appropriate.
            view.updatePadding(bottom= insets.bottom)

            // Return CONSUMED if you don't want want the window insets to keep being
            // passed down to descendant views.
            WindowInsetsCompat.CONSUMED
        }

        //set the lifecycle owner to this fragment
        binding.lifecycleOwner = this

        //Arguments from bundle
        val wallpaperProperty = DetailsFragmentArgs.fromBundle(requireArguments()).wallpaperProperty

        val viewModelFactory = DetailsViewModelFactory(wallpaperProperty, application)

        binding.viewModel = ViewModelProvider(
            this,
            viewModelFactory).get(DetailsViewModel::class.java)

        //Always return root view
        return binding.root
    }

}