package com.example.demo_basic_staff_android.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_calculator_table")
data class History(
    @PrimaryKey(autoGenerate = true)
    var historyId: Long = 0L,

    @ColumnInfo(name = "operation")
    val operation: String = "",

    @ColumnInfo(name = "result")
    var result: String = "",

)