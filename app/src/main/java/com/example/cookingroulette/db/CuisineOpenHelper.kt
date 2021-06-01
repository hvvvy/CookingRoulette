package com.example.cookingroulette.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class CuisineOpenHelper(context: Context?): ManagedSQLiteOpenHelper(context!!,DB_NAME,null,DB_VERSION) {

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //DB とテーブルを作成
        db?.createTable(
            TABLE_NAME,
            true,
            "_id" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            "title" to TEXT
        )
    }

    //定数などを準備（インスタンス化せずに、"クラス名.定数名"で利用できる定数）
    companion object{
        private val DB_NAME = "CuisineEntity"
        private val DB_VERSION = 1
        val TABLE_NAME = "Cuisine"

        //自分自身のオブジェクトを保持する変数（オブジェクトを複数作らないように、ここで1つ作る）
        private var instance: CuisineOpenHelper? = null

        /**
         * 自分自身のオブジェクトを返すメソッド
         */
        @Synchronized
        fun getInstance(context: Context): CuisineOpenHelper{
            //instance が null でなければそれを返す、 null なら CuisineOpenHelper をインスタンス
            return instance ?: CuisineOpenHelper(context.applicationContext)
        }
    }



}