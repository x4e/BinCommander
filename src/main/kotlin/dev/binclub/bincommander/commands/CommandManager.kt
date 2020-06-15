package dev.binclub.bincommander.commands

import dev.binclub.bincommander.*
import dev.binclub.bincommander.interop.Discord
import dev.binclub.bincommander.interop.MessageOptions
import kotlin.js.Json

/**
 * @author cookiedragon234 29/May/2020
 */
class CommandManager(val instance: MinecraftUserConfig): Serializable {
	companion object {
		/**
		 * Singleton commands are not tied to any minecraft account, but are tied to the discord user that executes them
		 */
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
						setFooter("If you meant to invoke an account command make sure you spelled the account name correctly")
					}
					reply = message.author
				})
			}
		}
	}
	
	/**
	 * Regular commands are tied to a minecraft account
	 */
	val commands = arrayOf<Command>(
		LoginCommand(instance),
		LogoutCommand(instance),
		ConnectCommand(instance),
		DisconnectCommand(instance),
		TpsCommand(instance),
		VitalsCommand(instance),
		CoordsCommand(instance),
		SpamCommand(instance),
		AlteningInfo(instance),
		ToggleCommand(instance),
		ChatCommand(instance),
		EchoCommand(instance)
	)
	
	operator fun <T: Command> get(klass: JsClass<T>): T = commands.firstOrNull { it::class.js == klass }!! as T
	
	fun invoke(message: Discord.Message, command: String, args: List<String>) {
		if (commands.firstOrNull { it.name == command }?.invoke(message, args) == null) {
			message.reply(options = MessageOptions().apply {
				embed = Discord.MessageEmbed().apply {
					addField("Result", "Could not find command `$command`")
				}
				reply = message.author
			})
		}
	}
	
	override fun deserialize(obj: dynamic) {
		if (obj == null) return
		commands.forEach {
			val lObj = obj
			val dyn = js("lObj[it.name]")
			it.alsoDeserialize(dyn)
		}
	}
	override fun serialize(obj: dynamic) {
		if (obj == null) return
		commands.forEach {
			val lObj = obj
			val dyn = it.alsoSerialize(object {})
			js("lObj[it.name] = dyn")
		}
	}
}

abstract class SingletonCommand(val name: String): Serializable {
	abstract fun invoke(message: Discord.Message, args: List<String>)
}

abstract class Command(val name: String, val user: MinecraftUserConfig): Serializable {
	abstract fun invoke(message: Discord.Message, args: List<String>)
}
