package dev.binclub.bincommander.interop

/**
 * @author cookiedragon234 29/May/2020
 */
@JsModule("process")
external class Process {
	companion object {
		fun on(name: String, handler: () -> Unit)
		fun exit(code: Int)
	}
}
