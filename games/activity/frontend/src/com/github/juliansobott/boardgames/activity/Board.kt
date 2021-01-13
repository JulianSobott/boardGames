package com.github.juliansobott.boardgames.activity

import com.github.juliansobott.boardgames.activity.utils.Vec2
import processing.core.PApplet
import java.util.ArrayList
import kotlin.math.floor
import kotlin.random.Random

class Board : Drawable {

    private val ratios = hashMapOf(
        Category.EXPLAINING to 1,
        Category.PANTOMIME to 1,
        Category.DRAWING to 1,
    )
    private val numFields = 40
    private val fieldSize = Vec2(60F, 60F)
    private val fields = arrayListOf<Category>()

    init {
        val ratioSum = ratios.values.sum()
        var remainingFields = numFields
        for (ratio in ratios.entries) {
            val f = floor((ratio.value.toFloat() / ratioSum) * numFields).toInt()
            addFields(fields, f, ratio.key)
            remainingFields -= f
        }
        if (remainingFields != 0) { // numFields is not a multiple of ratios
            // PANTOMIME is an arbitrary choice  and could be changed
            addFields(fields, remainingFields, Category.PANTOMIME)
        }
        // shuffle fields
        for (i in (fields.size - 1) downTo 1) {
            val j = (0..i).random()
            val temp = fields[i]
            fields[i] = fields[j]
            fields[j] = temp
        }
    }

    override fun draw(d: PApplet) {
        val provider = SnakeFieldPositionProvider(Vec2(d.width.toFloat(), d.height.toFloat()), fieldSize)
        var pos: Vec2
        for (field in fields) {
            pos = provider.nextPosition()
            val color = when (field) {
                Category.PANTOMIME -> 100
                Category.DRAWING -> 200
                Category.EXPLAINING -> 0
            }
            d.fill(color)
            d.rect(pos.x, pos.y, fieldSize.x, fieldSize.y)
        }
    }

    private fun addFields(fields: ArrayList<Category>, num: Int, category: Category): Unit {
        for (i in 0..num) {
            fields.add(category)
        }
    }
}

