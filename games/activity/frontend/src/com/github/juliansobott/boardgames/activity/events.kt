package com.github.juliansobott.boardgames.activity

object EventManager {

    private val subscriptions = hashMapOf<Events, ArrayList<(Event) -> Unit>>()

    fun publish(type: Events, data: Event) {
        for (e in subscriptions[type]!!) {
            e(data)
        }
    }

    fun <T : Event> subscribe(event: Events, handler: (T) -> Unit) {
        event.javaClass
        if (event !in subscriptions) {
            subscriptions[event] = arrayListOf()
        }
        subscriptions[event]?.add(handler as (Event) -> Unit)
    }
}


interface Event

data class NewWord(val word: String) : Event
class Timeout : Event
class StartTimer : Event
class RequestStartRond : Event
class RequestNextWord : Event

enum class Events {
    NEW_WORD,
    TIMEOUT,
    START_TIMER,
    REQUEST_START_ROUND,
    REQUEST_NEXT_WORD,
}

