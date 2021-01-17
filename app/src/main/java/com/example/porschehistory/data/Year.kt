package com.example.porschehistory.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "year_table")
data class Year(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val year: Int
)

//need to add a list of YearEvents