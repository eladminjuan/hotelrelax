/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Detalle_Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class CDetalle_Reserva {
    
   private Conexion mysql=new Conexion();
   private Connection cn=mysql.conectar();
   private String sSQL="";
   public Integer totalregistros;
   public Double totalvalor;
   
   
   public DefaultTableModel mostrar(String buscar){
       DefaultTableModel modelo;
       
       String [] titulos = {"ID","idreserva","idhabitacion","Num Habitacion","Tipo Habitacion","Personas","Precio Reserva","Estado"};
       
       String [] registro =new String [8];
       
       totalregistros=0;
       totalvalor=0.0;
       modelo = new DefaultTableModel(null,titulos);
       
       sSQL="select d.Cod_Detalle,d.Cod_Reserva,d.Cod_Habitacion,h.Numero,h.Tipo_Habitacion,d.Nom_Persona,d.Precio_Reserva,h.Estado"
               + " from detalle_reserva d inner join habitacion h on d.Cod_Habitacion=h.Cod_Habitacion"
               + " where d.Cod_Reserva ="+ buscar + " order by d.Cod_Detalle desc";
       
       try {
           Statement st= cn.createStatement();
           ResultSet rs=st.executeQuery(sSQL);
           
           while(rs.next()){
               registro [0]=rs.getString("Cod_Detalle");
               registro [1]=rs.getString("Cod_Reserva");
               registro [2]=rs.getString("Cod_Habitacion");
               registro [3]=rs.getString("Numero");
               registro [4]=rs.getString("Tipo_Habitacion");
               registro [5]=rs.getString("Nom_Persona");
               registro [6]=rs.getString("Precio_Reserva");
               registro [7]=rs.getString("Estado");
               
               totalregistros=totalregistros+1;
               totalvalor=totalvalor + (rs.getDouble("Precio_Reserva"));
               
               modelo.addRow(registro);
               
           }
           return modelo;
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
       }
       
       
       
   } 
   
   public boolean insertar (Detalle_Reserva dts){
       sSQL="insert into detalle_reserva (Cod_Reserva,Cod_Habitacion,Nom_Persona,Precio_Reserva)" +
               "values (?,?,?,?)";
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setInt(1, dts.getIdReserva());
           pst.setInt(2, dts.getIdHabitacion());
           pst.setString(3, dts.getPersona());
           pst.setDouble(4, dts.getPrecio());
           
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
           
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }
   
   public boolean editar (Detalle_Reserva dts){
       sSQL="update detalle_reserva set Cod_Reserva=?,Cod_Habitacion=?,Nom_Persona=?,Precio_Reserva=?"+
               " where Cod_Detalle=?";
           
       
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setInt(1, dts.getIdReserva());
           pst.setInt(2, dts.getIdHabitacion());
           pst.setString(3, dts.getPersona());
           pst.setDouble(4, dts.getPrecio());
           
           pst.setInt(5, dts.getIdDetalle());
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }
  
   public boolean eliminar (Detalle_Reserva dts){
       sSQL="delete from detalle_reserva where Cod_Detalle=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, dts.getIdDetalle());
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }
    
    
    
    
    
}

