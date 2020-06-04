package dev.binclub.bincommander.modules

import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.Serializable
import dev.binclub.bincommander.alsoDeserialize
import dev.binclub.bincommander.alsoSerialize
import dev.binclub.bincommander.interop.Mineflayer.Bot

/**
 * @author cookiedragon234 30/May/2020
 */
class ModuleManager(val instance: MinecraftUserConfig): Serializable {
	val modules = arrayOf<Module>(
		TpsCounterModule(instance),
		SpammerModule(instance)
	)
	
	operator fun <T: Module> get(klass: JsClass<T>): T = modules.firstOrNull { it::class.js == klass }!! as T
	
	fun onBotStart(bot: Bot) {
		modules.forEach { it.onBotStart(bot) }
	}
	
	override fun deserialize(obj: dynamic) {
		if (obj == null) return
		modules.forEach {
			val lObj = obj
			val dyn = js("lObj[it.name]")
			it.alsoDeserialize(dyn)
		}
	}
	override fun serialize(obj: dynamic) {
		if (obj == null) return
		modules.forEach {
			val lObj = obj
			val dyn = it.alsoSerialize(object {})
			js("lObj[it.name] = dyn")
		}
	}
}

abstract class Module(val name: String, val instance: MinecraftUserConfig): Serializable {
	open fun onBotStart(bot: Bot) {}
}
abstract class ToggleableModule(name: String, instance: MinecraftUserConfig): Module(name, instance) {
	var enabled: Boolean = false
	
	override fun deserialize(obj: dynamic) {
		this.enabled = obj.enabled
	}
	override fun serialize(obj: dynamic) {
		obj.enabled = this.enabled
	}
}
