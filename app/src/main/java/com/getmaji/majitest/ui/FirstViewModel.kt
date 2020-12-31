package com.getmaji.majitest.ui

import androidx.lifecycle.MutableLiveData
import com.getmaji.majitest.repository.RetrofitClient
import com.getmaji.majitest.repository.test.TestData
import com.getmaji.majitest.base.BaseViewModel
import java.util.*

class FirstViewModel : BaseViewModel() {

    val mResult = MutableLiveData<TestData>()
    var timer:Timer? = null

    fun initData(){

    }

    fun getData() {
        launch(
            block = {
                val result = RetrofitClient.apiService.getData()
                mResult.value = result
            },
            error = {

            }
        )
    }

    fun startLoop(){
        timer = Timer()
        timer!!.schedule(object:TimerTask(){
            override fun run() {
                getData()
            }

        },5*1000,5*1000)
    }

    fun stopLoop(){
        timer?.cancel()
        timer?.purge();
        timer = null;
    }









}