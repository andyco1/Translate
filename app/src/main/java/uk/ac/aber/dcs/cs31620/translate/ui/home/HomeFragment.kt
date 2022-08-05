package uk.ac.aber.dcs.cs31620.translate.ui.home

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import uk.ac.aber.dcs.cs31620.translate.R
import uk.ac.aber.dcs.cs31620.translate.databinding.FragmentHomeBinding

// TODO: Make content fit screen better as it looks vague
// TODO: Make the spinners show country flags

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

    private fun setupSpinner(view: View?, spinner: Spinner, arrayResourceId: Int) {
        spinner.setSelection(1)

        val adapter =
            ArrayAdapter.createFromResource(
                requireContext(),
                arrayResourceId,
                android.R.layout.simple_spinner_item
            )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // We don't need this but we have to provide
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // String of the selected languages to be passed to vocabulary fragment
                var nativeLanguage : String = homeFragmentBinding.nativeLanguageSpinner.selectedItem.toString()
                var foreignLanguage : String = homeFragmentBinding.foreignLanguageSpinner.selectedItem.toString()

                Toast.makeText(context, "Item $id selected", Toast.LENGTH_SHORT).show()
            }
        }
    }
}