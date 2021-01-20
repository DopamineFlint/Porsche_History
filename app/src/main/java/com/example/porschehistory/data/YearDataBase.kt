package com.example.porschehistory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Year::class, Event::class], version = 1, exportSchema = false)
abstract class YearDataBase: RoomDatabase() {

    abstract fun yearDao(): YearDao

    companion object {
        @Volatile
        private var INSTANCE: YearDataBase? = null

        fun getDatabase(context: Context): YearDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) { //need to learn about it more
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    YearDataBase::class.java,
                    "year_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}