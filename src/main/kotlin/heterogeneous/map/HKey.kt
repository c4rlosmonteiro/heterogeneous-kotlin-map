package heterogeneous.map

import java.lang.reflect.ParameterizedType

inline fun <reified T> createHKey(name: String): HKey<T> = object : HKey<T>(name) {}

abstract class HKey<T>(val name: String): Comparable<HKey<T>> {
	private val typeName: String = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0].typeName
	private var hashcode: Int = 0

	override fun hashCode(): Int {
		var h: Int = hashcode
		if (h == 0) {
			val prime = 31
			var result = 1
			result = prime * result + name.hashCode()
			result = prime * result + typeName.hashCode()
			h = result
			hashcode = h
		}
		return h
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) {
			return true
		}
		if (other == null) {
			return false
		}
		if (other !is HKey<*>) {
			return false
		}
		if (typeName != other.typeName) {
			return false
		}
		return name == other.name
	}

	override fun toString(): String {
		return "$name ($typeName)"
	}

	override fun compareTo(other: HKey<T>): Int {
		return name.compareTo(other.name).let {
			if (it != 0) {
				it
			} else {
				typeName.compareTo(other.typeName)
			}
		}
	}
}