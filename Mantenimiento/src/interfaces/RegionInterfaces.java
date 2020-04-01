
package interfaces;


import java.util.ArrayList;
import modelo.Region;

public interface RegionInterfaces {
    public String agregarRegion(Region region);
    public String eliminarRegion(Region region);
    public String modificarRegion(Region region);
    //Devuelve un objeto
    public Region obtenerRegion(int  codRegion);
    //Aqui se colocan todos los metodos que llevaran la implementacion de los datos
    public ArrayList<Region> listaRegiones(); 
}
