package com.getmaji.majitest.repository.test

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(students: TestData?)

    @get:Query("select * from testdata order by id DESC limit 1")
    val recent: TestData?

    @Query("SELECT * FROM testdata")
    fun getAll(): List<TestData>?
}