package tp02v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Comandas implements Serializable{
    
    private List<Bebidas> beb;
    private List<Recetas> rec;
    private int numeroMesa; 
    private double precio;
    private Camarero cm;

    public Comandas() {
        beb = new ArrayList<>();
        rec = new ArrayList<>();
    }
    

    public List<Bebidas> getBeb() {
        return beb;
    }

    public void setBeb(List<Bebidas> beb) {
        this.beb = beb;
    }
    public void cargaBebida(Bebidas b)
    {
        beb.add(b);
    }
    public void cargaReceta(Recetas r)
    {
        rec.add(r);
    }
    
    public double precioBebidas()
    {
        double tmp = 0;
        for(Preparaciones p: beb)
        {
            precio = precio + p.getPrecio();
        }
        return precio;
    }
     public double precioRecetas()
    {
        double tmp = 0;
        for(Preparaciones p: rec)
        {
            precio = precio + p.getPrecio();
        }
        return precio;
    }

    public List<Recetas> getRec() {
        return rec;
    }

    public void setRec(List<Recetas> rec) {
        this.rec = rec;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Camarero getCm() {
        return cm;
    }

    public void setCm(Camarero cm) {
        this.cm = cm;
    }

   
}
