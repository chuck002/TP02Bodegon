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

public class ArrayPersonas implements Serializable {
    private static ArrayPersonas instance = null;
    
    private List<Personas> ap;
    
    private ArrayPersonas() {
        ap = new ArrayList<>();
        
        try {
            ObjectInputStream entradaPersonas = new ObjectInputStream(new FileInputStream("personas.obj"));
            ap = (List<Personas>)entradaPersonas.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArrayPersonas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArrayPersonas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ArrayPersonas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static ArrayPersonas getInstance()
    {
        if(instance == null)
        {
            instance = new ArrayPersonas();
            return instance;
        }
        else
        {
            return instance;
        }
    }

    /**
     * @return the ap
     */
    public List<Personas> getAp() {
        return ap;
    }

    /**
     * @param ap the ap to set
     */
    public void setAp(List<Personas> ap) {
        this.ap = ap;
    }

}
