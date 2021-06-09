package com.example.cookingroulette

import android.app.Application
class CuisineDataList :Application(){
    var sample: MutableList<String?> = mutableListOf()

    companion object {
        private var instance : CuisineDataList? = null

        fun  getInstance(): CuisineDataList {
            if (instance == null)
                instance = CuisineDataList()

            return instance!!
        }
    }
}
