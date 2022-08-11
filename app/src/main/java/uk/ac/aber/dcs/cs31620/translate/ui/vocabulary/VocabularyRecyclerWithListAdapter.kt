package uk.ac.aber.dcs.cs31620.translate.ui.vocabulary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uk.ac.aber.dcs.cs31620.translate.databinding.VocabularyRowBinding
import uk.ac.aber.dcs.cs31620.translate.model.Vocabulary

//class VocabularyRecyclerWithListAdapter() :
//    RecyclerView.Adapter<VocabularyRecyclerWithListAdapter.ViewHolder>() {
//    private var vocabularyList = emptyList<Vocabulary>()
//    private lateinit var itemBinding: VocabularyRowBinding
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val itemBinding =
//            VocabularyRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//
//        return ViewHolder(itemBinding)
//        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.vocabulary_row, parent, false))
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val vocabularyRow: Vocabulary = vocabularyList[position]
//
//    }
//
//    override fun getItemCount(): Int = vocabularyList.size


//     class ViewHolder (private val itemBinding: VocabularyRowBinding) : RecyclerView.ViewHolder(itemBinding.root) {
//         fun bind(vocabulary: Vocabulary) {
//             itemBinding.vocabNative.text = vocabulary.nativeString
//             itemBinding.vocabForeign.text = vocabulary.foreignString
//         }
//     }

//    fun changeDataSet(vocabulary: List<Vocabulary>) {
//        this.vocabularyList = vocabulary
//        notifyDataSetChanged()
//    }
//}
//    override fun getItemCount(): Int {
//        return vocabularyList.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int,) {
//        val vocabularyItemBinding = VocabularyRowBinding.inflate(LayoutInflater.from(context), parent, false)
//
//        val currentRow = vocabularyList[position]
//        holder.itemView.
//    }

class VocabularyRecyclerWithListAdapter(
    private val context: Context?) :
    RecyclerView.Adapter<VocabularyRecyclerWithListAdapter.ViewHolder>() {

//    private var dataSet: MutableList<Vocabulary> = mutableListOf()
    private var dataSet: MutableList<Vocabulary> = mutableListOf()
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
        val vocabularyItemBinding = VocabularyRowBinding.inflate(LayoutInflater.from(context), parent, false)
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
        notifyDataSetChanged()
    }

}