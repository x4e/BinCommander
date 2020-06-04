package dev.binclub.bincommander.commands

import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.interop.Discord
import dev.binclub.bincommander.interop.MessageOptions
import dev.binclub.bincommander.modules.SpammerModule

/**
 * @author Robeart 1/06/2020
 */
class SpamCommand(user: MinecraftUserConfig) : Command("spam", user) {
	override fun invoke(message: Discord.Message, args: List<String>) {
		val module = user.modules[SpammerModule::class.js]
		module.enabled = !module.enabled
		message.reply("", options = MessageOptions().apply {
			reply = message.author
			embed = Discord.MessageEmbed().apply {
				addField("Success", "Spammer has been " + if (module.enabled) "enabled" else "disabled")
				setColor(if (module.enabled) "GREEN" else "RED")
			}
		})
	}
    
    override fun deserialize(obj: dynamic) {
    }
    override fun serialize(obj: dynamic) {
    }
}
