package controlador;

import dao.DaoRegionImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Region;
import vista.JFrmRegion;

public class RegionControlador implements ActionListener,MouseListener {

    //instanciar vista y modelo=DaoRegionImp
    JFrmRegion vista = new JFrmRegion();
    DaoRegionImpl dao = new DaoRegionImpl();
    Region region = new Region();
    String mensaje;
   

    public RegionControlador(JFrmRegion vista) {
        this.vista = vista;
        asignarEventos();
        listarRegiones();
    }
      public void listarRegiones(){
          //definimos los titulos de la tabla
     String[] titulos={"Codigo","Nombre","Descripcion"};
     //crear modelo de la tabla y agregar los titulos a la fila 0
      DefaultTableModel modelo=new DefaultTableModel(titulos,0);
      //establecer cantidad de columnas
      Object[] columnas= new Object[3];
      //para cada elementos vamos a crar un objeto tipo reegion
          for (Region region : dao.listaRegiones()) {
              columnas[0]=region.getCodregion();
              columnas[1]=region.getNombre();
              columnas[2]=region.getDescripcion();
              modelo.addRow(columnas);
              
          }
          this.vista.jTblTabla.setModel(modelo);
        
    }

    public void asignarEventos() {
        this.vista.jBtnBuscar.addActionListener(this);
        this.vista.jBtnSalir.addActionListener(this);
        this.vista.jBtnEliminar.addActionListener(this);
        this.vista.jBtnModificar.addActionListener(this);
        this.vista.jTblTabla.addMouseListener(this);
//        this.vista.jBtnSalir.addActionListener(this);
    }
    
    public void obtenerDatosTabla(){
       this.vista.jTxtCodRegion.setText(String.valueOf(this.vista.jTblTabla.getValueAt(this.vista.jTblTabla.getSelectedRow(), 0)));
        this.vista.jTxtNombreRegion.setText(String.valueOf(this.vista.jTblTabla.getValueAt(this.vista.jTblTabla.getSelectedRow(), 1)));
        this.vista.jTxtDescripcion.setText(String.valueOf(this.vista.jTblTabla.getValueAt(this.vista.jTblTabla.getSelectedRow(), 2)));
    }

    public void llamarMetodos() {
        region.setCodregion(Integer.parseInt(this.vista.jTxtCodRegion.getText()));
        region.setNombre(this.vista.jTxtNombreRegion.getText());
        region.setDescripcion(this.vista.jTxtDescripcion.getText());
    }

    public void eliminarDeCajaDeTexto() {
        this.vista.jTxtCodRegion.setText(null);
        this.vista.jTxtNombreRegion.setText("");
        this.vista.jTxtDescripcion.setText("");
    }

    public void guardarRegion() {
        llamarMetodos();
        mensaje = dao.agregarRegion(region);
        JOptionPane.showMessageDialog(vista, mensaje);
        listarRegiones();
        eliminarDeCajaDeTexto();
    }

    public void modificarRegion() {
        llamarMetodos();
        mensaje = dao.modificarRegion(region);
        JOptionPane.showMessageDialog(vista, mensaje);
        listarRegiones();
        eliminarDeCajaDeTexto();

    }
    public void eliminarRegion(){
        llamarMetodos();
        mensaje=dao.eliminarRegion(region);
        JOptionPane.showMessageDialog(vista, mensaje);
        listarRegiones();
        eliminarDeCajaDeTexto();
    }
    public void buscarRegionPorCodigo(){
        int codigo= Integer.parseInt(this.vista.jTxtCodRegion.getText());
        region=dao.obtenerRegion(codigo);
        
        this.vista.jTxtDescripcion.setText(region.getDescripcion());
        this.vista.jTxtNombreRegion.setText(region.getNombre());
      
    }
    public void salir(){
        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION);
        if(resp==0){
            System.exit(0);
        }
//        
    }
  

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.jBtnGuardar1) {
            guardarRegion();
        }
        if (e.getSource() == this.vista.jBtnModificar) {
            modificarRegion();
        }
        if (e.getSource()== this.vista.jBtnEliminar) {
            eliminarRegion();
        }
        if (e.getSource()==this.vista.jBtnBuscar) {
            buscarRegionPorCodigo();
        }
        if(e.getSource()==this.vista.jBtnSalir){
            salir();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource()==this.vista.jTblTabla) {
            obtenerDatosTabla();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
