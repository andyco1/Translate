package uk.ac.aber.dcs.cs31620.translate.ui.home

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uk.ac.aber.dcs.cs31620.translate.R
import uk.ac.aber.dcs.cs31620.translate.databinding.FragmentHomeBinding
import uk.ac.aber.dcs.cs31620.translate.model.TranslateViewModel
import java.io.File

private const val FOREIGN_TEXT_FILE = "ForeignTextFile.txt"
private const val NATIVE_TEXT_FILE = "NativeTextFile.txt"
private const val POSITION = "POSITION"

class HomeFragment : Fragment() {

    private lateinit var homeFragmentBinding: FragmentHomeBinding

    private lateinit var translateViewModel: TranslateViewModel

    private lateinit var settings: SharedPreferences
    private lateinit var foreignTextFile: File
    private lateinit var nativeTextFile: File
    private lateinit var foreignEditor: Spinner
    private lateinit var nativeEditor: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeFragmentBinding =
            FragmentHomeBinding.inflate(inflater, container, false)

        translateViewModel = ViewModelProvider(this).get(TranslateViewModel::class.java)

        setupSpinner(view, homeFragmentBinding.nativeLanguageSpinner, R.array.NativeLanguages)
        setupSpinner(view, homeFragmentBinding.foreignLanguageSpinner, R.array.ForeignLanguages)

        foreignEditor = homeFragmentBinding.foreignLanguageSpinner
        nativeEditor = homeFragmentBinding.nativeLanguageSpinner

        return homeFragmentBinding.root

    }

    override fun onStart() {
        super.onStart()

        foreignTextFile = File(requireActivity().filesDir, FOREIGN_TEXT_FILE)
        nativeTextFile = File(requireActivity().filesDir, NATIVE_TEXT_FILE)
        readData()
        settings = requireActivity().getPreferences(MODE_PRIVATE)

        val foreignDefault = homeFragmentBinding.foreignLanguageSpinner.id
        val foreignSpinnerPosition = settings.getInt(POSITION, foreignDefault)
        homeFragmentBinding.foreignLanguageSpinner.id = foreignSpinnerPosition

        if (foreignSpinnerPosition != -1) {
            with(settings.edit()) {
                putInt(POSITION, foreignSpinnerPosition)
                apply()
                foreignEditor.id = foreignSpinnerPosition
            }
        }

        val nativeDefault = homeFragmentBinding.nativeLanguageSpinner.id
        val nativeSpinnerPosition = settings.getInt(POSITION, nativeDefault)
        homeFragmentBinding.nativeLanguageSpinner.id = nativeSpinnerPosition

        if (nativeSpinnerPosition != -1) {
            with(settings.edit()) {
                putInt(POSITION, nativeSpinnerPosition)
                apply()
                nativeEditor.id = nativeSpinnerPosition
            }
        }

    }

    override fun onPause() {
        super.onPause()
        foreignTextFile.writeText(foreignEditor.selectedItemId.toString())
        nativeTextFile.writeText(nativeEditor.selectedItemId.toString())
    }

    private fun readData() {
        if (foreignTextFile.exists()) {
            foreignEditor.setSelection(foreignTextFile.readText().toInt())
        }

        if (nativeTextFile.exists()) {
            nativeEditor.setSelection(nativeTextFile.readText().toInt())
        }
    }

    private fun setupSpinner(view: View?, spinner: Spinner, arrayResourceId: Int) {

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

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                val button = homeFragmentBinding.confirmButton


                button.setOnClickListener {

                    val action = HomeFragmentDirections.actionNavigationHomeToNavigationVocabulary()
                    translateViewModel.deleteAllVocabulary() // When the user confirms language configuration, the view model is called to delete the vocabulary list
                    Toast.makeText(requireContext(), "Configuration updated successfully.", Toast.LENGTH_LONG).show()
                    findNavController().navigate(action)
                }
            }
        }
    }
}

