
public static void main(String[] args) {
    String modelo = "Galaxy A55";
    String nombrePropietario = "";
    int bateria = 18;
    boolean ahorroDeEnergia = false;

    String estadoBateria = obtenerEstadoBateria(bateria);

    System.out.println("=== TELÉFONO ===");
    System.out.println("Modelo: " + modelo);
    System.out.println("Bateria: " + bateria + "%");
    System.out.println("Estado de bateria: " + estadoBateria);
    System.out.println("Ahorro de energia: " + ahorroDeEnergia);
    System.out.println("Nombre propietario: " + (nombrePropietario.isEmpty() ? "Sin propietario" : nombrePropietario));
}

    public static String obtenerEstadoBateria(int bateria) {
        if (bateria <= 20) {
            return "Batería baja";
        } else if (bateria > 20 && bateria <= 80) {
            return "Batería suficiente";
        } else {
            return "Batería Alta";
        }
    }



