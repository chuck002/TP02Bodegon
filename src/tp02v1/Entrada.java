package tp02v1;

import java.io.Serializable;
import javax.swing.JOptionPane;

public class Entrada implements Serializable{

    public static String LeerString(String msj)
    {
       return JOptionPane.showInputDialog(msj);
    }
    
    public static int LeerInt(String msj)
    {
        return Integer.parseInt(JOptionPane.showInputDialog(msj));
    }

    public static void Informacion(String msj) {
        JOptionPane.showInternalMessageDialog(null, msj);
    }
    
}
