package com.example.demo_basic_staff_android.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(history: History)

    @Query("DELETE FROM history_calculator_table")
    suspend fun clear()

    @Query("SELECT * FROM history_calculator_table ORDER BY historyId ASC ")
    fun getAllHistories(): LiveData<List<History>>

}