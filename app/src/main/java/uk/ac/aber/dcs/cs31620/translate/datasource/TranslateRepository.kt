package uk.ac.aber.dcs.cs31620.translate.datasource

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.translate.model.Vocabulary
import uk.ac.aber.dcs.cs31620.translate.model.VocabularyDao

class TranslateRepository (private val vocabularyDao: VocabularyDao){

    val getAllVocabulary: LiveData<List<Vocabulary>> = vocabularyDao.getAllVocabulary()

    suspend fun addVocabulary(vocabulary: Vocabulary){
        vocabularyDao.insertSingleVocabularyRow(vocabulary)
    }

    suspend fun deleteVocabulary(vocabulary: Vocabulary){
        vocabularyDao.deleteVocabularyRow(vocabulary)
    }

    suspend fun deleteAllVocabulary(){
        vocabularyDao.deleteAll()
    }
}
//    private val vocabularyDao = TranslateRoomDatabase.getDatabase(application)!!.vocabularyDao()
//    private val coroutineScope = CoroutineScope(Dispatchers.IO)
////    val getAllVocabulary: LiveData<List<Vocabulary>> = vocabularyDao.getAllVocabulary()
//
//    suspend fun addVocabulary(vocabulary: Vocabulary){
//        vocabularyDao.insertSingleVocabularyRow(vocabulary)
//    }
//
//    fun insertMultipleRows(vocabularyList: List<Vocabulary>){
//        coroutineScope.launch {
//            vocabularyDao.insertMultipleVocabularyRows(vocabularyList)
//        }
//    }
//
//    fun deleteRow(vocabulary: Vocabulary) {
//        coroutineScope.launch {
//            vocabularyDao.deleteVocabularyRow(vocabulary)
//        }
//    }
//
//    fun deleteAll() {
//        coroutineScope.launch {
//            vocabularyDao.deleteAll()
//        }
//    }
//
//    fun getAllVocabulary() = vocabularyDao.getAllVocabulary()
//
//    fun getSelectVocabulary(id: Int) {
//        vocabularyDao.getSelectVocabulary(id)
//    }
