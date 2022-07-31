package uk.ac.aber.dcs.cs31620.translate.ui.practice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.ac.aber.dcs.cs31620.translate.R
import uk.ac.aber.dcs.cs31620.translate.databinding.FragmentHomeBinding
import uk.ac.aber.dcs.cs31620.translate.databinding.FragmentPracticeBinding

class PracticeFragment : Fragment() {

    private lateinit var practiceFragmentBinding: FragmentPracticeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        practiceFragmentBinding =
            FragmentPracticeBinding.inflate(inflater, container, false)

        return practiceFragmentBinding.root
    }

}

