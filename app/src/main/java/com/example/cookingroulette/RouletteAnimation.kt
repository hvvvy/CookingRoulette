package com.example.cookingroulette

import android.util.Log
import android.view.animation.Animation
import android.view.animation.Transformation



class RouletteAnimation internal constructor(rouletteView: RouletteView, pos: Int) : Animation() {
    private var currentPosition = 0
    private var endPosition = 0
    private val rouletteView: RouletteView

    override fun applyTransformation(interpolatedTime: Float, transformation: Transformation) {
        // interpolatedTime: 0.f -> 1.0f
        val pp = ((endPosition - currentPosition) * interpolatedTime).toInt()

        // 矩形のY軸位置をセット
        rouletteView.setPositon(pp)
        rouletteView.requestLayout()
        Log.d("debug", " showPp = ${pp}")
    }

    init {
        currentPosition = rouletteView.getPosition()
        endPosition = pos
        this.rouletteView = rouletteView
    }
}