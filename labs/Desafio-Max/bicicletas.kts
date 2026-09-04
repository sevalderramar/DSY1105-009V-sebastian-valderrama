import kotlin.collections.MutableList

val centro =
var opcion: Int? //lee numeros enteros.? se encarga de los datos nulleables

do {
    println("\n========================================")
    println("         CENTRO DE ARRIENDO DE BICICLETAS")
    println("========================================")
    println("1. Registrar bicicleta")
    println("2. Buscar bicicleta por identificador")
    println("3. Arrendar bicicleta (calcular costo)")
    println("4. Devolver bicicleta")
    println("5. Listar bicicletas (disponibles y ocupadas)")
    println("6. Ver resumen final")
    println("7. Salir")
    print("Seleccione una opción: ")

    opcion = readLine()?.toIntOrNull() //Lee la entrada del usuario, validando que no ingrese simbolo o texto

    when (opcion) {
        1 -> {
            print("Ingrese ID de la bicicleta: ")
            val id = readLine() ?: ""
            centro.add(id.toInt())
            print("Ingrese tipo (ej. 1.Urbana, 2.Montaña): ")
            val tipo = readLine() ?: ""
            centro.add(tipo.toInt())

        }
        2 -> {
            print("Ingrese ID de la bicicleta: ")
            val id = readLine() ?: ""
            val bici =
            if (bici = !null) {

            }

        }

        7 -> {
            println("Saliendo del sistema de arriendos. ¡Hasta luego!")
        }
        else -> {
            println("-> Opción inválida. Por favor, ingrese un número del 1 al 7.")
        }
    }
} while (opcion == 7)


class Bicicleta(
    val identificador: Int
    val tipo: String,
    val tarifaPorHora: Double,
    var estaDisponible: Boolean = true
)

class Centro(
    val bicicleta: MutableList<Int> = mutableListOf()
)