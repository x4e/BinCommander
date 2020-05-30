package dev.binclub.bincommander.modules

import dev.binclub.bincommander.MinecraftAccountInstance
import dev.binclub.bincommander.interop.Mineflayer
import dev.binclub.bincommander.interop.Mineflayer.Bot

/**
 * @author cookiedragon234 30/May/2020
 */
class ModuleManager(val instance: MinecraftAccountInstance) {
	val modules = arrayOf<Module>(
		TpsCounterModule(instance)
	)
	
	operator fun <T: Module> get(klass: JsClass<T>): T = modules.firstOrNull { it::class.js == klass }!! as T
	
	fun onBotStart(bot: Bot) {
		modules.forEach { it.onBotStart(bot) }
	}
}

abstract class Module(val instance: MinecraftAccountInstance) {
	open fun onBotStart(bot: Bot) {}
}
abstract class ToggleableModule(instance: MinecraftAccountInstance): Module(instance) {
	var enabled: Boolean = false
}
