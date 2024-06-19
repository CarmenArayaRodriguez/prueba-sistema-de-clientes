package cl.praxis.vista;

import cl.praxis.modelo.CategoriaEnum;
import cl.praxis.modelo.Cliente;
import cl.praxis.servicio.ClienteServicio;
import cl.praxis.servicio.ArchivoServicio;
import cl.praxis.servicio.ExportarCsv;
import cl.praxis.servicio.ExportarTxt;
import cl.praxis.utilidades.Utilidad;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final ClienteServicio clienteServicio = new ClienteServicio();
    private final ArchivoServicio archivoServicio = new ArchivoServicio();
    private final ExportarCsv exportadorCsv = new ExportarCsv();
    private final ExportarTxt exportarTxt = new ExportarTxt();
    private final String fileName = "Clientes";
    private final String fileName1 = "DBClientes.csv";
    private final Scanner scanner = new Scanner(System.in);

    public void iniciarMenu() {
        int opcion;
        do {
            Utilidad.limpiarPantalla();  // Limpia la consola antes de mostrar el menú
            System.out.println("\nMenu Principal:");
            System.out.println("1. Listar Clientes");
            System.out.println("2. Agregar Cliente");
            System.out.println("3. Editar Cliente");
            System.out.println("4. Cargar Datos");
            System.out.println("5. Exportar Datos");
            System.out.println("6. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                listarClientes();
            } else if (opcion == 2) {
                agregarCliente();
            } else if (opcion == 3) {
                editarCliente();
            } else if (opcion == 4) {
                cargarDatos();
            } else if (opcion == 5) {
                exportarDatos();
            } else if (opcion == 6) {
                terminarPrograma();
            } else {
                Utilidad.mostrarMensaje("Opción no válida, por favor intente de nuevo.");
            }
            System.out.println("Presione Enter para continuar.");
            scanner.nextLine();  // Espera que el usuario presione Enter
        } while (opcion != 6);
    }

    private void listarClientes() {
        System.out.println("Listado de clientes:");
        clienteServicio.listarClientes();
    }

private void agregarCliente() {
    System.out.println("Agregar nuevo cliente:");
    try {
        System.out.print("Ingrese RUN del cliente: ");
        String run = scanner.nextLine();
        System.out.print("Ingrese nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese apellido del cliente: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese años como cliente: ");
        int anios = Integer.parseInt(scanner.nextLine()); // Convierte a entero

        clienteServicio.agregarCliente(new Cliente(run, nombre, apellido, anios));
        System.out.println("Cliente agregado exitosamente.");
    } catch (NumberFormatException e) {
        System.out.println("Error: Debe ingresar un número válido para los años."); // Captura el error si el formato del número es incorrecto
    }
}

