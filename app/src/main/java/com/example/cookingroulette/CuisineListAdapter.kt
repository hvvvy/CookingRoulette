package com.example.cookingroulette

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.cookingroulette.entity.CuisineEntity
import org.jetbrains.anko.layoutInflater

class CuisineListAdapter : ArrayAdapter<CuisineEntity> {

    constructor(context: Context?, resource: Int) : super(context!!, resource)
    constructor(context: Context?, resource: Int, textViewResourceId: Int) : super(context!!, resource, textViewResourceId)
    constructor(context: Context?, resource: Int, objects: Array<out CuisineEntity>?) : super(context!!, resource, objects!!)
    constructor(context: Context?, resource: Int, textViewResourceId: Int, objects: Array<out CuisineEntity>?) : super(context!!, resource, textViewResourceId, objects!!)
    constructor(context: Context?, resource: Int, objects: MutableList<CuisineEntity>?) : super(context!!, resource, objects!!)
    constructor(context: Context?, resource: Int, textViewResourceId: Int, objects: MutableList<CuisineEntity>?) : super(context!!, resource, textViewResourceId, objects!!)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var newView = convertView ?: context.layoutInflater.inflate(R.layout.row, null)

        getItem(position)?.run {
            newView.findViewById<TextView>(R.id.rowId).text = _id.toString()
            newView.findViewById<TextView>(R.id.rowTitle).text = title
        }
        return newView
    }
}
