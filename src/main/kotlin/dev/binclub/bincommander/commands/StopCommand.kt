package dev.binclub.bincommander.commands

import dev.binclub.bincommander.BinCommander
import dev.binclub.bincommander.interop.Discord
import dev.binclub.bincommander.interop.Process

/**
 * @author cookiedragon234 29/May/2020
 */
object StopCommand: SingletonCommand("stop") {
	override fun invoke(message: Discord.Message, args: List<String>) {
		if (message.author.id == "226635735350902785") {
			Process.exit(0)
		}
	}
}
