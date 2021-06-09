package com.example.cookingroulette

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.fragment_first.view.*

//カスタムビューのコンストラクタはContextの他にAttributeSetまで記述しないとエラーになることがある
class RouletteView(context: Context?, attrs: AttributeSet) : View(context, attrs) {

    var textPaint = Paint()
    var rPaint = Paint()
    var yPaint = Paint()
    var lbPaint = Paint()
    private var position = 0
    var firstFragment = FirstFragment()
    val sample = CuisineDataList.getInstance()

    var viewflg = true



    //弧の描画
    // var rectf = RectF(500f, 500f, 500f, 500f)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas) {

        if (viewflg) {
            var test: MutableList<String?> = sample.sample
            Log.d("TestView", " viewflg = true" + test)

            //text用
            textPaint.textSize = 65f
            //赤色
            rPaint.color = Color.argb(255, 255, 0, 0)
            //黄色
            yPaint.color = Color.argb(255, 255, 255, 0)
            //水色
            lbPaint.color = Color.argb(255, 0, 255, 255)


            // (left, top, right, bottom) 左上(400, 100)を起点に幅200の矩形
            // canvas.drawRect(400f, (100 + position).toFloat(), 600f, (300 + position).toFloat(), paint)
            canvas.save()
            //translate()はcanvasごと移動させる rotate()はcanvas自体はそのまま動かさず回転軸だけを移動させる
            //回転軸を中心にする
            canvas.rotate(position.toFloat(), (canvas.width / 2).toFloat(), (canvas.width / 2).toFloat())
            //
            // canvas.scale(1f,0.70f)

            //一番左上の点から数える
            //中心にするならcanvas.drawRect(横幅の1/4f, 縦幅の1/4f,横幅の3/4f,縦幅の3/4f, paint)
            //canvas.drawRect((canvas.width/4).toFloat(), (canvas.height/4).toFloat(), ((canvas.width/4))*3.toFloat(),((canvas.height/4))*3.toFloat(), paint)
            var size = test.size
            when (size) {
                2 -> {
                    //1個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            270f, 180f,
                            true, rPaint)

                    //テスト
                    canvas.drawText(test[size - 1].toString(), (canvas.width / 2).toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)

                    //2個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            90f, 180f,
                            true, yPaint)
                }
                3 -> {
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            270f, 120f,
                            true, rPaint)

                    //テスト
                    canvas.drawText(test[size - 3].toString(), (canvas.width / 2).toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)

                    //2個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            30f, 120f,
                            true, yPaint)

                    //テスト
                    canvas.drawText(test[size - 2].toString(), (canvas.width / 2).toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)

                    //3個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            150f, 120f,
                            true, lbPaint)

                    //テスト
                    canvas.drawText(test[size - 1].toString(), (canvas.width / 2).toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)
                }
                else -> {
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            270f, 180f,
                            true, rPaint)

                    //テスト
                    canvas.drawText("data is not found", (canvas.width / 2).toFloat(),
                            (canvas.width / 2).toFloat(), textPaint)

                    /*//2個目
                    canvas.drawArc((canvas.width / 4).toFloat(),
                            (canvas.width / 4).toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            ((canvas.width / 4)) * 3.toFloat(),
                            90f, 180f,
                            true, yPaint)*/
                }
            }
        }else{
            Log.d("TestView", " viewflg = false")

            // 描画クリア
            canvas.drawColor(0, PorterDuff.Mode.CLEAR)
        }

//        canvas.restore()
    }

    fun showCanvas(flg: Boolean) {
        viewflg = flg
        // 再描画
        invalidate()
    }
    /*fun showCanvas() {
        // 再描画
        invalidate()
    }*/

    fun setPositon(pos: Int) {
        position = pos
    }

    fun getPosition(): Int {
        return position
    }
}