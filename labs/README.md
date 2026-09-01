# 🔬 Laboratorios

# Kotlin · Colecciones rápidas

| Función       | Sirve para                   | Ejemplo                            |
| ------------- | ---------------------------- | ---------------------------------- |
| `MutableList` | Crear una lista modificable  | `mutableListOf("A", "B")`          |
| `for`         | Recorrer elementos           | `for (x in lista)`                 |
| `forEach`     | Hacer algo con cada elemento | `lista.forEach { println(it) }`    |
| `filter`      | Dejar los que cumplen        | `filter { it.startsWith("M") }`    |
| `filterNot`   | Dejar los que NO cumplen     | `filterNot { it.startsWith("M") }` |
| `map`         | Transformar elementos        | `map { "MonkeyD" + it }`           |
| `count`       | Contar elementos             | `count { it.startsWith("M") }`     |
| `it`          | Elemento actual              | `println(it)`                      |
| `startsWith`  | Ver cómo empieza un texto    | `startsWith("MonkeyD")`            |
| `contains`    | Ver si contiene algo         | `contains("Monkey")`               |

## Ejemplo

```kotlin
val lista = mutableListOf("MonkeyDLuffy", "Ace", "Garp")

val nuevaLista = lista.map {
    if (it.startsWith("MonkeyD")) it
    else "MonkeyD" + it
}

val cantidad = nuevaLista.count {
    it.startsWith("MonkeyD")
}
```

## Para recordar

```text
Recorrer    → for / forEach
Seleccionar → filter / filterNot
Transformar → map
Contar      → count
```

> `map` crea una lista nueva, no modifica automáticamente la original.
