package dev.binclub.bincommander.modules

import dev.binclub.bincommander.BinCommander
import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.interop.Discord
import dev.binclub.bincommander.interop.MessageOptions
import dev.binclub.bincommander.interop.Mineflayer
import dev.binclub.bincommander.interop.toFixed

/**
 * @author cookiedragon234 15/Jun/2020
 */
class EchoModule(instance: MinecraftUserConfig) : ToggleableModule("echo", instance) {
	var channel: Discord.TextChannel? = null
	
	override fun onBotStart(bot: Mineflayer.Bot) {
		bot.on("chat") { username: String, message: String, translate: String?, jsonMsg: String, matches: Array<String> ->
			onChat(username, message)
		}
		bot.on("whisper") { username: String, message: String, translate: String?, jsonMsg: String, matches: Array<String> ->
			onChat(username, message, colour = "LUMINOUS_VIVID_PINK") // lol
		}
	}
	
	private fun onChat(username: String, message: String, colour: String = "DEFAULT") {
		if (this.enabled) {
			channel?.send("", options = MessageOptions().apply {
				embed = Discord.MessageEmbed().apply {
					var escapedMessage = message
					//escapedMessage = escapedMessage.replace(">", "\\>")
					//escapedMessage = escapedMessage.replace("/", "\\/")
					setTitle(escapedMessage)
					if (!username.isBlank()) {
						setAuthor(username, "https://minotar.net/avatar/$username")
					}
					setColor(colour)
				}
			})
		}
	}
	
	override fun serialize(obj: dynamic) {
		obj.channel = channel?.id
	}
	
	override fun deserialize(obj: dynamic) {
		val channelId = obj.channel as? String
		if (channelId != null) {
			BinCommander.client.channels.forEach { channel ->
				if (channel.id == channelId && channel is Discord.TextChannel) {
					this.channel = channel
				}
			}
		}
	}
}
