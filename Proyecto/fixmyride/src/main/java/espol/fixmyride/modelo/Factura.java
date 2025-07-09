package espol.fixmyride.modelo;

public class Factura {
    // Atributos
    Empresa empresa;
    String periodo;


    // Constructor
    public Factura(Empresa empresa, String periodo) {
        this.empresa = empresa;
        this.periodo = periodo;
    }
    
    // Getters y Setters

    @Override
    public String toString(){ return (""); }
}