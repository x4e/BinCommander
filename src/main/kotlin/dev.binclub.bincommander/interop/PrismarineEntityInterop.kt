package dev.binclub.bincommander.interop

/**
 * @author cookiedragon234 29/May/2020
 */
@JsModule("primarine-entity")
external class PrismarineEntity {
	class Entity {
		/**
		 * One of player, mob, object, global, orb, other
		 */
		var type: String
		var username: String?
		var mobType: String?
		var displayName: String?
		var entityType: Number?
		var kind: String?
		var name: String?
		var objectType: String?
		var count: Number?
		var position: Vec3
		var velocity: Vec3
		var yaw: Number
		var pitch: Number
		var height: Number
		var onGround: Boolean
		var equipment: Array<PrismarineItem.Item>
		var heldItem: PrismarineItem.Item
		var metadata: Array<Any>
		var isValid: Boolean
		var health: Number?
		var food: Number?
		var player: Any?
		fun setEquipment(index: Number, item: PrismarineItem.Item)
	}
}
