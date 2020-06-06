package dev.binclub.bincommander.server

import dev.binclub.bincommander.interop.MinecraftProtocol

/**
 * @author cookiedragon234 05/Jun/2020
 */
object ServerManager {
	val srv = MinecraftProtocol.createServer(object {
		var version = "1.12.2"
	}.also {
		js("it['online-mode'] = false")
	}).apply {
		on("login") { client: MinecraftProtocol.Client ->
			client.on("error") { error: Throwable ->
				console.log(error)
			}
		}
	}
}
