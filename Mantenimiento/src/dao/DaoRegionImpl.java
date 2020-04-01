package dao;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Region;
import interfaces.RegionInterfaces;

public class DaoRegionImpl implements RegionInterfaces {

    private String mensaje;
    private PreparedStatement ejecutar;
    Conexion conex = new Conexion();

    @Override
    public String agregarRegion(Region region) {
        conex.abrirConexion();
        String sqlInsertar = "insert into regiones values(?,?,?)";
        try {
            ejecutar = conex.getMiConex().prepareStatement(sqlInsertar);
            ejecutar.setInt(1, region.getCodregion());
            ejecutar.setString(2, region.getNombre());
            ejecutar.setString(3, region.getDescripcion());
            ejecutar.executeUpdate();
            mensaje = "datos almacenados con exito";

        } catch (SQLException ex) {
            mensaje = "No se almacenaron los datos";
            System.out.println("Error en DacRegionImp: " + ex);
        }
        conex.cerrarConexion();
        return mensaje;
    }

    @Override
    public String eliminarRegion(Region region) {
        conex.abrirConexion();
        String sqlEliminar = "delete from regiones where cod_Region=?";
        try {

            ejecutar = conex.getMiConex().prepareStatement(sqlEliminar);
            ejecutar.setInt(1, region.getCodregion());
            int cantidad = ejecutar.executeUpdate();
            System.out.println(cantidad);
            if (cantidad == 0) {
                mensaje = "El registro no existe";

            } else {
                mensaje = "Registro eliminado cono exito";
            }
        } catch (SQLException ex) {
            mensaje = "No se eliminaron los datos";
            System.out.println("Error en DaoRegion eliminar: " + ex);
        } finally {
            conex.cerrarConexion();
        }
        return mensaje;
    }

    @Override
    public String modificarRegion(Region region) {
        conex.abrirConexion();
        String sqlModificar = "update regiones set Nombre=?,Descripcion=? where cod_Region=?";
        try {

            ejecutar = conex.getMiConex().prepareStatement(sqlModificar);
            ejecutar.setString(1, region.getNombre());
            ejecutar.setString(2, region.getDescripcion());
            ejecutar.setInt(3, region.getCodregion());
            ejecutar.executeUpdate();
            mensaje = "Registro modificado con exito";

        } catch (SQLException ex) {
            mensaje = "No se pudo modificar los datos";
            System.out.println("Error en DaoRegion modificar");
        } finally {
            conex.cerrarConexion();
        }
        return mensaje;
    }

    @Override
    public Region obtenerRegion(int codregion) {
        Region datos = new Region();
        ResultSet resultadoSelect;
        try {
            conex.abrirConexion();
            String sqlBusqueda = "select * from regiones where cod_region=?";
            ejecutar = conex.getMiConex().prepareStatement(sqlBusqueda);
            ejecutar.setInt(1, codregion);
            //asignar al Resultselect el o los resultados de la busqueda
            resultadoSelect = ejecutar.executeQuery();
            //obtener los datos del registro localizado
            resultadoSelect.next();
            //asignar los datos a retornar(objeto region) datos
            datos.setCodregion(resultadoSelect.getInt("cod_region"));
            datos.setNombre(resultadoSelect.getString("nombre"));
            datos.setDescripcion(resultadoSelect.getString("descripcion"));
            
            resultadoSelect.close();
            
        } catch (SQLException ex) {
            System.out.println("Error dao Region obtenerRegion" + ex);
        }

        conex.cerrarConexion();
        return datos;
    }

    @Override
    public ArrayList<Region> listaRegiones() {
       ArrayList<Region> lista=new ArrayList();
       ResultSet resultado;
       Region region;
       
        try {
            conex.abrirConexion();
            String sql="select * from regiones";
            ejecutar=conex.getMiConex().prepareStatement(sql);
            resultado=ejecutar.executeQuery();
            while(resultado.next()){
            //importante    
            region=new Region();
            
            region.setCodregion(resultado.getInt("cod_region"));
            region.setNombre(resultado.getString("nombre"));
            region.setDescripcion(resultado.getString("descripcion"));
            lista.add(region);
            }
           
            
        } catch (Exception e) {
            System.out.println("Error: "+ e);
        }
     finally{
            conex.cerrarConexion();
        } 
        return lista;
    }

}
