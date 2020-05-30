package dev.binclub.bincommander.interop

/**
 * @author cookiedragon234 29/May/2020
 */
@JsModule("vec3")
external class Vec3 {
	constructor(x: Number, y: Number, z: Number)
	
	var x: Number
	var y: Number
	var z: Number
	
	fun set(x: Number, y: Number, z: Number): Vec3
	fun update(other: Vec3): Vec3
	fun floored(): Vec3
	fun floor(): Vec3
	fun offset(dx: Number, dy: Number, dz: Number): Vec3
	fun translate(dx: Number, dy: Number, dz: Number): Vec3
	fun add(other: Vec3): Vec3
	fun subtract(other: Vec3)
	fun plus(other: Vec3): Vec3
	fun minus(other: Vec3): Vec3
	fun scaled(scalar: Number): Vec3
	fun abs(): Vec3
	fun volume(): Number
	fun modulus(other: Vec3): Vec3
	fun distanceTo(other: Vec3): Number
	fun equals(other: Vec3): Boolean
	fun clone(): Vec3
	fun min(other: Vec3): Vec3
	fun max(other: Vec3): Vec3
	fun norm(): Number
	fun unit(): Vec3
	fun scale(scalar: dynamic): Vec3
	// I skipped some here
	fun manhattanDistanceTo(other: Vec3): Number
	fun toArray(): Array<Number>
}
