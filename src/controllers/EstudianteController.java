/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import conexiones.BD;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.EstudianteModel;
import views.EstudianteView;

/**
 *
 * @author Mario
 */
public class EstudianteController implements ActionListener, MouseListener, WindowListener {

    ArrayList<EstudianteModel> lista = new ArrayList<>();
    DefaultTableModel tabla = new DefaultTableModel();
    //
    BD conexionBaseDatos = new BD();
    EstudianteModel estudiante;
    EstudianteView vista;

    public EstudianteController(EstudianteModel estudiante, EstudianteView vista) {
        this.estudiante = estudiante;
        this.vista = vista;
        this.estudiante.setBaseDatos(conexionBaseDatos);
        this.inciarVista();
        this.enviarDatosTabla();
    }

    public void inciarVista() {
        this.vista.setTitle("Lista de estudiantes");
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
        this.vista.agregarBtn.addActionListener(this);
        this.vista.eliminarBtn.addActionListener(this);
        this.vista.actualizarBtn.addActionListener(this);
        this.vista.nuevoBtn.addActionListener(this);
        this.vista.guardarBtn.addActionListener(this);

        this.vista.dataTable.addMouseListener(this);
        this.vista.addWindowListener(this);
        this.tabla = (DefaultTableModel) this.vista.dataTable.getModel();

    }

    public void enviarDatosTabla() {
        this.lista = estudiante.select();
        tabla.setNumRows(0);
        for (EstudianteModel estudiante : estudiante.select()) {
            tabla.addRow(estudiante.traerDatos());
        }
    }

    public void enviarDatosCajaTexto() {
        this.vista.nameField.setText(this.vista.dataTable.getValueAt(this.vista.dataTable.getSelectedRow(), 1).toString());
        this.vista.idField.setText(this.vista.dataTable.getValueAt(this.vista.dataTable.getSelectedRow(), 0).toString());
        this.vista.lastNameField.setText(this.vista.dataTable.getValueAt(this.vista.dataTable.getSelectedRow(), 2).toString());
        this.vista.phoneField.setText(this.vista.dataTable.getValueAt(this.vista.dataTable.getSelectedRow(), 4).toString());
        this.vista.genderField.setSelectedItem(this.vista.dataTable.getValueAt(this.vista.dataTable.getSelectedRow(), 3).toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int resultado;
        if (e.getSource() == this.vista.agregarBtn) {
            System.out.println(this.vista.agregarBtn.getCursor());

            if (JOptionPane.showConfirmDialog(vista, "Esta seguro de crear la persona", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0) {

                if (!(vista.nameField.getText().equals("")
                        || vista.lastNameField.getText().equals("")
                        || vista.idField.getText().equals("")
                        || vista.phoneField.getText().equals(""))) {
                    estudiante.setNombre(this.vista.nameField.getText());
                    estudiante.setCedula(this.vista.idField.getText());
                    estudiante.setApellido(this.vista.lastNameField.getText());
                    estudiante.setTelefono(this.vista.phoneField.getText());
                    estudiante.setGenero(this.vista.genderField.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(vista, estudiante.insert());
                    this.enviarDatosTabla();

                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar datos");
                }
            }
        }
        if (e.getSource() == this.vista.eliminarBtn) {
            if (this.vista.idField.getText().equals("")) {//cuando campo cedula sea vacio
                JOptionPane.showMessageDialog(vista, "Debe seleccionar un estudiante en la tabla");
            } else {
                if (JOptionPane.showConfirmDialog(vista, "Esta seguro de eliminar la persona", "Confirmación",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0) {
                    JOptionPane.showMessageDialog(vista, this.estudiante.delete(this.vista.dataTable.getValueAt(this.vista.dataTable.getSelectedRow(), 0).toString()));
                    enviarDatosTabla();
                }
            }

        }
        if (e.getSource() == this.vista.actualizarBtn) {
            // validacion si los campos estan vacios cuando se presiona el boton actualizar
            if ((vista.nameField.getText().equals("")
                    || vista.lastNameField.getText().equals("")
                    || vista.idField.getText().equals("")
                    || vista.phoneField.getText().equals(""))) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar un estudiante de la lista ");
            } else {

                if (JOptionPane.showConfirmDialog(vista, "Desea modificar los datos de este estudiante ", "Confirmación",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0) {
                    this.estudiante.setNombre(this.vista.nameField.getText());
                    this.estudiante.setCedula(this.vista.idField.getText());
                    this.estudiante.setApellido(this.vista.lastNameField.getText());
                    this.estudiante.setTelefono(this.vista.phoneField.getText());
                    this.estudiante.setGenero(this.vista.genderField.getSelectedItem().toString());

                    JOptionPane.showMessageDialog(vista, this.estudiante.update(this.vista.dataTable.getValueAt(this.vista.dataTable.getSelectedRow(), 0).toString()));
                    enviarDatosTabla();

                }
            }

            //
        }
        if (e.getSource() == this.vista.nuevoBtn) {
            this.vista.idField.setText("");
            this.vista.nameField.setText("");
            this.vista.lastNameField.setText("");
            this.vista.phoneField.setText("");

        }
        if (e.getSource() == this.vista.guardarBtn) {
            if ((vista.nameField.getText().equals("")
                    || vista.lastNameField.getText().equals("")
                    || vista.idField.getText().equals("")
                    || vista.phoneField.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "Debe ingresar datos");

            } else {
                if (JOptionPane.showConfirmDialog(vista, "Desea   guardar este estudiante? ", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == 0) {

                    estudiante.setNombre(this.vista.nameField.getText());
                    estudiante.setCedula(this.vista.idField.getText());
                    estudiante.setApellido(this.vista.lastNameField.getText());
                    estudiante.setTelefono(this.vista.phoneField.getText());
                    estudiante.setGenero(this.vista.genderField.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(vista, estudiante.insert());
                    this.enviarDatosTabla();

                }

            }

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == this.vista.dataTable) {
            enviarDatosCajaTexto();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e
    ) {
    }

    @Override
    public void mouseEntered(MouseEvent e
    ) {

    }

    @Override
    public void mouseExited(MouseEvent e
    ) {

    }

    @Override
    public void windowOpened(WindowEvent e
    ) {
    }

    @Override
    public void windowClosing(WindowEvent e
    ) {
        this.estudiante.getBaseDatos().desconectar();
    }

    @Override
    public void windowClosed(WindowEvent e
    ) {
    }

    @Override
    public void windowIconified(WindowEvent e
    ) {
    }

    @Override
    public void windowDeiconified(WindowEvent e
    ) {
    }

    @Override
    public void windowActivated(WindowEvent e
    ) {
    }

    @Override
    public void windowDeactivated(WindowEvent e
    ) {
    }
}
