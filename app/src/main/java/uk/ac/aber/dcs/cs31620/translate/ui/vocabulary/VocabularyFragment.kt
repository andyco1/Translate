package uk.ac.aber.dcs.cs31620.translate.ui.vocabulary

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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

//        vocabularyRecyclerAdapter.clickListener = View.OnClickListener { v ->
//            val nameView: TextView = v.findViewById(R.id.catNameTextView)
//            Toast.makeText(context, "Cat ${nameView.text} clicked",
//                Toast.LENGTH_SHORT).show()

        return vocabularyFragmentBinding.root
    }

}