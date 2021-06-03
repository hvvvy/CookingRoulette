package com.example.cookingroulette.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class CuisineDatabaseOpenHelper (context:Context):ManagedSQLiteOpenHelper(context,"cuisinedb.db",null,1)
{
    companion object {
        val tableName = "Cuisine"
        private  var instance :CuisineDatabaseOpenHelper? = null

        fun getInstance(context: Context):CuisineDatabaseOpenHelper{
            return instance ?: CuisineDatabaseOpenHelper(context.applicationContext)!!
        }
    }
    override fun onCreate(db: SQLiteDatabase?) {
        //エルビス演算子でinstance変数がnullでなければinstance変数のオブジェクトを返し、nullの場合はオブジェクトを生成して返している
        //run拡張関数でnullでなければSQLiteDatabaseクラスのcreateTableメソッドでテーブルを作成
        db?.run { createTable(tableName,ifNotExists = true,
                //カラム名と型を指定している箇所で使用しているarrayOfにアスタリスクを指定している
                //これはvarargで値を渡す時に事前に配列を作成した場合は、その変数やarrayOfにアスタリスクが必要となる
                columns = *arrayOf( "_id" to TEXT, "title" to TEXT))
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }


}