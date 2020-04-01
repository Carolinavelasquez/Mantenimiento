
package vista;

import controlador.RegionControlador;

public class Ejecutar {

    public static void main(String[] args) {
        JFrmRegion Formulario=new JFrmRegion();
        RegionControlador controlador=new RegionControlador(Formulario);
        Formulario.setVisible(true);
    }
    
}
