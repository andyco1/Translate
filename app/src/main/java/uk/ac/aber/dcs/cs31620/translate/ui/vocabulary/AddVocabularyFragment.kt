package uk.ac.aber.dcs.cs31620.translate.ui.vocabulary

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uk.ac.aber.dcs.cs31620.translate.R
import uk.ac.aber.dcs.cs31620.translate.databinding.FragmentAddVocabularyBinding
import uk.ac.aber.dcs.cs31620.translate.databinding.FragmentVocabularyBinding
import uk.ac.aber.dcs.cs31620.translate.model.TranslateViewModel
import uk.ac.aber.dcs.cs31620.translate.model.Vocabulary

class AddVocabularyFragment : Fragment() {
    private lateinit var addFragmentBinding: FragmentAddVocabularyBinding
    private lateinit var translateViewModel: TranslateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        addFragmentBinding =
            FragmentAddVocabularyBinding.inflate(inflater, container, false)

        translateViewModel = ViewModelProvider(this).get(TranslateViewModel::class.java)

        addFragmentBinding.addButton.setOnClickListener {
            insertDataToDatabase()
        }

        return addFragmentBinding.root
    }

    private fun insertDataToDatabase() {
        val nativeVocabulary = addFragmentBinding.WordTextView.text.toString()
        val foreignVocabulary = addFragmentBinding.TranslationTextView.text.toString()

        if(inputCheck(nativeVocabulary, foreignVocabulary)) {
            // Create Vocabulary object
            val vocabulary = Vocabulary(0, nativeVocabulary, foreignVocabulary)
            // Add data to the Database
            translateViewModel.addVocabulary(vocabulary)
            Toast.makeText(requireContext(), "Vocabulary added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addVocabularyFragment_to_navigation_vocabulary)

        }else{
            Toast.makeText(requireContext(), "ERROR: Please complete all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(nativeVocabulary: String, foreignVocabulary: String): Boolean{
        return !(TextUtils.isEmpty(nativeVocabulary) && TextUtils.isEmpty(foreignVocabulary))
    }


}