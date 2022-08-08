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

    companion object {
        private var instance: TranslateRoomDatabase? = null
        private val coroutineScope = CoroutineScope(Dispatchers.Main)

        fun getDatabase(context: Context): TranslateRoomDatabase? {
            synchronized(this) {
                if (instance == null) {
                    instance =
                        Room.databaseBuilder<TranslateRoomDatabase>(
                            context.applicationContext,
                            TranslateRoomDatabase::class.java,
                            "vocabulary_database"
                        )
                            .allowMainThreadQueries()
                            .addCallback(roomDatabaseCallback(context))
                            //.addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                            .build()
                }
                return instance!!
            }
        }

        private fun roomDatabaseCallback(context: Context): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    coroutineScope.launch(Dispatchers.IO) {
                        populatedDatabase(context, getDatabase(context)!!)
                    }
                }
            }
        }

        val MIGRATION_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.d("migrate", "Doing a migrate from version 1 to 2")
                // This is where we make relevant database data changes,
                // or copy data from old table to a new table.
                // Deals with the migration from version 1 to version 2
            }
        }

        private suspend fun populatedDatabase(context: Context, instance: TranslateRoomDatabase) {
            val vocabulary = Vocabulary(
                0,
                "TestNative",
                "TestForeign"
            )

            val vocabulary2 = Vocabulary(
                1,
                "Test2Native",
                "Test2Foreign"
            )

            val vocabulary3 = Vocabulary(
                2,
                "Test3Native",
                "Test3Foreign"
            )

            val listOfVocab = mutableListOf(
                vocabulary,
                vocabulary2,
                vocabulary3
            )

            val dao = instance.vocabularyDao()
            dao.insertMultipleVocabularyRows(listOfVocab)

        }
    }
}