package com.github.juliansobott.boardgames.activity

import com.github.juliansobott.boardgames.activity.utils.Vec2

/*
Abstraction classes to make it easier to get the positions of the fields
 */
interface IFieldPositionProvider {
    fun nextPosition(): Vec2
}

abstract class FieldPositionProvider(protected val boardSize: Vec2, protected val fieldSize: Vec2): IFieldPositionProvider

/*
Provides all fields in one vertical line. Ignores the board width.
Only for testing useful.
 */
class LineFieldPositionProvider(boardSize: Vec2, fieldSize: Vec2): FieldPositionProvider(boardSize, fieldSize) {

    private var nextX = 0F

    override fun nextPosition(): Vec2 {
        val next = Vec2(nextX, 0F)
        nextX += fieldSize.x
        return next
    }
}

/*
Provides all fields in a snake like line.
Grid based.
 */
class SnakeFieldPositionProvider(boardSize: Vec2, fieldSize: Vec2): FieldPositionProvider(boardSize, fieldSize) {

    private var lastPos = Vec2(0F, 0F)
    private var state = 0
    private val offset = 20

    /*
    States based on current field

    0 1 1 1 2
    6 5 5 4 3
    7 8 1 1 2
     */
    override fun nextPosition(): Vec2 {
        val next = when (state) {
            0 -> Vec2(0F, 0F)
            1 -> Vec2(lastPos.x + fieldSize.x, lastPos.y)
            2 -> Vec2(lastPos.x + fieldSize.x, lastPos.y + offset)
            3 -> Vec2(lastPos.x, lastPos.y + fieldSize.y)
            4 -> Vec2(lastPos.x - fieldSize.x, lastPos.y + offset)
            5 -> Vec2(lastPos.x - fieldSize.x, lastPos.y)
            6 -> Vec2(lastPos.x - fieldSize.x, lastPos.y + offset)
            7 -> Vec2(lastPos.x, lastPos.y + fieldSize.y)
            8 -> Vec2(lastPos.x + fieldSize.x, lastPos.y + offset)
            else -> throw AssertionError("State: $state should never be reached!")
        }
        if      (state == 0) state++
        else if (state == 1 && next.x + 3 * fieldSize.x > boardSize.x) state++
        else if (state == 2) state++
        else if (state == 3) state++
        else if (state == 4) state++
        else if (state == 5 && next.x - 2 * fieldSize.x < 0) state++
        else if (state == 6) state++
        else if (state == 7) state++
        else if (state == 8) state = 1

        lastPos = next
        return next
    }

}