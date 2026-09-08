import java.time.LocalDateTime

class Paciente(
    val id: String,
    val nombre: String,
    val especie: String,
    val tipo: String,
    val tipoDueño: String,
    val silvestre: Boolean = false
){
    val  fechaIngreso = LocalDateTime.now()

    init {
        require(id.isNotBlank()) {
            "El ID del paciente no puede estar vacío. \n"
        }
        require(nombre.isNotBlank()) {
            "El nombre del paciente no puede estar vacío. \n"
        }
        require(especie.isNotBlank()) {
            "La especie del paciente no puede estar vacía. \n"
        }
        require(tipo in listOf("Canino", "Felino", "Exotico")) {
            "El tipo de dueño del paciente no es válido. \n"
        }
        require(tipoDueño in listOf("convenio", "particular", "municipal")) {
            "El tipo de dueño del paciente no es válido. \n"
        }
    }

    fun calcularTarifa(minutos: Int): Double {

        require(minutos > 0) {
            "La cantidad de minutos debe ser mayor a 0. \n"
        }
        val horas = minutos / 60.0
        var costo = when (tipo){

            "Canino" -> {
                12000 * horas
            }
            "Felino" -> {
                if (minutos < 20) {
                    0.0
                } else {
                    9000 * horas
                }
            }
            "Exotico" -> {
                var costoExotico = 20000 * horas

                if (silvestre) {
                    costoExotico *= 1.30
                }
                costoExotico
            }
            else -> {
                0.0
            }
        }

        if(tipo == "Felino" && minutos < 20) {
            return 0.0
        }

        if(costo <= 0) {
            throw IllegalArgumentException("La tarifa no puede ser negativa. \n")
        }

        if(tipoDueño == "convenio") {
            costo *= 0.80
        }
        costo *= 1.19

        if (tipoDueño == "municipal") {
            costo *= 0.50
        }
        return costo
    }
}

class box (
    val numero: Int,
) {
        var estado = "Libre"
        var paciente: Paciente? = null
        var motivo = ""

        fun entrada(nuevoPaciente: Paciente): Boolean {
            if (estado != "Libre") {
                return false
            }
            paciente = nuevoPaciente
            estado = "En proceso"
            motivo = "Registrando entrada"
            return true
        }
        fun confirmarEntrada() {
            if (estado == "En proceso") {
                estado = "En atención"
                motivo = ""
            }
        }
        fun iniciarSalida(): Boolean {
            if (estado != "En atención") {
                return false
            }
            estado= "En proceso"
            motivo = "Registrando salida"

            return true
        }
        fun cancelarSalida(){
            estado = "En atención"
            motivo = ""
        }
        fun liberar(){
            paciente = null
            estado = "Libre"
            motivo = ""
        }
        fun fueraDeServicio(motivoNuevo: String){
            if (estado == "Libre" && motivoNuevo.isNotBlank()) {
                estado = "Fuera de servicio"
                motivo = motivoNuevo
            }
        }
}
class ticket (
    val numero: Int,
    val paciente: Paciente,
    val minutos: Int,
    val monto:  Double
)

val boxes = mutableListOf<box>()
    for (i in 1..10) {
        boxes.add(box(i))
    }

val historal = mutableListOf<ticket>()
var ingresoTotal = 0.0
var numeroTicket = 1

