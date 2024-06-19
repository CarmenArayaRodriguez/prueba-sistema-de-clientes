package cl.praxis.utilidades;

public class Utilidad {

    // Método para limpiar la pantalla en la consola
    public static void limpiarPantalla() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            // Aquí se captura cualquier excepción, incluyendo problemas con la variable TERM no configurada
            System.out.println("No se pudo limpiar la pantalla debido a: " + e.getMessage());
        }
    }

    // Método para mostrar mensajes
    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
