/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package crud;

import conexiones.BD;
import controllers.EstudianteController;
import models.EstudianteModel;
import views.EstudianteView;

/**
 *
 * @author Mario
 */
public class Crud {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EstudianteModel estudiante = new EstudianteModel();
        
       EstudianteView vista = new EstudianteView();
        EstudianteController controlador = new EstudianteController(estudiante, vista);
      
        
        
    }
    
}
