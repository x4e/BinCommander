package dev.binclub.bincommander.commands

import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.interop.*

/**
 * @author cookiedragon234 30/May/2020
 */
class DisconnectCommand(user: MinecraftUserConfig): Command("disconnect", user) {
	override fun invoke(message: Discord.Message, args: List<String>) {
		val bot = user.bot
		if (bot != null) {
			val originMessage = message.reply("", MessageOptions().apply {
				reply = message.author
				embed = Discord.MessageEmbed().apply {
					addField("Please Wait", "Connecting...")
				}
			})
			bot.on("end") { reason: String ->
				originMessage.then {
					it.edit(MessageEditOptions().apply {
						embed = Discord.MessageEmbed().apply {
							addField("Success", "Disconnected ${user.mcName}")
							setColor("GREEN")
						}
					})
				}
			}
			bot._client.end("Disconnecting")
		} else {
			message.reply("", options = MessageOptions().apply {
				reply = message.author
				embed = Discord.MessageEmbed().apply {
					addField("Error", "$${user.mcName} is not connected to a server")
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
