import java.util.Date
class Paciente(
    val id: String,
    val nombre: String,
    val tipoDueño: String,
    var silvestre: Boolean,
    var disponible: Boolean


){
    init {
        require(id.isNotBlank()) {
            "El ID del paciente no puede estar vacío."
        }
    }
    fun registrarEntrada(): Boolean {

        return if (disponible) {
            disponible = false
            println("El box está libre. \n")
            true
        } else {
            println("El box tiene un pasiente asignado.\n")
            false
        }
    }
    fun registrarSalida(): Boolean {

        return if (!disponible) {
            disponible = true
            println("Paciente $id dado de alta con éxito\n")
            true

        } else {
            println("Paciente $id no estaba en atención\n")
            false
        }
    }

}

fun listar(Pacientes: MutableList<Paciente>){

    for (Paciente in Pacientes) {
        val estado = if(Paciente.disponible) "Disponible" else "en atención"
        println("Paciente " +
                "Id: " + "${Paciente.id} " +
                "| Tipo: ${Paciente.nombre} " +
                "| Dueño: ${Paciente.tipoDueño}" +
                "| $estado \n")
    }
    println()
}

fun calcularTarifa(Paciente: Paciente, minutos: Int): Double {
    val horas = minutos / 60.0
    var costoBase = when (Paciente.tipoDueño){
        "Canino" -> {
            var costo = 12000 * horas
            if (Paciente.tipoDueño == "convenio") {
                costo = costo * 0.8
            }
            costo
        }
        "Felino" -> {
            var costoFelino = 9000 * horas
            if (Paciente.tipoDueño == "convenio") {
                costoFelino = costoFelino *
            }
            costoFelino
        }


        else -> 0.0
    }
    return
}


fun procesarRegistro(Pacientes: MutableList<Paciente>): Int {

    print("Ingrese el ID del paciente que desea registrar: ")

    val idPaciente = readln()
        .trim()
        .uppercase()
        .replace(" ", "")

    val paciente = Pacientes.find { it.id.uppercase() == idPaciente }

    if (paciente == null) {
        println("El paciente con ID $idPaciente no encontrado\n")
        return 0
    }

    if (!paciente.disponible) {
        println("El Box con no está disponible. \n")
        return 0
    }

    print("Ingrese la cantidad de horas que atendio al paciente: ")

    val horas = readln()
        .trim()
        .toIntOrNull()

    if (horas == null || horas <= 0) {
        println("Cantidad de horas inválida. Ingrese un número mayor a 0.\n")
        return 0
    }


    val tarifaTotal = paciente.cobro * horas

    val registroExitoso = paciente.registrarEntrada()

    if (registroExitoso) {
        println("El paciente ${paciente.id} se ha registrado con éxito")
        return tarifaTotal
    }
    return 0
}


fun resumen(
    paciente: MutableList<Paciente>,
    ingresTotal: Double,
    cantidadAtencioines: Int,
) {
    println("Resumen de Paciente")

    val disponibles = Pacientes.count {
        it.disponible
    }
    val enAtencion = Pacientes.count {
        !it.disponible
    }
    val ingresoPromedio = if (cantidadAtencioines > 0){
        ingresoTotal / cantidadAtencioines
        } else {
            0.0
        }
    println("ingreso total $ingresoTotal:")
    println("Cantidad de Atenciones: $cantidadAtencioines")
    println("Boxes disponibles: $disponibles")
    println("Boxes en atencion: $enAtencion")
    println("Ingreso promedio: $ingresoPromedio")

}


var Pacientes = mutableListOf<Paciente>()

val paciente1 = Paciente("CA12CD", "Canino", "convenio",  false, true)
val paciente2 = Paciente("CA99ZA", "Canino", "particular",  false, true)
val paciente3 = Paciente("FE22TO", "Felino", "particular",  false, true)
val paciente4 = Paciente("EX44RG", "Exotico", "particular",  true, true)
val paciente5 = Paciente("EX77RG", "Exotico", "municipal",  false, true)

Pacientes.add(paciente1)
Pacientes.add(paciente2)
Pacientes.add(paciente3)
Pacientes.add(paciente4)
Pacientes.add(paciente5)

try {
    val pacienteInvalido = Paciente("", "Canino", "particular", false,  true)
    Pacientes.add(pacienteInvalido)

} catch (e: IllegalArgumentException) {
    println("Registro inválido, rechazado: ${e.message}\n")
}


var ingresoTotal = 0.0
var cantidadAtenciones = 0
var opcion = 0

while (opcion != 5) {
    println(""" ===== Centro Veterinario =====
    |         1. Listar pacientes
    |         2. Registrar entrada de paciente
    |         3. Dar de alta a paciente
    |         4. Resumen
    |         5. Salir
""".trimMargin())
    print("Ingrese una opción: ")
    opcion = readln()
        .trim()
        .toIntOrNull() ?: 0 /* Si es texto lo convierte a null, luego devueve un 0 */

    when (opcion) {
        1 -> {
            listar(Pacientes)

        }

        2 -> {
            procesarRegistro(Pacientes)

        }

        3 -> {
            val monto = dardeAlta(Pacientes)
            if (monto > 0) {
                ingresoTotal += monto
                cantidadAtenciones++
            }
        }

        4 -> {
            resumen(Pacientes, ingresoTotal, cantidadAtenciones)

        }

        5 -> {
            println("Saliendo del programa...")

        } else -> {
        println("Opción inválida. Por favor, ingrese una opción válida. \n")
    }
    }
}

