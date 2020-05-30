package dev.binclub.bincommander.interop

import kotlin.js.Date
import kotlin.js.Promise

@JsModule("discord.js")
external class Discord {
	
	companion object {
		val version: String
	}
	
	class MessageAttachment {
		constructor(attachment: Any, name: String? = definedExternally, data: dynamic? = definedExternally)
		
		var height: Number?
		var id: String
		var name: String?
		var proxyURL: String
		var size: Number
		val spoiler: Boolean?
		var url: String
		var width: Number?
		
		fun setFile(attachment: Any, name: String? = definedExternally): MessageAttachment
		fun setName(name: String): MessageAttachment
	}
	
	open class Channel {
		val client: Client
		val createdAt: Date
		val createdTimestamp: Number
		val deleted: Boolean
		val id: String
		val type: String
		
		fun delete(): Promise<Channel>
	}
	
	class CategoryChannel : GuildChannel
	
	class Client {
		val channels: Collection<String, Channel>
		val guilds: Collection<String, Guild>
		val user: ClientUser
		val users: Collection<String, User>
		val ping: Number
		
		fun destroy(): Promise<*>
		fun login(token: String): Promise<String>
		fun on(event: String, cb: (item: dynamic) -> Unit)
	}
	
	class ClientUser : User {
		fun setPresence(data: dynamic): Promise<ClientUser>
		fun setUsername(username: String, password: String = definedExternally): Promise<ClientUser>
	}
	
	class Collection<K, V> {
		val size: Number
		
		fun get(key: K): V
		fun filter(func: (item: V) -> Unit): Array<V>
		fun find(func: (item: V) -> Unit): V
		fun forEach(func: (item: V) -> Unit)
		fun first(): V
	}
	
	class DMChannel : Channel
	
	class Guild : UserResolvable {
		val channels: Collection<String, GuildChannel>
		val me: GuildMember
		
		fun leave(): Promise<Guild>
		fun setName(name: String): Promise<Guild>
	}
	
	open class GuildChannel : Channel {
		val calculatedPosition: Number
		val deletable: Boolean
		val guild: Guild
		val manageable: Boolean
		val name: String
		val parent: CategoryChannel?
		val parentID: String?
		val permissionOverwrites: Collection<String, PermissionOverwrites>
		val position: Number
		
		fun fetchInvites(): Promise<Collection<String, Invite>>
	}
	
	class Invite {
		val channel: Channel
		val code: String
		val guild: Guild
	}
	
	class Message : UserResolvable {
		val attachments: Collection<String, dynamic>
		val author: User
		val channel: TextChannel
		val cleanContent: String
		val client: Client
		val content: String
		val createdAt: Date
		val createdTimestamp: Number
		val deletable: Boolean
		val deleted: Boolean
		val guild: Guild
		val id: String
		
		fun edit(content: String): Promise<Message>
		
		fun edit(embed: MessageEmbed): Promise<Message>
		fun edit(content: String, embed: MessageEmbed): Promise<Message>
		
		fun edit(attachment: MessageAttachment): Promise<Message>
		fun edit(content: String, attachment: MessageAttachment): Promise<Message>
		
		fun edit(options: MessageEditOptions): Promise<Message>
		fun edit(content: String, options: MessageEditOptions): Promise<Message>
		
		fun react(emoji: String): Promise<MessageReaction>
		
		fun reply(content: Any = definedExternally): Promise<Message>
		fun reply(content: Any = definedExternally, options: MessageOptionsResolvable = definedExternally): Promise<Message>
		fun reply(content: Any = definedExternally, options: Array<out MessageOptionsResolvable> = definedExternally)
	}
	
	/**
	 * MessageOptions or MessageAdditions
	 */
	interface MessageOptionsResolvable
	
	class MessageEmbed: MessageOptionsResolvable {
		var author: MessageEmbedAuthor?
		var color: Number?
		val createdAt: Date?
		var description: String?
		var fields: Array<EmbedFieldData>
		var files: Array<Any>
		var footer: MessageEmbedFooter?
		val hexColor: String?
		var image: MessageEmbedImage?
		val length: Number
		var provider: MessageEmbedProvider?
		var thumbnail: MessageEmbedThumbnail?
		var timestamp: Number?
		var title: String?
		/**
		 * Either rich, image, video, gifv, article, link
		 */
		var type: String
		var url: String?
		var video: MessageEmbedVideo
		fun addField(name: Any, value: Any, inline: Boolean = definedExternally): MessageEmbed
		fun addFields(vararg fields: EmbedFieldData): MessageEmbed
		fun addFields(fields: Array<EmbedFieldData>): MessageEmbed
		fun attachFiles(files: Array<String>): MessageEmbed
		fun attachFiles(files: Array<MessageAttachment>): MessageEmbed
		fun attachFiles(files: Array<FileOptions>): MessageEmbed
		fun setAuthor(name: Any, iconURL: String = definedExternally, url: String = definedExternally): MessageEmbed
		/**
		 * https://discord.js.org/#/docs/main/stable/typedef/ColorResolvable
		 */
		fun setColor(color: Any): MessageEmbed
		fun setDescription(description: Any): MessageEmbed
		fun setFooter(text: Any, iconURL: String = definedExternally): MessageEmbed
		fun setImage(url: String): MessageEmbed
		fun setThumbnail(url: String): MessageEmbed
		fun setTimestamp(timestamp: Date = definedExternally): MessageEmbed
		fun setTitle(title: Any): MessageEmbed
		fun setURL(url: String): MessageEmbed
		fun toJSON(): Any
	}
	
	class MessageEmbedVideo {
		var url: String
		var proxyURL: String
		var height: Number
		var width: Number
	}
	
	class MessageEmbedThumbnail {
		var url: String
		var proxyURL: String
		var height: Number
		var width: Number
	}
	
	class MessageEmbedProvider {
		var name: String
		var url: String
	}
	
	class MessageEmbedImage {
		var url: String
		var proxyURL: String
		var height: Number
		var width: Number
	}
	
	class MessageEmbedFooter {
		var text: String
		var iconURL: String
		var proxyIconURL: String
	}
	
	class EmbedFieldData {
		var name: String
		var value: String
		var inline: Boolean
	}
	
	class MessageEmbedAuthor {
		var name: String
		var url: String
		var iconURL: String
		var proxyIconUrl: String
	}
	
	class MessageReaction {
		val count: Number
		val emoji: dynamic
		val me: Boolean
		val message: Message
		val users: Collection<String, User>
	}
	
	class PermissionOverwrites {
		var allowed: Permissions
		val channel: GuildChannel
		var denied: Permissions
		var id: String
		var type: String
		
		fun delete(): Promise<PermissionOverwrites>
		fun delete(reason: String): Promise<PermissionOverwrites>
	}
	
	class Permissions {
		var bitfield: Number
		
		fun add(vararg permissions: dynamic): Permissions
		fun freeze(): Permissions
		fun has(permission: dynamic): Boolean
		fun has(permission: dynamic, checkAdmin: Boolean): Boolean
		fun missing(permission: dynamic): dynamic
		fun missing(permission: dynamic, checkAdmin: Boolean): dynamic
		fun remove(vararg permissions: dynamic): Permissions
		
		// static fields
		companion object {
			val ALL: Number
			val DEFAULT: Number
			val FLAGS: dynamic
		}
	}
	
	class Presence
	
	interface TextBasedChannel {
		val lastMessage: Message?
		val lastPinAt: Date?
		val typing: Boolean
		val typingCount: Number
		fun awaitMessages(filter: Function<Boolean>)
	}
	
	open class TextChannel : GuildChannel {
		fun send(content: String): Promise<Message>
		
		fun send(embed: MessageEmbed): Promise<Message>
		fun send(content: String, embed: MessageEmbed): Promise<Message>
		
		fun send(attachment: MessageAttachment): Promise<Message>
		fun send(content: String, attachment: MessageAttachment): Promise<Message>
		
		fun send(options: MessageOptions): Promise<Message>
		fun send(content: String, options: MessageOptions): Promise<Message>
	}
	
	open class User : UserResolvable {
		val avatar: String
		val avatarURL: String?
		val bot: Boolean
		val client: Client
		val createdAt: Date
		val createdTimestamp: Number
		val defaultAvatarURL: String
		val discriminator: String
		val displayAvatarURL: String
		val dmChannel: DMChannel
		val id: String
		val lastMessage: Message
		val lastMessageID: String
		val presence: Presence
		val tag: String
		val username: String
	}
	
	open class GuildMember {
		fun edit(data: JSON): Promise<GuildMember>
	}
	
	// Things that can be resolved
	interface UserResolvable
}

data class MessageEditOptions(
	var content: String? = null,
	var embed: Any? = null,
	var code: Any? = null
)

data class MessageOptions(
	var tts: Boolean = false,
	var content: String = "",
	var embed: Discord.MessageEmbed? = null,
	var reply: Discord.UserResolvable? = null
): Discord.MessageOptionsResolvable

data class FileOptions(
	var attachment: Any,
	var name: String = "file.jpg"
)
