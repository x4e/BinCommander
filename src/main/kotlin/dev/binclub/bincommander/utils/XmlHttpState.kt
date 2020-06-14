package dev.binclub.bincommander.utils

import org.w3c.xhr.XMLHttpRequest

/**
 * @author cookiedragon234 13/Jun/2020
 */
enum class XmlHttpState {
	/**
	 * Client has been created. open() not called yet.
	 */
	READY_UNSENT(),
	/**
	 * open() has been called.
	 */
	READY_OPENED(),
	/**
	 * send() has been called, and headers and status are available.
	 */
	READY_HEADERS_RECIEVED(),
	/**
	 * Downloading; responseText holds partial data.
	 */
	READY_LOADING(),
	/**
	 * The operation is complete.
	 */
	READY_DONE();
	
	val code: Short
		get() = this.ordinal.toShort()
}
