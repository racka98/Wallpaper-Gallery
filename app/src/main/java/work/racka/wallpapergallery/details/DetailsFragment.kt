package work.racka.wallpapergallery.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import work.racka.wallpapergallery.R
import work.racka.wallpapergallery.databinding.DetailsFragmentBinding

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = DetailsFragmentBinding.inflate(inflater)

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