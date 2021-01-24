package tp02v1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Administrador extends Personas implements Serializable {
    
    private ArrayPersonas ap;
    private StockComandas sc;
    private Menu mn;
    
    public Administrador(String nombre, int id, String pass) {
        super(nombre, id, pass);
        ap = ArrayPersonas.getInstance();
        sc = StockComandas.getInstance();
        mn = Menu.getInstance();
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
        int op;
        
        do {
            try {
                op = Entrada.LeerInt("Bienvenido Sr. Administrador " + getNombre() + ".\n"
                        + "¿Que desea hacer?: \n"
                        + "1. Agregar nuevo Cocinero.\n"
                        + "2. Agregar nuevo Camarero.\n"
                        + "3. Valorar Recetas y Bebidas.\n"
                        + "4. Ver Informes.\n"
                        + "5. Salir del Usuario.\n"
                        + "6. Salir del Sistema.");
                switch (op) {
                    case 1:
                        Cocinero coci = new Cocinero(Entrada.LeerString("Cargar nuevo Cocinero.\n" + "Por favor coloca su nombre: "), ap.getAp().size() + 1,
                                Entrada.LeerString("Cargar nuevo Cocinero.\n" + "Por favor coloca su password: "));
                        ap.getAp().add(coci);
                        
                        try {
                            ObjectOutputStream salidaPersonas = new ObjectOutputStream(new FileOutputStream("personas.obj"));
                            salidaPersonas.writeObject(ap.getAp());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        Entrada.Informacion("Cocinero Agregado al Plantel");
                        break;
                    case 2:
                        ap.getAp().add(new Camarero(Entrada.LeerString("Cargar nuevo Camarero.\n" + "Por favor coloca su nombre: "), ap.getAp().size() + 1,
                                Entrada.LeerString("Cargar nuevo Camarero.\n" + "Por favor coloca su password: ")));
                        
                        try {
                            ObjectOutputStream salidaPersonas = new ObjectOutputStream(new FileOutputStream("personas.obj"));
                            salidaPersonas.writeObject(ap.getAp());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        Entrada.Informacion("Camarero Agregado al Plantel.");
                        break;
                    case 3:
                        if (mn.getLmenu().isEmpty()) {
                            Entrada.Informacion("Todavia no hay nada cargado en el menu. ");
                        } else {
                            for (Preparaciones p : mn.getLmenu()) {
                                p.setPrecio(Double.parseDouble(Entrada.LeerString(p.getDescripcion() + "\nQue Precio desea ponerle?: ")));
                            }
                            
                            try {
                                ObjectOutputStream salidaMenu = new ObjectOutputStream(new FileOutputStream("menu.obj"));
                                salidaMenu.writeObject(mn.getLmenu());
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        }
                        
                        break;
                    case 4:
                        boolean salirInformes = false;
                        do {
                            switch (Entrada.LeerInt("Ver Informes del dia.\n"
                                    + "1. Recaudacion Total.\n"
                                    + "2. Preparacion mas pedida.\n"
                                    + "3. Bebida mas pedida.\n"
                                    + "4. Camarero que atendio mas pedidos.\n"
                                    + "5. Salir del usuario.\n")) {
                                case 1:
                                    double total = 0;
                                    if (sc.getSc().isEmpty()) {
                                        Entrada.Informacion("No hubo comandas durante el dia.");
                                    } else {
                                        for (Comandas c : sc.getSc()) {
                                            total = total + c.getPrecio();
                                        }
                                        Entrada.Informacion("Recaudacion total del dia: $" + total);
                                    }
                                    break;
                                case 2:
                                    Entrada.Informacion(preparacionMasPedida(sc));
                                    break;
                                case 3:
                                    Entrada.Informacion(bebidaMasPedida(sc));
                                    break;
                                case 4:
                                    
                                    Entrada.Informacion(camareroConMasPedidos(sc));
                                    break;
                                
                                case 5:
                                    salirInformes = true;
                                    break;
                            }
                            
                        } while (!salirInformes);
                    case 5:
                        Entrada.Informacion("Saliendo del Administrador. \nAdios.");
                        salir = true;
                        break;
                    case 6:
                        System.exit(0);
                        break;
                }
            } catch (NumberFormatException | NullPointerException ex) {
                Entrada.Informacion("No se permiten letras, la opcion debe ser numerica.");
                salir = false;
            }
        } while (!salir);
        
    }
    
    private String preparacionMasPedida(StockComandas sc) {
        int[] prepa = new int[100];
        int contador = 0;
        int pos = 0;
        int tmp = 0;
        try {
            for (Preparaciones beb : mn.getLmenu()) {
                for (int i = 0; i < sc.getSc().size(); i++) {
                    for (int j = 0; j < sc.getSc().get(i).getRec().size(); j++) {
                        if (beb.getDescripcion().equals(sc.getSc().get(i).getRec().get(j).getDescripcion())) {
                            prepa[contador]++;
                        }
                    }
                    
                }
                contador++;
            }
            
            for (int i = 0; i < prepa.length; i++) {
                if (prepa[i] > tmp) {
                    tmp = prepa[i];
                    pos = i;
                }
            }
            
            return "La preparacion mas pedida fue " + mn.getLmenu().get(pos).getDescripcion() + " con " + prepa[pos] + " recetas pedidas.";
        } catch (IndexOutOfBoundsException ex) {
            return ex.getMessage();
        }
    }
    
    private String bebidaMasPedida(StockComandas sc) {
        Preparaciones p = null;
        int[] prepa = new int[100];
        int contador = 0;
        int pos = 0;
        int tmp = 0;
        for (Preparaciones beb : mn.getLmenu()) {
            for (int i = 0; i < sc.getSc().size(); i++) {
                for (int j = 0; j < sc.getSc().get(i).getBeb().size(); j++) {
                    if (beb.getDescripcion().equals(sc.getSc().get(i).getBeb().get(j).getDescripcion())) {
                        prepa[contador]++;
                    }
                }
            }
            contador++;
        }
        
        for (int i = 0; i < prepa.length; i++) {
            if (prepa[i] > tmp) {
                tmp = prepa[i];
                pos = i;
            }
        }
        
        return "La bebida mas pedida fue " + mn.getLmenu().get(pos).getDescripcion() + " con " + prepa[pos] + " bebidas pedidas.";
    }
    
    private String camareroConMasPedidos(StockComandas sc) {
        int[] camarero = new int[100];
        int recorre = 0;
        int tot = 0;
        int posi = 0;
        for (Personas pers : ap.getAp()) {
            for (int i = 0; i < sc.getSc().size(); i++) {
                System.out.println(i + "\n");
                if (pers.getNombre().equals(sc.getSc().get(i).getCm().getNombre())) {
                    camarero[recorre]++;
                }
            }
            recorre++;
        }
        for (int j = 0; j < camarero.length; j++) {
            if (tot < camarero[j]) {
                tot = camarero[j];
                posi = j;
            }
        }
        return "El camarero que mas pedidos saco el dia de hoy"
                + " fue: " + ap.getAp().get(posi).getNombre() + ".";
    }
    
}
