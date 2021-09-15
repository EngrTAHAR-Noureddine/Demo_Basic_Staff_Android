package com.example.demo_basic_staff_android.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [History::class], version = 1, exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {


    abstract fun HistoryDao() : HistoryDao


    companion object {
        private var INSTANCE: HistoryDataBase? = null

        fun getInstance(context: Context): HistoryDataBase? {
            if (INSTANCE == null) {
                synchronized(HistoryDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        HistoryDataBase::class.java, "History.db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}