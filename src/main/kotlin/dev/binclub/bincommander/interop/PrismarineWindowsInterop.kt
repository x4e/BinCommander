package dev.binclub.bincommander.interop

/**
 * @author cookiedragon234 29/May/2020
 */
@JsModule("primarine-windows")
external class PrismarineWindows {
	class Window: EventEmitter {
		var id: Number
		var type: dynamic // Number/String
		var title: String
		var slots: Array<PrismarineItem.Item>
		var inventoryStart: Number
		var inventoryEnd: Number
		var craftingResultSlot: Number
		var requiresConfirmation: Boolean
		var selectedItem: PrismarineItem.Item?
		fun acceptClick(click: Click)
		fun acceptOutsideWindowClick(click: Click)
		fun acceptInventoryClick(click: Click)
		fun acceptNonInventorySwapAreaClick(click: Click)
		fun acceptSwapAreaLeftClick(click: Click)
		fun acceptCraftingClick(click: Click)
		fun updateSlot(slot: Number, newItem: PrismarineItem.Item)
		fun findItemRange(start: Number, end: Number, itemType: Number, metadata: Number?, notFull: Boolean): PrismarineItem.Item?
		fun findInventoryItem(itemType: Number, metadata: Number?, notFull: Boolean): PrismarineItem.Item?
		fun firstEmptySlotRange(start: Number, end: Number): Number?
		fun firstEmptyInventorySlot(): Number?
		fun countRange(start: Number, end: dynamic, itemType: Number, metadata: Number?): Number
		fun itemsRange(start: Number, end: Number): Array<PrismarineItem.Item>
		fun count(itemType: dynamic, metadata: Number?): Number
		fun items(): Array<PrismarineItem.Item>
		fun emptySlotCount(): Number
		fun transactionRequiresConfirmation(click: Click?): Boolean
		
		interface Click {
			var mode: Number
			var mouseButton: Number
			var slot: Number
		}
		interface WindowInfo {
			var type: dynamic // number/string
			var inventory: Inventory
			var slots: Number
			var craft: Number
			var requireConfirmation: Boolean
			
			interface Inventory {
				var start: Number
				var end: Number
			}
		}
	}
}
