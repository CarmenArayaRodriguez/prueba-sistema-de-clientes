package cl.praxis.servicio;

import cl.praxis.modelo.CategoriaEnum;
import cl.praxis.modelo.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteServicio implements ClienteServicioInterface {  // Indica que esta clase implementa la interfaz
    private List<Cliente> listaClientes;

    public ClienteServicio() {
        listaClientes = new ArrayList<>();
    }

    @Override
    public void agregarCliente(Cliente cliente) {
        if (cliente != null) {
            listaClientes.add(cliente);
        } else {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
    }

    @Override
    public void listarClientes() {
        if (listaClientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            listaClientes.stream().forEach(System.out::println);
        }
    }

    @Override
    public void editarCliente(String runCliente, int opcion, String valor) {
        Cliente cliente = buscarCliente(runCliente);
        if (cliente != null) {
            switch (opcion) {
                case 1: // Cambiar RUN
                    cliente.setRunCliente(valor);
                    break;
                case 2: // Cambiar nombre
                    cliente.setNombreCliente(valor);
                    break;
                case 3: // Cambiar apellido
                    cliente.setApellidoCliente(valor);
                    break;
                case 4: // Cambiar años como cliente
                    try {
                        int anios = Integer.parseInt(valor);
                        cliente.setAniosCliente(anios);
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, ingrese un número válido para los años.");
                    }
                    break;
                case 5: // Cambiar estado
                    try {
                        cliente.setNombreCategoria(CategoriaEnum.valueOf(valor.toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Por favor, ingrese un estado válido ('ACTIVO' o 'INACTIVO').");
                    }
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    @Override
    public Cliente buscarCliente(String runCliente) {
        return listaClientes.stream().filter(cliente -> cliente.getRunCliente().equals(runCliente)).findFirst().orElse(null);
    }

    @Override
    public List<Cliente> getListaClientes() {
        return listaClientes;
    }
}
