package uk.ac.aber.dcs.cs31620.translate.ui.home

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import uk.ac.aber.dcs.cs31620.translate.R
import uk.ac.aber.dcs.cs31620.translate.databinding.FragmentHomeBinding
import java.io.File

// TODO: Make content fit screen better as it looks vague
// TODO: Make the spinners show country flags

private const val FOREIGN_TEXT_FILE = "ForeignTextFile.txt"
private const val NATIVE_TEXT_FILE = "NativeTextFile.txt"
private const val POSITION = "POSITION"

class HomeFragment : Fragment() {

    private lateinit var homeFragmentBinding: FragmentHomeBinding

    private val languagesArray by lazy { resources.getStringArray(R.array.ForeignLanguages) }

    private lateinit var settings: SharedPreferences
    private lateinit var foreignTextFile: File
    private lateinit var nativeTextFile: File
    private lateinit var foreignEditor: Spinner
    private lateinit var nativeEditor: Spinner


//    val sharedPreference:SharedPreference = SharedPreference(this.requireActivity())

//    private lateinit var mPreferences: SharedPreferences

//    override fun onCreate(savedInstanceState: Bundle?) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeFragmentBinding =
            FragmentHomeBinding.inflate(inflater, container, false)


        setupSpinner(view, homeFragmentBinding.nativeLanguageSpinner, R.array.NativeLanguages)
        setupSpinner(view, homeFragmentBinding.foreignLanguageSpinner, R.array.ForeignLanguages)

        foreignEditor = homeFragmentBinding.foreignLanguageSpinner
        nativeEditor = homeFragmentBinding.nativeLanguageSpinner

//        val repository = TranslateRepository(requireActivity().application)
//        val selectVocabulary = repository.getSelectVocabulary(1)

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
//        spinner.setSelection(1)
//        textFile = File(requireActivity().filesDir, TEXT_FILE)
//        readData()


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
//                var selectedLanguage = languagesArray.get(position)
                // String of the selected languages to be passed to vocabulary fragment
//                var nativeLanguage : String = homeFragmentBinding.nativeLanguageSpinner.selectedItem.toString()
//                var foreignLanguage : String = homeFragmentBinding.foreignLanguageSpinner.selectedItem.toString()

                val button = homeFragmentBinding.confirmButton


//                val currentPosition = editor.selectedItemPosition
                button.setOnClickListener {
////                    val default = position
//                    settings = requireActivity().getPreferences(MODE_PRIVATE)
////                    val spinnerPosition = settings.getInt(POSITION, default)
//
//                    if (currentPosition != -1) {
//                        with(settings.edit()) {
//                            putInt(POSITION, currentPosition)
//                            apply()
////                            editor.setSelection(currentPosition)
//                            spinner.setSelection(currentPosition)
//                            }
//                        }
                    val action = HomeFragmentDirections.actionNavigationHomeToNavigationVocabulary()
                    findNavController().navigate(action)
//                }
//                spinner.setSelection(currentPosition)
//                }

//                Toast.makeText(context, "Item $selectedLanguage selected", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}


    //    }
//        mPreferences = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
//        super.onCreate(savedInstanceState)
//    val preferencesEditor: SharedPreferences.Editor get() = mPreferences.edit()
