package dev.binclub.bincommander.commands

import dev.binclub.bincommander.MinecraftAccountInstance
import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.UserConfig
import dev.binclub.bincommander.interop.Discord
import dev.binclub.bincommander.interop.MessageOptions

/**
 * @author cookiedragon234 29/May/2020
 */
class LogoutCommand(instance: MinecraftAccountInstance): Command("logout", instance) {
	override fun invoke(message: Discord.Message, account: MinecraftUserConfig, args: List<String>) {
		if (args.isNotEmpty()) {
			val username = args[0]
			
			if (account != null) {
				account.clientToken = null
				account.accessToken = null
				
				message.reply("", options = MessageOptions().apply {
					reply = message.author
					embed = Discord.MessageEmbed().apply {
						addField("Success", "Logged out of $username")
						setColor("GREEN")
					}
				})
			} else {
				message.reply("", options = MessageOptions().apply {
					reply = message.author
					embed = Discord.MessageEmbed().apply {
						addField("Error", "Could not find account $username")
						setColor("RED")
					}
				})
			}
		}
	}
}
