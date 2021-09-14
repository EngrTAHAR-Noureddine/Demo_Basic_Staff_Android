package com.example.demo_basic_staff_android.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [History::class], version = 1, exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {


    abstract val historyDatabaseDao: HistoryDao


    companion object {

        @Volatile
        private var INSTANCE: HistoryDataBase? = null


        fun getInstance(context: Context): HistoryDataBase {

            synchronized(this) {

                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HistoryDataBase::class.java,
                        "sleep_history_database"
                    )

                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}