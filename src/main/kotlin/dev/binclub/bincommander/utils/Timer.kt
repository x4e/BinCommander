package dev.binclub.bincommander.utils

import kotlin.js.Date

/**
 * @author Robeart 1/06/2020
 */
class Timer {
    private var time: Double

    fun passed(ms: Double): Boolean {
        return  Date.now() - time >= ms
    }

    fun reset() {
        time = Date.now()
    }

    fun getTime(): Double {
        return time
    }

    fun setTime(time: Double) {
        this.time = time
    }

    init {
        time = -1.0
    }
}