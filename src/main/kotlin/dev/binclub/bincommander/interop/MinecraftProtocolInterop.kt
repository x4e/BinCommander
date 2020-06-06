package dev.binclub.bincommander.interop

/**
 * @author cookiedragon234 29/May/2020
 */
@JsModule("minecraft-protocol")
external class MinecraftProtocol {
	class Client: EventEmitter {
		constructor(isServer: Boolean, version: String, customPackets: Any?)
		var isServer: Boolean
		var latency: Number
		var profile: Any
		var session: Any
		var socket: dynamic
		var state: States
		var username: String
		var uuid: String
		var protocolVersion: Number
		fun connect(port: Number, host: String)
		fun end(reason: String)
		fun registerChannel(name: String, typeDefinition: Any, custom: Boolean?)
		fun unregisterChannel(name: String)
		fun write(name: String, params: Any)
		fun writeRaw(buffer: Any)
		fun writeChannel(channel: Any, params: Any)
		/*
		on(event: 'error', listener: (error: Error) => void): this
		on(event: 'packet', handler: (data: any, packetMeta: PacketMeta) => void): this
		on(event: 'raw', handler: (data: any, packetMeta: PacketMeta) => void): this
		on(event: 'session', handler: (session: any) => void): this
		on(event: 'state', handler: (newState: States, oldState: States) => void): this
		on(event: 'end', handler: (reason: string) => void): this
		on(event: string, handler: (data: any, packetMeta: PacketMeta)=> unknown): this
		 */
	}
	interface ClientOptions {
		var accessToken: String?
		var checkTimeoutInterval: Number?
		var clientToken: String?
		var customPacket: Any?
		var hideErrors: Boolean?
		var host: String?
		var keepAlive: Boolean?
		var password: String?
		var port: Number?
		var username: String
		var version: String?
		var skipValidation: Boolean?
		var stream: dynamic? // Stream
		var connect: ((client: Client) -> Unit)? // Client
		var agent: dynamic? // agent
	}
	class Server: EventEmitter {
		constructor(version: String, customPackets: Any?)
		var clients: dynamic // ClientsMap
		var favicon: String
		var maxPlayers: Number
		var motd: String
		var onlineModeExceptions: Any
		var playerCount: Number
		fun close()
		/*
		on(event: 'connection', handler: (client: Client) => void): this
		on(event: 'error', listener: (error: Error) => void): this
		on(event: 'login', handler: (client: Client) => void): this
		 */
	}
	interface ServerOptions {
		var checkTimeoutInterval: Number?
		var customPacket: Any?
		var hideErrors: Boolean?
		var host: String?
		var keepAlive: Boolean?
		var kickTimeout: Number?
		var maxPlayers: Number?
		var motd: String?
		var port: Number?
		var version: String?
		var beforePing: ((response: Any, client: Client, callback: ((result: Any) -> Any)?) -> Any)?
		var errorHandler: ((client: Client, error: Error) -> Unit)?
		var agent: dynamic? // Agent
	}
	interface SerializerOptions {
		var customPackets: Any
		var isServer: Boolean?
		var state: States?
		var version: String
	}
	enum class States {
		HANDSHAKING,
		LOGIN,
		PLAY,
		STATUS
	}
	interface PacketMeta {
		var name: String
		var state: States
	}
	interface PingOptions {
		var host: String?
		var majorVersion: String?
		var port: Number?
		var protocolVersion: String?
		var version: String?
	}
	interface PingResult
	interface OldPingResult: PingResult {
		var maxPlayers: Number
		var motd: String
		var playerCount: Number
		var prefix: String
		var protocol: String
		var version: String
	}
	interface NewPingResult: PingResult {
		var description: String?
		var players: PlayersPingInfo
		var version: VersionPingInfo
		var favicon: String
		var latency: Number
		interface VersionPingInfo {
			var name: String
			var protocol: String
		}
		interface PlayersPingInfo {
			var max: Number
			var online: Number
			var sample: Array<PlayerPingInfo>?
			interface PlayerPingInfo {
				var id: String
				var name: String
			}
		}
	}
	companion object {
		val supportedVersions: Array<String>
		fun createServer(options: ServerOptions): Server
		fun createServer(options: dynamic): Server
		fun createClient(options: ClientOptions): Client
		fun createSerializer(options: SerializerOptions): Any
		fun createDeserializer(options: SerializerOptions): Any
		fun ping(options: PingOptions, callback: (error: Error?, result: PingResult) -> Unit)
	}
}
