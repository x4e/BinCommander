package dev.binclub.bincommander

import dev.binclub.bincommander.commands.CommandManager
import dev.binclub.bincommander.interop.*

/**
 * @author cookiedragon234 29/May/2020
 */
object BinCommander {
	val config = JSON.parse<BinCommanderConfig>(Fs.readFileSync("config.json", "utf8")).also {
		it.users.forEach {
			it.mcAccounts.forEach {
				it.instance = MinecraftAccountInstance()
			}
		}
	}
	val client = Discord.Client()
	
	init {
		require("debug").enable("minecraft-protocol")
		require("debug").enable("*")
		
		var shutdown = false
		val handler = {
			if (!shutdown) {
				shutdown = true
				Fs.writeFileSync("config.json", JSON.stringify(config, { key, value ->
					if (key.endsWith("instance")) {
						undefined
					} else {
						value
					}
				}, 4))
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
				for (user in config.users) {
					if (user.discordID == id) {
						val command = message.content.substringBefore(' ').substring(1)
						val args = message.content.substringAfter(' ').split(' ')
						val username = if (args.isNotEmpty()) args[0] else null
						val mcAccount = if (username != null) user.mcAccounts.firstOrNull { it.mcName == username } else null
						if (mcAccount == null) {
							CommandManager.invokeStaticCommand(message, command, args)
						} else {
							mcAccount.instance.commands.invoke(mcAccount, message, command, args)
						}
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
