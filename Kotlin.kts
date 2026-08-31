

val modelo = "Galaxy A55"
val nombrePropietario = ""
val bateria = 18
val ahorroDeEnergia = false

val estadoBateria = obtenerEstadoBateria(bateria)

println("=== TELÉFONO ===")
println("Modelo: $modelo")
println("Bateria: $bateria%")
println("Estado de bateria: $estadoBateria")
println("Ahorro de energia: $ahorroDeEnergia")
println("Nombre propietario: ${if (nombrePropietario.isEmpty()) "Sin propietario" else nombrePropietario}")


fun obtenerEstadoBateria(bateria: Int): String {
    return when {
        bateria <= 20 -> "Batería baja"
        bateria in 21..80 -> "Batería suficiente"
        else -> "Batería Alta"
    }
}
