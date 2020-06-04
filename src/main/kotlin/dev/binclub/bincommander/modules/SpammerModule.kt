package dev.binclub.bincommander.modules

import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.interop.MinecraftProtocol
import dev.binclub.bincommander.interop.Mineflayer
import dev.binclub.bincommander.utils.Timer

/**
 * @author Robeart 1/06/2020
 */
class SpammerModule(instance: MinecraftUserConfig) : ToggleableModule("Spammer", instance) {
	private val timer = Timer()
    
    var spamDelay: Int = 10000 // 10 seconds
    var messages: MutableList<String> = ArrayList()
	
	override fun onBotStart(bot: Mineflayer.Bot) {
		bot._client.on("packet") { data: Any, packetMeta: MinecraftProtocol.PacketMeta ->
			if (this.enabled) {
				if (timer.passed(spamDelay.toDouble() * 1000)) {
					bot.chat(messages.random())
					timer.reset()
				}
			}
		}
	}
	
	override fun deserialize(obj: dynamic) {
		super.deserialize(obj)
		this.spamDelay = obj.spamDelay
		this.messages = (obj.messages as Array<String>).toMutableList()
	}
	override fun serialize(obj: dynamic) {
		super.serialize(obj)
		obj.spamDelay = this.spamDelay
		obj.messages = this.messages.toTypedArray()
	}
}
