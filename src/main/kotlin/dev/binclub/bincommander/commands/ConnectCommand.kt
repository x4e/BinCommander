package dev.binclub.bincommander.commands

import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.integration.AlteningDataRetriever
import dev.binclub.bincommander.interop.*
import dev.binclub.bincommander.utils.betterToString

/**
 * @author cookiedragon234 29/May/2020
 */
class ConnectCommand(user: MinecraftUserConfig): Command("connect", user) {
	@Suppress("UnsafeCastFromDynamic")
	override fun invoke(message: Discord.Message, args: List<String>) {
		if (args.size >= 1) {
			val ip = args[0]
			val port = if (args.size > 1) args[1].toInt() else 25565
			
			if (user.clientToken != null) {
				val originMessage = message.reply("", MessageOptions().apply {
					reply = message.author
					embed = Discord.MessageEmbed().apply {
						addField("Please Wait", "Connecting...")
					}
				})
				try {
					val username = user.user
					val password = user.pass
					
					val options = MineflayerOptions(
						username = username,
						password = password,
						host = ip,
						clientToken = user.clientToken,
						accessToken = user.accessToken,
						version = "1.12.2",
						viewDistance = "far"
					).asDynamic()
					Mineflayer.createBot(
						options
					).let { bot ->
						println("Created bot")
						bot.asDynamic()["options"] = options
						user.setupNewBot(bot)
						bot.on("error") { err: Any ->
							originMessage.then {
								it.edit(MessageEditOptions().apply {
									embed = Discord.MessageEmbed().apply {
										setTitle("Error")
										var str = err.toString()
										if (str == "[object Object]") {
											str = err.betterToString()
										}
										addField("Reason", str)
										setColor("RED")
									}
								})
							}
							println(err.betterToString())
						}
						bot.on("kicked") { reason: String, loggedIn: Boolean ->
							println("Kicked " + reason.betterToString() + " " + loggedIn.betterToString())
							val message = JSON.parse<dynamic>(reason)
							originMessage.then {
								it.edit(MessageEditOptions().apply {
									embed = Discord.MessageEmbed().apply {
										setTitle("I was kicked!")
										addField("Reason", message?.text?.toString() ?: reason)
										setColor("RED")
									}
								})
							}
						}
						bot.on("login") {
							originMessage.then {
								it.edit(MessageEditOptions().apply {
									embed = Discord.MessageEmbed().apply {
										addField("Success", "${user.mcName} has logged in on $ip:$port")
										setColor("GREEN")
									}
								})
							}
						}
						bot.on("spawn") {
							originMessage.then {
								it.edit(MessageEditOptions().apply {
									embed = Discord.MessageEmbed().apply {
										addField("Success", "${user.mcName} has spawned in on $ip:$port")
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
	
	override fun deserialize(obj: dynamic) {
	}
	override fun serialize(obj: dynamic) {
	}
}
