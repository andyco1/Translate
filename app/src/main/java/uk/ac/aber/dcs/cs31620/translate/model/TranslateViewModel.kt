package uk.ac.aber.dcs.cs31620.translate.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.translate.datasource.TranslateRepository
import uk.ac.aber.dcs.cs31620.translate.datasource.TranslateRoomDatabase

class TranslateViewModel(application : Application) : AndroidViewModel(application) {

    val readAllVocabulary: LiveData<List<Vocabulary>>
    private val repository: TranslateRepository

    init {
        val vocabularyDao = TranslateRoomDatabase.getDatabase(application).vocabularyDao()
        repository = TranslateRepository(vocabularyDao)
        readAllVocabulary = repository.getAllVocabulary
    }

    fun addVocabulary(vocabulary: Vocabulary) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addVocabulary(vocabulary)
        }
    }
}

//    private val repository: TranslateRepository = TranslateRepository(application)
//    var vocabularyList: LiveData<List<Vocabulary>> = repository.getAllVocabulary()
//
//    fun getVocabulary(): LiveData<List<Vocabulary>> {
//
//        return vocabularyList
//    }
//
//    fun addVocabulary(vocabulary: Vocabulary) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.addVocabulary(vocabulary)
//        }
//    }
//
//    fun deleteSingleVocabulary(vocabulary: Vocabulary) {
//        repository.deleteRow(vocabulary)
//    }
//
//    fun deleteAllVocabulary() {
//        repository.deleteAll()
//    }


//    private val readAllData: LiveData<List<Vocabulary>>
//    private val repository: TranslateRepository
//
//    init {
//        val vocabularyDao = TranslateRoomDatabase.getDatabase(application).vocabularyDao()
//        repository = TranslateRepository(vocabularyDao)
//        readAllData = repository.getAllVocabulary
//    }
//
//    fun addVocabulary(vocabulary: Vocabulary){
//        viewModelScope.launch(Dispatchers.IO) { // Make use of a background thread not main thread
//            repository.addVocabulary(vocabulary)
//        }
//    }

