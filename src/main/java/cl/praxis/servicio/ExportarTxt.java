package cl.praxis.servicio;

import cl.praxis.modelo.Cliente;
import cl.praxis.utilidades.Utilidad;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;

public class ExportarTxt extends Exportador {
    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName ))) {
            for (Cliente cliente : listaClientes) {
                pw.println("Cliente: " + cliente.getRunCliente());
                pw.println("Nombre: " + cliente.getNombreCliente());
                pw.println("Apellido: " + cliente.getApellidoCliente());
                pw.println("Años como Cliente: " + cliente.getAniosCliente());
                pw.println("Estado: " + cliente.getNombreCategoria());
                pw.println("-------------------------------------");
            }
        } catch (IOException e) {
           Utilidad.mostrarMensaje("Error al escribir el archivo txt: " + e.getMessage());
        }
    }
}
