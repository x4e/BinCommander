package dev.binclub.bincommander.interop

/**
 * @author cookiedragon234 29/May/2020
 */
@JsModule("primarine-block")
external class PrismarineBlock {
	class Block {
		var type: Number
		var metadata: Number
		var light: Number
		var skyLight: Number
		var biome: PrimarineBiome.Biome
		var position: Vec3
		var stateId: Number?
		var name: String
		var displayName: String
		var hardness: Number
		var boundingBox: String
		var diggable: Boolean
		var material: String?
		var harvestTools: Map<String, Boolean>?
		var drops: Array<dynamic> // Array<DropType>
		var signText: String?
		var painting: Any?
		fun canHarvest(heldItemType: Number?): Boolean
		fun digTime(heldItemType: Number?, creative: Boolean, inWater: Boolean, notOnGround: Boolean): Number
		fun fromStateId(stateId: Number, biomeId: Number): Block
		
		var blockEntity: dynamic // added by mineflayer
	}
}
