package tp02v1;

import java.io.Serializable;

public class Recetas extends Preparaciones implements Serializable{
    
    public Recetas(String descripcion, double precio) {
        super(descripcion, precio);
    }
    
}
