package dev.binclub.bincommander.interop

import dev.binclub.bincommander.interop.MinecraftProtocol.Client
import dev.binclub.bincommander.interop.MinecraftProtocol.ClientOptions
import dev.binclub.bincommander.interop.PrismarineBlock.Block
import dev.binclub.bincommander.interop.PrismarineEntity.Entity
import dev.binclub.bincommander.interop.PrismarineItem.Item
import dev.binclub.bincommander.interop.PrismarineRecipe.Recipe
import dev.binclub.bincommander.interop.PrismarineWindows.Window
import kotlin.js.RegExp

/**
 * @author cookiedragon234 29/May/2020
 */
@JsModule("mineflayer")
external class Mineflayer {
	companion object {
		fun createBot(options: BotOptions): Bot
	}
	
	interface BotOptions: ClientOptions {
		var logErrors: Boolean?
		var loadInternalPlugins: Boolean?
		var plugins: Map<String, Boolean>?
		var chat: String? // enabled/commandsOnly/disabled
		var colorsEnabled: Boolean?
		var viewDistance: String? // far/normal/short/tiny
		var difficulty: Number?
		var showCape: Boolean?
		var chatLengthLimit: Number?
	}
	
	class Bot: EventEmitter {
		var username: String?
		var protocolVersion: String
		var majorVersion: String
		var version: String
		var entity: Entity
		var entities: Map<String, Entity>
		var spawnPoint: Vec3
		var game: GameState
		var player: Player
		var players: Map<String, Player>
		var isRaining: Boolean
		var chatPatterns: Array<ChatPattern>
		var settings: GameSettings
		var experience: Experience
		var health: Number
		var food: Number
		var foodSaturation: Number
		var physics: PhysicsOptions
		var time: Time
		var quickBarSlot: Number
		var inventory: Window
		var targetDigBlock: Block
		var isSleeping: Boolean
		var scoreboards: Map<String, ScoreBoard>
		var scoreboard: Map<dynamic, ScoreBoard>
		var controlState: ControlStateStatus
		var creative: creativeMethods
		var _client: Client
		
		fun connect(options: BotOptions)
		fun supportFeature(feature: String): Boolean
		fun end()
		fun blockAt(point: Vec3): Block?
		fun blockInSight(maxSteps: Number, vectorLength: Number): Block?
		fun canSeeBlock(block: Block): Boolean
		fun canDigBlock(block: Block): Boolean
		fun recipesFor(itemType: Number, metadata: Number?, minResultCount: Number?, craftingTable: Block?): Array<Recipe>
		fun recipesAll(itemType: Number, metadata: Number?, craftingTable: Block?): Array<Recipe>
		fun quit(reason: String?)
		fun tabComplete(str: String, cb: (matches: Array<String>) -> Unit, assumeCommand: Boolean?, sendBlockInSight: Boolean?)
		fun chat(message: String)
		fun whisper(username: String, message: String)
		fun chatAddPattern(pattern: RegExp, chatType: String, description: String?)
		fun setSettings(options: GameSettings)
		fun loadPlugin(plugin: (bot: Bot, options: BotOptions) -> Unit)
		fun loadPlugins(plugins: Array<(bot: Bot, options: BotOptions) -> Unit>)
		fun sleep(bedBlock: Block, cb: ((err: Error?) -> Unit)?)
		fun isABd(bedBlock: Block)
		fun wake(cb: ((err: Error?) -> Unit)?)
		fun setControlState(control: String, state: Boolean)
		fun clearControlStates()
		fun lookAt(point: Vec3, force: Boolean?, callback: (() -> Unit)?)
		fun look(yaw: Number, pitch: Number, force: Boolean?, callback: (() -> Unit)?)
		fun updateSign(block: Block, text: String, callback: ((error: Error?) -> Unit)?)
		fun equip(item: Item, destination: String?, callback: (() -> Unit)?)
		fun unequip(destination: String?, callback: (() -> Unit)?)
		fun tossStack(item: Item, callback: ((error: Error?) -> Unit)?)
		fun toss(itemType: Number, metadata: Number?, count: Number?, callback: ((err: Error?) -> Unit)?)
		fun dig(block: Block, callback: ((error: Error?) -> Unit)?)
		fun stopDigging()
		fun digTime(block: Block): Number
		fun placeBlock(referenceBlock: Block, faceVector: Vec3, cb: () -> Unit)
		fun activateBlock(block: Block, callback: ((err: Error?) -> Unit)?)
		fun activateEntity(block: Block, callback: ((err: Error?) -> Unit)?)
		fun consume(callback: (err: Error?) -> Unit)
		fun fish(callback: (err: Error?) -> Unit)
		fun activateItem()
		fun deactivateItem()
		fun useOn(targetEntity: Entity)
		fun attack(entity: Entity)
		fun swingArm(hand: String?) // left/right
		fun mount(entity: Entity)
		fun dismount()
		fun moveVehicle(left: Number, forward: Number)
		fun setQuickBarSlot(slot: Number)
		fun craft(recipe: Recipe, count: Number?, craftingTable: Block, callback: (() -> Unit)?)
		fun writeBook(slot: Number, pages: Array<String>, callback: ((err: Error?) -> Unit)?)
		fun openChest(chest: Block): Chest
		fun openChest(chest: Entity): Chest
		fun openFurnace(furnace: Block): Furnace
		fun openDispenser(dispenser: Block): Dispenser
		fun openEnchantmentTable(enchantmentTable: Block): EnchantmentTable
		fun openVillager(villager: Entity, cb: ((err: Error?, villager: Villager) -> Unit)?): Villager
		fun trade(villagerInstance: Villager, tradeIndex: String, times: Number?, cb: ((err: Error?) -> Unit)?)
		fun trade(villagerInstance: Villager, tradeIndex: Number, times: Number?, cb: ((err: Error?) -> Unit)?)
		fun setCommandBlock(pos: Vec3, command: String, trackOutput: Boolean)
		fun clickWindow(slot: Number, mouseButton: Number, mode: Number, cb: ((err: Error?) -> Unit)?)
		fun putSelectedItemRange(start: Number, end: Number, window: Window, slot: Any, cb: ((err: Error?) -> Unit)?)
		fun putAway(slot: Number, cb: ((err: Error?) -> Unit)?)
		fun closeWindow(window: Window)
		fun transfer(options: TransferOptions, cb: ((err: Error?) -> Unit)?)
		fun openBlock(block: Block, Class: EventEmitter)
		fun openEntity(block: Entity, Class: EventEmitter)
		fun moveSlotItem(sourceSlot: Number, destSlot: Number, cb: ((err: Error?) -> Unit)?)
		fun updateHeldItem()
		/*
		on(
    event: "chat",
    listener: (
      username: string,
      message: string,
      translate: string | null,
      jsonMsg: string,
      matches: Array<String> | null
    ) => void
  ): this;

  on(
    event: "whisper",
    listener: (
      username: string,
      message: string,
      translate: string | null,
      jsonMsg: string,
      matches: Array<String> | null
    ) => void
  ): this;

  on(event: "actionBar", listener: (jsonMsg: string) => void): this;

  on(event: "message", listener: (jsonMsg: string) => void): this;

  on(event: "login", listener: () => void): this;

  on(event: "spawn", listener: () => void): this;

  on(event: "respawn", listener: () => void): this;

  on(event: "game", listener: () => void): this;

  on(event: "title", listener: (text: string) => void): this;

  on(event: "rain", listener: () => void): this;

  on(event: "time", listener: () => void): this;

  on(
    event: "kicked",
    listener: (reason: string, loggedIn: boolean) => void
  ): this;

  on(event: "end", listener: () => void): this;

  on(event: "spawnReset", listener: () => void): this;

  on(event: "death", listener: () => void): this;

  on(event: "health", listener: () => void): this;

  on(event: "entitySwingArm", listener: (entity: Entity) => void): this;

  on(event: "entityHurt", listener: (entity: Entity) => void): this;

  on(event: "entityWake", listener: (entity: Entity) => void): this;

  on(event: "entityEat", listener: (entity: Entity) => void): this;

  on(event: "entityCrouch", listener: (entity: Entity) => void): this;

  on(event: "entityUncrouch", listener: (entity: Entity) => void): this;

  on(event: "entityEquipmentChange", listener: (entity: Entity) => void): this;

  on(event: "entitySleep", listener: (entity: Entity) => void): this;

  on(event: "entitySpawn", listener: (entity: Entity) => void): this;

  on(
    event: "playerCollect",
    listener: (collector: Entity, collected: Entity) => void
  ): this;

  on(event: "entityGone", listener: (entity: Entity) => void): this;

  on(event: "entityMoved", listener: (entity: Entity) => void): this;

  on(
    event: "entityDetach",
    listener: (entity: Entity, vehicle: Entity) => void
  ): this;

  on(
    event: "entityAttach",
    listener: (entity: Entity, vehicle: Entity) => void
  ): this;

  on(event: "entityUpdate", listener: (entity: Entity) => void): this;

  on(
    event: "entityEffect",
    listener: (entity: Entity, effect: Effect) => void
  ): this;

  on(
    event: "entityEffectEnd",
    listener: (entity: Entity, effect: Effect) => void
  ): this;

  on(event: "playerJoined", listener: (player: Player) => void): this;

  on(event: "playerLeft", listener: (entity: Player) => void): this;

  on(
    event: "blockUpdate",
    listener: (oldBlock: Block | null, newBlock: Block) => void
  ): this;

  on(
    event: "blockUpdate:(x, y, z)",
    listener: (oldBlock: Block | null, newBlock: Block) => void
  ): this;

  on(event: "chunkColumnLoad", listener: (entity: Vec3) => void): this;

  on(event: "chunkColumnUnload", listener: (entity: Vec3) => void): this;

  on(
    event: "soundEffectHeard",
    listener: (
      soundName: string,
      position: Vec3,
      volume: number,
      pitch: number
    ) => void
  ): this;

  on(
    event: "hardcodedSoundEffectHeard",
    listener: (
      soundId: number,
      soundCategory: number,
      position: Vec3,
      volume: number,
      pitch: number
    ) => void
  ): this;

  on(
    event: "noteHeard",
    listener: (block: Block, instrument: Instrument, pitch: number) => void
  ): this;

  on(
    event: "pistonMove",
    listener: (block: Block, isPulling: number, direction: number) => void
  ): this;

  on(
    event: "chestLidMove",
    listener: (block: Block, isOpen: number) => void
  ): this;

  on(
    event: "blockBreakProgressObserved",
    listener: (block: Block, destroyStage: number) => void
  ): this;

  on(event: "blockBreakProgressEnd", listener: (block: Block) => void): this;

  on(event: "diggingCompleted", listener: (block: Block) => void): this;

  on(event: "diggingAborted", listener: (block: Block) => void): this;

  on(event: "move", listener: () => void): this;

  on(event: "forcedMove", listener: () => void): this;

  on(event: "mount", listener: () => void): this;

  on(event: "dismount", listener: (vehicle: Entity) => void): this;

  on(event: "windowOpen", listener: (vehicle: Window) => void): this;

  on(event: "windowClose", listener: (vehicle: Window) => void): this;

  on(event: "sleep", listener: () => void): this;

  on(event: "wake", listener: () => void): this;

  on(event: "experience", listener: () => void): this;

  on(
    event: "scoreboardCreated",
    listener: (scoreboard: ScoreBoard) => void
  ): this;

  on(
    event: "scoreboardDeleted",
    listener: (scoreboard: ScoreBoard) => void
  ): this;

  on(
    event: "scoreboardTitleChanged",
    listener: (scoreboard: ScoreBoard) => void
  ): this;

  on(
    event: "scoreUpdated",
    listener: (scoreboard: ScoreBoard, item: number) => void
  ): this;

  on(
    event: "scoreRemoved",
    listener: (scoreboard: ScoreBoard, item: number) => void
  ): this;

  on(
    event: "scoreboardPosition",
    listener: (position: DisplaySlot, scoreboard: ScoreBoard) => void
  ): this;

  on(event: "bossBarCreated", listener: (bossBar: BossBar) => void): this;

  on(event: "bossBarDeleted", listener: (bossBar: BossBar) => void): this;

  on(event: "bossBarUpdated", listener: (bossBar: BossBar) => void): this;
		 */
	}
	interface GameState {
		var levelType: String
		var gameMode: String
		var hardcore: Boolean
		var dimension: String
		var difficulty: String
		var maxPlayers: Number
	}
	interface Player {
		var username: String
		var displayName: ChatMessage
		var gamemode: Number
		var ping: Number
		var entity: Entity
	}
	class ChatMessage {
		var json: dynamic
		var text: String?
		var translate: String?
		var with: Array<ChatMessage>?
		var extra: Array<ChatMessage>?
		var bold: Boolean
		var italic: Boolean
		var underlined: Boolean
		var strikeThrough: Boolean
		var obfuscated: Boolean
		var color: String
		var clickEvent: dynamic
		var hoverEvent: dynamic
		constructor(message: dynamic)
		fun parse()
		fun length(): Number
		fun getText(idx: Number, lang: Map<String, String>?): String
		fun toString(lang: Map<String, String>?): String
		fun valueOf(): String
		fun toMotd(lang: Map<String, String>?): String
		fun toAnsi(lang: Map<String, String>?): String
	}
	interface ChatPattern {
		var pattern: RegExp
		var type: String
		var description: String
	}
	interface GameSettings {
		var chat: String
		var colorsEnabled: Boolean
		var viewDistance: String
	}
	interface Experience {
		var level: Number
		var points: Number
		var progress: Number
	}
	interface PhysicsOptions {
		var maxGroundSpeed: Number
		var terminalVelocity: Number
		var walkingAcceleration: Number
		var gravity: Number
		var groundFriction: Number
		var playerApothem: Number
		var playerHeight: Number
		var jumpSpeed: Number
		var yawSpeed: Number
		var sprintSpeed: Number
		var maxGroundSpeedSoulSand: Number
		var maxGroundSpeedWater: Number
	}
	interface Time {
		var day: Number
		var age: Number
	}
	interface ControlStateStatus {
		var forward: Boolean
		var back: Boolean
		var left: Boolean
		var right: Boolean
		var jump: Boolean
		var sprint: Boolean
		var sneak: Boolean
	}
	interface Effect {
		var id: Number
		var amplifier: Number
		var duration: Number
	}
	interface Instrument {
		var id: Number
		var name: String
	}
	interface FindBlockOptions {
		var point: Vec3
		var matching: dynamic // ((block: Block) -> Boolean)/number/Array<Number>
		var maxDistance: Number?
	}
	interface TransferOptions {
		var window: Window
		var itemType: Number
		var metadata: Number?
		var sourceStart: Number
		var sourceEnd: Number
		var destStart: Number
		var destEnd: Number
	}
	interface creativeMethods {
		fun setInventorySlot(slot: Number, item: Item?, callback: ((error: Error?) -> Unit)?)
		fun flyTo(destination: Vec3, cb: (() -> Unit)?)
		fun startFlying()
		fun stopFlying()
	}
	class Location {
		constructor(absoluteVector: Vec3)
		
		var floored: Vec3
		var blockPoint: Vec3
		var chunkCorner: Vec3
		var blockIndex: Number
		var biomeBlockIndex: Number
		var chunkYIndex: Number
	}
	class Painting {
		var id: Number
		var position: Vec3
		var name: String
		var direction: Vec3
		constructor(id: Number, position: Vec3, name: String, direction: Vec3)
	}
	class Chest: EventEmitter {
		var window: dynamic?
		fun close()
		fun deposit(itemType: Number, metadata: Number?, count: Number?)
		fun withdraw(itemType: Number, metadata: Number?, count: Number?)
		fun count(itemType: Number, metadata: Number?): Number
		fun items(): Array<Item>
		/*
		  on(event: "open", listener: () => void): this;
  on(event: "close", listener: () => void): this;
  on(
    event: "updateSlot",
    listener: (oldItem: Item | null, newItem: Item) => void
  ): this;
		 */
	}
	class Furnace: EventEmitter {
		var fuel: Number
		var progress: Number
		fun close()
		fun takeInput(cb: (err: Error?, item: Item) -> Unit)
		fun takeFuel(cb: (err: Error?, item: Item) -> Unit)
		fun takeOutput(cb: (err: Error?, item: Item) -> Unit)
		fun putInput(itemType: Number, metadata: Number?, cb: ((err: Error?) -> Unit)?)
		fun putFuel(itemType: Number, metadata: Number?, cb: ((err: Error?) -> Unit)?)
		fun inputItem(): Item
		fun fuelItem(): Item
		fun outputItem(): Item
		/*
		  on(event: "open", listener: () => void): this;
  on(event: "close", listener: () => void): this;
  on(event: "update", listener: () => void): this;
  on(
    event: "updateSlot",
    listener: (oldItem: Item | null, newItem: Item) => void
  ): this;
		 */
	}
	class Dispenser: EventEmitter {
		fun close()
		fun deposit(itemType: Number, metadata: Number?, count: Number)
		fun withdraw(itemType: Number, metadata: Number?, count: Number?)
		fun count(itemType: Number, metadata: Number?): Number
		fun items(): Array<Item>
		/*
		  on(event: "open", listener: () => void): this;
  on(event: "close", listener: () => void): this;
  on(
    event: "updateSlot",
    listener: (oldItem: Item | null, newItem: Item) => void
  ): this;
		 */
	}
	class EnchantmentTable: EventEmitter {
		var enchantments: Array<Enchantment>
		fun close()
		fun targetItem(): Item
		fun enchant(choice: dynamic, cb: ((err: Error?, item: Item) -> Unit)?) // choice = string/number
		fun takeTargetItem(cb: ((err: Error?, item: Item) -> Unit)?)
		fun putTargetItem(item: Item, cb: ((err: Error?, item: Item) -> Unit)?)
		fun putLapis(item: Item, cb: ((err: Error?, item: Item) -> Unit)?)
		/*
		  on(event: "open", listener: () => void): this;
  on(event: "close", listener: () => void): this;
  on(event: "ready", listener: () => void): this;
  on(
    event: "updateSlot",
    listener: (oldItem: Item | null, newItem: Item) => void
  ): this;
		 */
	}
	interface Enchantment {
		var level: Number
	}
	class Villager: EventEmitter {
		var trades: Array<VillagerTrade>
		fun close()
		/*
		  on(event: "open", listener: () => void): this;
  on(event: "close", listener: () => void): this;
  on(event: "ready", listener: () => void): this;
  on(
    event: "updateSlot",
    listener: (oldItem: Item | null, newItem: Item) => void
  ): this;
		 */
	}
	interface VillagerTrade {
		var firstInput: Item
		var output: Item
		var hasSecondItem: Boolean
		var secondaryInput: Item?
		var disabled: Boolean
		var tooluses: Number
		var maxTradeuses: Number
	}
	class ScoreBoard {
		var name: String
		var title: String
		var itemsMap: Map<String, ScoreBoardItem>
		var items: Array<ScoreBoardItem>
		constructor(packet: dynamic)
		fun setTitle(title: String)
		fun add(name: String, value: Number): ScoreBoardItem
		fun remove(name: String): ScoreBoardItem
	}
	interface ScoreBoardItem {
		var name: String
		var value: Number
	}
	class BossBar {
		var entityUUID: String
		var title: ChatMessage
		var health: Number
		var dividers: Number
		var color: String
		var shouldDarkenSky: Boolean
		var isDragonBar: Boolean
		var shouldCreateFog: Boolean
		constructor(uuid: String, title: String, health: Number, dividers: Number, color: Number, flags: Number)
	}
	var supportedVersions: Array<String>
	var testedVersions: Array<String>
	fun supportFeature(feature: String, version: String): Boolean
}

