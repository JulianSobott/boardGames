package com.github.juliansobott.boardgames.activity

import processing.core.PApplet
import kotlin.jvm.JvmStatic

enum class Category {
    PANTOMIME, DRAWING, EXPLAINING
}

class Activity : PApplet() {

    private val drawables = arrayListOf<Drawable>()

    override fun settings() {
        size(500, 500)
    }

    override fun setup() {
        drawables.add(Board())
    }

    override fun draw() {
        background(100)
        drawables.forEach {
            it.draw(this)
        }
    }

    fun addDrawable(drawable: Drawable) {
        drawables.add(drawable)
    }
}

fun main(args: Array<String>) {
    PApplet.main("com.github.juliansobott.boardgames.activity.Activity")
}