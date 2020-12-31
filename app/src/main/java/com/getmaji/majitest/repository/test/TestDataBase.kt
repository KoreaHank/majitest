package com.getmaji.majitest.repository.test

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TestData::class], version = 1)
abstract class TestDataBase : RoomDatabase() {
    abstract fun testDao(): TestDao?

    companion object {

        // 单例
        @Volatile
        private var INSTANCE : TestDataBase? = null

        fun getDatabase(context : Context) : TestDataBase {
            val temp = INSTANCE
            if (null != temp) {
                return temp
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TestDataBase::class.java,
                    "maji_db"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}