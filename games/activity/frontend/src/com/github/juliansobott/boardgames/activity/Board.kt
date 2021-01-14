package com.github.juliansobott.boardgames.activity

import com.github.juliansobott.boardgames.activity.utils.ColorRGBA
import com.github.juliansobott.boardgames.activity.utils.Vec2
import com.github.juliansobott.boardgames.activity.utils.fill
import processing.core.PApplet
import java.util.*
import kotlin.math.floor

class Board(boardSize: Vec2) : Drawable {

    private val ratios = hashMapOf(
        Category.EXPLAINING to 1,
        Category.PANTOMIME to 1,
        Category.DRAWING to 1,
    )
    private val numFields = 40
    private val fieldSize = Vec2(60F, 60F)
    private val playerSize = Vec2(30F, 30F)
    private val fieldsOrder = arrayListOf<Category>()
    private val fieldPositions = arrayListOf<Vec2>()
    private val players = hashMapOf<Int, Player>()

    init {
        val ratioSum = ratios.values.sum()
        var remainingFields = numFields
        for (ratio in ratios.entries) {
            val f = floor((ratio.value.toFloat() / ratioSum) * numFields).toInt()
            addFields(fieldsOrder, f, ratio.key)
            remainingFields -= f
        }
        if (remainingFields != 0) { // numFields is not a multiple of ratios
            // PANTOMIME is an arbitrary choice  and could be changed
            addFields(fieldsOrder, remainingFields, Category.PANTOMIME)
        }
        // shuffle fields
        for (i in (fieldsOrder.size - 1) downTo 1) {
            val j = (0..i).random()
            val temp = fieldsOrder[i]
            fieldsOrder[i] = fieldsOrder[j]
            fieldsOrder[j] = temp
        }

        val provider = SnakeFieldPositionProvider(boardSize, fieldSize)
        var pos: Vec2
        for (field in fieldsOrder) {
            pos = provider.nextPosition()
            fieldPositions.add(pos)
        }

        // DEBUG
        players[0] = Player(ColorRGBA(100, 0, 0), "Tom", 0, 0)
        players[1] = Player(ColorRGBA(0, 100, 0), "Yu", 1, 0)
        players[2] = Player(ColorRGBA(100, 0, 250), "Mii", 2, 0)
    }

    override fun draw(d: PApplet) {
        // Board
        for (fieldId in 0..numFields) {
            val pos = fieldPositions[fieldId]
            val color = when (fieldsOrder[fieldId]) {
                Category.PANTOMIME -> 100
                Category.DRAWING -> 200
                Category.EXPLAINING -> 0
            }
            d.fill(color)
            d.rect(pos.x, pos.y, fieldSize.x, fieldSize.y)
        }

        // Players
        for (player in players.values) {
            // TODO: maybe better handling of player index
            val pos = fieldPositions[player.fieldId] + (fieldSize / 2F) - (playerSize.x / 2) + (5 + player.index * 5F)
            fill(d, player.color)
            d.ellipse(pos.x, pos.y, playerSize.x, playerSize.y)
        }
    }

    fun setPlayerField(playerId: Int, fieldId: Int) {
        // TODO: Animate later on
        assert(playerId in players)
        players[playerId]?.fieldId = fieldId
    }

    private fun addFields(fields: ArrayList<Category>, num: Int, category: Category) {
        for (i in 0..num) {
            fields.add(category)
        }
    }
}


data class Player(val color: ColorRGBA, val name: String, val index: Int, var fieldId: Int)
