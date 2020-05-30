package dev.binclub.bincommander

import dev.binclub.bincommander.interop.Mineflayer
import dev.binclub.bincommander.interop.Mineflayer.Bot

/**
 * @author cookiedragon234 29/May/2020
 */
external class BinCommanderConfig {
	val discord: DiscordConfig
	val users: Array<UserConfig>
}

external class DiscordConfig {
	val token: String
	val prefix: String
}

external class UserConfig {
	val discordID: String
	val mcAccounts: Array<MinecraftUserConfig>
}

external class MinecraftUserConfig {
	var user: String
	var pass: String
	var clientToken: String?
	var accessToken: String?
	var mcName: String?
	var instance: MinecraftAccountInstance
}
