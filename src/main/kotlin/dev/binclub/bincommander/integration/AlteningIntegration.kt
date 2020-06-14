package dev.binclub.bincommander.integration

import dev.binclub.bincommander.interop.require
import dev.binclub.bincommander.utils.XmlHttpState
import org.w3c.dom.url.URL
import org.w3c.fetch.Request
import org.w3c.xhr.XMLHttpRequest

/**
 * @author cookiedragon234 13/Jun/2020
 */
object AlteningDataRetriever {
	private const val BASE_URL = "http://api.thealtening.com/v1/"
	private const val LICENSE_URL = BASE_URL + "license?token="
	private const val GENERATE_URL = BASE_URL + "generate?info=true&token="
	private const val PRIVATE_ACC_URL = BASE_URL + "private?acctoken="
	private const val FAVORITE_ACC_URL = BASE_URL + "favorite?acctoken="
	
	fun getLicense(apiKey: String, callback: (license: AlteningLicense) -> Unit, failure: () -> Unit) {
		retrieveData(LICENSE_URL + apiKey, {
			callback(it.unsafeCast<AlteningLicense>())
		}, failure = failure)
	}
	
	fun getAccount(apiKey: String, callback: (account: AlteningAccount) -> Unit, failure: () -> Unit) {
		retrieveData(GENERATE_URL + apiKey, callback = {
			callback(it.unsafeCast<AlteningAccount>())
		}, failure = failure)
	}
	
	fun isPrivate(apiKey: String, token: String, callback: (private: Boolean) -> Unit, failure: () -> Unit) {
		retrieveData("$PRIVATE_ACC_URL$token&token=$apiKey", {
			callback(it.success == true)
		}, failure = failure)
	}
	
	fun isFavorite(apiKey: String, token: String, callback: (favorite: Boolean) -> Unit, failure: () -> Unit) {
		retrieveData("$FAVORITE_ACC_URL&token=$apiKey", {
			callback(it.success == true)
		}, failure = failure)
	}
	
	fun connect(url: String, callback: (response: String) -> Unit, failure: (req: XMLHttpRequest) -> Unit = { console.error(it.statusText) }) {
		js("""
			global.XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;
		""")
		val xmlHttp = XMLHttpRequest()
		xmlHttp.open("GET", url)
		xmlHttp.onload = {
			if (xmlHttp.readyState == XmlHttpState.READY_DONE.code) {
				if (xmlHttp.status == 200.toShort()) { // not gonna make an enum of every http status code LOL
					callback.invoke(xmlHttp.responseText)
				} else {
					failure.invoke(xmlHttp)
				}
			}
		}
		xmlHttp.send()
	}
	
	fun retrieveData(url: String, callback: (response: dynamic) -> Unit, failure: () -> Unit) {
		connect(url, callback = { response ->
			val dyn = JSON.parse<dynamic>(response)
			
			if (dyn.error != undefined && dyn.errorMessage != undefined) {
				console.error("Error ${dyn.error}: ${dyn.errorMessage}")
				failure()
			} else {
				callback(dyn)
			}
		}, failure = {
			console.error(it.statusText)
			failure()
		})
	}
}



class AlteningException: Throwable {
	constructor() : super()
	constructor(message: String?) : super(message)
	constructor(cause: Throwable?) : super(cause)
	constructor(message: String?, cause: Throwable?) : super(message, cause)
}
