package com.example.cookingroulette

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        /*//ドラムロール表示用の配列作成
        val fruits = arrayOf("りんご", "いちご", "みかん")
        //NumberPickerを取得
        var v = inflater.inflate(R.layout.fragment_first, container, false)
        val picker = v.findViewById<NumberPicker>(R.id.picker)
        //配列のインデックス最小、最大を指定
        picker.minValue = 0
        picker.maxValue = fruits.size - 1
        //NumberPickerに配列をセットする
        picker.displayedValues = fruits*/

        /*var v = inflater.inflate(R.layout.fragment_first, container, false)
        val picker = v.findViewById<NumberPicker>(R.id.picker)
        picker.minValue=0
        picker.maxValue=20
        picker.value=3*/




        return inflater.inflate(R.layout.fragment_first, container, false)
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
           // findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}