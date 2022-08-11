package uk.ac.aber.dcs.cs31620.translate.ui.vocabulary

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
import androidx.navigation.fragment.findNavController
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

//    private lateinit var vocabularyRecyclerAdapter: VocabularyRecyclerWithListAdapter
//
//    private var oldVocabularyList: LiveData<List<Vocabulary>>? = null

//    private var vocabularyViewModel: TranslateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        vocabularyFragmentBinding =
            FragmentVocabularyBinding.inflate(inflater, container, false)


        // Insert code here
//        val listVocabulary = vocabularyFragmentBinding.vocabularyList
//        listVocabulary.setHasFixedSize(true)

        // Sets the layout of the recycler view

//        val adapter = VocabularyRecyclerWithListAdapter()
//        val recyclerView = vocabularyFragmentBinding.vocabularyList
//        recyclerView.adapter
//        val linearLayoutManager = LinearLayoutManager(requireContext())
//        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        val listVocabulary = vocabularyFragmentBinding.vocabularyList

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        listVocabulary.layoutManager = linearLayoutManager

        vocabularyRecyclerWithListAdapter = VocabularyRecyclerWithListAdapter(context)
        listVocabulary.adapter = vocabularyRecyclerWithListAdapter

//        vocabularyRecyclerWithListAdapter.changeDataSet()

//        val adapter = VocabularyRecyclerWithListAdapter(context)
//        val recyclerView = vocabularyFragmentBinding.vocabularyList
//        recyclerView.adapter
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        val linearLayoutManager = LinearLayoutManager.VERTICAL



        translateViewModel = ViewModelProvider(this).get(TranslateViewModel::class.java)
        translateViewModel.readAllVocabulary.observe(viewLifecycleOwner, Observer { vocabulary ->
            vocabularyRecyclerWithListAdapter.changeDataSet(vocabulary.toMutableList())
        })



//        listVocabulary.layoutManager = linearLayoutManager

//        val vocabList = vocabularyViewModel.getVocabulary()

//        vocabularyRecyclerAdapter = VocabularyRecyclerWithListAdapter(context)
//        listVocabulary.adapter = vocabularyRecyclerAdapter

//        vocabularyViewModel = ViewModelProvider(this).get(TranslateViewModel::class.java)

//        if (oldVocabularyList != vocabList) {
//            oldVocabularyList?.removeObservers(viewLifecycleOwner)
//            oldVocabularyList = vocabList
//        }
//
//        if (!vocabList.hasObservers()) {
//            vocabList.observe(viewLifecycleOwner) { vocab ->
//                vocabularyRecyclerAdapter.changeDataSet(vocab.toMutableList())
//            }
//        }




//        val vocabulary = VocabularyList().vocabList

//        val repository = TranslateRepository(requireActivity().application)
//        val vocabulary = TranslateViewModel(application = )
//        println(vocabulary)

//        val vocabularyRecyclerAdapter = VocabularyRecyclerWithListAdapter(context, vocabulary.toMutableList())
//        listVocabulary.adapter = vocabularyRecyclerAdapter

        // TODO: Improve the on click listener as currently there is a bug which toasts both text
        //  views instead of separate

//        vocabularyRecyclerAdapter.clickListener = View.OnClickListener { v ->
//            val nativeVocabTextView: TextView = v.findViewById(R.id.vocabNative)
//            Toast.makeText(
//                context, "${nativeVocabTextView.text} row was clicked",
//                Toast.LENGTH_SHORT).show()
//
//            val foreignVocabTextView: TextView = v.findViewById(R.id.vocabForeign)
//            Toast.makeText(
//                context, "${foreignVocabTextView.text} row was clicked",
//                Toast.LENGTH_SHORT).show()
//        }

        val fab = vocabularyFragmentBinding.fabAdd
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_vocabulary_to_addVocabularyFragment)
            val snackbar = Snackbar.make(it, "Add new vocabulary FAB", Snackbar.LENGTH_LONG)

            val bnv = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav_view)

            snackbar.anchorView = bnv

            snackbar.setAction("Undo") {

            }
            snackbar.show()
        }


        return vocabularyFragmentBinding.root
    }

}