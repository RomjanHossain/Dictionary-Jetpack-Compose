package com.capx.dictionary.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Fts4
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "dictionaryDetails")
data class DictionaryDataDetails(
    // Retain original primary key and notNull settings for the required columns
    @PrimaryKey(autoGenerate = true)
    val id: Int?, // Changed to Int? to match notNull='false'

    @ColumnInfo(name = "title")
    val title: String?, // Kept String (notNull='true' matches)

    @ColumnInfo(name = "body")
    val body: String?, // Changed to String? to match notNull='false'

    @ColumnInfo(name = "source_lang")
    val originalFile: String? // Changed to String? to match notNull='false'
)


@Entity(tableName = "dictionaryTitles")
data class DictionaryDataTitles(
    // Retain original primary key and notNull settings for the required columns
    @PrimaryKey(autoGenerate = true)
    val id: Int?, // Changed to Int? to match notNull='false'

    @ColumnInfo(name = "title")
    val title: String?, // Kept String (notNull='true' matches)

    @ColumnInfo(name = "source_lang")
    val originalFile: String?
)


@Fts4(contentEntity = DictionaryDataTitles::class)
@Entity(tableName = "dictionary_fts") // Separate FTS table
data class DictionaryFts(
    @PrimaryKey
    @ColumnInfo(name = "rowid")
    val id: Int?,
    val title: String?,
    val source_lang: String?,
)

@Entity(
    tableName = "bookmark", foreignKeys = [
        ForeignKey(
            DictionaryDataTitles::class,
            parentColumns = ["id"],
            childColumns = ["wordID"],
            onDelete = CASCADE,
        )
    ],
    indices = [Index("wordID", unique = true)]
)
data class DictionaryBookmark(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String?,
    val wordID: Int,
)