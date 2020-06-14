package dev.binclub.bincommander.modules

import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.interop.Mineflayer

/**
 * @author cookiedragon234 14/Jun/2020
 */
class AutoReconnectModule(instance: MinecraftUserConfig) : ToggleableModule("autoreconnect", instance) {
	override fun onBotStart(bot: Mineflayer.Bot) {
		bot.on("kicked") { reason: String, loggedIn: Boolean ->
			if (this.enabled) {
				val options = bot.asDynamic()["options"].unsafeCast<Mineflayer.BotOptions>()
				bot.connect(options)
			}
		}
	}
}
