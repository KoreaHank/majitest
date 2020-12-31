package com.getmaji.majitest.repository.test

data class TestData(
    var current_user_url: String?,
    var current_user_authorizations_html_url:String?,
    var time:Long? = System.currentTimeMillis()
){
    override fun toString(): String {
        return "TestData{" +
                ", user='" + current_user_url + '\'' +
                ", html=" + current_user_authorizations_html_url +
                ", time=" + time +
                '}'
    }
}