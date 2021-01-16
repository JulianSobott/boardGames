package com.github.juliansobott.boardgames.activity

import com.github.juliansobott.boardgames.activity.utils.Button
import com.github.juliansobott.boardgames.activity.utils.TextWidget
import com.github.juliansobott.boardgames.activity.utils.Vec2
import com.github.juliansobott.boardgames.activity.utils.textWidget
import processing.core.PApplet

class GameArea(private val windowSize: Vec2) : Drawable {

    private val board: Board
    private val timerWidget: TimerWidget
    private val wordWidget: WordWidget
    private val nextWordButton: Button
    private val startTimerButton: Button
    private val currentPlayerWidget: TextWidget


    init {
        val boardSize = Vec2(windowSize.x / 2, windowSize.y)
        board = Board(Vec2(windowSize.x / 2, 0F), boardSize)
        timerWidget = TimerWidget(Vec2(0F, 100F))
        wordWidget = WordWidget(Vec2(0F, 200F))
        nextWordButton = Button(Vec2(0F, 300F), "NextWord") { println("Clicked button") }   // TODO: next word
        startTimerButton = Button(Vec2(0F, 400F), "Start") { println("Clicked button") }   // TODO: start
        currentPlayerWidget = textWidget(Vec2(0F, 50F), "TODO: CurrentPlayer")
    }

    override fun draw(d: PApplet) {
        board.draw(d)
        timerWidget.draw(d)
        wordWidget.draw(d)
        nextWordButton.draw(d)
        startTimerButton.draw(d)
        currentPlayerWidget.draw(d)
    }
}


class TimerWidget(pos: Vec2) : TextWidget(pos, "00:00") {

    private val initialTime = 1 * 60 * 1000 // 1 minute
    private var timeStart: Long = 0
    private var running = false

    fun start() {
        timeStart = System.currentTimeMillis()
        running = true
    }

    override fun draw(d: PApplet) {
        var text = "00:00"
        if (running) {
            val remaining = initialTime - (System.currentTimeMillis() - timeStart)
            if (remaining <= 0) {
                running = false
            } else {
                text = "${remaining / 1000}"
            }
        }
        text(text)
        super.draw(d)
    }
}


class WordWidget(pos: Vec2) : TextWidget(pos, "") {

    private var showing = false
    private var word = "PampelmusenPflücker"

    fun showWord(w: String) {
        showing = true
        word = w
    }

    fun hide() {
        showing = false
    }

    override fun draw(d: PApplet) {
        if (showing) {
            text(word)
            super.draw(d)
        }
    }

}