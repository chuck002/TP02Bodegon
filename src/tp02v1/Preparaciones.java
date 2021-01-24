package tp02v1;

import java.io.Serializable;

public abstract class Preparaciones implements Serializable{
    
    private String descripcion;
    private double precio;

    public Preparaciones(String descripcion, double precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "["+descripcion + "] precio =$" + precio;
    }
    
}
