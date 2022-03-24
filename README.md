Implementation of heterogeneous type-safe map pattern in Kotlin.

Use to create a type-safe map and access the data in a type-safe manner without manual casting.

e.g.

`val hMap = HeterogeneousMap()`

put:

		val isPairKey = createHKey<Func1<Int, Boolean>>("isPair")
		hMap.put(isPairKey, Func1 { it % 2 == 0 })
		//or
		hMap.put<Func1<Int, Boolean>>("isPair", Func1 { it % 2 == 0 })

get:

		hMap.get(isPairKey)?.call(2)
		//or
		hMap.get(createHKey<Func1<Int, Boolean>>("isPair"))?.call(2)
		//or
		hMap.get<Func1<Int, Boolean>>("isPair")?.call(2)
