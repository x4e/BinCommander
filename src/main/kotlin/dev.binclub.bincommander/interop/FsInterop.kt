package dev.binclub.bincommander.interop

/**
 * @author cookiedragon234 29/May/2020
 */
@JsModule("fs")
external class Fs {
	companion object {
		fun readFileSync(file: String, charset: String): String
		fun writeFileSync(file: String, data: String)
	}
}
