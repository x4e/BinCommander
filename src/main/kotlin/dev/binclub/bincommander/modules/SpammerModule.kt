package dev.binclub.bincommander.modules

import dev.binclub.bincommander.BinCommander
import dev.binclub.bincommander.MinecraftAccountInstance
import dev.binclub.bincommander.interop.MinecraftProtocol
import dev.binclub.bincommander.interop.Mineflayer
import dev.binclub.bincommander.utils.Timer
import kotlin.js.Date
import kotlin.random.Random

/**
 * @author Robeart 1/06/2020
 */
class SpammerModule(instance: MinecraftAccountInstance): ToggleableModule(instance) {
    private val timer = Timer()

    override fun onBotStart(bot: Mineflayer.Bot) {
        val account = BinCommander.config.users.firstOrNull { it.mcAccounts.any { it.instance == instance } } ?: return // maybe exception/message in discord instead of return?
        bot._client.on("packet") { data: Any, packetMeta: MinecraftProtocol.PacketMeta ->
            if (packetMeta.name == "update_time" && this.enabled) {
                if(timer.passed(account.spamdelay.toDouble() * 1000)) {
                    bot.chat(account.spam.random())
                    timer.reset()
                }
            }
        }
    }
}