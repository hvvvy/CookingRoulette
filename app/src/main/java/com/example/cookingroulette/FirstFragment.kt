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
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController


class FirstFragment : Fragment() {
    var position = 0
        private set

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val rouletteView: RouletteView = RouletteView(activity)
        //setContentView(rouletteView)

        // 最終position
        val endPosition = 1000
        val rouletteAnimation = RouletteAnimation(rouletteView, endPosition)
        // アニメーションの起動期間を設定
        rouletteAnimation.setDuration(10000)
        rouletteView.startAnimation(rouletteAnimation)

        return inflater.inflate(R.layout.fragment_first, container, false)
    }
    inner class RouletteView(context: Context?) : View(context) {
        var paint = Paint()
        var mPaint = Paint()
        var kPaint = Paint()


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.startButton).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}