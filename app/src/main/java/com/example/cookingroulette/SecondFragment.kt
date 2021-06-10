package com.example.cookingroulette

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cookingroulette.db.CuisineDatabaseOpenHelper
import com.example.cookingroulette.db.ListDataParser
import com.example.cookingroulette.entity.CuisineEntity
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_second.*
import org.jetbrains.anko.db.select

class SecondFragment : Fragment() {
    val cuisineData = CuisineDataList.getInstance()



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        val helper = CuisineDatabaseOpenHelper.getInstance(this.requireContext())
        //データベースから登録されているデータを取得
        /*select(BMIDatabaseOpenHelper.tableName)で実際に実行するSQLを構築しているがこの時点では実行されない。
          引数にテーブル名を指定する必要がある。 この時点でのSQLは【select * from テーブル名】と同義。メソッドチェインでWhere句なども追加も可能。*/
        val dataList = helper.readableDatabase.select(CuisineDatabaseOpenHelper.tableName).parseList<CuisineEntity>(ListDataParser())
        //カスタマイズしたアダプターを生成
        cuisineList.adapter = CuisineListAdapter(context, R.layout.row).apply {
            //アダプターに対し、表示するデータを設定
            addAll(dataList)
        }

        // OnItemClickListenerを実装
        //各項目を選択した時の処理
        cuisineList.setOnItemClickListener { parent, view, position, id ->
            // Toast.makeText(this.requireContext(),dataList[position].title, Toast.LENGTH_SHORT).show()
            cuisineData.cuisineData.add(dataList[position].title)

           // rouletteView.showCanvas(true)
            Log.d("debug", " showCanvas = true${cuisineData.cuisineData}${cuisineData.cuisineData.size}")


            /*if (showCanvas) {
                rouletteView.showCanvas(false)
                showCanvas = false
                Log.d("debug"," showCanvas = false")

            } else {
                rouletteView.showCanvas(true)
                showCanvas = true
                Log.d("debug"," showCanvas = true")
            }*/

            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        //戻るボタン
        view.findViewById<Button>(R.id.backButton).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}