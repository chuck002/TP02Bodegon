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

public class StockComandas implements Serializable {

    public static StockComandas instance = null;

    private List<Comandas> sc;

    private StockComandas() {
        sc = new ArrayList<>();

        try {
            ObjectInputStream entradaComandas = new ObjectInputStream(new FileInputStream("comandas.obj"));
            sc = (List<Comandas>) entradaComandas.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StockComandas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(StockComandas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static StockComandas getInstance() {
        if (instance == null) {
            instance = new StockComandas();
            return instance;
        } else {
            return instance;
        }
    }

    /**
     * @return the sc
     */
    public List<Comandas> getSc() {
        return sc;
    }

    /**
     * @param sc the sc to set
     */
    public void setSc(List<Comandas> sc) {
        this.sc = sc;
    }

}
