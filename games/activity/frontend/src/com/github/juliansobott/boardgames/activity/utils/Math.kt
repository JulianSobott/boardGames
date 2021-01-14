package com.github.juliansobott.boardgames.activity.utils

data class Vec2(val x: Float, val y: Float) {
    operator fun plus(that: Vec2): Vec2 {
        return Vec2(x + that.x, y + that.y)
    }

    operator fun plus(that: Float): Vec2 {
        return Vec2(x + that, y + that)
    }

    operator fun minus(that: Vec2): Vec2 {
        return Vec2(x - that.x, y - that.y)
    }

    operator fun minus(that: Float): Vec2 {
        return Vec2(x - that, y - that)
    }

    operator fun times(that: Float): Vec2 {
        return Vec2(x * that, y * that)
    }

    operator fun div(that: Float): Vec2 {
        return Vec2(x / that, y / that)
    }

}
