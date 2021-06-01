package com.example.cookingroulette

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.cookingroulette.db.CuisineOpenHelper
import com.example.cookingroulette.db.CuisineRowParser
import com.example.cookingroulette.entity.CuisineEntity
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.coroutines.selects.select
import org.jetbrains.anko.db.SqlOrderDirection
import org.jetbrains.anko.db.select
import java.util.Collections.addAll

class SecondFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val helper = CuisineOpenHelper(this.context)


            val dataList = helper.readableDatabase.select(CuisineOpenHelper.TABLE_NAME).parseList<CuisineEntity>(CuisineRowParser())
            cuisineList.adapter = CuisineListAdapter(this.context, android.R.layout.simple_list_item_1).apply {
                addAll(dataList)
            }
        

        /*//use を利用すると、最後に close() を実行してくれる
        helper.use {
            val list = select(CuisineOpenHelper.TABLE_NAME)
                    .orderBy("_id", SqlOrderDirection.DESC)
                .parseList(CuisineRowParser())*/

            //データとListViewをつなぐアダプター
        //cuisineList.adapter = ArrayAdapter<String>(this.context!!, android.R.layout.simple_list_item_1, list.map{"${it.title}"})


        view.findViewById<Button>(R.id.backButton).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}