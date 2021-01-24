package tp02v1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu implements Serializable{

    public static Menu instance = null;

    private List<Preparaciones> lmenu;

    private Menu() {
        lmenu = new ArrayList<>();
        
        try {
            ObjectInputStream entradaMenu=new ObjectInputStream(new FileInputStream("menu.obj"));
            lmenu = (List<Preparaciones>) entradaMenu.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
            return instance;
        } else {
            return instance;
        }
    }

    /**
     * @return the lmenu
     */
    public List<Preparaciones> getLmenu() {
        return lmenu;
    }
    
    public void agregaPreparacion(Preparaciones prepa)
    {
        lmenu.add(prepa);
    }

    public String muestraMenuBebidas() {
        String salida = "";
        for(int i = 0; i < lmenu.size(); i++)
        {
            if(lmenu.get(i) instanceof Bebidas)
            {   
            if(lmenu.get(i).getPrecio() != 0.0)
            {
            salida = salida + (i+1)+lmenu.get(i).toString()+"\n";
            }
            }
        }
        
        return salida;
    }
    public String muestraMenuRecetas() {
        String salida = "";
        for(int i = 0; i < lmenu.size(); i++)
        {
              if(lmenu.get(i) instanceof Recetas)
            {                    
            if(lmenu.get(i).getPrecio() != 0.0)
            {
            salida = salida + (i+1)+lmenu.get(i).toString()+"\n";
            }
            }
        }
        
        return salida;
    }

    /**
     * @param lmenu the lmenu to set
     */
    public void setLmenu(List<Preparaciones> lmenu) {
        this.lmenu = lmenu;
    }

}
