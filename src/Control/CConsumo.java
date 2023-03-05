/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Consumo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class CConsumo {
    
   private Conexion mysql=new Conexion();
   private Connection cn=mysql.conectar();
   private String sSQL="";
   public Integer totalregistros;
   public Double totalconsumo;
   
   
   public DefaultTableModel mostrar(String buscar){
       DefaultTableModel modelo;
       
       String [] titulos = {"ID","idreserva","idproducto","producto","cantidad","Precio Venta","Estado"};
       
       String [] registro =new String [7];
       
       totalregistros=0;
       totalconsumo=0.0;
       modelo = new DefaultTableModel(null,titulos);
       
       sSQL="select c.Cod_Consumo,c.Cod_Reserva,c.Cod_Producto,p.Nombre,c.Cantidad,c.Precio_Venta "
               + ",c.Estado from consumo c inner join producto p on c.Cod_Producto=p.Cod_Producto"
               + " where c.Cod_Reserva ="+ buscar + " order by c.Cod_Consumo desc";
       
       try {
           Statement st= cn.createStatement();
           ResultSet rs=st.executeQuery(sSQL);
           
           while(rs.next()){
               registro [0]=rs.getString("Cod_Consumo");
               registro [1]=rs.getString("Cod_Reserva");
               registro [2]=rs.getString("Cod_Producto");
               registro [3]=rs.getString("Nombre");
               registro [4]=rs.getString("Cantidad");
               registro [5]=rs.getString("Precio_Venta");
               registro [6]=rs.getString("Estado");
               
               totalregistros=totalregistros+1;
               totalconsumo=totalconsumo + (rs.getDouble("Cantidad") * rs.getDouble("Precio_Venta") );
               
               modelo.addRow(registro);
               
           }
           return modelo;
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
       }
       
       
       
   } 
   
   public boolean insertar (Consumo dts){
       sSQL="insert into consumo (Cod_Reserva,Cod_Producto,Cantidad,Precio_Venta,Estado)" +
               "values (?,?,?,?,?)";
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setInt(1, dts.getIdReserva());
           pst.setInt(2, dts.getIdProducto());
           pst.setDouble(3, dts.getCantidad());
           pst.setDouble(4, dts.getPrecioVenta());
           pst.setString(5, dts.getEstado());
           
           
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
   
   public boolean editar (Consumo dts){
       sSQL="update consumo set Cod_Reserva=?,Cod_Producto=?,Cantidad=?,Precio_Venta=?,Estado=?"+
               " where Cod_Consumo=?";
           
       
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setInt(1, dts.getIdReserva());
           pst.setInt(2, dts.getIdProducto());
           pst.setDouble(3, dts.getCantidad());
           pst.setDouble(4, dts.getPrecioVenta());
           pst.setString(5, dts.getEstado());
           
           pst.setInt(6, dts.getIdConsumo());
           
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
  
   public boolean eliminar (Consumo dts){
       sSQL="delete from consumo where Cod_Consumo=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, dts.getIdConsumo());
           
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

