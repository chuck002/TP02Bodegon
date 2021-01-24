package tp02v1;

import java.io.Serializable;

public class Bebidas extends Preparaciones implements Serializable{

    public Bebidas(String descripcion, double precio) {
        super(descripcion, precio);
    }

}
