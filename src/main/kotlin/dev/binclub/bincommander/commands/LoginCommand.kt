package dev.binclub.bincommander.commands

import dev.binclub.bincommander.MinecraftAccountInstance
import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.UserConfig
import dev.binclub.bincommander.interop.*
import dev.binclub.bincommander.utils.betterToString

/**
 * @author cookiedragon234 29/May/2020
 */
class LoginCommand(instance: MinecraftAccountInstance): Command("login", instance) {
	override fun invoke(message: Discord.Message, account: MinecraftUserConfig, args: List<String>) {
		val originMessage = message.reply("", MessageOptions().apply {
			reply = message.author
			embed = Discord.MessageEmbed().apply {
				addField("Please Wait", "Logging in...")
			}
		})
		if (account.clientToken != null ) {
			originMessage.then {
				it.edit(MessageEditOptions().apply {
					embed = Discord.MessageEmbed().apply {
						addField("Success", "Already logged into ${account.mcName}")
						setDescription("Use logout command if the token has expired")
						setColor("GREEN")
					}
				})
			}
		} else {
			PrimarineTokens.use(PrismarineOptions(account.user, account.pass)) { err, opts ->
				if (err == null && opts.session != null) {
					account.mcName = opts.session?.selectedProfile?.name ?: account.mcName
					account.clientToken = opts.clientToken
					originMessage.then {
						it.edit(Discord.MessageEmbed().apply {
							addField("Success", "Logged into ${account.mcName}")
							setColor("GREEN")
						})
					}
				} else {
					originMessage.then {
						it.edit(Discord.MessageEmbed().apply {
							addField("Error", "Could not log into ${account.mcName}")
							addField("Cause", err.betterToString())
							setColor("RED")
						})
					}
				}
			}
		}
	}
}
