// fun main() {
    //Checkpoint 1 · Lista y recorrido básico
    val temperaturas: List<Int> = listOf(18, 22, 25, 17, 29, 31, 20)
    println(temperaturas)

    for (temp in temperaturas) {
        println("Temperatura: $temp °C -> ${clasificarTemperatura(temp)} \n")
    }

    val totalAltas = contarTemperaturasAltas(temperaturas)
    println("Total de temperaturas altas: $totalAltas \n")

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

//Checkpoint 3
fun contarTemperaturasAltas(temperaturas: List<Int>): Int {
    var contador = 0
    for (temp in temperaturas) {
        if (temp >= 25) {
            contador++
        }
    }
    return contador
}