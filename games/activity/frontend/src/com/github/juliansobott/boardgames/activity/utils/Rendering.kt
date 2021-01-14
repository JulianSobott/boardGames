package com.github.juliansobott.boardgames.activity.utils

import processing.core.PApplet

data class ColorRGBA(val r: Int, val g: Int, val b: Int, val a: Int = 255)

fun fill(p: PApplet, c: ColorRGBA) {
    p.fill(c.r.toFloat(), c.g.toFloat(), c.b.toFloat(), c.a.toFloat())
}