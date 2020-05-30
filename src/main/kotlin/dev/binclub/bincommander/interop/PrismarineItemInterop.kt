package dev.binclub.bincommander.interop

/**
 * @author cookiedragon234 29/May/2020
 */
@JsModule("primsarine-item")
external class PrismarineItem {
	class Item {
		var type: Number
		var count: Number
		var metadata: Number
		var nbt: dynamic? // Buffer type
		var name: String
		var displayName: String
		var stackSize: Number
		companion object {
			fun equal(item1: Item, item2: Item): Boolean
			fun toNotch(item: Item): NotchItem
			fun fromNotch(item: NotchItem): Item
		}
		interface NotchItem {
			var blockId: Number?
			var itemDamage: Number?
			var present: Boolean?
			var itemId: Number?
			var itemCount: Number?
			var nbtData: dynamic? // Buffer type
		}
	}
}
