package cl.praxis.modelo;

public class Cliente {
    private String runCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private int aniosCliente;
    private CategoriaEnum nombreCategoria;


    public Cliente() {
        this.nombreCategoria = CategoriaEnum.ACTIVO; // Estado por defecto
    }

    // Constructor que no especifica la categoría, asigna 'ACTIVO' por defecto
    public Cliente(String runCliente, String nombreCliente, String apellidoCliente, int aniosCliente) {
        this(runCliente, nombreCliente, apellidoCliente, aniosCliente, CategoriaEnum.ACTIVO);
    }


    // Constructor con parámetros
    public Cliente(String runCliente, String nombreCliente, String apellidoCliente, int aniosCliente, CategoriaEnum nombreCategoria) {
        this.runCliente = runCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.aniosCliente = aniosCliente;
        this.nombreCategoria = nombreCategoria;
    }

    // Getters y Setters
    public String getRunCliente() {
        return runCliente;
    }

    public void setRunCliente(String runCliente) {
        this.runCliente = runCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public int getAniosCliente() {
        return aniosCliente;
    }

    public void setAniosCliente(int aniosCliente) {
        this.aniosCliente = aniosCliente;
    }

    public CategoriaEnum getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(CategoriaEnum nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    // Método toCsvFormat para exportar datos en formato csv
    public String toCsvFormat() {
        return runCliente + "," + nombreCliente + "," + apellidoCliente + "," + aniosCliente + "," + nombreCategoria;
    }

    // Método toString
    @Override
    public String toString() {
        return "-------------Datos del Cliente-------------\n" +
                "RUN del Cliente: " + runCliente + "\n" +
                "Nombre del Cliente: " + nombreCliente + "\n" +
                "Apellido del Cliente: " + apellidoCliente + "\n" +
                "Años como Cliente: " + aniosCliente + " años\n" +
                "Categoría del Cliente: " + nombreCategoria +
                "\n-------------------------------------------";
    }
}
