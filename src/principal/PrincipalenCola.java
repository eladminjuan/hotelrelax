/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import Control.Cola;
import Modelo.IO;

/**
 *
 * @author jdgom
 */
public class PrincipalenCola {
   public static void main(String[] args) {
      Cola cola=new Cola();
      cola.crearCola();
     IO.escribir(cola.visualizar());
       
    } 
}
