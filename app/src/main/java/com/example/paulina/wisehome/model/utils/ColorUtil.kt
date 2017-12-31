package com.example.paulina.wisehome.model.utils

import android.graphics.Color
import com.example.paulina.wisehome.model.transportobjects.RGBColor

object ColorUtil {

    fun intToHex(colorInt: Int): String {
        return java.lang.Integer.toHexString(colorInt)
    }

    fun intToRgb(colorInt: Int): RGBColor {
        return RGBColor(Color.red(colorInt).toString(), Color.green(colorInt).toString(), Color.blue(colorInt).toString())
    }

    fun rgbToInt(rgbColor: RGBColor): Int {
        var Red: Float = rgbColor.red.toFloat()
        var Green: Float = rgbColor.green.toFloat()
        var Blue: Float = rgbColor.blue.toFloat()
        var R = Math.round(255 * Red)
        var G = Math.round(255 * Green)
        var B = Math.round(255 * Blue)

        R = R shl 16 and 0x00FF0000
        G = G shl 8 and 0x0000FF00
        B = B and 0x000000FF

        return -0x1000000 or R or G or B
    }
}