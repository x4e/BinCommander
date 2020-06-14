@file:Suppress("UnsafeCastFromDynamic")

package dev.binclub.bincommander

import dev.binclub.bincommander.commands.CommandManager
import dev.binclub.bincommander.integration.AlteningAccount
import dev.binclub.bincommander.interop.Fs
import dev.binclub.bincommander.interop.Mineflayer
import dev.binclub.bincommander.modules.ModuleManager
import dev.binclub.bincommander.utils.betterToString

/**
 * @author cookiedragon234 29/May/2020
 */
interface Serializable {
	fun deserialize(obj: dynamic) // Initialises this object with the serialized obj
	fun serialize(obj: dynamic) // Serializes itself into the given obj
}

fun <T: Serializable> T.alsoDeserialize(obj: dynamic): T  {
	try {
		this.deserialize(obj)
	} catch (t: Throwable) {
		console.log(t)
	}
	return this
}
fun <T: Serializable> T.alsoSerialize(obj: dynamic): dynamic {
	try {
		serialize(obj)
	} catch (t: Throwable) {
		console.log(t)
	}
	return obj
}

private const val configFile = "config.json"
private const val configCharset = "utf8"

fun readConfig(): BinCommanderConfig {
	val obj = JSON.parse<dynamic>(Fs.readFileSync("config.json", "utf8"))
	return BinCommanderConfig().alsoDeserialize(obj)
}

fun writeConfig() {
	println("Wrote config")
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
		obj.discord = this.discord.alsoSerialize(object {})
		obj.users = this.users.map { it.alsoSerialize(object {}) }
	}
}

/**
 * Represents the discord configuration for BinCommander
 */
class DiscordConfig: Serializable {
	lateinit var token: String
	lateinit var admin: String
	lateinit var prefix: String
	override fun deserialize(obj: dynamic) {
		this.token = obj.token
		this.admin = obj.admin
		this.prefix = obj.prefix
	}
	override fun serialize(obj: dynamic) {
		obj.token = this.token
		obj.admin = this.admin
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
		obj.mcAccounts = this.mcAccounts.map { it.alsoSerialize(object {}) }
	}
}

/**
 * Represents the configuration for an MC Account. This MC Account can be owned by multiple UserConfigs
 */
class MinecraftUserConfig: Serializable {
	var user: String? = null
	var pass: String? = null
	var alteningToken: String? = null
	var alteningAccount: AlteningAccount? = null
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
		this.alteningToken = obj.alteningToken
		this.clientToken = obj.clientToken
		this.accessToken = obj.accessToken
		this.mcName = obj.mcName
		this.commands = CommandManager(this).alsoDeserialize(obj.commands)
		this.modules = ModuleManager(this).alsoDeserialize(obj.modules)
	}
	override fun serialize(obj: dynamic) {
		obj.user = this.user
		obj.pass = this.pass
		obj.alteningToken = this.alteningToken
		obj.clientToken = this.clientToken
		obj.accessToken = this.accessToken
		obj.mcName = this.mcName
		obj.commands = this.commands.alsoSerialize(object {})
		obj.modules = this.modules.alsoSerialize(object {})
	}
}