fun buscarBoxLibre(): box? {
    return boxes.find { it.estado == "Libre" }
}
fun buscarPaciente(id: String): box? {
    return boxes.find { it.paciente?.id == id }
}
fun listarBoxes() {
    println("Estado de los boxes:")
    for (box in boxes) {
        val pacienteId = box.paciente?.id ?: "Sin paciente"
        println("Box ${box.numero}: | Estado: ${box.estado}  | Paciente ID: $pacienteId | Motivo: ${box.motivo}")
    }
}
fun registrarEntrada(){
    try {
        print("Ingrese el ID del paciente: ")
        val id = readln()
                .trim()
                .uppercase()
        val paciente = Pacientes.find { it.id == id }
        if (paciente == null) {
            println("No se encontró un paciente con ID $id. \n")
            return
        }

        if (boxes.any { it.paciente?.id == id }) {
            println("El paciente con ID $id ya se encuentra en un box. \n")
            return
        }
        val boxLibre = buscarBoxLibre()

        if (boxLibre == null) {
            println("No hay boxes disponibles para el paciente con ID $id. \n")
            return
        }
        boxLibre.entrada(paciente)
        boxLibre.confirmarEntrada()
        println("Paciente con ID $id ingresado al box ${boxLibre.numero}. \n")

    } catch (e: Exception) {
        println("Error al registrar entrada: ${e.message} \n")
    }
}
fun registrarSaida(){
    print("Ingrese el ID del paciente para registrar salida: ")
    val id = readln().trim().uppercase()
    val boxPaciente = buscarPaciente(id)

    if (boxPaciente == null) {
        println("No se encontró un paciente con ID $id en ningún box. \n")
        return
    }
    if (boxPaciente.estado != "En atención") {
        println("El paciente con ID $id no está en atención. \n")
        return
    }
    val paciente = boxPaciente.paciente?: return

    print("Ingrese la cantidad de minutos de atención: ")
    val minutos = readln().trim().toIntOrNull()

    if (minutos == null || minutos <= 0) {
        println("Cantidad de minutos inválida. \n")
        return
    }
    if (!boxPaciente.iniciarSalida()) {
        print("No se puede iniciar la salida del paciente con ID $id. \n")
        return
    }
    try{
        val monto = paciente.calcularTarifa(minutos)
        val ticket = ticket(numeroTicket, paciente, minutos, monto)
        historal.add(ticket)
        ingresoTotal += monto
        numeroTicket++

        boxPaciente.liberar()

        println("=======Ticket=======")
        println("Número de ticket: ${ticket.numero}")
        println("Paciente: ${ticket.paciente.nombre} | ID: ${ticket.paciente.id}")
        println("Minutos de atención: ${ticket.minutos} min")
        println("Monto a pagar: $${"%.2f".format(ticket.monto)}")
        println("====================\n")

    } catch (e: Exception) {
        println("Error al calcular la tarifa: ${e.message} \n")
        boxPaciente.cancelarSalida()
        return
    }
}
fun resumen(){
    println("Resumen de ingresos y atenciones:")
    historal.forEach {
        println("Ticket: ${it.numero} | Paciente: ${it.paciente.tipo} | Minutos: ${it.minutos} min | Monto: ${it.monto}")
        }

    val disponibles = boxes.count { it.estado == "Libre" }
    val promedio = if (historal.isNotEmpty()) {
                        historal.map { it.monto }.average() } else 0.0
    val convenio = historal.filter { it.paciente.tipoDueño == "convenio" }
    val ids = historal.map { it.paciente.id }

    val mayorTiempo = historal.maxByOrNull { it.minutos }
    val ingresoCanino = historal.filter { it.paciente.tipo == "Canino" }.sumOf { it.monto }
    val ingresoFelino = historal.filter { it.paciente.tipo == "Felino" }.sumOf { it.monto }
    val ingresoExotico = historal.filter { it.paciente.tipo == "Exotico" }.sumOf { it.monto }

    var tipoMayor = "Canino"
    var mayorIngreso = ingresoCanino
    if (ingresoFelino > mayorIngreso) {
        tipoMayor = "Felino"
        mayorIngreso = ingresoFelino
    }
    if (ingresoExotico > mayorIngreso) {
        tipoMayor = "Exotico"
        mayorIngreso = ingresoExotico
    }
    println("Total de ingresos: $ingresoTotal")
    println("Cantidad de atenciones: ${historal.size}")
    println("Cantidad de boxes disponibles: $disponibles")
    println("Promedio de ingresos por atención: $promedio")
    println("Cantidad de pacientes con convenio: ${convenio.size}")
    println("IDs de pacientes atendidos: $ids")
    if (mayorTiempo != null) {
        println("Paciente con mayor tiempo de atención: ${mayorTiempo.paciente.nombre} con ${mayorTiempo.minutos} minutos")
    } else {
        println("No hay pacientes atendidos.\n")
    }
}
val paciente1 = Paciente("CA12CD", "Max", "Golden Retriever", "Canino", "convenio")
val paciente2 = Paciente("CA99ZA", "Luna", "Labrador", "Canino", "particular")
val paciente3 = Paciente("FE22TO", "Misi", "Siamés", "Felino", "particular")
val paciente4 = Paciente("EX44RG", "Loro", "Amazónico", "Exotico", "municipal", true)
val paciente5 = Paciente("EX77RG", "Iguana", "Verde", "Exotico", "particular", false)
var Pacientes = mutableListOf<Paciente>(paciente1, paciente2, paciente3, paciente4, paciente5)

try {
    val pacienteInvalido = Paciente("", "test", "equis", "Canino",  "particular")
    Pacientes.add(pacienteInvalido)
} catch (e: IllegalArgumentException) {
    println("Registro inválido, rechazado: ${e.message}\n")
}
var opcion = 0
while (opcion != 5) {
    println(""" ===== Centro Veterinario =====
    |         1. Listar pacientes
    |         2. Registrar entrada de paciente
    |         3. Registrar salida
    |         4. Resumen
    |         5. Salir
    """.    trimMargin())
        print("Ingrese una opción: ")
        opcion = readln()
        .trim()
        .toIntOrNull() ?: 0 /* Si es texto lo convierte a null, luego devueve un 0 */
        when (opcion) {
            1 -> listarBoxes()
            2 -> registrarEntrada()
            3 -> registrarSaida()
            4 -> resumen()
            5 -> {
                println("Saliendo del programa... \n")
            } else -> {
            println("Opción inválida. Por favor, ingrese una opción válida. \n")
            }
        }
    }
