package dev.binclub.bincommander.interop

/**
 * @author cookiedragon234 29/May/2020
 */
@JsModule("events")
open external class EventEmitter {
	fun addListener(event: Any, listener: (event: Any) -> Unit)
	fun on(event: Any, listener: () -> Unit)
	fun once(event: Any, listener: () -> Unit)
	fun removeListener(event: Any, listener: () -> Unit)
	fun <A> on(event: Any, listener: (A) -> Unit)
	fun <A> once(event: Any, listener: (A) -> Unit)
	fun <A> removeListener(event: Any, listener: (A) -> Unit)
	fun <A, B> on(event: Any, listener: (A, B) -> Unit)
	fun <A, B> once(event: Any, listener: (A, B) -> Unit)
	fun <A, B> removeListener(event: Any, listener: (A, B) -> Unit)
	fun <A, B, C> on(event: Any, listener: (A, B, C) -> Unit)
	fun <A, B, C> once(event: Any, listener: (A, B, C) -> Unit)
	fun <A, B, C> removeListener(event: Any, listener: (A, B, C) -> Unit)
	fun <A, B, C, D> on(event: Any, listener: (A, B, C, D) -> Unit)
	fun <A, B, C, D> once(event: Any, listener: (A, B, C, D) -> Unit)
	fun <A, B, C, D> removeListener(event: Any, listener: (A, B, C, D) -> Unit)
	fun <A, B, C, D, E> on(event: Any, listener: (A, B, C, D, E) -> Unit)
	fun <A, B, C, D, E> once(event: Any, listener: (A, B, C, D, E) -> Unit)
	fun <A, B, C, D, E> removeListener(event: Any, listener: (A, B, C, D, E) -> Unit)
	fun <A, B, C, D, E, F> on(event: Any, listener: (A, B, C, D, E, F) -> Unit)
	fun <A, B, C, D, E, F> once(event: Any, listener: (A, B, C, D, E, F) -> Unit)
	fun <A, B, C, D, E, F> removeListener(event: Any, listener: (A, B, C, D, E, F) -> Unit)
	fun <A, B, C, D, E, F, G> on(event: Any, listener: (A, B, C, D, E, F, G) -> Unit)
	fun <A, B, C, D, E, F, G> once(event: Any, listener: (A, B, C, D, E, F, G) -> Unit)
	fun <A, B, C, D, E, F, G> removeListener(event: Any, listener: (A, B, C, D, E, F, G) -> Unit)
	fun <A, B, C, D, E, F, G, H> on(event: Any, listener: (A, B, C, D, E, F, G, H) -> Unit)
	fun <A, B, C, D, E, F, G, H> once(event: Any, listener: (A, B, C, D, E, F, G, H) -> Unit)
	fun <A, B, C, D, E, F, G, H> removeListener(event: Any, listener: (A, B, C, D, E, F, G, H) -> Unit)
	fun <A, B, C, D, E, F, G, H, I> on(event: Any, listener: (A, B, C, D, E, F, G, H, I) -> Unit)
	fun <A, B, C, D, E, F, G, H, I> once(event: Any, listener: (A, B, C, D, E, F, G, H, I) -> Unit)
	fun <A, B, C, D, E, F, G, H, I> removeListener(event: Any, listener: (A, B, C, D, E, F, G, H, I) -> Unit)
	fun <A, B, C, D, E, F, G, H, I, J> on(event: Any, listener: (A, B, C, D, E, F, G, H, I, J) -> Unit)
	fun <A, B, C, D, E, F, G, H, I, J> once(event: Any, listener: (A, B, C, D, E, F, G, H, I, J) -> Unit)
	fun <A, B, C, D, E, F, G, H, I, J> removeListener(event: Any, listener: (A, B, C, D, E, F, G, H, I, J) -> Unit)
	fun <A, B, C, D, E, F, G, H, I, J, K> on(event: Any, listener: (A, B, C, D, E, F, G, H, I, J, K) -> Unit)
	fun <A, B, C, D, E, F, G, H, I, J, K> once(event: Any, listener: (A, B, C, D, E, F, G, H, I, J, K) -> Unit)
	fun <A, B, C, D, E, F, G, H, I, J, K> removeListener(event: Any, listener: (A, B, C, D, E, F, G, H, I, J, K) -> Unit)
	fun removeAllListeners(event: Any)
	fun setMaxListeners(n: Number)
	fun listeners(event: Any): MutableList<Any>
	fun emit(event: Any, vararg args: Any)
}
