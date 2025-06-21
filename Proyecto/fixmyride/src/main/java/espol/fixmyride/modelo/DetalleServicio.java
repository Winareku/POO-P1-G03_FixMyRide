package modelo;

public class DetalleServicio {
    private Servicio servicio;
    private int cantidad;
    private double precio;
    private double total;

    public DetalleServicio(Servicio servicio, int cantidad, double precio) {
        this.servicio = servicio;
        this.cantidad = cantidad;
        this.precio= precio;
    }

    // getters y setters
    public Servicio getServicio() { 
        return servicio; 
    }
    public int getCantidad() { 
        return cantidad;
    }
    public double getPrecio(){
        return precio;
    }
    public void setServicio(Servicio s){
        this.servicio=s;
    }
    public void setCantidad(int c){
        this.cantidad= c;
    }
    public void setPrecio(double p){
        this.precio=p;
    }
    public void setTotal(double t){
        this.total= t;
    }

}
