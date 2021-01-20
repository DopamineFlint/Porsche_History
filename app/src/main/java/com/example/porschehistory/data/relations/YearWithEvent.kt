package com.example.porschehistory.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.porschehistory.data.Event
import com.example.porschehistory.data.Year

data class YearWithEvent(
    @Embedded val year: Year,
    @Relation(
        parentColumn = "id",
        entityColumn = "yearId"
    )
    val events: List<Event>
)