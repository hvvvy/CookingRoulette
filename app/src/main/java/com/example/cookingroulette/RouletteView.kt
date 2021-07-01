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
            //緑色
            lbPaint.color = Color.argb(255, 210, 237, 159)
            //水色
            gPaint.color = Color.argb(255, 42, 232, 219)




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
                1 -> {
                    //1個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            270f, 360f,
                            true, rPaint)

                    //テスト
                    canvas.drawText(cuisine[size - 1].toString(), (canvas.width / 2).toFloat(),
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
                    canvas.drawText(cuisine[size - 2].toString(), (canvas.width / 2).toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)

                    //2個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            90f, 180f,
                            true, yPaint)

                    //テスト
                    canvas.drawText(cuisine[size - 1].toString(), (canvas.width / 2).toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)
                }
                3 -> {
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            270f, 120f,
                            true, rPaint)

                    //テスト
                    canvas.drawText(cuisine[size - 3].toString(), (canvas.width / 2).toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)

                    //2個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            30f, 120f,
                            true, yPaint)

                    //テスト
                    canvas.drawText(cuisine[size - 2].toString(), (canvas.width / 2).toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)

                    //3個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            150f, 120f,
                            true, lbPaint)

                    //テスト
                    canvas.drawText(cuisine[size - 1].toString(), (canvas.width / 2).toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)
                }
                10 -> {
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            270f, 36f,
                            true, rPaint)

                    //テスト
                    canvas.drawText(cuisine[size - 3].toString(), (canvas.width / 2).toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)

                    //2個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            306f, 36f,
                            true, yPaint)

                    //テスト
                    canvas.drawText(cuisine[size - 2].toString(), (canvas.width / 2).toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)

                    //3個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            342f, 36f,
                            true, lbPaint)

                    //テスト
                    canvas.drawText(cuisine[size - 1].toString(), (canvas.width / 2).toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)
                }
                else -> {

// テキストの表示エリア
                    textPaint.textSize = 30f




                    /*canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            270f, 360f,
                            true, rPaint)

                    //真ん中に合わせるため文言の前に半角スペース
                    canvas.drawText(" data is not found", (canvas.width / 4).toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)*/
                    //1個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            270f, 36f,
                            true, rPaint)
                    //テスト
                    canvas.drawText("test1", (canvas.width / 11) * 6.toFloat(),
                            (canvas.width / 11) * 2.toFloat(), textPaint)
                    //2個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            306f, 36f,
                            true, yPaint)
                    //テスト
                    canvas.drawText("test2", (canvas.width / 11) * 8.toFloat(),
                            (canvas.width / 11) * 4.toFloat(), textPaint)

                    //3個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            342f, 36f,
                            true, lbPaint)
                    //テスト
                    canvas.drawText("test3", (canvas.width / 11) * 9.toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)

                    //4個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            18f, 36f,
                            true, gPaint)
                    //テスト
                    canvas.drawText("test4", (canvas.width / 11) * 8.toFloat(),
                            (canvas.width / 11) * 7.toFloat(), textPaint)
                    //5個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            54f, 36f,
                            true, rPaint)
                    //テスト
                    canvas.drawText("test5", (canvas.width / 11) * 6.toFloat(),
                            (canvas.width / 11) * 9.toFloat(), textPaint)

                    //6個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            90f, 36f,
                            true, yPaint)
                    //テスト
                    canvas.drawText("test6", (canvas.width / 11) * 4.toFloat(),
                            (canvas.width / 11) * 9.toFloat(), textPaint)

                    //7個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            126f, 36f,
                            true, lbPaint)
                    //テスト
                    canvas.drawText("test7", (canvas.width / 11) * 2.toFloat(),
                            (canvas.width / 11) * 7.toFloat(), textPaint)
                    //8個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            162f, 36f,
                            true, gPaint)
                    //テスト
                    canvas.drawText("test8", (canvas.width / 11) * 1.toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)

                    //9個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            198f, 36f,
                            true, rPaint)
                    //テスト
                    canvas.drawText("test9", (canvas.width / 11) * 2.toFloat(),
                            (canvas.width / 11) * 4.toFloat(), textPaint)

                    //10個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            234f, 36f,
                            true, yPaint)
                    //テスト
                   // canvas.rotate(288f,(canvas.width / 2).toFloat(), (canvas.width / 2).toFloat())
                    canvas.drawText("test10", (canvas.width / 11) * 4.toFloat(),
                            (canvas.width / 11) * 2.toFloat(), textPaint)


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

    private fun drawText(canvas:Canvas, rect:Rect, paint: Paint, text: String, angle:Float) {
        val textRect = Rect()
        paint.getTextBounds(text, 0, text.length, textRect)
        val top = rect.top + (rect.height() + textRect.height()) / 2f - textRect.bottom
        val left = rect.left + (rect.width() - textRect.width()) / 2f - textRect.left
        canvas.save()
        canvas.rotate(angle, rect.centerX().toFloat(), rect.centerY().toFloat())
        canvas.drawText(text, left, top, paint)
        canvas.restore()
    }

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