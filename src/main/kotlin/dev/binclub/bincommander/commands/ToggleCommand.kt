package dev.binclub.bincommander.commands

import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.interop.Discord
import dev.binclub.bincommander.interop.MessageOptions
import dev.binclub.bincommander.modules.ToggleableModule

/**
 * @author cookiedragon234 14/Jun/2020
 */
class ToggleCommand(user: MinecraftUserConfig): Command("toggle", user) {
	override fun invoke(message: Discord.Message, args: List<String>) {
		if (args.isNotEmpty()) {
			val moduleName = args[0].trim()
			val module = user.modules.modules.firstOrNull { it.name.equals(moduleName, true) }
			if (module != null) {
				if (module is ToggleableModule) {
					module.enabled = module.enabled.not()
					val state = if (module.enabled) "enabled" else "disabled"
					message.reply("", MessageOptions().apply {
						reply = message.author
						embed = Discord.MessageEmbed().apply {
							addField("Success", "Module [${module.name}] is now $state")
							setColor("GREEN")
						}
					})
				} else {
					message.reply("", MessageOptions().apply {
						reply = message.author
						embed = Discord.MessageEmbed().apply {
							addField("Error", "Module [${module.name}] is not toggleable")
							setColor("RED")
						}
					})
				}
			} else {
				message.reply("", MessageOptions().apply {
					reply = message.author
					embed = Discord.MessageEmbed().apply {
						addField("Error", "Couldn't find module [$moduleName]")
						setColor("RED")
					}
				})
			}
		} else {
			message.reply("", MessageOptions().apply {
				reply = message.author
				embed = Discord.MessageEmbed().apply {
					addField("Error", "Please specify a module")
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
