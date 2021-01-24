package tp02v1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sistema implements Serializable{

    private ArrayPersonas ap;

    public Sistema() {
        ap = ArrayPersonas.getInstance();
    }

    public void arrancar() {
        int id = 0;
        String pass;
        boolean aceptado = false;

        do {
            if (ap.getAp().isEmpty()) {
                ap.getAp().add(new Administrador(Entrada.LeerString("Bienvenido. \nPor favor coloque el nombre para el administrador:"), 1, Entrada.LeerString("Coloque su Password: ")));
                
                try {
                    ObjectOutputStream salidaPersonas = new ObjectOutputStream(new FileOutputStream("personas.obj"));
                    salidaPersonas.writeObject(ap.getAp());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            } else {
                try {
                    id = Entrada.LeerInt("Bienvenido al sistema del Bodegon.\n"
                            + "Por favor coloque su ID para comenzar. (1 para Admin)");
                
                if (id == 1) {
                    pass = Entrada.LeerString("Bienvenido Sr. Administrador.\n"
                            + "Por favor coloque su Password para Ingresar.");
                    if (pass.equals(ap.getAp().get(id - 1).getPass())) {
                        ap.getAp().get(id - 1).ejecutar();
                        aceptado = false;
                    } else {
                        Entrada.Informacion("Contraseña Incorrecta.");
                    }
                } else {
                    for (Personas p : ap.getAp()) {
                        System.out.println("Nombre: " + p.getNombre() + "\nID: " + p.getId());
                        if (id == p.getId()) {
                            pass = Entrada.LeerString("Bienvenido Sr. " + p.getNombre() + ".\n"
                                    + "Por favor coloque su Password para Ingresar.");
                            if (pass.equals(ap.getAp().get(id - 1).getPass())) {
                                ap.getAp().get(id - 1).ejecutar();
                                aceptado = false;
                            } else {
                                Entrada.Informacion("Contraseña Incorrecta.");
                            }
                        }
                    }
                }
                
                } catch (NumberFormatException ex) {
                    Entrada.Informacion("No se permiten letras, la opcion debe ser numerica.");
                    aceptado = false;
                }
            }
        } while (!aceptado);
    }

    /**
     * @return the ap
     */
    public ArrayPersonas getAp() {
        return ap;
    }

    /**
     * @param ap the ap to set
     */
    public void setAp(ArrayPersonas ap) {
        this.ap = ap;
    }

}
