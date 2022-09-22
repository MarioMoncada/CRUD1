package models;


import conexiones.BD;
import java.sql.*;
import java.util.ArrayList;

public class EstudianteModel {

    //clases requeridas para realizar operaciones con BD (sql)
    Statement stmt;
    //guarda los resultados de una consulta de seleccion
    ResultSet rs;
    //variable de trabajo para almacenar la consulta sql
    String sql;
    private static final String TABLA = "Estudiantes";
    private BD conexionBaseDatos;
    //atributos de la clase(tabla)
    private String nombre;
    private String apellido;
    private String genero;
    private String telefono;
    private String cedula;

    public EstudianteModel() {
    }

    public BD getBaseDatos() {
        return conexionBaseDatos;
    }

    public void setBaseDatos(BD conexionBaseDatos) {
        this.conexionBaseDatos = conexionBaseDatos;
    }

    public EstudianteModel(String nombre, String apellido, String genero, String telefono, String cedula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.telefono = telefono;
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public ArrayList<EstudianteModel> select() {

        ArrayList<EstudianteModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLA;
        try {
            Statement stm = conexionBaseDatos.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                EstudianteModel estudiante = new EstudianteModel();
                estudiante.setCedula(rs.getString("cedula"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setGenero(rs.getString("genero"));
                estudiante.setTelefono(rs.getString("telefono"));
                lista.add(estudiante);
            }
        } catch (Exception e) {
        }

        return lista;

    }

    public String[] traerDatos() {

        String dato[] = new String[5];
        dato[0] = getCedula();
        dato[1] = getNombre();
        dato[2] = getApellido();
        dato[3] = getGenero();
        dato[4] = getTelefono();
        return dato;
    }

    public String insert() {
//ejemplo de conexion con variable de trabajo
        //Connection conexion = baseDatos.getCon();

        //crear una sentencia sql de acuerdo con la operacion (insert,delete, update)
        //defino los parametros e enviar los parametros se identifican con un ?
        //los parametros se ingresan en orden secuencial y debe llevar el mismo tipo de dato de la tabla
        sql = " INSERT INTO " + TABLA + "(nombre,apellido,genero,telefono,cedula)"
                + " VALUES (?,?,?,?,?)";
        try {
            //preparo mi statemet (envio la consulta a ejecutar) - que me permite ejecutar la consulta
            PreparedStatement pstm = conexionBaseDatos.getCon().prepareStatement(sql);
            //envio los valores a cada uno de los parametros en el ordenm que se haya creado y con el mismo tipo de dato
            pstm.setString(1, nombre);
            pstm.setString(2, apellido);
            pstm.setString(3, genero);
            pstm.setString(4, telefono);
            pstm.setString(5, cedula);

            //validar si al ejecutar la consultar (pstm.executeUpdate()) se afecta 0 o más registros
            //si el resultado es 0 no se afecto ningun registro
            if (pstm.executeUpdate() > 0) {

                //retorno un mensaje para que el controlador le envie a la vista y 
                //ésta la pueda mostrar en un cuadro de diagolo (mensaje) - JOptionPane
                return ("estudiante(s) insertado(s)");
            } else {
                return ("No existe el/los estudiante(s)");
            }
        } catch (SQLException excepcion) {
            return ("Ha ocurrido un error al insertar  " + excepcion.getMessage());
        }
    }

    public String update(String cedulaModificar) {
        //ejemplo de conexion con variable de trabajo
        //Connection conexion = baseDatos.getCon();

        //crear una sentencia sql de acuerdo con la operacion (insert,delete, update)
        //defino los parametros e enviar los parametros se identifican con un ?
        //los parametros se ingresan en orden secuencial y debe llevar el mismo tipo de dato de la tabla
        sql = " UPDATE " + TABLA
                + " SET nombre=?,apellido=?,genero=?,telefono=?"
                + " WHERE cedula=?";
        try {
            //preparo mi statemet (envio la consulta a ejecutar) - que me permite ejecutar la consulta
            PreparedStatement pstm = conexionBaseDatos.getCon().prepareStatement(sql);
            //envio los valores a cada uno de los parametros en el ordenm que se haya creado y con el mismo tipo de dato
            pstm.setString(1, nombre);
            pstm.setString(2, apellido);
            pstm.setString(3, genero);
            pstm.setString(4, telefono);
            pstm.setString(5, cedulaModificar);

            //validar si al ejecutar la consultar (pstm.executeUpdate()) se afecta 0 o más registros
            //si el resultado es 0 no se afecto ningun registro
            if (pstm.executeUpdate() > 0) {

                //retorno un mensaje para que el controlador le envie a la vista y 
                //ésta la pueda mostrar en un cuadro de diagolo (mensaje) - JOptionPane
                return ("estudiante(s) actualziado(s)");
            } else {
                return ("No existe el/los estudiante(s)");
            }
        } catch (SQLException excepcion) {
            return ("Ha ocurrido un error al insertar  " + excepcion.getMessage());
        }

    }

    public String delete(String cedulaElimnar) {
        //ejemplo de conexion con variable de trabajo
        //Connection conexion = baseDatos.getCon();

        //crear una sentencia sql de acuerdo con la operacion (insert,delete, update)
        //defino los parametros e enviar los parametros se identifican con un ?
        //los parametros se ingresan en orden secuencial y debe llevar el mismo tipo de dato de la tabla
        sql = "DELETE FROM " + TABLA + " WHERE cedula= ?";
        try {
            //preparo mi statemet (envio la consulta a ejecutar) - que me permite ejecutar la consulta
            PreparedStatement pstm = conexionBaseDatos.getCon().prepareStatement(sql);
            //envio los valores a cada uno de los parametros en el ordenm que se haya creado y con el mismo tipo de dato
            pstm.setString(1, cedulaElimnar);

            //validar si al ejecutar la consultar (pstm.executeUpdate()) se afecta 0 o más registros
            //si el resultado es 0 no se afecto ningun registro
            if (pstm.executeUpdate() > 0) {

                //retorno un mensaje para que el controlador le envie a la vista y 
                //ésta la pueda mostrar en un cuadro de diagolo (mensaje) - JOptionPane
                return ("estudiante(s) eliminado(s)");
            } else {
                return ("No existe el/los estudiante(s)");
            }
        } catch (SQLException excepcion) {
            return ("Ha ocurrido un error al insertar  " + excepcion.getMessage());
        }

    }
}

