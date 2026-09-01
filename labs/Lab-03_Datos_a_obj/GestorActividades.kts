// --------------------------------------------------------------------------------------------------------------------------------------


//01 · Colecciones y deuda de modelado
var lista: MutableList<String> = mutableListOf("MonkeyDPasta","MonkeyDLuffy", "MonkeyDGarp", "pepito", "andres")
println(lista )
println("\n")

// for
for (nombre in lista) {
    println("La nombre es: $nombre")
}
println("\n")

//for each
lista.forEach {
    println("Los integrantes son: $it")
}
println("\n")

//combinado
lista.filter { it.startsWith("MonkeyD") }
    .forEach { println("Los integrantes que empiezan con MonkeyD son: $it") }
println("\n")

//map y foreach
val  nuevaLista = lista.map { if (it.startsWith("MonkeyD")) it else "MonkeyD" + it}
println(nuevaLista)
println("\n")

//count
val test = nuevaLista.count { it.startsWith("MonkeyD") }
println("Hay " + test + " integrantes que empiezan con MonkeyD \n")


// --------------------------------------------------------------------------------------------------------------------------------------


//02 · Clases y colecciones de objetos

class Actividad(
        val nombre: String,
        val duracion: Int,
        val prioridad: Int) {

    fun resumen(): String {
        var resumen = ""
        resumen += "Actividad: $nombre | Duración: $duracion | Prioridad: $prioridad \n"
        return resumen
    }
}

val actividades = mutableListOf<Actividad>() // Cree una lista mutable que guarda obj tipo Actividad

val actividad1 = Actividad("Estudiar", 2, 1)
val actividad3 = Actividad("Comer", 1, 3)
val actividad2 = Actividad("Dormir", 8, 2)

actividades.add(actividad1)
actividades.add(actividad2)
actividades.add(actividad3)

println(actividad1.resumen())
println(actividad2.resumen())
println(actividad3.resumen())


/*fun resumen(): String {
    var resumen = ""
    for (actividad in actividades){
        resumen += "Actividad: ${actividad.nombre} | Duración: ${actividad.duracion} | Prioridad: ${actividad.prioridad} \n"
    }
    return resumen
}
println(resumen())
*/

