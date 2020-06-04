package dev.binclub.bincommander.commands

import dev.binclub.bincommander.BinCommander
import dev.binclub.bincommander.MinecraftAccountInstance
import dev.binclub.bincommander.MinecraftUserConfig
import dev.binclub.bincommander.interop.Discord
import dev.binclub.bincommander.interop.MessageOptions
import dev.binclub.bincommander.interop.toFixed
import dev.binclub.bincommander.modules.SpammerModule

/**
 * @author Robeart 1/06/2020
 */
class SpamCommand(instance: MinecraftAccountInstance) : Command("spam", instance) {

    override fun invoke(message: Discord.Message, account: MinecraftUserConfig, args: List<String>) {
        val module: SpammerModule = instance.modules.modules.firstOrNull { it is SpammerModule } as SpammerModule
        module.enabled = !module.enabled
        message.reply("", options = MessageOptions().apply {
            reply = message.author
            embed = Discord.MessageEmbed().apply {
                addField("Success", "Spam has been " + if(module.enabled) "enabled" else "disable")
                setColor(if(module.enabled) "GREEN" else "RED")
            }
        })
    }

}