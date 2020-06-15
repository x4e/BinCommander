package dev.binclub.bincommander.commands

import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.interop.Discord
import dev.binclub.bincommander.interop.MessageOptions
import dev.binclub.bincommander.interop.toFixed

/**
 * @author cookiedragon234 15/Jun/2020
 */
class ChatCommand(user: MinecraftUserConfig) : Command("chat", user) {
	override fun invoke(message: Discord.Message, args: List<String>) {
		val text = args.joinToString(" ").trim().replace("`", "")
		if (text.isBlank()) {
			message.reply("", options = MessageOptions().apply {
				reply = message.author
				embed = Discord.MessageEmbed().apply {
					addField("Failure", "Please specify a message to send")
					setColor("RED")
				}
			})
		} else {
			val bot = user.bot
			if (bot == null) {
				message.reply("", options = MessageOptions().apply {
					reply = message.author
					embed = Discord.MessageEmbed().apply {
						addField("Failure", "No active bot")
						setColor("RED")
					}
				})
			} else {
				bot.chat(text)
			}
		}
	}
	
	override fun deserialize(obj: dynamic) {
	}
	
	override fun serialize(obj: dynamic) {
	}
}
