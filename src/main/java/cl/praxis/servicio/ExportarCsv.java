package cl.praxis.servicio;

import cl.praxis.modelo.Cliente;
import cl.praxis.utilidades.Utilidad;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;

public class ExportarCsv extends Exportador {
    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            pw.println("RUN,Nombre,Apellido,Años,Estado");
            for (Cliente cliente : listaClientes) {
                pw.println(cliente.getRunCliente() + "," +
                        cliente.getNombreCliente() + "," +
                        cliente.getApellidoCliente() + "," +
                        cliente.getAniosCliente() + "," +
                        cliente.getNombreCategoria());
            }
        } catch (IOException e) {
            Utilidad.mostrarMensaje("Error al escribir el archivo csv: " + e.getMessage());
        }
    }
}
