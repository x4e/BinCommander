@file:Suppress("UnsafeCastFromDynamic")

package dev.binclub.bincommander

import dev.binclub.bincommander.commands.CommandManager
import dev.binclub.bincommander.interop.Fs
import dev.binclub.bincommander.interop.Mineflayer
import dev.binclub.bincommander.modules.ModuleManager

/**
 * @author cookiedragon234 29/May/2020
 */
interface Serializable {
	@Deprecated("use alsoDeserialize")
	fun deserialize(obj: dynamic) // Initialises this object with the serialized obj
	@Deprecated("use alsoSerialize")
	fun serialize(obj: dynamic) // Serializes itself into the given obj
}

fun <T: Serializable> T.alsoDeserialize(obj: dynamic): T = this.also { this.deserialize(obj) }
fun <T: Serializable> T.alsoSerialize(obj: dynamic): dynamic = obj.also { this.deserialize(obj) }

private const val configFile = "config.json"
private const val configCharset = "utf8"

fun readConfig(): BinCommanderConfig {
	val obj = JSON.parse<dynamic>(Fs.readFileSync("config.json", "utf8"))
	return BinCommanderConfig().alsoDeserialize(obj)
}

fun writeConfig() {
	Fs.writeFileSync("config.json", JSON.stringify(BinCommander.config.alsoSerialize(object {}), space = 4))
}

class BinCommanderConfig: Serializable {
	lateinit var discord: DiscordConfig
	lateinit var users: MutableList<UserConfig>
	override fun deserialize(obj: dynamic) {
		this.discord = DiscordConfig().alsoDeserialize(obj.discord)
		this.users = (obj.users as Array<UserConfig>).mapTo(ArrayList()) { UserConfig().alsoDeserialize(it) }
	}
	override fun serialize(obj: dynamic) {
		obj.discord = this.discord.serialize(object {})
		obj.users = this.users.map { it.serialize(object {}) }
	}
}

/**
 * Represents the discord configuration for BinCommander
 */
class DiscordConfig: Serializable {
	lateinit var token: String
	lateinit var prefix: String
	override fun deserialize(obj: dynamic) {
		this.token = obj.token
		this.prefix = obj.prefix
	}
	override fun serialize(obj: dynamic) {
		obj.token = this.token
		obj.prefix = this.prefix
	}
}

/**
 * Represents the configuration for a single discord user
 */
class UserConfig: Serializable {
	lateinit var discordID: String
	lateinit var mcAccounts: MutableList<MinecraftUserConfig>
	override fun deserialize(obj: dynamic) {
		this.discordID = obj.discordID
		this.mcAccounts = (obj.mcAccounts as Array<MinecraftUserConfig>).mapTo(ArrayList()) { MinecraftUserConfig().alsoDeserialize(it) }
	}
	override fun serialize(obj: dynamic) {
		obj.discordID = this.discordID
		obj.mcAccounts = this.mcAccounts
	}
}

/**
 * Represents the configuration for an MC Account. This MC Account can be owned by multiple UserConfigs
 */
class MinecraftUserConfig: Serializable {
	lateinit var user: String
	lateinit var pass: String
	var clientToken: String? = null
	var accessToken: String? = null
	var mcName: String? = null
	lateinit var commands: CommandManager
	lateinit var modules: ModuleManager
	
	fun setupNewBot(bot: Mineflayer.Bot) {
		this.bot = bot
		modules.onBotStart(bot)
	}
	
	var bot: Mineflayer.Bot? = null
	
	override fun deserialize(obj: dynamic) {
		this.user = obj.user
		this.pass = obj.pass
		this.clientToken = obj.clientToken
		this.accessToken = obj.accessToken
		this.mcName = obj.mcName
		this.commands = CommandManager(this).alsoDeserialize(obj.commands)
		this.modules = ModuleManager(this).alsoDeserialize(obj.modules)
	}
	override fun serialize(obj: dynamic) {
		obj.user = this.user
		obj.pass = this.pass
		obj.clientToken = this.clientToken
		obj.accessToken = this.accessToken
		obj.mcName = this.mcName
		obj.commands = this.commands.serialize(object {})
		obj.modules = this.modules.serialize(object {})
	}
}
