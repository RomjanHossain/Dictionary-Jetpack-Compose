package com.capx.dictionary.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
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
