package com.github.juliansobott.boardgames.activity

import processing.core.PApplet

interface Drawable {
    fun draw(d: PApplet): Unit
}