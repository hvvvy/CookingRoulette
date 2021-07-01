package com.example.cookingroulette

import android.animation.Animator
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
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

            /*val randomPosition = (3000..4000).random()
            Log.d("debug", " randomPosition = $randomPosition")
            // 最終position
            val endPosition = randomPosition*/

            val endPosition = 4000



            val rouletteAnimation = RouletteAnimation(rouletteView, endPosition)
            // アニメーションの起動期間を設定
            rouletteAnimation.duration = 7000

            //RouletteAnimationのリスナー
            rouletteAnimation.setAnimationListener(object : Animation.AnimationListener {
                //onAnimationStartでアニメーションが開始したことを通知し、
                // そのタイミングでaddFlagsで画面操作を受け付けなくする
                override fun onAnimationStart(p0: Animation?) {
                    activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                }
                //onAnimationEndでアニメーションが終了したことを通知し、
                // そのタイミングでclearFlagsで画面操作を再度受け付ける
                override fun onAnimationEnd(p0: Animation?) {
                    activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                }
                //アニメーションの繰り返し時を通知する
                override fun onAnimationRepeat(p0: Animation?) {
                    TODO("Not yet implemented")
                }
            })

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