/*
class MineflayerOptionsImpl(
	override var username: String = "Player",
	override var port: Number? = 25565,
	override var password: String? = null,
	override var host: String? = "localhost",
	override var version: String? = null,
	override var clientToken: String? = null,
	override var accessToken: String? = null,
	override var logErrors: Boolean? = true,
	override var keepAlive: Boolean? = true,
	override var checkTimeoutInterval: Number? = (30 * 1000),
	override var loadInternalPlugins: Boolean? = true,
	override var viewDistance: String? = null,
	override var chatLengthLimit: Number? = null,
	override var plugins: Map<String, Boolean>? = null,
	override var chat: String? = null,
	override var colorsEnabled: Boolean? = null,
	override var difficulty: Number? = null,
	override var showCape: Boolean? = null,
	override var customPacket: Any? = null,
	override var hideErrors: Boolean? = null,
	override var skipValidation: Boolean? = null,
	override var stream: dynamic = null,
	override var connect: ((client: Client) -> Unit)? = null,
	override var agent: dynamic = null
): Mineflayer.BotOptions*/

data class MineflayerOptions(
	var username: String? = null,
	var port: Number = 25565,
	var password: String? = null,
	var host: String = "localhost",
	var version: String? = null,
	var clientToken: String? = null,
	var accessToken: String? = null,
	var logErrors: Boolean = true,
	var keepAlive: Boolean = true,
	var checkTimeoutInterval: Number = (30 * 1000),
	var loadInternalPlugins: Boolean = true,
	var viewDistance: String? = null,
	var chatLengthLimit: Number? = null
)
