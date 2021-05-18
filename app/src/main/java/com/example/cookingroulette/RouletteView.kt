package com.example.cookingroulette

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi

//カスタムビューのコンストラクタはContextの他にAttributeSetまで記述しないとエラーになることがある
class RouletteView(context: Context?,attrs: AttributeSet) : View(context,attrs) {
    var paint = Paint()
    var mPaint = Paint()
    var kPaint = Paint()
    private var position = 0


    //弧の描画
    // var rectf = RectF(500f, 500f, 500f, 500f)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas) {
        //赤色
        paint.color = Color.argb(255, 255, 0, 0)
        //黄色
        mPaint.color = Color.argb(255, 255,255,0 )
        //水色
        kPaint.color = Color.argb(255, 0, 255, 255)


        // (left, top, right, bottom) 左上(400, 100)を起点に幅200の矩形
        // canvas.drawRect(400f, (100 + position).toFloat(), 600f, (300 + position).toFloat(), paint)
        canvas.save()
        //translate()はcanvasごと移動させる rotate()はcanvas自体はそのまま動かさず回転軸だけを移動させる
        //回転軸を中心にする
        canvas.rotate(position.toFloat(),(canvas.width/2).toFloat(),(canvas.width/2).toFloat())
        //
        // canvas.scale(1f,0.70f)

        //一番左上の点から数える
        //canvas.drawRect(横幅の1/4f, 縦幅の1/4f,横幅の3/4f,縦幅の3/4f, paint)
        //canvas.drawRect((canvas.width/4).toFloat(), (canvas.height/4).toFloat(), ((canvas.width/4))*3.toFloat(),((canvas.height/4))*3.toFloat(), paint)
        //1個目
        canvas.drawArc((canvas.width/4).toFloat(),
            (canvas.width/4).toFloat(),
            ((canvas.width/4))*3.toFloat(),
            ((canvas.width/4))*3.toFloat(),
            270f,120f,
            true, paint)
        //2個目
        canvas.drawArc((canvas.width/4).toFloat(),
            (canvas.width/4).toFloat(),
            ((canvas.width/4))*3.toFloat(),
            ((canvas.width/4))*3.toFloat(),
            30f,120f,
            true, mPaint)
        //3個目
        canvas.drawArc((canvas.width/4).toFloat(),
            (canvas.width/4).toFloat(),
            ((canvas.width/4))*3.toFloat(),
            ((canvas.width/4))*3.toFloat(),
            150f,120f,
            true, kPaint)

        canvas.restore()
    }

    fun setPositon(pos: Int) {
        position = pos
    }
    fun getPosition() :Int {
        return position
    }
}