
class Bicicleta (
    val id: String,
    val tipo: String,
    var tarifaHora: Int,
    var disponible: Boolean
) {

    init {
        require(id.isNotBlank()) {
            "El ID de la bicicleta no puede estar vacío."
        }

        require(tarifaHora > 0) {
            "La tarifa por hora debe ser mayor a 0."
        }
    }

    fun arrendarBicicleta(): Boolean {

        return if (disponible) {
            disponible = false
            println("Bicicleta $id arrendada con éxito.\n")
            true
        } else {
            println("Bicicleta $id no disponible para arrendar.\n")
            false
        }
    }

    fun devolverBicicleta(): Boolean {

        return if (!disponible) {
            disponible = true
            println("Bicicleta $id devuelta con éxito.\n")
            true

        } else {
            println("Bicicleta $id no estaba arrendada.\n")
            false
        }
    }
}

// ===========================================================================

fun listar(bicicletas: MutableList<Bicicleta>){

    for (bici in bicicletas) {
        val estado = if(bici.disponible) "Disponible" else "No disponible"
        println("Bicicleta " +
                "ID: " + "${bici.id} " +
                "| Tipo: ${bici.tipo} " +
                "| Tarifa por hora: $${bici.tarifaHora} pesos" +
                "| $estado \n")
    }
    println()
}

// ===========================================================================

fun procesarArriendo(bicicletas: MutableList<Bicicleta>): Int {

    print("Ingrese el ID de la bicicleta que desea arrendar: ")

    val idBicicleta = readln()
        .trim()
        .uppercase()
        .replace(" ", "")

    val bici = bicicletas.find { it.id.uppercase() == idBicicleta }

    if (bici == null) {
        println("Bicicleta con ID $idBicicleta no encontrada\n")
        return 0
    }

    if (!bici.disponible) {
        println("Bicicleta con ID $idBicicleta no está disponible\n")
        return 0
    }

    print("Ingrese la cantidad de horas que desea arrendar la bicicleta: ")

    val horas = readln()
        .trim()
        .toIntOrNull()

    if (horas == null || horas <= 0) {
        println("Cantidad de horas inválida. Ingrese un número mayor a 0.\n")
        return 0
    }

    // Cálculo del costo.
    val tarifaTotal = bici.tarifaHora * horas

    // Se cambia el estado de la bicicleta.
    val arriendoExitoso = bici.arrendarBicicleta()

    // Solo mostramos el costo si realmente se arrendó.
    if (arriendoExitoso) {

        println("La tarifa total por arrendar la bicicleta ${bici.id} por $horas horas es de \$${tarifaTotal} pesos.\n")
        return tarifaTotal
    }
    return 0
}

// ===========================================================================

fun devolverBicicleta(bicicletas: MutableList<Bicicleta>) {

    print("Ingrese el ID de la bicicleta que desea devolver: ")

    val idBicicleta = readln()
        .trim()
        .uppercase()
        .replace(" ", "")
    val bici = bicicletas.find { it.id == idBicicleta }

    if (bici != null){
        bici.devolverBicicleta()
    } else {
        println("Bicicleta con ID $idBicicleta no encontrada \n")
    }
}

// ===========================================================================

fun resumen(
    bicicletas: MutableList<Bicicleta>,
    ingresoTotal: Int,
    cantidadArriendos: Int
) {

    println("\n===== RESUMEN FINAL =====")

    // Cantidad de bicicletas disponibles.
    val disponibles = bicicletas.count {
        it.disponible
    }

    // Cantidad no disponible.
    val noDisponibles = bicicletas.count {
        !it.disponible
    }

    // Suma de todas las tarifas.
    val sumaTarifas = bicicletas.sumOf {
        it.tarifaHora
    }

    // Promedio de tarifa.
    val tarifaPromedio =
        if (bicicletas.isNotEmpty()) {
            sumaTarifas.toDouble() / bicicletas.size
        } else {
            0.0
        }

    println("Ingreso total: \$${ingresoTotal}")

    println("Cantidad de arriendos: $cantidadArriendos")

    println("Bicicletas disponibles: $disponibles")

    println("Bicicletas no disponibles: $noDisponibles")

    println("Tarifa promedio por hora: \$${tarifaPromedio}")

    println()
}

/* ===========================================================================
   =========================================================================== */

val bicicletas = mutableListOf<Bicicleta>()

val bici1 = Bicicleta("S001", "Montaña", 5000, true)
val bici2 = Bicicleta("S002", "Ruta", 4000, true)
val bici3 = Bicicleta("S003", "Urbana", 3500, true)
val bici4 = Bicicleta("S004", "BMX", 4500, true)
val bici5 = Bicicleta("S005", "Eléctrica", 7000, true)

bicicletas.add(bici1)
bicicletas.add(bici2)
bicicletas.add(bici3)
bicicletas.add(bici4)
bicicletas.add(bici5)

try {
    val biciInvalida = Bicicleta("", "Ruta", -1000, true)
    bicicletas.add(biciInvalida)

} catch (e: IllegalArgumentException) {

    println("Registro inválido rechazado: ${e.message}\n")
}


// =============== MENU ===============

var ingresoTotal = 0
var cantidadArriendos = 0
var opcion = 0

while (opcion != 5) {
    println(""" ===== Centro de arriendo de bicicletas =====
    |         1. Listar bicicletas
    |         2. Arrendar bicicleta
    |         3. Devolver bicicleta
    |         4. Resumen
    |         5. Salir
""".trimMargin())
    print("Ingrese una opción: ")
    opcion = readln()
        .trim()
        .toIntOrNull() ?: 0 /* Si es texto lo convierte a null, luego devueve un 0 */

    when(opcion){

        1 -> {
            listar(bicicletas)
        }
        2 -> {
            val ingreso = procesarArriendo(bicicletas)

            // Si ingreso > 0, entonces hubo un arriendo exitoso.
            if (ingreso > 0) {

                ingresoTotal += ingreso
                cantidadArriendos++
            }
        }
        3 -> {
            devolverBicicleta(bicicletas)
        }
        4 -> {
            resumen(bicicletas, ingresoTotal, cantidadArriendos)
        }
        5 -> {
            println("Saliendo del programa... \n")
        }
        else -> {
            println("Opción inválida. Por favor, ingrese una opción válida. \n")
        }
    }
}


