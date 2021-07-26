package com.example.cookingroulette

import android.app.PendingIntent.getActivity
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.fragment_first.view.*

//カスタムビューのコンストラクタはContextの他にAttributeSetまで記述しないとエラーになることがある
class RouletteView(context: Context?, attrs: AttributeSet) : View(context, attrs) {

    private val textPaint = Paint()
    private val rPaint = Paint()
    private val yPaint = Paint()
    private val lbPaint = Paint()
    private val gPaint = Paint()
    private var position = 0
    private val cuisineData = CuisineDataList.getInstance()
    private var viewflg = true
    val firstFragment = FirstFragment()



    //弧の描画
    // var rectf = RectF(500f, 500f, 500f, 500f)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas) {

        if (viewflg) {
            val cuisine: MutableList<String?> = cuisineData.cuisineData
            Log.d("TestView", " viewflg = true" + cuisine)

            //text用
            textPaint.textSize = 50f
           /* //赤色
            rPaint.color = Color.argb(255, 255, 0, 0)
            //黄色
            yPaint.color = Color.argb(255, 255, 255, 0)
            //水色
            lbPaint.color = Color.argb(255, 0, 255, 255)
            //緑色
            gPaint.color = Color.argb(255, 0, 255, 0)*/

            //赤色
            rPaint.color = Color.argb(255, 255, 63, 63)
            //黄色
            yPaint.color = Color.argb(255, 250, 216, 25)
            //水色
            lbPaint.color = Color.argb(255, 42, 232, 219)
            //緑色
            gPaint.color = Color.argb(255, 210, 237, 159)

            val rectf = RectF((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                    ((canvas.width / 4)) * 3.toFloat(),
                    ((canvas.width / 4)) * 3.toFloat())




            // (left, top, right, bottom) 左上(400, 100)を起点に幅200の矩形
            // canvas.drawRect(400f, (100 + position).toFloat(), 600f, (300 + position).toFloat(), paint)
            //canvas.save()
            //translate()はcanvasごと移動させる rotate()はcanvas自体はそのまま動かさず回転軸だけを移動させる
            //回転軸を中心にする
            canvas.rotate(position.toFloat(), (canvas.width / 2).toFloat(), (canvas.width / 2).toFloat())
            //
            // canvas.scale(1f,0.70f)

            //一番左上の点から数える
            //中心にするならcanvas.drawRect(横幅の1/4f, 縦幅の1/4f,横幅の3/4f,縦幅の3/4f, paint)
            //canvas.drawRect((canvas.width/4).toFloat(), (canvas.height/4).toFloat(), ((canvas.width/4))*3.toFloat(),((canvas.height/4))*3.toFloat(), paint)



            val size = cuisine.size
            when (size) {
                0 -> {
                    //「＋ボタンを押して料理を追加してください」テキスト
                    textPaint.textSize = 85f
                    canvas.drawText("＋ボタンを押して", (canvas.width / 13) * 2.toFloat(),
                            (canvas.width / 5) * 1.toFloat(), textPaint)
                    canvas.drawText("料理を追加してください", (canvas.width / 15) * 1.toFloat(),
                            (canvas.width / 6) * 2.toFloat(), textPaint)
                }
                1 -> {
                    //1個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            270f, 360f,
                            true, rPaint)

                    //テスト
                    canvas.drawText(cuisine[0].toString(), (canvas.width / 2).toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)
                }
                2 -> {
                        //1個目
                        canvas.drawArc((canvas.width / 4).toFloat(),
                                (canvas.width / 4).toFloat(),
                                ((canvas.width / 4)) * 3.toFloat(),
                                ((canvas.width / 4)) * 3.toFloat(),
                                270f, 180f,
                                true, rPaint)

                    //テスト
                    canvas.drawText(cuisine[0].toString(), (canvas.width / 11) * 6.toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)

                    //2個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            90f, 180f,
                            true, yPaint)

                    canvas.drawText(cuisine[1].toString(), (canvas.width / 11) * 3.toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)
                }
                3 -> {
                    //1個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            270f, 120f,
                            true, rPaint)

                    canvas.drawText(cuisine[0].toString(), (canvas.width / 11) * 6.toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)

                    //2個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            30f, 120f,
                            true, yPaint)

