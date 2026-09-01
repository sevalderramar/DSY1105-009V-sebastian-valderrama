// fun main() {
    //Checkpoint 1 · Lista y recorrido básico
    val temperaturas: List<Int> = listOf(18, 22, 25, 17, 29, 31, 20)
    println(temperaturas + "\n")

    for (temp in temperaturas) {
        println("Temperatura: $temp °C -> ${clasificarTemperatura(temp)}")
    }

    val totalAltas = contarTemperaturasAltas(temperaturas)
    println("\n Total de temperaturas altas: $totalAltas \n")

// }

//Checkpoint 2
fun clasificarTemperatura(temp: Int): String {
    if (temp < 18) {
        return "Frío"
    }
    else if (temp >= 18 && temp <= 24) {
        return "Templado"
    }
    return "Caluroso"
}

//02 · Funciones y mutabilidad

fun contarTemperaturasAltas(temperaturas: List<Int>): Int {
    var contador = 0
    for (temp in temperaturas) {
        if (temp >= 25) {
            contador++
        }
    }
    return contador
}

val temperaturas2: List<Int> = mutableListOf(18, 22, 25, 17, 29, 31, 20, 26)
println(temperaturas2)

// 03 · Operaciones de colección y cierre

// filter, filtra las temperaturas mayores o iguales a 25 grados
val filterTemp = temperaturas2.filter { it >= 25 }
println("\n Las temperaturas mayores o iguales a 25 grados son: " + filterTemp)


// map,  transforma cada elemento de la lista según la condicion
val mapTemp = temperaturas2.map { it + 1 }
println("\n Las lsita de temperaturas quedará de la siguiente forma al sumar 1 grado: " + mapTemp)


// count, cuenta la temperaturas con la condicion indicada
val cantidad = temperaturas.count({ it >= 25 })
println("\n La cantidad de temperaturas igual o mayor a 25 grados es de: " + cantidad)

