package dev.binclub.bincommander.commands

import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.interop.Discord
import dev.binclub.bincommander.interop.MessageOptions
import dev.binclub.bincommander.modules.EchoModule
import dev.binclub.bincommander.modules.ModuleManager

/**
 * @author cookiedragon234 15/Jun/2020
 */
class EchoCommand(user: MinecraftUserConfig): Command("echo", user) {
	override fun invoke(message: Discord.Message, args: List<String>) {
		val module = user.modules[EchoModule::class.js]
		module.enabled = module.enabled.not()
		module.channel = message.channel
		val state = if (module.enabled) "enabled" else "disabled"
		message.reply("", MessageOptions().apply {
			reply = message.author
			embed = Discord.MessageEmbed().apply {
				addField("Success", "Module [${module.name}] is now $state")
				setColor("GREEN")
			}
		})
	}
	
	override fun deserialize(obj: dynamic) {
	}
	
	override fun serialize(obj: dynamic) {
	}
	
}