private void editarCliente() {
    System.out.println("-------------Editar Cliente-------------");
    System.out.print("Ingrese RUN del cliente a editar: ");
    String run = scanner.nextLine();
    Cliente cliente = clienteServicio.buscarCliente(run);
    int eleccion;

    if (cliente != null) {
        do {
            System.out.println("Seleccione qué desea hacer:");
            System.out.println("1.- Cambiar el estado del cliente");
            System.out.println("2.- Editar los datos ingresados del cliente");
            System.out.println("3.- Salir");
            System.out.print("Ingrese opción: ");
            eleccion = scanner.nextInt();
            scanner.nextLine();

            if (eleccion == 1) {
                System.out.println("-----Actualizando estado del Cliente----");
                System.out.println("El estado actual es: " + cliente.getNombreCategoria());
                // Cambia las opciones dependiendo del estado actual
                if (cliente.getNombreCategoria() == CategoriaEnum.ACTIVO) {
                    System.out.println("1.- Si desea cambiar el estado del cliente a Inactivo");
                    System.out.println("2.- Si desea mantener el estado del cliente Activo");
                } else {
                    System.out.println("1.- Si desea cambiar el estado del cliente a Activo");
                    System.out.println("2.- Si desea mantener el estado del cliente Inactivo");
                }
                System.out.print("Ingrese opcion: ");
                int opcionEstado = scanner.nextInt();
                scanner.nextLine();

                if (opcionEstado == 1) {
                    // Cambia el estado al opuesto del actual
                    if (cliente.getNombreCategoria() == CategoriaEnum.ACTIVO) {
                        clienteServicio.editarCliente(run, 5, "INACTIVO");
                        System.out.println("Estado cambiado a Inactivo");
                    } else {
                        clienteServicio.editarCliente(run, 5, "ACTIVO");
                        System.out.println("Estado cambiado a Activo");
                    }
                } else if (opcionEstado == 2) {
                    System.out.println("El estado se mantiene como " + cliente.getNombreCategoria());
                } else {
                    System.out.println("Opción no válida.");
                }
            } else if (eleccion == 2) {
                System.out.println("----Actualizando datos del Cliente-----");
                System.out.println("1.-El RUN del cliente es: " + cliente.getRunCliente());
                System.out.println("2.-El nombre del cliente es: " + cliente.getNombreCliente());
                System.out.println("3.-El apellido del cliente es: " + cliente.getApellidoCliente());
                System.out.println("4.-Los años como cliente son: " + cliente.getAniosCliente());
                System.out.print("Ingrese opción a editar de los datos del cliente: ");
                int opcionDato = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Ingrese el nuevo valor: ");
                String nuevoValor = scanner.nextLine();
                clienteServicio.editarCliente(run, opcionDato, nuevoValor);
                System.out.println("Datos cambiados con éxito.");
            } else if (eleccion == 3) {
                System.out.println("Saliendo del editor...");
            } else {
                System.out.println("Opción no válida.");
            }
        } while (eleccion != 3);
    } else {
        System.out.println("Cliente no encontrado.");
    }
    System.out.println("----------------------------------------");
}

    private void cargarDatos() {
        System.out.println("---------Cargar Datos-----------");
        System.out.println("Presione Enter para usar la ruta predeterminada (" + fileName1 + ") o ingrese una nueva ruta:");
        String entradaUsuario = scanner.nextLine().trim();
        String rutaArchivo = entradaUsuario.isEmpty() ? fileName1 : entradaUsuario;
        cargarDatosDesdeRuta(rutaArchivo);
    }

    private void cargarDatosDesdeRuta(String rutaArchivo) {
        System.out.println("Intentando cargar desde: " + rutaArchivo);
        List<Cliente> clientes = archivoServicio.cargarDatos(rutaArchivo);
        if (clientes.isEmpty()) {
            System.out.println("No se encontraron datos o el archivo está vacío.");
        } else {
            clienteServicio.getListaClientes().addAll(clientes);
            Utilidad.mostrarMensaje("Datos cargados correctamente en la lista");
            mostrarClientes(clientes);
        }
        System.out.println("----- Datos cargados correctamente en la lista -----");
    }

    private void mostrarClientes(List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

private void exportarDatos() {
    System.out.println("Exportar Datos:");
    System.out.print("Seleccione el formato a exportar:\n1.- Formato csv\n2.- Formato txt\nIngrese una opción para exportar:");
    int formato = scanner.nextInt();
    scanner.nextLine();

    String archivoPredeterminado = (formato == 1) ? this.fileName + ".csv" : this.fileName + ".txt";
    System.out.println("Presione Enter para usar la ruta predeterminada (" + archivoPredeterminado + ") o ingrese una nueva ruta:");
    String entradaUsuario = scanner.nextLine().trim();

    String rutaArchivo = entradaUsuario.isEmpty() ? archivoPredeterminado : entradaUsuario;

    if (formato == 1) {
        exportadorCsv.exportar(rutaArchivo, clienteServicio.getListaClientes());
    } else if (formato == 2) {
        exportarTxt.exportar(rutaArchivo, clienteServicio.getListaClientes());
    } else {
        System.out.println("Opción no válida, por favor intente de nuevo.");
    }
    System.out.println("Datos exportados correctamente en formato " + (formato == 1 ? "csv" : "txt") + " a: " + rutaArchivo);
}

    private void terminarPrograma() {
        System.out.println("Saliendo del programa.");
        scanner.close();
        System.exit(0);
    }
}
