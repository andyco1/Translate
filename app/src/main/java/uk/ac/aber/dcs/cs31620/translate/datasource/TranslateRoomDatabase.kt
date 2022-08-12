package uk.ac.aber.dcs.cs31620.translate.datasource

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs31620.translate.model.Vocabulary
import uk.ac.aber.dcs.cs31620.translate.model.VocabularyDao

@Database(entities = [Vocabulary::class], version = 1)
abstract class TranslateRoomDatabase : RoomDatabase() {

    abstract fun vocabularyDao(): VocabularyDao

    companion object{
        @Volatile
        private var INSTANCE: TranslateRoomDatabase? = null

        fun getDatabase(context: Context): TranslateRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TranslateRoomDatabase::class.java,
                    "vocabulary_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}