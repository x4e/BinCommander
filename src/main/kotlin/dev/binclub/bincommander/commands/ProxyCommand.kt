package dev.binclub.bincommander.commands

import dev.binclub.bincommander.UserConfig
import dev.binclub.bincommander.interop.Discord

/**
 * @author cookiedragon234 30/May/2020
 */
object ProxyCommand: SingletonCommand("proxy") {
	override fun invoke(message: Discord.Message, args: List<String>) {
		TODO("Not yet implemented")
	}
	
	override fun deserialize(obj: dynamic) {
	}
	override fun serialize(obj: dynamic) {
	}
}
