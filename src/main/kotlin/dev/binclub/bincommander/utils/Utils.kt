package dev.binclub.bincommander.utils

/**
 * @author cookiedragon234 29/May/2020
 */
fun betterToString(any: Any?) = any.betterToString()

fun Any?.betterToString(): String {
	return when (this) {
		null -> "null"
		this is Boolean -> this.toString()
		this is Int -> this.toString()
		this is String -> this.toString()
		this is Short -> this.toString()
		this is Long -> this.toString()
		this is Double -> this.toString()
		else -> this::class.js.name + ": " + JSON.stringify(this, null, 4)
	}
}
