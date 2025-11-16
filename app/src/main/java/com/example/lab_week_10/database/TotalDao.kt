package com.example.lab_week_10.database

import androidx.room.*

@Dao
interface TotalDao {

    @Query("SELECT * FROM total WHERE id = 1 LIMIT 1")
    fun getTotal(): Total?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(total: Total)

    @Update
    fun update(total: Total)
}
