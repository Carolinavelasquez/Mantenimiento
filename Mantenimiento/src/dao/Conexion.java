
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
  //establecer valores fijos para la conexion (ruta,servidor y base de datos,usuario,contraseña )  
    private static final String URL= "jdbc:mysql://localhost:3306/ciudadanos";
    private static final String USER="root";
    private static final String PASSWORD="";
    
    private Connection miConex;

    public Connection getMiConex() {
        return miConex;
    }

    public void setMiConex(Connection miConex) {
        this.miConex = miConex;
    }
    public void abrirConexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                this.miConex=DriverManager.getConnection(URL, USER, PASSWORD);
               
            } catch (SQLException ex) {
                System.out.println("Error en la conexion"+ ex);
            }
        } catch (ClassNotFoundException ex ) {
            System.out.println("Error en driver "+ ex);
        }
       
    }
     public void cerrarConexion(){
        try {
            if (this.miConex.isClosed()!=true && miConex !=null) {
               this.miConex.close();
  
            }
        } catch (SQLException ex) {
           
        }
   
        }
}
