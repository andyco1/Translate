package uk.ac.aber.dcs.cs31620.translate.ui.vocabulary

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.ac.aber.dcs.cs31620.translate.R
import uk.ac.aber.dcs.cs31620.translate.databinding.FragmentHomeBinding
import uk.ac.aber.dcs.cs31620.translate.databinding.FragmentVocabularyBinding

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

        return vocabularyFragmentBinding.root
    }

}