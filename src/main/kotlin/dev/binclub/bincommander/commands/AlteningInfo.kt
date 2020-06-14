package dev.binclub.bincommander.commands

import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.interop.Discord
import dev.binclub.bincommander.interop.MessageOptions
import dev.binclub.bincommander.utils.betterToString

/**
 * @author cookiedragon234 14/Jun/2020
 */
class AlteningInfo(user: MinecraftUserConfig): Command("alteninginfo", user) {
	override fun invoke(message: Discord.Message, args: List<String>) {
		val altening = user.alteningAccount
		if (altening != null) {
			message.reply("", MessageOptions().apply {
				reply = message.author
				embed = Discord.MessageEmbed().apply {
					addField("Username", altening.username)
					addField("Limit", altening.limit)
					setColor("GREEN")
				}
			})
		} else {
			message.reply("", MessageOptions().apply {
				reply = message.author
				embed = Discord.MessageEmbed().apply {
					addField("Failure", "You are not logged into an altening account")
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
