package com.getmaji.majitest.ui

import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.getmaji.majitest.repository.RetrofitClient
import com.getmaji.majitest.repository.test.TestData
import com.getmaji.majitest.base.BaseViewModel
import com.getmaji.majitest.repository.test.TestDao
import java.util.*

class SecondViewModel : BaseViewModel() {

    val mResult = MutableLiveData<List<TestData>>()
    var dao: TestDao? = null

    fun getData() {
        launch(
            block = {
                mResult.value = dao?.getAll()
            },
            error = {

            }
        )
    }
}