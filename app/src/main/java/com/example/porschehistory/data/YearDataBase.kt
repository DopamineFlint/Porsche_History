package com.example.porschehistory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Year::class, Event::class], version = 2, exportSchema = false)
abstract class YearDataBase: RoomDatabase() {

    abstract fun yearDao(): YearDao

    companion object {
        @Volatile
        private var INSTANCE: YearDataBase? = null

        val migration_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE event_table(id INTEGER NOT NULL, yearId INTEGER NOT NULL, eventName TEXT NOT NULL, PRIMARY KEY (id))")
            }
        }

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
                )
                    .addMigrations(migration_1_2)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}