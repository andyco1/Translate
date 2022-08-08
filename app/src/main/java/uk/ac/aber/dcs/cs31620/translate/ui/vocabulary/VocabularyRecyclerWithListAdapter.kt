package uk.ac.aber.dcs.cs31620.translate.ui.vocabulary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uk.ac.aber.dcs.cs31620.translate.databinding.VocabularyRowBinding
import uk.ac.aber.dcs.cs31620.translate.model.Vocabulary

class VocabularyRecyclerWithListAdapter(
    private val context: Context?,
    private var dataSet: MutableList<Vocabulary>
) :
    RecyclerView.Adapter<VocabularyRecyclerWithListAdapter.ViewHolder>() {

    var clickListener: View.OnClickListener? = null

    inner class ViewHolder(
        itemView: View,
        val nativeVocabView: TextView,
        val foreignVocabView: TextView
    ) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener(clickListener)
        }

        fun bindDataSet(vocabulary: Vocabulary) {
            nativeVocabView.text = vocabulary.nativeString
            foreignVocabView.text = vocabulary.foreignString
        }
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VocabularyRecyclerWithListAdapter.ViewHolder {
        val vocabularyItemBinding =
            VocabularyRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(
            vocabularyItemBinding.vocabCard,
            vocabularyItemBinding.vocabNative,
            vocabularyItemBinding.vocabForeign
        )
    }

    override fun onBindViewHolder(holder: VocabularyRecyclerWithListAdapter.ViewHolder, position: Int) {
        holder.bindDataSet(dataSet[position])
    }

    fun changeDataSet(dataSet: MutableList<Vocabulary>){
        this.dataSet = dataSet
        this.notifyDataSetChanged()
    }

}