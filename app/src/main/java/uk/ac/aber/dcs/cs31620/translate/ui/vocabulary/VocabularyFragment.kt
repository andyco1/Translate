package uk.ac.aber.dcs.cs31620.translate.ui.vocabulary

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import uk.ac.aber.dcs.cs31620.translate.R
import uk.ac.aber.dcs.cs31620.translate.databinding.FragmentHomeBinding
import uk.ac.aber.dcs.cs31620.translate.databinding.FragmentVocabularyBinding
import uk.ac.aber.dcs.cs31620.translate.model.VocabularyList

//private const val GRID_COLUMN_COUNT = 2

class VocabularyFragment : Fragment() {
    private lateinit var vocabularyFragmentBinding: FragmentVocabularyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        vocabularyFragmentBinding =
            FragmentVocabularyBinding.inflate(inflater, container, false)

        // Insert code here
        val listVocabulary = vocabularyFragmentBinding.vocabularyList
        listVocabulary.setHasFixedSize(true)

//        val gridLayoutManager = GridLayoutManager(context, GRID_COLUMN_COUNT)
//        listVocabulary.layoutManager = gridLayoutManager

        // Sets the layout of the recycler view
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        listVocabulary.layoutManager = linearLayoutManager

        val vocabulary = VocabularyList().vocabList

        val vocabularyRecyclerAdapter = VocabularyRecyclerWithListAdapter(context, vocabulary.toMutableList())
        listVocabulary.adapter = vocabularyRecyclerAdapter

        // TODO: Improve the on click listener as currently there is a bug which toasts both text
        //  views instead of separate

        vocabularyRecyclerAdapter.clickListener = View.OnClickListener { v ->
            val nativeVocabTextView: TextView = v.findViewById(R.id.vocabNative)
            Toast.makeText(
                context, "${nativeVocabTextView.text} row was clicked",
                Toast.LENGTH_SHORT).show()

            val foreignVocabTextView: TextView = v.findViewById(R.id.vocabForeign)
            Toast.makeText(
                context, "${foreignVocabTextView.text} row was clicked",
                Toast.LENGTH_SHORT).show()
        }

        val fab = vocabularyFragmentBinding.fabAdd
        fab.setOnClickListener {
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