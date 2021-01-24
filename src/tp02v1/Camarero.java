package tp02v1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Camarero extends Personas implements Serializable {

    private Menu mn;
    private StockComandas sc;

    public Camarero(String nombre, int id, String pass) {
        super(nombre, id, pass);
        mn = Menu.getInstance();
        sc = StockComandas.getInstance();
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

    /**
     * @return the sc
     */
    public StockComandas getSc() {
        return sc;
    }

    /**
     * @param sc the sc to set
     */
    public void setSc(StockComandas sc) {
        this.sc = sc;
    }

    @Override
    public void ejecutar() {
        boolean salir = false;
        int op = 0;
        int tipo = 0;
        do {
            op = Entrada.LeerInt("Camarero: " + getNombre() + "\n"
                    + "1. Cargar Comanda.\n"
                    + "2. Salir.");
            switch (op) {
                case 1:
                    boolean termina = false;
                    Comandas c = new Comandas();
                    do {
                        tipo = Entrada.LeerInt("¿Que desea Cargar en la comanda?\n"
                                + "1. Bebidas.\n"
                                + "2. Recetas.\n"
                                + "3. Continuar.");
                        switch (tipo) {
                            case 1:
                                boolean terminaBebi = false;
                                do {

                                    int pedidoBebida = 0;
                                    pedidoBebida = Entrada.LeerInt(mn.muestraMenuBebidas() + (mn.getLmenu().size() + 1) + " Terminar de Cargar de Bebidas.");
                                    if (pedidoBebida == mn.getLmenu().size() + 1) {
                                        terminaBebi = true;
                                    } else {
                                        c.cargaBebida((Bebidas) mn.getLmenu().get(pedidoBebida - 1));

                                    }
                                } while (!terminaBebi);
                                break;
                            case 2:
                                boolean terminaRece = false;
                                do {
                                    int pedidoRecetas = 0;

                                    pedidoRecetas = Entrada.LeerInt(mn.muestraMenuRecetas() + (mn.getLmenu().size() + 1) + " Terminar de Cargar de Comidas.");
                                    if (pedidoRecetas == mn.getLmenu().size() + 1) {
                                        terminaRece = true;
                                    } else {
                                        c.cargaReceta((Recetas) mn.getLmenu().get(pedidoRecetas - 1));
                                    }
                                } while (!terminaRece);
                                break;
                            default:
                                termina = true;
                                break;
                        }
                    } while (!termina);
                    c.setNumeroMesa(Entrada.LeerInt("Numero de Mesa: "));
                    c.setPrecio(c.precioBebidas() + c.precioRecetas());
                    c.setCm(this);
                    Entrada.Informacion("Mesa: " + c.getNumeroMesa() + "\nPrecio Total: " + c.getPrecio() + "\nCamarero: " + c.getCm().getNombre());
                    sc.getSc().add(c);

                    try {
                        ObjectOutputStream salidaComandas = new ObjectOutputStream(new FileOutputStream("comandas.obj"));
                        salidaComandas.writeObject(sc.getSc());
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Camarero.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Camarero.class.getName()).log(Level.SEVERE, null, ex);
                    }

                case 2:

                    salir = true;
                    break;
            }
        } while (!salir);
    }

}
