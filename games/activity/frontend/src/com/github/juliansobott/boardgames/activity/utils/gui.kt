package com.github.juliansobott.boardgames.activity.utils

import com.github.juliansobott.boardgames.activity.Drawable
import com.github.juliansobott.boardgames.activity.InputHandler
import processing.core.PApplet
import kotlin.math.max


open class TextWidget(private val pos: Vec2, private var text: String) : Drawable {

    private var fontSize: Int = 18
    private var size: Vec2 = Vec2(max(100F, text.length * 18F), fontSize.toFloat()) // TODO: char width
    private var fontColor = ColorRGBA(0, 0, 0)
    private var backgroundColor = ColorRGBA(200, 200, 200)


    override fun draw(d: PApplet) {
        fill(d, backgroundColor)
        d.rect(pos.x, pos.y, size.x, size.y)
        fill(d, fontColor)
        d.textSize(fontSize.toFloat())
        d.text(text, pos.x + 3F, pos.y + fontSize - 3F)  // -3F for some kind of centering/padding
    }

    fun text(t: String) {
        text = t
    }

    fun fontSize(size: Int) {
        fontSize = size
    }

    fun fontColor(colorRGBA: ColorRGBA) {
        fontColor = colorRGBA
    }

    fun backgroundColor(colorRGBA: ColorRGBA) {
        backgroundColor = colorRGBA
    }
}

// TODO: maybe PApplet.textWidget
fun textWidget(pos: Vec2, text: String, op: TextWidget.() -> Unit = {}): TextWidget {
    val t = TextWidget(pos, text)
    op(t)
    return t
}

class Button(pos: Vec2, text: String, private val handle: () -> Unit) : TextWidget(pos, text), ClickHandler {

    private val fontSize = 18
    private var size: Vec2 = Vec2(max(100F, text.length * 18F), fontSize.toFloat()) // TODO: char width

    init {
        InputHandler.add(pos, size, this)
    }

    override fun onClick() {
        handle()
    }
}


interface ClickHandler {

    fun onClick()
}