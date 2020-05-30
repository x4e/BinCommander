package dev.binclub.bincommander.utils

/**
 * @author cookiedragon234 29/May/2020
 */
fun Any?.betterToString(): String {
	return if (this == null) {
		"null"
	} else {
		this::class.js.name + ": " + JSON.stringify(this, null, 4)
	}
}
