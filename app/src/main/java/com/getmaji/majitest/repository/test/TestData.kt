package com.getmaji.majitest.repository.test

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "testdata")
class TestData {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "user")
    var current_user_url: String? = null

    @ColumnInfo(name = "html")
    var current_user_authorizations_html_url:String? = null

    @ColumnInfo
    var time:Long? = System.currentTimeMillis()


    override fun toString(): String {
        return "TestData{" +
                "id=" + id +
                ", user='" + current_user_url + '\'' +
                ", html=" + current_user_authorizations_html_url +
                ", time=" + time +
                '}'
    }
}