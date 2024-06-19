package cl.praxis.test;

import cl.praxis.modelo.Cliente;
import cl.praxis.modelo.CategoriaEnum;
import cl.praxis.servicio.ClienteServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class ClienteServicioTest {
    private ClienteServicio clienteServicio;

    @BeforeEach
    void setUp() {
        clienteServicio = new ClienteServicio();
    }

    @Test
    void agregarClienteTest() {
        Cliente cliente = new Cliente("12.345.678-9", "Alejandra", "Barrios", 3, CategoriaEnum.ACTIVO);
        clienteServicio.agregarCliente(cliente);
        List<Cliente> clientes = clienteServicio.getListaClientes();
        assertTrue(clientes.contains(cliente), "El cliente debe estar en la lista después de ser agregado.");
    }


    @Test
    void estadoPorDefectoDebeSerActivo() {
        Cliente cliente = new Cliente("23.456.789-0", "Ester", "Barrios", 5);
        assertEquals(CategoriaEnum.ACTIVO, cliente.getNombreCategoria(), "El estado por defecto del cliente debe ser 'Activo'");
    }

    @Test
    void agregarClienteNullTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            clienteServicio.agregarCliente(null);
        });
        assertEquals("El cliente no puede ser nulo", exception.getMessage(), "Se esperaba un mensaje específico al intentar agregar un cliente nulo.");
    }
}
