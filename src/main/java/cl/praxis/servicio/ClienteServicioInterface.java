package cl.praxis.servicio;

import cl.praxis.modelo.Cliente;
import java.util.List;

public interface ClienteServicioInterface {
    void agregarCliente(Cliente cliente);
    void listarClientes();
    void editarCliente(String runCliente, int opcion, String valor);
    Cliente buscarCliente(String runCliente);
    List<Cliente> getListaClientes();
}
