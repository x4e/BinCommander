package dev.binclub.bincommander.commands

import dev.binclub.bincommander.MinecraftAccountInstance
import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.UserConfig
import dev.binclub.bincommander.interop.*
import dev.binclub.bincommander.utils.betterToString

/**
 * @author cookiedragon234 30/May/2020
 */
class DisconnectCommand(instance: MinecraftAccountInstance): Command("disconnect", instance) {
	override fun invoke(message: Discord.Message, account: MinecraftUserConfig, args: List<String>) {
		val bot = instance.bot
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
							addField("Success", "Disconnected ${account.mcName}")
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
					addField("Error", "$${account.mcName} is not connected to a server")
					setColor("RED")
				}
			})
		}
	}
}
