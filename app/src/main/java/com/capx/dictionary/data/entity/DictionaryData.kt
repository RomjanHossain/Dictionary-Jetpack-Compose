package com.capx.dictionary.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Fts4
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "bangladic")
data class DictionaryData(
    // Retain original primary key and notNull settings for the required columns
    @PrimaryKey(autoGenerate = true)
    val id: Int?, // Changed to Int? to match notNull='false'

    @ColumnInfo(name = "title")
    val title: String, // Kept String (notNull='true' matches)

    @ColumnInfo(name = "body")
    val body: String?, // Changed to String? to match notNull='false'

    @ColumnInfo(name = "original_file")
    val originalFile: String? // Changed to String? to match notNull='false'
)


@Fts4(contentEntity = DictionaryData::class)
@Entity(tableName = "bangladic_fts") // Separate FTS table
data class DictionaryFts(
    @PrimaryKey
    @ColumnInfo(name = "rowid")
    val id: Int?,
    val title: String?,
    val original_file: String?,
)

@Entity(
    tableName = "bookmark", foreignKeys = [
        ForeignKey(
            DictionaryFts::class,
            parentColumns = ["rowid"],
            childColumns = ["wordID"],
            onDelete = CASCADE,
        )
    ],
    indices = [Index("wordID")]
)
data class DictionaryBookmark(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val title: String?,
    val wordID: Int,
)