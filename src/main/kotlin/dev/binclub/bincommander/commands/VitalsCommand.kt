package dev.binclub.bincommander.commands

import dev.binclub.bincommander.MinecraftAccountInstance
import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.interop.Discord
import dev.binclub.bincommander.interop.MessageOptions
import dev.binclub.bincommander.interop.toFixed
import dev.binclub.bincommander.modules.TpsCounterModule

/**
 * @author cookiedragon234 30/May/2020
 */
class VitalsCommand(instance: MinecraftAccountInstance): Command("vitals", instance) {
	override fun invoke(message: Discord.Message, account: MinecraftUserConfig, args: List<String>) {
		val bot = account.instance.bot
		
		if (bot != null) {
			message.reply("", MessageOptions().apply {
				reply = message.author
				embed = Discord.MessageEmbed().apply {
					addField("Username", bot.username.toString())
					addField("Health", bot.health.toFixed(2))
					addField("Food", bot.food.toFixed(2))
					addField("Saturation", bot.foodSaturation.toFixed(2))
					val gameState = bot.game
					addField("Mode", gameState.gameMode)
					addField("Dimension", gameState.dimension)
					addField("Latency", bot._client.latency)
					val tps = instance.modules[TpsCounterModule::class.js]
					addField("TPS", tps.getTps().toFixed(2))
					setColor("GREEN")
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
}
