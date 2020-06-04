package dev.binclub.bincommander.commands

import dev.binclub.bincommander.BinCommander
import dev.binclub.bincommander.interop.Discord
import dev.binclub.bincommander.interop.MessageOptions

/**
 * @author cookiedragon234 29/May/2020
 */
object AccountsCommand: SingletonCommand("accounts") {
	override fun invoke(message: Discord.Message, args: List<String>) {
		val account = BinCommander.config.users.firstOrNull { it.discordID == message.author.id } ?: return
		
		message.reply("", options = MessageOptions().apply {
			reply = message.author
			embed = Discord.MessageEmbed().apply {
				setTitle("You have access to ${account.mcAccounts.size} Minecraft accounts: ")
				var thumbnail: String? = null
				account.mcAccounts.forEach { mcAccount ->
					addField(mcAccount.mcName.toString(), "https://namemc.com/${mcAccount.mcName}")
					thumbnail = "https://minotar.net/avatar/${mcAccount.mcName}"
				}
				if (thumbnail != null) {
					setImage(thumbnail.toString())
				}
			}
		})
	}
	
	override fun deserialize(obj: dynamic) {
	}
	override fun serialize(obj: dynamic) {
	}
}
