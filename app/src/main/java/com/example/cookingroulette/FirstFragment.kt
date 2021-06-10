package com.example.cookingroulette

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.NumberPicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment() {

    private val cuisineData = CuisineDataList.getInstance()



    //private var rouletteView: RouletteView? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        //NumberPickerを取得
        val v = inflater.inflate(R.layout.fragment_first, container, false)
        val numPicker = v.findViewById<NumberPicker>(R.id.numPicker)
        //配列のインデックス最小、最大を指定
        numPicker.minValue = 2
        numPicker.maxValue = 20
        numPicker.value = 2
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.startButton).setOnClickListener {
            // 最終position
            val endPosition = 1000
            val rouletteAnimation = RouletteAnimation(rouletteView, endPosition)
            // アニメーションの起動期間を設定
            rouletteAnimation.setDuration(10000)
            rouletteView.startAnimation(rouletteAnimation)
        }

        view.findViewById<ImageButton>(R.id.addButton).setOnClickListener {

            rouletteView.showCanvas(false)

            Log.d("debug", " showCanvas = false${cuisineData.cuisineData}")

            //secondfragmentに画面遷移
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

        }
        view.findViewById<ImageButton>(R.id.removeButton).setOnClickListener {

            rouletteView.showCanvas(false)

            Log.d("debug", " showCanvas = false${cuisineData.cuisineData}")

            //cuisineDataの要素をチェック
            //ある場合は末尾の要素を削除
            //ない場合は削除できるピースがありませんと表示
            if (cuisineData.cuisineData.size != 0) {
                val removeCuisine = cuisineData.cuisineData.removeAt(cuisineData.cuisineData.size - 1)
                Toast.makeText(this.requireContext(), "$removeCuisine を削除しました", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this.requireContext(), "削除できるピースがありません", Toast.LENGTH_SHORT).show()
            }
            rouletteView.showCanvas(true)
        }

    }

}