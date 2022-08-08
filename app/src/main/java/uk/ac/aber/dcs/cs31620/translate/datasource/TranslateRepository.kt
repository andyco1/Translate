package uk.ac.aber.dcs.cs31620.translate.datasource

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.translate.model.Vocabulary

class TranslateRepository (application: Application){
    private val vocabularyDao = TranslateRoomDatabase.getDatabase(application)!!.vocabularyDao()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun insert(vocabulary: Vocabulary){
        coroutineScope.launch {
            vocabularyDao.insertSingleVocabularyRow(vocabulary)
        }
    }

    fun insertMultipleRows(vocabularyList: List<Vocabulary>){
        coroutineScope.launch {
            vocabularyDao.insertMultipleVocabularyRows(vocabularyList)
        }
    }

    fun delete(vocabulary: Vocabulary) {
        coroutineScope.launch {
            vocabularyDao.deleteVocabularyRow(vocabulary)
        }
    }

    fun deleteAll() {
        coroutineScope.launch {
            vocabularyDao.deleteAll()
        }
    }

    fun getAllVocabulary() {
        vocabularyDao.getAllVocabulary()
    }

    fun getSelectVocabulary(id: Int) {
        vocabularyDao.getSelectVocabulary(id)
    }
// Remainder on next slide
}
