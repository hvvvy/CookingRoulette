package com.example.cookingroulette.entity

//データベースから引っ張ってきたデータ(CuisineRowParserクラスによって変換されたデータ)を保持するクラス
data class CuisineEntity(val _id:Int?, val title: String)