package dev.binclub.bincommander.interop

/**
 * @author cookiedragon234 29/May/2020
 */
@JsModule("prismarine-tokens")
external class PrimarineTokens {
	companion object {
		fun use(options: PrismarineOptions, op: (err: Any?, opts: PrismarineOptions) -> Unit)
	}
	
	class PrismarineProfile {
		var name: String
		var id: String
	}
	
	class PrismarineSession {
		var accessToken: String
		var clientToken: String
		var selectedProfile: PrismarineProfile
		var availableProfiles: Array<PrismarineProfile>
	}
}

data class PrismarineOptions(
	var username: String,
	var password: String,
	var session: PrimarineTokens.PrismarineSession? = null,
	var clientToken: String? = null,
	var tokensLocation: String = "./bot_tokens.json",
	var tokensDebug: Boolean = true
)
