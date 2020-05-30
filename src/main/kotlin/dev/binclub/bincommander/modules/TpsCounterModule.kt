package dev.binclub.bincommander.modules

import dev.binclub.bincommander.MinecraftAccountInstance
import dev.binclub.bincommander.interop.MinecraftProtocol
import dev.binclub.bincommander.interop.MinecraftProtocol.PacketMeta
import dev.binclub.bincommander.interop.Mineflayer
import dev.binclub.bincommander.interop.Mineflayer.Bot
import kotlin.js.Date

/**
 * @author cookiedragon234 30/May/2020
 */
class TpsCounterModule(instance: MinecraftAccountInstance): Module(instance) {
	private val tps = FloatArray(20) { 20f }
	private var lastUpdate: Double = -1.0
	private var next: Int = 0
	
	override fun onBotStart(bot: Bot) {
		tps.fill(20f)
		bot._client.on("packet") { data: Any, packetMeta: PacketMeta ->
			if (packetMeta.name == "update_time") {
				val now = Date.now()
				if (lastUpdate >= 0) {
					val delta = (Date.now() - lastUpdate) / 1000
					tps[next % 20] = (20f / delta).toFloat()
					next += 1
				}
				lastUpdate = now
			}
		}
	}
	
	fun getTps(): Float = if (tps.isNotEmpty()) tps.sum() / tps.size else 0f
}
