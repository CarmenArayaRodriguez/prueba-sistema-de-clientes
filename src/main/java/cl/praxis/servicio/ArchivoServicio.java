package cl.praxis.servicio;

import cl.praxis.modelo.Cliente;
import cl.praxis.modelo.CategoriaEnum;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoServicio extends Exportador {

    // Método para cargar datos de un archivo
    public List<Cliente> cargarDatos(String fileName) {
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine(); // Lee y descarta la primera línea del archivo (los encabezados)
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) { // Asegura que todas las partes requeridas están presentes
                    try {
                        int anios = Integer.parseInt(data[3]); // Conviete a entero
                        Cliente cliente = new Cliente(data[0], data[1], data[2], anios, CategoriaEnum.valueOf(data[4]));
                        clientes.add(cliente);
                    } catch (NumberFormatException e) {
                        System.err.println("Error al convertir a número en la línea: " + line + "; Error: " + e.getMessage());
                    }
                } else {
                    System.err.println("Formato de línea incorrecto: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar datos del archivo: " + e.getMessage());
        }
        return clientes;
    }

    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Cliente cliente : listaClientes) {
                writer.println(cliente.toCsvFormat());
            }
        } catch (IOException e) {
            System.err.println("Error al exportar datos: " + e.getMessage());
        }
    }
}
