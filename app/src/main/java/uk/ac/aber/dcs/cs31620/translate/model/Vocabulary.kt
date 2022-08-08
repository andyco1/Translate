package uk.ac.aber.dcs.cs31620.translate.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vocabularyTable")
data class Vocabulary(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0,
    var nativeString: String = "",
    var foreignString: String = ""
)
