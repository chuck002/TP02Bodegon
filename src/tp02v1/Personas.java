package tp02v1;

import java.io.Serializable;

public abstract class Personas implements Serializable{

    private String nombre;
    private int id;
    private String pass;

    public Personas(String nombre, int id, String pass) {
        this.nombre = nombre;
        this.id = id;
        this.pass = pass;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    public abstract void ejecutar();

}
