/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexiones;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Mario
 */
public class BD {
    
    //El objeto que trae la conexion con la BD
    private Connection con;
    //parametros de conexión
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "localhost:3306";
    private static final String DB = "misiontic";
    private static final String URL = "jdbc:mysql://" + HOST + "/" + DB; //URL DB
    private static final String USERNAME = "root"; //usuario base de datos global 
    private static final String PASSWORD = "1234567890";

    public BD() {

        try {
            Class.forName(DB_DRIVER);
            //creo la conexion entre java y mysql y se almancea en con
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            System.err.println("Error:" + e);
        }
    }

    public Connection getCon() {
        //envío la conexión al quien instancie el objeto
        return con;
    }

    public void desconectar() {
        //cirro la conexion con la BD
        try {
            if (con != null) {
                con.close();
                System.out.println("La desconexion fue exitosa");
            }

        } catch (Exception excepcion) {
            System.out.println("Ha ocurrido un error al desconectar  " + excepcion.getMessage());
        }
    }

}
