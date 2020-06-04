package dev.binclub.bincommander.commands

import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.interop.*
import dev.binclub.bincommander.utils.betterToString

/**
 * @author cookiedragon234 29/May/2020
 */
class LoginCommand(user: MinecraftUserConfig): Command("login", user) {
	override fun invoke(message: Discord.Message, args: List<String>) {
		val originMessage = message.reply("", MessageOptions().apply {
			reply = message.author
			embed = Discord.MessageEmbed().apply {
				addField("Please Wait", "Logging in...")
			}
		})
		if (user.clientToken != null ) {
			originMessage.then {
				it.edit(MessageEditOptions().apply {
					embed = Discord.MessageEmbed().apply {
						addField("Success", "Already logged into ${user.mcName}")
						setFooter("Use logout command if the token has expired")
						setColor("GREEN")
					}
				})
			}
		} else {
			PrimarineTokens.use(PrismarineOptions(user.user, user.pass)) { err, opts ->
				if (err == null && opts.session != null) {
					user.mcName = opts.session?.selectedProfile?.name ?: user.mcName
					user.clientToken = opts.clientToken
					originMessage.then {
						it.edit(Discord.MessageEmbed().apply {
							addField("Success", "Logged into ${user.mcName}")
							setColor("GREEN")
						})
					}
				} else {
					originMessage.then {
						it.edit(Discord.MessageEmbed().apply {
							addField("Error", "Could not log into ${user.mcName}")
							addField("Cause", err.betterToString())
							setColor("RED")
						})
					}
				}
			}
		}
	}
	
	override fun deserialize(obj: dynamic) {
	}
	override fun serialize(obj: dynamic) {
	}
}
