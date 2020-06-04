package dev.binclub.bincommander

import dev.binclub.bincommander.commands.CommandManager
import dev.binclub.bincommander.interop.*

/**
 * @author cookiedragon234 29/May/2020
 */
object BinCommander {
	val config = readConfig()
	val client = Discord.Client()
	
	init {
		require("debug").enable("minecraft-protocol")
		require("debug").enable("*")
		
		var shutdown = false
		val handler = {
			println("Shutdown handler")
			if (!shutdown) {
				shutdown = true
				writeConfig()
			}
		}
		Process.on("SIGINT", handler)
		Process.on("SIGTERM", handler)
		Process.on("SIGHUP", handler)
		Process.on("SIGBREAK", handler)
		Process.on("SIGUSR1", handler)
		Process.on("SIGUSR2", handler)
		Process.on("exit", handler)
		
		client.on("ready") {
			println("Logged in as ${client.user.tag}")
			
			client.user.setPresence(JSON.parse("""{ "activity": { "name": "minecraft" }, "status": "online" }""".trimIndent()))
		}
		
		client.on("message") { message: Discord.Message ->
			if (!message.author.bot && message.content.startsWith(config.discord.prefix)) {
				val id = message.author.id
				val user = config.users.firstOrNull { it.discordID == id }
				if (user == null) {
					// User is not registered
				} else {
					val command = message.content.substringBefore(' ').substring(config.discord.prefix.length)
					val args = message.content.substringAfter(' ').split(' ')
					val username = if (args.isNotEmpty()) args[0] else null
					val mcAccount = if (username != null) user.mcAccounts.firstOrNull { it.mcName == username } else null
					if (mcAccount == null) {
						CommandManager.invokeStaticCommand(message, command, args)
					} else {
						mcAccount.commands.invoke(message, command, args)
					}
				}
			}
		}
		
		client.login(config.discord.token)
	}
}

fun main() {
	BinCommander
}
