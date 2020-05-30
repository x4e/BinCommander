package dev.binclub.bincommander.commands

import dev.binclub.bincommander.MinecraftAccountInstance
import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.UserConfig
import dev.binclub.bincommander.interop.*
import dev.binclub.bincommander.modules.ModuleManager
import dev.binclub.bincommander.utils.betterToString

/**
 * @author cookiedragon234 29/May/2020
 */
class ConnectCommand(instance: MinecraftAccountInstance): Command("connect", instance) {
	@Suppress("UnsafeCastFromDynamic")
	override fun invoke(message: Discord.Message, account: MinecraftUserConfig, args: List<String>) {
		if (args.size >= 2) {
			val ip = args[1]
			val port = if (args.size > 2) args[2].toInt() else 25565
			
			if (account.clientToken != null) {
				val originMessage = message.reply("", MessageOptions().apply {
					reply = message.author
					embed = Discord.MessageEmbed().apply {
						addField("Please Wait", "Connecting...")
					}
				})
				try {
					Mineflayer.createBot(MineflayerOptions(
						username = account.user,
						password = account.pass,
						host = ip,
						clientToken = account.clientToken,
						accessToken = account.accessToken,
						version = "1.12.2",
						viewDistance = "far"
					).asDynamic()).let { bot ->
						println("Created bot")
						account.instance.setupNewBot(bot)
						bot.on("error") { err: Any ->
							println("ERROR: $err")
							println(err.betterToString())
						}
						bot.on("kicked") { reason: String, loggedIn: Boolean ->
							println("Kicked " + reason.betterToString() + " " + loggedIn.betterToString())
							val message = JSON.parse<dynamic>(reason)
							message.reply("", MessageOptions().apply {
								reply = message.author
								embed = Discord.MessageEmbed().apply {
									setTitle("I was kicked!")
									addField("Reason", message?.text?.toString() ?: reason)
									setColor("RED")
								}
							})
						}
						bot.on("login") {
							originMessage.then {
								it.edit(MessageEditOptions().apply {
									embed = Discord.MessageEmbed().apply {
										addField("Success", "${account.mcName} has logged in on $ip:$port")
										setColor("GREEN")
									}
								})
							}
						}
						bot.on("spawn") {
							originMessage.then {
								it.edit(MessageEditOptions().apply {
									embed = Discord.MessageEmbed().apply {
										addField("Success", "${account.mcName} has spawned in on $ip:$port")
										setColor("GREEN")
									}
								})
							}
						}
						bot.on("connect") {
						}
						bot.on("disconnect") { packet: dynamic ->
						}
						bot.on("end") {
						}
						bot.on("chat") { packet: dynamic ->
						}
					}
				} catch (err: Throwable) {
					console.log(err)
					originMessage.then {
						it.edit(MessageEditOptions().apply {
							embed = Discord.MessageEmbed().apply {
								addField("Failed", "Encountered an error")
								addField("Error", err.betterToString())
								setColor("RED")
							}
						})
					}
				}
			} else {
				message.reply("", options = MessageOptions().apply {
					reply = message.author
					embed = Discord.MessageEmbed().apply {
						addField("Error", "Please login to this account first")
					}
				})
			}
		} else {
			message.reply("", options = MessageOptions().apply {
				reply = message.author
				embed = Discord.MessageEmbed().apply {
					addField("Error", "Please provide a username and ip")
				}
			})
		}
	}
}
