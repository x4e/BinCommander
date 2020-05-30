package dev.binclub.bincommander

import dev.binclub.bincommander.commands.CommandManager
import dev.binclub.bincommander.interop.Mineflayer.Bot
import dev.binclub.bincommander.modules.ModuleManager

/**
 * @author cookiedragon234 30/May/2020
 */
class MinecraftAccountInstance {
	val commands = CommandManager(this)
	val modules = ModuleManager(this)
	
	fun setupNewBot(bot: Bot) {
		this.bot = bot
		modules.onBotStart(bot)
	}
	
	var bot: Bot? = null
}