                    canvas.drawText(cuisine[1].toString(), (canvas.width / 11) * 4.toFloat(),
                            (canvas.width / 11) * 7.toFloat(), textPaint)

                    //3個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            150f, 120f,
                            true, lbPaint)

                    canvas.drawText(cuisine[2].toString(), (canvas.width / 11) * 3.toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)
                }
                4 -> {
                    //1個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                        (canvas.width / 4).toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        270f, 90f,
                        true, rPaint)

                    canvas.drawText(cuisine[0].toString(), (canvas.width / 11) * 6.toFloat(),
                        (canvas.width / 11) * 5.toFloat(), textPaint)

                    //2個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                        (canvas.width / 4).toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        360f, 90f,
                        true, yPaint)

                    canvas.drawText(cuisine[1].toString(), (canvas.width / 11) * 6.toFloat(),
                        (canvas.width / 11) * 6.toFloat(), textPaint)

                    //3個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                        (canvas.width / 4).toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        90f, 90f,
                        true, lbPaint)

                    canvas.drawText(cuisine[2].toString(), (canvas.width / 11) * 3.toFloat(),
                        (canvas.width / 11) * 6.toFloat(), textPaint)

                    //4個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                        (canvas.width / 4).toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        180f, 90f,
                        true, gPaint)

                    canvas.drawText(cuisine[3].toString(), (canvas.width / 11) * 3.toFloat(),
                        (canvas.width / 11) * 5.toFloat(), textPaint)
                }
                5 -> {
                    textPaint.textSize = 45f
                    //1個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            270f, 72f,
                            true, rPaint)

                    canvas.drawText(cuisine[0].toString(), (canvas.width / 2).toFloat(),
                            (canvas.width / 11) * 4.toFloat(), textPaint)

                    //2個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            342f, 72f,
                            true, yPaint)

                    canvas.drawText(cuisine[1].toString(), (canvas.width / 11) * 6.toFloat(),
                            (canvas.width / 11) * 6.toFloat(), textPaint)

                    //3個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            54f, 72f,
                            true, lbPaint)

                    canvas.drawText(cuisine[2].toString(), (canvas.width / 11) * 4.toFloat(),
                            (canvas.width / 11) * 8.toFloat(), textPaint)

                    //4個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            126f, 72f,
                            true, gPaint)

                    canvas.drawText(cuisine[3].toString(), (canvas.width / 11) * 3.toFloat(),
                            (canvas.width / 11) * 6.toFloat(), textPaint)

                    //5個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            198f, 72f,
                            true, lbPaint)

                    canvas.drawText(cuisine[3].toString(), (canvas.width / 11) * 3.toFloat(),
                            (canvas.width / 11) * 4.toFloat(), textPaint)
                }
                6 -> {
                    textPaint.textSize = 30f

                    //1個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            270f, 60f,
                            true, rPaint)

                    canvas.drawText(cuisine[0].toString(), (canvas.width / 11) * 6.toFloat(),
                            (canvas.width / 11) * 2.toFloat(), textPaint)

                    //2個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            330f, 60f,
                            true, yPaint)

                    canvas.drawText(cuisine[1].toString(), (canvas.width / 11) * 9.toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)

                    //3個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            30f, 60f,
                            true, lbPaint)

                    canvas.drawText(cuisine[2].toString(), (canvas.width / 11) * 6.toFloat(),
                            (canvas.width / 11) * 9.toFloat(), textPaint)

                    //4個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            90f, 60f,
                            true, gPaint)

                    canvas.drawText(cuisine[3].toString(), (canvas.width / 11) * 3.toFloat(),
                            (canvas.width / 11) * 9.toFloat(), textPaint)
                    //5個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            150f, 60f,
                            true, rPaint)

                    canvas.drawText(cuisine[4].toString(), (canvas.width / 11) * 1.toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)

                    //6個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            210f, 60f,
                            true, yPaint)

                    canvas.drawText(cuisine[5].toString(), (canvas.width / 11) * 3.toFloat(),
                            (canvas.width / 11) * 2.toFloat(), textPaint)
                }
                7 -> {
                    textPaint.textSize = 30f

                    //1個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            270f, 51f,
                            true, rPaint)

                    canvas.drawText(cuisine[0].toString(), (canvas.width / 11) * 6.toFloat(),
                            (canvas.width / 11) * 2.toFloat(), textPaint)

                    //2個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            321f, 51f,
                            true, yPaint)

                    canvas.drawText(cuisine[1].toString(), (canvas.width / 11) * 9.toFloat(),
                            (canvas.width / 11) * 5.toFloat(), textPaint)

                    //3個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            12f, 51f,
                            true, lbPaint)

                    canvas.drawText(cuisine[2].toString(), (canvas.width / 11) * 9.toFloat(),
                            (canvas.width / 11) * 7.toFloat(), textPaint)

                    //4個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            63f, 51f,
                            true, gPaint)

                    canvas.drawText(cuisine[3].toString(), (canvas.width / 11) * 5.toFloat(),
                            (canvas.width / 11) * 9.toFloat(), textPaint)
                    //5個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            114f, 51f,
                            true, rPaint)

                    canvas.drawText(cuisine[4].toString(), (canvas.width / 11) * 1.toFloat(),
                            (canvas.width / 11) * 8.toFloat(), textPaint)

                    //6個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            165f, 51f,
                            true, yPaint)

                    canvas.drawText(cuisine[5].toString(), (canvas.width / 11) * 1.toFloat(),
                            (canvas.width / 11) * 5.toFloat(), textPaint)

                    //7個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            216f, 54f,
                            true, lbPaint)

                    canvas.drawText(cuisine[6].toString(), (canvas.width / 11) * 2.toFloat(),
                            (canvas.width / 11) * 2.toFloat(), textPaint)
                }
                8 -> {
                    textPaint.textSize = 30f

                    //1個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            270f, 45f,
                            true, rPaint)

                    canvas.drawText(cuisine[0].toString(), (canvas.width / 11) * 6.toFloat(),
                            (canvas.width / 11) * 2.toFloat(), textPaint)

                    //2個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            315f, 45f,
                            true, yPaint)

                    canvas.drawText(cuisine[1].toString(), (canvas.width / 11) * 8.toFloat(),
                            (canvas.width / 11) * 4.toFloat(), textPaint)

                    //3個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            360f, 45f,
                            true, lbPaint)

                    canvas.drawText(cuisine[2].toString(), (canvas.width / 11) * 8.toFloat(),
                            (canvas.width / 11) * 7.toFloat(), textPaint)

                    //4個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            45f, 45f,
                            true, gPaint)

                    canvas.drawText(cuisine[3].toString(), (canvas.width / 11) * 6.toFloat(),
                            (canvas.width / 11) * 9.toFloat(), textPaint)
                    //5個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            90f, 45f,
                            true, rPaint)

                    canvas.drawText(cuisine[4].toString(), (canvas.width / 11) * 3.toFloat(),
                            (canvas.width / 11) * 9.toFloat(), textPaint)

                    //6個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            135f, 45f,
                            true, yPaint)

                    canvas.drawText(cuisine[5].toString(), (canvas.width / 11) * 1.toFloat(),
                            (canvas.width / 11) * 7.toFloat(), textPaint)

                    //7個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            180f, 45f,
                            true, lbPaint)

                    canvas.drawText(cuisine[6].toString(), (canvas.width / 11) * 1.toFloat(),
                            (canvas.width / 11) * 4.toFloat(), textPaint)
                    //8個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            225f, 45f,
                            true, gPaint)

                    canvas.drawText(cuisine[7].toString(), (canvas.width / 11) * 3.toFloat(),
                            (canvas.width / 11) * 2.toFloat(), textPaint)
                }
                9 -> {
                    textPaint.textSize = 30f

                    //1個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            270f, 40f,
                            true, rPaint)

                    canvas.drawText(cuisine[0].toString(), (canvas.width / 11) * 6.toFloat(),
                            (canvas.width / 11) * 2.toFloat(), textPaint)

                    //2個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            310f, 40f,
                            true, yPaint)

                    canvas.drawText(cuisine[1].toString(), (canvas.width / 11) * 8.toFloat(),
                            (canvas.width / 11) * 4.toFloat(), textPaint)

                    //3個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            350f, 40f,
                            true, lbPaint)

                    canvas.drawText(cuisine[2].toString(), (canvas.width / 11) * 9.toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)

                    //4個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            30f, 40f,
                            true, gPaint)

                    canvas.drawText(cuisine[3].toString(), (canvas.width / 11) * 7.toFloat(),
                            (canvas.width / 11) * 8.toFloat(), textPaint)
                    //5個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            70f, 40f,
                            true, rPaint)

                    canvas.drawText(cuisine[4].toString(), (canvas.width / 11) * 5.toFloat(),
                            (canvas.width / 11) * 9.toFloat(), textPaint)

                    //6個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            110f, 40f,
                            true, yPaint)

                    canvas.drawText(cuisine[5].toString(), (canvas.width / 11) * 2.toFloat(),
                            (canvas.width / 11) * 8.toFloat(), textPaint)

                    //7個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            150f, 40f,
                            true, lbPaint)

                    canvas.drawText(cuisine[6].toString(), (canvas.width / 11) * 1.toFloat(),
                            (canvas.width / 11) * 6.toFloat(), textPaint)
                    //8個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            190f, 40f,
                            true, gPaint)

                    canvas.drawText(cuisine[7].toString(), (canvas.width / 11) * 1.toFloat(),
                            (canvas.width / 11) * 4.toFloat(), textPaint)

                    //9個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            230f, 40f,
                            true, yPaint)

                    canvas.drawText(cuisine[8].toString(), (canvas.width / 11) * 2.toFloat(),
                            (canvas.width / 11) * 2.toFloat(), textPaint)
                }
                10 -> {
                    textPaint.textSize = 30f

                    //1個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                        (canvas.width / 4).toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        270f, 36f,
                        true, rPaint)

                    canvas.drawText(cuisine[0].toString(), (canvas.width / 11) * 6.toFloat(),
                        (canvas.width / 11) * 2.toFloat(), textPaint)

                    //2個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                        (canvas.width / 4).toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        306f, 36f,
                        true, yPaint)

                    canvas.drawText(cuisine[1].toString(), (canvas.width / 11) * 8.toFloat(),
                        (canvas.width / 11) * 4.toFloat(), textPaint)

                    //3個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                        (canvas.width / 4).toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        342f, 36f,
                        true, lbPaint)

                    canvas.drawText(cuisine[2].toString(), (canvas.width / 11) * 9.toFloat(),
                        (canvas.width / 2).toFloat(), textPaint)

                    //4個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                        (canvas.width / 4).toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        18f, 36f,
                        true, gPaint)

                    canvas.drawText(cuisine[3].toString(), (canvas.width / 11) * 8.toFloat(),
                        (canvas.width / 11) * 7.toFloat(), textPaint)
                    //5個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                        (canvas.width / 4).toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        54f, 36f,
                        true, rPaint)

                    canvas.drawText(cuisine[4].toString(), (canvas.width / 11) * 6.toFloat(),
                        (canvas.width / 11) * 9.toFloat(), textPaint)

                    //6個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                        (canvas.width / 4).toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        90f, 36f,
                        true, yPaint)

                    canvas.drawText(cuisine[5].toString(), (canvas.width / 11) * 4.toFloat(),
                        (canvas.width / 11) * 9.toFloat(), textPaint)

                    //7個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                        (canvas.width / 4).toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        126f, 36f,
                        true, lbPaint)

                    canvas.drawText(cuisine[6].toString(), (canvas.width / 11) * 2.toFloat(),
                        (canvas.width / 11) * 7.toFloat(), textPaint)
                    //8個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                        (canvas.width / 4).toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        162f, 36f,
                        true, gPaint)

                    canvas.drawText(cuisine[7].toString(), (canvas.width / 11) * 1.toFloat(),
                        (canvas.width / 2).toFloat(), textPaint)

                    //9個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                        (canvas.width / 4).toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        198f, 36f,
                        true, rPaint)

                    canvas.drawText(cuisine[8].toString(), (canvas.width / 11) * 2.toFloat(),
                        (canvas.width / 11) * 4.toFloat(), textPaint)

                    //10個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                        (canvas.width / 4).toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        ((canvas.width / 4)) * 3.toFloat(),
                        234f, 36f,
                        true, yPaint)

                    // canvas.rotate(288f,(canvas.width / 2).toFloat(), (canvas.width / 2).toFloat())
                    canvas.drawText(cuisine[9].toString(), (canvas.width / 11) * 4.toFloat(),
                        (canvas.width / 11) * 2.toFloat(), textPaint)
                }
                else -> {

                    //10個以上データを入れようとすると10個のピース上に「そんなに入りません！」テキストを表示する
                    textPaint.textSize = 30f

                    //1個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            270f, 36f,
                            true, rPaint)

                    canvas.drawText(cuisine[0].toString(), (canvas.width / 11) * 6.toFloat(),
                            (canvas.width / 11) * 2.toFloat(), textPaint)

                    //2個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            306f, 36f,
                            true, yPaint)

                    canvas.drawText(cuisine[1].toString(), (canvas.width / 11) * 8.toFloat(),
                            (canvas.width / 11) * 4.toFloat(), textPaint)

                    //3個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            342f, 36f,
                            true, lbPaint)

                    canvas.drawText(cuisine[2].toString(), (canvas.width / 11) * 9.toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)

                    //4個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            18f, 36f,
                            true, gPaint)

                    canvas.drawText(cuisine[3].toString(), (canvas.width / 11) * 8.toFloat(),
                            (canvas.width / 11) * 7.toFloat(), textPaint)
                    //5個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            54f, 36f,
                            true, rPaint)

                    canvas.drawText(cuisine[4].toString(), (canvas.width / 11) * 6.toFloat(),
                            (canvas.width / 11) * 9.toFloat(), textPaint)

                    //6個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            90f, 36f,
                            true, yPaint)

                    canvas.drawText(cuisine[5].toString(), (canvas.width / 11) * 4.toFloat(),
                            (canvas.width / 11) * 9.toFloat(), textPaint)

                    //7個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            126f, 36f,
                            true, lbPaint)

                    canvas.drawText(cuisine[6].toString(), (canvas.width / 11) * 2.toFloat(),
                            (canvas.width / 11) * 7.toFloat(), textPaint)
                    //8個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            162f, 36f,
                            true, gPaint)

                    canvas.drawText(cuisine[7].toString(), (canvas.width / 11) * 1.toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)

                    //9個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            198f, 36f,
                            true, rPaint)

                    canvas.drawText(cuisine[8].toString(), (canvas.width / 11) * 2.toFloat(),
                            (canvas.width / 11) * 4.toFloat(), textPaint)

                    //10個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            234f, 36f,
                            true, yPaint)

                    // canvas.rotate(288f,(canvas.width / 2).toFloat(), (canvas.width / 2).toFloat())
                    canvas.drawText(cuisine[9].toString(), (canvas.width / 11) * 4.toFloat(),
                            (canvas.width / 11) * 2.toFloat(), textPaint)


                    //「そんなに入りません！」テキスト
                    textPaint.textSize = 100f
                    canvas.drawText("そんなに入りません！", (canvas.width / 13) * 1.toFloat(),
                           (canvas.width / 2).toFloat(), textPaint)


                    /*val rect = Rect((canvas.width / 11) * 6.toFloat().toInt(), (canvas.height / 4).toFloat().toInt(), ((canvas.width / 11) * 6.toFloat()).toInt(), ((canvas.width / 9) * 5.toFloat()).toInt())
                    // テキスト1
                    this.drawText(canvas, rect, textPaint, "サーモンのカルパッチョ", 288f)*/
                    // テキストの表示エリアの色を分かりやすいようにグレーで描画する。
                    //canvas.drawRect(rect, rPaint)

                    // テキスト2
                    //this.drawText(canvas, rect, textPaint, "hoge2", 324f)
                }
            }
        }else{
            Log.d("TestView", " viewflg = false")

            // 描画クリア
            canvas.drawColor(0, PorterDuff.Mode.CLEAR)
        }
    }

   /* private fun drawText(canvas:Canvas, rect:Rect, paint: Paint, text: String, angle:Float) {
        val textRect = Rect()
        paint.getTextBounds(text, 0, text.length, textRect)
        val top = rect.top + (rect.height() + textRect.height()) / 2f - textRect.bottom
        val left = rect.left + (rect.width() - textRect.width()) / 2f - textRect.left
        canvas.save()
        canvas.rotate(angle, rect.centerX().toFloat(), rect.centerY().toFloat())
        canvas.drawText(text, left, top, paint)
        canvas.restore()
    }*/

    fun showCanvas(flg: Boolean) {
        viewflg = flg
        // 再描画
        invalidate()
    }

    fun setPositon(pos: Int) {
        position = pos
    }

    fun getPosition(): Int {
        position = 0
        return position
    }
}