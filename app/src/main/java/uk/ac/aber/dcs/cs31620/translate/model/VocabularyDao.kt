package uk.ac.aber.dcs.cs31620.translate.model

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.concurrent.Flow

@Dao
interface VocabularyDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE) // Ignore new vocab if a duplicate exists already in database
    suspend fun insertSingleVocabularyRow(vocabulary: Vocabulary)

    @Insert
    suspend fun insertMultipleVocabularyRows(vocabularyList: List<Vocabulary>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateVocabulary(vocabulary: Vocabulary)

    @Delete
    suspend fun deleteVocabularyRow(vocabulary: Vocabulary)

    @Query("DELETE FROM vocabulary_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM vocabulary_table ORDER BY id ASC")
    fun getAllVocabulary(): LiveData<List<Vocabulary>>

    @Query("""SELECT * FROM vocabulary_table WHERE id = :id""")
    fun getSelectVocabulary(id: Int): List<Vocabulary>
}