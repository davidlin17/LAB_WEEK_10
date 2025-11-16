package com.example.lab_week_10

import androidx.room.*

@Dao
interface TotalDao {

    @Query("SELECT * FROM total WHERE id = :id LIMIT 1")
    fun getTotal(id: Long): Total?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(total: Total)

    @Update
    fun update(total: Total)
}
