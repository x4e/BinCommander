package dev.binclub.bincommander.commands

import dev.binclub.bincommander.MinecraftAccountInstance
import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.interop.Discord
import dev.binclub.bincommander.interop.MessageOptions
import dev.binclub.bincommander.interop.toFixed

/**
 * @author cookiedragon234 30/May/2020
 */
class CoordsCommand(instance: MinecraftAccountInstance): Command("coords", instance) {
	override fun invoke(message: Discord.Message, account: MinecraftUserConfig, args: List<String>) {
		val bot = instance.bot
		if (bot != null) {
			message.author.dmChannel.send("", options = MessageOptions().apply {
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
}
