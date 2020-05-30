package dev.binclub.bincommander.commands

import dev.binclub.bincommander.BinCommander
import dev.binclub.bincommander.MinecraftAccountInstance
import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.UserConfig
import dev.binclub.bincommander.interop.Discord
import dev.binclub.bincommander.interop.MessageOptions

/**
 * @author cookiedragon234 29/May/2020
 */
class CommandManager(val instance: MinecraftAccountInstance) {
	companion object {
		val singletonCommands = arrayOf<SingletonCommand>(
			AccountsCommand,
			StopCommand,
			PingCommand
		)
		
		fun invokeStaticCommand(message: Discord.Message, command: String, args: List<String>) {
			if (singletonCommands.firstOrNull { it.name == command }?.invoke(message, args) == null) {
				message.reply(options = MessageOptions().apply {
					embed = Discord.MessageEmbed().apply {
						addField("Result", "Could not find command `$command`")
					}
					reply = message.author
				})
			}
		}
	}
	
	val commands = arrayOf<Command>(
		LoginCommand(instance),
		LogoutCommand(instance),
		ConnectCommand(instance),
		DisconnectCommand(instance),
		TpsCommand(instance),
		VitalsCommand(instance),
		CoordsCommand(instance)
	)
	
	operator fun <T: Command> get(klass: JsClass<T>): T = commands.firstOrNull { it::class.js == klass }!! as T
	
	fun invoke(account: MinecraftUserConfig, message: Discord.Message, command: String, args: List<String>) {
		val id = message.author.id
		if (commands.firstOrNull { it.name == command }?.invoke(message, account, args) == null) {
			message.reply(options = MessageOptions().apply {
				embed = Discord.MessageEmbed().apply {
					addField("Result", "Could not find command `$command`")
				}
				reply = message.author
			})
		}
	}
}

abstract class SingletonCommand(val name: String) {
	abstract fun invoke(message: Discord.Message, args: List<String>)
}

abstract class Command(val name: String, val instance: MinecraftAccountInstance) {
	abstract fun invoke(message: Discord.Message, account: MinecraftUserConfig, args: List<String>)
}
