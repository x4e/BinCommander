package dev.binclub.bincommander.commands

import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.interop.Discord
import dev.binclub.bincommander.interop.MessageOptions

/**
 * @author cookiedragon234 29/May/2020
 */
class LogoutCommand(user: MinecraftUserConfig): Command("logout", user) {
	override fun invoke(message: Discord.Message, args: List<String>) {
		if (args.isNotEmpty()) {
			user.clientToken = null
			user.accessToken = null
			
			message.reply("", options = MessageOptions().apply {
				reply = message.author
				embed = Discord.MessageEmbed().apply {
					addField("Success", "Logged out of ${user.mcName}")
					setColor("GREEN")
				}
			})
		}
	}
	
	override fun deserialize(obj: dynamic) {
	}
	override fun serialize(obj: dynamic) {
	}
}
