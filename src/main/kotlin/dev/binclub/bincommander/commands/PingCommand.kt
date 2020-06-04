package dev.binclub.bincommander.commands

import dev.binclub.bincommander.interop.*
import dev.binclub.bincommander.interop.MinecraftProtocol.NewPingResult
import dev.binclub.bincommander.interop.MinecraftProtocol.OldPingResult
import dev.binclub.bincommander.utils.betterToString

/**
 * @author cookiedragon234 29/May/2020
 */
object PingCommand: SingletonCommand("ping") {
	@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
	override fun invoke(message: Discord.Message, args: List<String>) {
		if (args.isNotEmpty()) {
			val originMessage = message.reply("", MessageOptions().apply {
				reply = message.author
				embed = Discord.MessageEmbed().apply {
					addField("Please Wait", "Pinging...")
				}
			})
			
			val host = args[0]
			val port = if (args.size > 1) args[1].toInt() else 25565
			
			val options = object: MinecraftProtocol.PingOptions {
				override var host: String? = host
				override var majorVersion: String? = null
				override var port: Number? = port
				override var protocolVersion: String? = null
				override var version: String? = null
			}
			MinecraftProtocol.ping(options) { error, result ->
				if (error == null) {
					try {
						if (result.hasProperty("latency")) {
							result as NewPingResult
							
							originMessage.then {
								it.edit(MessageEditOptions().apply {
									embed = Discord.MessageEmbed().apply {
										var desc = result.description?.asDynamic()?.text as? String
										if (desc.isNullOrBlank()) {
											desc = result.description?.betterToString() ?: "Undefined"
										}
										addField("description", desc)
										addField("players online", result.players.online)
										addField("players max", result.players.max)
										addField(
											"players sample",
											result.players.sample?.joinToString(transform = { it.name }) ?: "Undefined"
										)
										addField("version", "${result.version.name} ${result.version.protocol}")
										addField("latency", result.latency)
										setColor("GREEN")
										println("Favicon: ${result.favicon.betterToString()}")
										attachFiles(
											arrayOf(
												FileOptions(
													Buffer(result.favicon, "base64"), // result.favicon is a base64 image string
													"favicon.png"
												)
											)
										)
										setImage("attachment://favicon.png")
										setFooter("favicon", "attachment://favicon.png")
										println(this.files.betterToString())
										println(this.betterToString())
									}
								})
							}
						} else {
							result as OldPingResult
							
							originMessage.then {
								it.edit(MessageEditOptions().apply {
									embed = Discord.MessageEmbed().apply {
										addField("maxPlayers", result.maxPlayers)
										addField("motd", result.motd)
										addField("playerCount", result.playerCount)
										addField("prefix", result.prefix)
										addField("protocol", result.protocol)
										addField("version", result.version)
										setColor("GREEN")
									}
								})
							}
						}
						return@ping
					} catch (err: Throwable) {
						println(err.betterToString())
					}
				}
				originMessage.then {
					it.edit(MessageEditOptions().apply {
						embed = Discord.MessageEmbed().apply {
							addField("Failed", "Encountered an error")
							setColor("RED")
						}
					})
				}
			}
		} else {
			message.reply("", options = MessageOptions().apply {
				reply = message.author
				embed = Discord.MessageEmbed().apply {
					addField("Error", "Usage `+ping [host] [port?]`")
				}
			})
		}
	}
	
	override fun deserialize(obj: dynamic) {
	}
	override fun serialize(obj: dynamic) {
	}
}
