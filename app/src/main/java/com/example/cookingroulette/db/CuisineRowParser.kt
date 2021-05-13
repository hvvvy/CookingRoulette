package com.example.cookingroulette.db

import com.example.cookingroulette.entity.CuisineEntity
import org.jetbrains.anko.db.MapRowParser

//データベースから引っ張ってきたデータをCuisineEntityに変換するクラス
class CuisineRowParser: MapRowParser<CuisineEntity> {
    override fun parseRow(columns: Map<String, Any?>): CuisineEntity {
        return CuisineEntity(
            (columns["_id"] as Long).toInt(),
            columns["title"] as String
        )
    }
}