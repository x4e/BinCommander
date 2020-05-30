package dev.binclub.bincommander.interop

/**
 * @author cookiedragon234 29/May/2020
 */
external fun require(module: String): dynamic
fun Any.hasProperty(name: String): Boolean {
	return this.asDynamic().hasOwnProperty(name) as Boolean
}
fun Number.toFixed(decimalPoints: Int = 0): String {
	return this.asDynamic().toFixed(decimalPoints) as String
}
