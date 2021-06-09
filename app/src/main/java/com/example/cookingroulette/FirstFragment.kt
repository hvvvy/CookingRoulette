package com.example.cookingroulette

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment() {

    val sample = CuisineDataList.getInstance()

    private var showCanvas = false

    //private var rouletteView: RouletteView? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        /*//ドラムロール表示用の配列作成
        val fruits = arrayOf("りんご", "いちご", "みかん")
        //NumberPickerを取得
        var numPickerView = inflater.inflate(R.layout.fragment_first, container, false)
        val numPicker = numPickerView.findViewById<NumberPicker>(R.id.numPicker)
        //配列のインデックス最小、最大を指定
        numPicker.minValue = 0
        numPicker.maxValue = fruits.size - 1
        //NumberPickerに配列をセットする
        numPicker.displayedValues = fruits

        return numPickerView*/

        /*var rou = inflater.inflate(R.layout.fragment_second, container, false)
        rouletteView = rou.findViewById<RouletteView>(R.id.rouletteView)*/

        var v = inflater.inflate(R.layout.fragment_first, container, false)
        val numPicker = v.findViewById<NumberPicker>(R.id.numPicker)
        numPicker.minValue=2
        numPicker.maxValue=20
        numPicker.value=2
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

                Log.d("debug", " showCanvas = false${sample.sample}")




            //secondfragmentに画面遷移
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

        }

    }

}