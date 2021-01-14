package com.github.juliansobott.boardgames.activity

import com.github.juliansobott.boardgames.activity.utils.Vec2
import processing.core.PApplet

enum class Category {
    PANTOMIME, DRAWING, EXPLAINING
}

class Activity : PApplet() {

    private val drawables = arrayListOf<Drawable>()
    private var board = Board(Vec2(0F, 0F))

    override fun settings() {
        size(500, 500)
    }

    override fun setup() {
        board = Board(Vec2(width.toFloat(), height.toFloat()))
        drawables.add(board)
    }

    override fun draw() {
        background(100)
        drawables.forEach {
            it.draw(this)
        }
    }

    override fun mouseClicked() {
        board.setPlayerField(0, 5)
    }

    fun addDrawable(drawable: Drawable) {
        drawables.add(drawable)
    }
}

fun main(args: Array<String>) {
    PApplet.main("com.github.juliansobott.boardgames.activity.Activity")
}