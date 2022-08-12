package uk.ac.aber.dcs.cs31620.translate.ui.vocabulary

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import uk.ac.aber.dcs.cs31620.translate.R
import uk.ac.aber.dcs.cs31620.translate.databinding.FragmentVocabularyBinding
import uk.ac.aber.dcs.cs31620.translate.datasource.TranslateRepository
import uk.ac.aber.dcs.cs31620.translate.model.TranslateViewModel
import uk.ac.aber.dcs.cs31620.translate.model.Vocabulary

//private const val GRID_COLUMN_COUNT = 2

class VocabularyFragment : Fragment() {

    private lateinit var vocabularyFragmentBinding: FragmentVocabularyBinding
    private lateinit var vocabularyRecyclerWithListAdapter: VocabularyRecyclerWithListAdapter
    private lateinit var translateViewModel: TranslateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        vocabularyFragmentBinding =
            FragmentVocabularyBinding.inflate(inflater, container, false)

        val listVocabulary = vocabularyFragmentBinding.vocabularyList

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        listVocabulary.layoutManager = linearLayoutManager

        vocabularyRecyclerWithListAdapter = VocabularyRecyclerWithListAdapter(context)
        listVocabulary.adapter = vocabularyRecyclerWithListAdapter

        translateViewModel = ViewModelProvider(this).get(TranslateViewModel::class.java)
        translateViewModel.readAllVocabulary.observe(viewLifecycleOwner, Observer { vocabulary ->
            vocabularyRecyclerWithListAdapter.changeDataSet(vocabulary.toMutableList())
        })

        val fab = vocabularyFragmentBinding.fabAdd
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_vocabulary_to_addVocabularyFragment)
            val snackbar = Snackbar.make(it, "Add new vocabulary", Snackbar.LENGTH_LONG)

            val bnv = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav_view)

            snackbar.anchorView = bnv

            snackbar.setAction("Undo") {

            }
            snackbar.show()
        }

        return vocabularyFragmentBinding.root
    }

}