package com.example.cookingroulette

import android.animation.Animator
import android.app.AlertDialog
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
import com.example.cookingroulette.db.CuisineDatabaseOpenHelper
import com.example.cookingroulette.db.ListDataParser
import com.example.cookingroulette.entity.CuisineEntity
import kotlinx.android.synthetic.main.fragment_first.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import kotlin.random.Random


class FirstFragment : Fragment() {

    private val cuisineData = CuisineDataList.getInstance()
    private val cuisineRandomData: MutableList<String?> = mutableListOf()
    private var randomNum = 0
    
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
        numPicker.maxValue = 10
        numPicker.value = 2

        //numPickerが変更されずランダム生成ボタンが押された時のためにnumPickerのデフォルトの値を取得
        randomNum = numPicker.value
        //numPickerが変更されるたび値を取得
        numPicker.setOnValueChangedListener(object : NumberPicker.OnValueChangeListener{
            //p1に変更前の値、p2に変更後の値が入る
            override fun onValueChange(p0: NumberPicker?, p1: Int, p2: Int) {
                randomNum = p2
            }
        })

        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.startButton).setOnClickListener {

            //一周するのに360（360°）
            //下限値3600、上限値3960にすることで10周～11周の間に停止する
            /*val randomPosition = (3600..3960).random()
            Log.d("debug", " randomPosition = $randomPosition")
            // 最終position
            val endPosition = randomPosition*/

            //テストのため一時的に固定 //4000
            val endPosition = 3700



            val rouletteAnimation = RouletteAnimation(rouletteView, endPosition)
            // アニメーションの起動期間を設定　//7000ミリ秒
            rouletteAnimation.duration = 500

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

                    //cuisineDataの数に応じて、乱数で発生させたendPositionにより抽選結果を判別する
                    val cuisineList: MutableList<String?> = cuisineData.cuisineData
                    var cuisine:String? = null
                    val size = cuisineList.size
                    when (size) {
                        1 -> {
                            cuisine = cuisineList[0]
                        }
                        2 -> {
                            when(endPosition){
                                in 3600..3780->{
                                    cuisine = cuisineList[1]
                                }
                                in 3781..3960 ->{
                                    cuisine = cuisineList[0]
                                }
                            }
                        }
                        3 -> {
                            when(endPosition){
                                in 3600..3720->{
                                    cuisine = cuisineList[2]
                                }
                                in 3721..3840 ->{
                                    cuisine = cuisineList[1]
                                }
                                in 3841..3960 ->{
                                    cuisine = cuisineList[0]
                                }
                            }
                        }
                        4 -> {
                            when(endPosition){
                                in 3600..3690->{
                                    cuisine = cuisineList[3]
                                }
                                in 3691..3780 ->{
                                    cuisine = cuisineList[2]
                                }
                                in 3781..3870 ->{
                                    cuisine = cuisineList[1]
                                }
                                in 3871..3960 -> {
                                    cuisine = cuisineList[0]
                                }
                            }
                        }
                        5 -> {
                            when(endPosition){
                                in 3600..3672->{
                                    cuisine = cuisineList[4]
                                }
                                in 3673..3744 ->{
                                    cuisine = cuisineList[3]
                                }
                                in 3745..3816 ->{
                                    cuisine = cuisineList[2]
                                }
                                in 3817..3888 ->{
                                    cuisine = cuisineList[1]
                                }
                                in 3889..3960 -> {
                                    cuisine = cuisineList[0]
                                }
                            }
                        }
                        6 -> {
                            when(endPosition){
                                in 3600..3660->{
                                    cuisine = cuisineList[5]
                                }
                                in 3661..3720 ->{
                                    cuisine = cuisineList[4]
                                }
                                in 3721..3780 ->{
                                    cuisine = cuisineList[3]
                                }
                                in 3781..3840 ->{
                                    cuisine = cuisineList[2]
                                }
                                in 3841..3900 ->{
                                    cuisine = cuisineList[1]
                                }
                                in 3901..3960 -> {
                                    cuisine = cuisineList[0]
                                }
                            }
                        }
                        7 -> {
                            when(endPosition){
                                in 3600..3651->{
                                    cuisine = cuisineList[6]
                                }
                                in 3652..3703 ->{
                                    cuisine = cuisineList[5]
                                }
                                in 3704..3754 ->{
                                    cuisine = cuisineList[4]
                                }
                                in 3755..3806 ->{
                                    cuisine = cuisineList[3]
                                }
                                in 3807..3857 ->{
                                    cuisine = cuisineList[2]
                                }
                                in 3858..3909 ->{
                                    cuisine = cuisineList[1]
                                }
                                in 3910..3960 -> {
                                    cuisine = cuisineList[0]
                                }
                            }
                        }
                        8 -> {
                            when(endPosition){
                                in 3600..3645->{
                                    cuisine = cuisineList[7]
                                }
                                in 3646..3690 ->{
                                    cuisine = cuisineList[6]
                                }
                                in 3691..3735 ->{
                                    cuisine = cuisineList[5]
                                }
                                in 3736..3780 ->{
                                    cuisine = cuisineList[4]
                                }
                                in 3781..3825 ->{
                                    cuisine = cuisineList[3]
                                }
                                in 3826..3870 ->{
                                    cuisine = cuisineList[2]
                                }
                                in 3871..3915 ->{
                                    cuisine = cuisineList[1]
                                }
                                in 3916..3960 -> {
                                    cuisine = cuisineList[0]
                                }
                            }
                        }
                        9 -> {
                            when(endPosition){
                                in 3600..3640->{
                                    cuisine = cuisineList[8]
                                }
                                in 3641..3680 ->{
                                    cuisine = cuisineList[7]
                                }
                                in 3681..3720 ->{
                                    cuisine = cuisineList[6]
                                }
                                in 3721..3760 ->{
                                    cuisine = cuisineList[5]
                                }
                                in 3761..3800 ->{
                                    cuisine = cuisineList[4]
                                }
                                in 3801..3840 ->{
                                    cuisine = cuisineList[3]
                                }
                                in 3841..3880 ->{
                                    cuisine = cuisineList[2]
                                }
                                in 3881..3920 ->{
                                    cuisine = cuisineList[1]
                                }
                                in 3921..3960 -> {
                                    cuisine = cuisineList[0]
                                }
                            }
                        }
                        10 -> {
                            when(endPosition){
                                in 3600..3636->{
                                    cuisine = cuisineList[9]
                                }
                                in 3637..3672->{
                                    cuisine = cuisineList[8]
                                }
                                in 3673..3708 ->{
                                    cuisine = cuisineList[7]
                                }
                                in 3709..3744 ->{
                                    cuisine = cuisineList[6]
                                }
                                in 3745..3780 ->{
                                    cuisine = cuisineList[5]
                                }
                                in 3781..3816 ->{
                                    cuisine = cuisineList[4]
                                }
                                in 3817..3852 ->{
                                    cuisine = cuisineList[3]
                                }
                                in 3853..3888 ->{
                                    cuisine = cuisineList[2]
                                }
                                in 3889..3924 ->{
                                    cuisine = cuisineList[1]
                                }
                                in 3925..3960 -> {
                                    cuisine = cuisineList[0]
                                }
                            }
                        }
                    }
                    //アニメーション終了時に当選した料理（ピース）を表示するダイアログ

                    AlertDialog.Builder(context)
                            .setTitle("抽選結果")
                            .setMessage("今日のごはんは $cuisine です")
                            .setPositiveButton("OK"){ dialog, which -> }
                            .show()

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

        view.findViewById<Button>(R.id.randomButton).setOnClickListener {
            cuisineRandomData.clear()
            cuisineData.cuisineData.clear()

            rouletteView.showCanvas(false)

            Log.d("debug", " showCanvas = false${cuisineData.cuisineData}")

            val helper = CuisineDatabaseOpenHelper(requireContext())
            val database = helper.readableDatabase
            val sql = "SELECT title FROM Cuisine order BY RANDOM() LIMIT $randomNum ;"
            //use{}はDB使用後半強制的にクローズする
            helper.use{
                //rawQueryメソッドを使用し、その引数にSELECTを行うSQL文を設定
                //rawQueryメソッド自体はその名の通り生のSQL文を実行するメソッドなので、SELECT文以外でも使える
                val cursor = database.rawQuery(sql,null)
                if (cursor.count > 0) {
                    //SQLの実行結果はCursorクラスで受け取ることができるので、moveToFirstメソッドで最初のレコードに移動
                    cursor.moveToFirst()
                    //cursorが最後のデータの後になければ繰り返す
                    while (!cursor.isAfterLast) {
                        cuisineRandomData.add(cursor.getString(0))
                        // 最後のレコードに到達するまでmoveToNextメソッドを繰り返して全レコードの値を取得
                        cursor.moveToNext()
                    }
                }
                for (i in cuisineRandomData.indices) {
                    cuisineData.cuisineData.add(cuisineRandomData[i])
                }
            }
            Toast.makeText(this.requireContext(), "ランダム生成しました" + "$cuisineRandomData", Toast.LENGTH_SHORT).show()
            rouletteView.showCanvas(true)
        }
    }
}