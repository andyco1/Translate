package uk.ac.aber.dcs.cs31620.translate.ui.home

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import uk.ac.aber.dcs.cs31620.translate.R
import uk.ac.aber.dcs.cs31620.translate.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var homeFragmentBinding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeFragmentBinding =
            FragmentHomeBinding.inflate(inflater, container, false)

        setupSpinner(view, homeFragmentBinding.nativeLanguageSpinner, R.array.NativeLanguages)
        setupSpinner(view, homeFragmentBinding.foreignLanguageSpinner, R.array.ForeignLanguages)

        

        return homeFragmentBinding.root
    }

}