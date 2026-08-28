
// fun main() {
//Checkpoint 1 · Lista y recorrido básico
val list: List<Int> = listOf(18, 22, 25, 17, 29, 31, 20)
println(list)
println("\n")

for (temp in list) {
    println("Temperatura: $temp °C -> ${clasificarTemperatura(temperatura = temp)}" )
}
//}
//Checkpoint 2 · Función de clasificación
fun clasificarTemperatura(temperatura: Int): String {
    if (temperatura < 18) {
        return "Frío"
    }
    else if (temperatura in 18..24) {
        return "Templado"
    }
    return "Caluroso"
}


//Checkpoint 3 · Conteo sin magia calcula cuántas temperaturas son >= 25 con una variable contador, un for y un if.
fun conteoTemperatura(temperatura: List<Int>): Int {
    var tempCount = 0

    for (temp in list) {
        if (temp >= 25) {
            tempCount++
        }
    }
    return tempCount
}

val totalTempMayor25 = conteoTemperatura(list)
println("\n Total de temperaturas mayor a 25°C: $totalTempMayor25")

//Checkpoint 4 · Colección mutable

val list2: MutableList<Int> = mutableListOf(18, 22, 25, 17, 29, 31, 20)

list2.add(26)

println(list2)

//



