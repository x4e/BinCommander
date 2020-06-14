package dev.binclub.bincommander.integration

/**
 * @author cookiedragon234 14/Jun/2020
 */
external class AlteningAccountDetails {
	var hypixel: AlteningHypixelDetails?
	var mineplex: AlteningMineplexDetails?
	var labymod: AlteningLabymodDetails?
	//var 5zig: AlteningFiveZigDetails TODO: this isnt possible due to js names
}

var AlteningAccountDetails.fivezig: AlteningFiveZigDetails
	get() = this.asDynamic()["5zig"].unsafeCast<AlteningFiveZigDetails>()
	set(value) {
		this.asDynamic()["5zig"] = value
	}

fun AlteningAccountDetails.get5Zig() {

}

external class AlteningHypixelDetails {
	var lvl: Int?
	var rank: String?
}

external class AlteningMineplexDetails {
	var lvl: Int?
	var rank: String?
}

external class AlteningLabymodDetails {
	var cape: Boolean?
}

external class AlteningFiveZigDetails {
	var cape: Boolean?
}
