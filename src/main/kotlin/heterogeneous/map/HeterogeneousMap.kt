package heterogeneous.map

/**
 * Create a type-safe map.
 * e.g.
 * 	val hmap = HeterogeneousMap()
 *
 * 	Put.
 * 		val isPairKey = createHKey<Func1<Int, Boolean>>("isPair")
 * 		hmap.put(isPairKey, Func1 { it % 2 == 0 })
 * 		or
 * 		hmap.put<Func1<Int, Boolean>>("isPair", Func1 { it % 2 == 0 })
 *
 *  Get.
 * 		hmap.get(isPairKey)?.call(2)
 * 		or
 * 		hmap.get(createHKey<Func1<Int, Boolean>>("isPair"))?.call(2)
 * 		or
 * 		hmap.get<Func1<Int, Boolean>>("isPair")?.call(2)
 */
class HeterogeneousMap {
	private val map = mutableMapOf<HKey<*>, Any?>()

	@Suppress("UNCHECKED_CAST")
	operator fun <T: Any> get(key: HKey<T>): T? {
		return map[key] as? T
	}

	fun <T: Any> put(key: HKey<T>, value: T) {
		map[key] = value
	}

	fun <T: Any> putAll(items: Map<HKey<T>, T>) {
		map.putAll(items)
	}

	inline fun <reified T: Any> put(key: String, value: T) {
		put(createHKey(key), value)
	}

	inline fun <reified T: Any> get(key: String): T? {
		return get(createHKey(key))
	}
}