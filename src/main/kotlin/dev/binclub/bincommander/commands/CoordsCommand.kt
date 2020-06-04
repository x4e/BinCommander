package dev.binclub.bincommander.commands

import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.interop.Discord
import dev.binclub.bincommander.interop.MessageOptions
import dev.binclub.bincommander.interop.toFixed

/**
 * @author cookiedragon234 30/May/2020
 */
class CoordsCommand(user: MinecraftUserConfig): Command("coords", user) {
	override fun invoke(message: Discord.Message, args: List<String>) {
		val bot = user.bot
		if (bot != null) {
			message.reply("", options = MessageOptions().apply {
				reply = message.author
				embed = Discord.MessageEmbed().apply {
					val pos = bot.entity.position
					addField("Location", "${bot.username} is at ${pos.x.toFixed()}, ${pos.y.toFixed()}, ${pos.z.toFixed()} (${bot.game.dimension})")
					setColor("GREEN")
				}
			})
		} else {
			message.reply("", options = MessageOptions().apply {
				reply = message.author
				embed = Discord.MessageEmbed().apply {
					addField("Error", "The account is not connected to a server")
					setColor("RED")
				}
			})
		}
	}
	
	override fun deserialize(obj: dynamic) {
	}
	override fun serialize(obj: dynamic) {
	}
}
