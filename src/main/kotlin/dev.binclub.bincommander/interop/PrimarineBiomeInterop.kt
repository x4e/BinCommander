package dev.binclub.bincommander.interop

/**
 * @author cookiedragon234 29/May/2020
 */
@JsModule("primarine-biome")
external class PrimarineBiome {
	class Biome {
		var id: Number
		var name: String
		var color: Number?
		var displayName: String?
		var rainfall: Number
		var temperature: Number
		var height: Number?
	}
}
