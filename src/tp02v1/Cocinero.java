package tp02v1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cocinero extends Personas implements Serializable {

    private Menu mn;

    public Cocinero(String nombre, int id, String pass) {
        super(nombre, id, pass);
        mn = Menu.getInstance();
    }

    /**
     * @return the mn
     */
    public Menu getMn() {
        return mn;
    }

    /**
     * @param mn the mn to set
     */
    public void setMn(Menu mn) {
        this.mn = mn;
    }

    @Override
    public void ejecutar() {
        boolean salir = false;
        int op = 0;
        int tipo = 0;
        do {
            op = Entrada.LeerInt("Bienvenido Sr/a Cocinero/a " + getNombre() + ".\n"
                    + "1. Cargar Recetas.\n"
                    + "2. Salir.");
            switch (op) {
                case 1:
                    tipo = Entrada.LeerInt("¿Que desea Agregar?: \n"
                            + "1. Bebidas.\n"
                            + "2. Recetas.\n"
                            + "3. Salir.");
                    switch (tipo) {
                        case 1:
                            mn.agregaPreparacion(new Bebidas(Entrada.LeerString("Coloque el nombre de la bebida: "), 0.0));
                            
                            try {
                                ObjectOutputStream salidaMenu = new ObjectOutputStream(new FileOutputStream("menu.obj"));
                                salidaMenu.writeObject(mn.getLmenu());
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Cocinero.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(Cocinero.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;

                        case 2:
                            mn.agregaPreparacion(new Recetas(Entrada.LeerString("Coloque el nombre y la descripcion de la receta."), 0.0));

                            try {
                                ObjectOutputStream salidaMenu = new ObjectOutputStream(new FileOutputStream("menu.obj"));
                                salidaMenu.writeObject(mn.getLmenu());
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Cocinero.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(Cocinero.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            break;

                        default:
                            break;
                    }
                    break;
                default:
                    salir = true;
                    break;
            }

        } while (!salir);
    }

}
