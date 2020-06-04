package dev.binclub.bincommander.commands

import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.interop.Discord
import dev.binclub.bincommander.interop.MessageOptions
import dev.binclub.bincommander.interop.toFixed
import dev.binclub.bincommander.modules.TpsCounterModule

/**
 * @author cookiedragon234 30/May/2020
 */
class TpsCommand(user: MinecraftUserConfig): Command("tps", user) {
	override fun invoke(message: Discord.Message, args: List<String>) {
		val bot = user.bot
		
		if (bot != null) {
			val tps = user.modules[TpsCounterModule::class.js]
			message.reply("", MessageOptions().apply {
				reply = message.author
				embed = Discord.MessageEmbed().apply {
					addField("TPS", tps.getTps().toFixed(2))
				}
			})
		} else {
			message.reply("", MessageOptions().apply {
				reply = message.author
				embed = Discord.MessageEmbed().apply {
					addField("Failure", "You are not logged onto a server")
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
