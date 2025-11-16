package com.example.lab_week_10

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Total::class], version = 1)
abstract class TotalDatabase : RoomDatabase() {
    abstract fun totalDao(): TotalDao
}
