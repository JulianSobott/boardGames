package com.github.juliansobott.boardgames.activity

import com.github.juliansobott.boardgames.activity.utils.ClickHandler
import com.github.juliansobott.boardgames.activity.utils.Vec2
import processing.core.PApplet

enum class Category {
    PANTOMIME, DRAWING, EXPLAINING
}

class Activity : PApplet() {

    private val drawables = arrayListOf<Drawable>()

    override fun settings() {
        size(920, 700)
    }

    override fun setup() {
        val gameArea = GameArea(Vec2(width.toFloat(), height.toFloat()))
        drawables.add(gameArea)
    }

    override fun draw() {
        background(100)
        drawables.forEach {
            it.draw(this)
        }
    }

    override fun mouseClicked() {
        InputHandler.onClick(Vec2(mouseX.toFloat(), mouseY.toFloat()))
    }

    fun addDrawable(drawable: Drawable) {
        drawables.add(drawable)
    }
}

object InputHandler {

    private val items = arrayListOf<RectObject>()


    fun onClick(pos: Vec2) {
        for (i in items) {
            if (i.dimensions.pos.x <= pos.x && i.dimensions.pos.y <= pos.y &&
                i.dimensions.pos.x + i.dimensions.size.x >= pos.x && i.dimensions.pos.y + i.dimensions.size.y >= pos.y
            ) {
                i.clickHandler.onClick()
            }
        }
    }

    fun add(pos: Vec2, size: Vec2, handler: ClickHandler) {
        items.add(RectObject(Dimensions(pos, size), handler))
    }

    data class Dimensions(val pos: Vec2, val size: Vec2)

    data class RectObject(val dimensions: Dimensions, val clickHandler: ClickHandler)
}


fun main(args: Array<String>) {
    PApplet.main("com.github.juliansobott.boardgames.activity.Activity")
}