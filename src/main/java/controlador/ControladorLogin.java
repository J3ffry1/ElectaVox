/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author USER
 */


import com.mongodb.MongoException;
import dao.AdminDAO;
import dao.EstudianteDAO;
import javax.swing.JOptionPane;
import modelo.Estudiante;
import vista.FrmInicio;
import vista.FrmInicioest;
import vista.FrmLogin;

public class ControladorLogin {

    private final FrmLogin vista;
    private final EstudianteDAO estudianteDAO;
    private final AdminDAO adminDAO;

    public ControladorLogin(FrmLogin vista) {
        this.vista = vista;
        this.estudianteDAO = new EstudianteDAO();
        this.adminDAO = new AdminDAO();
    }

    private void abrirFrmInicioAdmin() {
        FrmInicio inicioAdmin = new FrmInicio();

        inicioAdmin.setLocationRelativeTo(null);
        inicioAdmin.setVisible(true);

        vista.dispose();
    }

    private void abrirFrmInicioEstudiante() {
        FrmInicioest inicioEstudiante = new FrmInicioest();

        inicioEstudiante.setLocationRelativeTo(null);
        inicioEstudiante.setVisible(true);

        vista.dispose();
    }

    public void iniciarSesion() {
        String cedula = vista.getCedula();

        if (!cedula.matches("\\d{10}")) {
            vista.mostrarMensaje(
                    "Ingrese una cédula válida de 10 dígitos.",
                    "Cédula inválida",
                    JOptionPane.WARNING_MESSAGE
            );

            vista.enfocarCedula();
            return;
        }

        try {
            // Primero verifica si es administrador
            if (adminDAO.existeAdmin(cedula)) {
                vista.mostrarMensaje(
                        "Bienvenido administrador.",
                        "Inicio de sesión correcto",
                        JOptionPane.INFORMATION_MESSAGE
                );

                abrirFrmInicioAdmin();
                return;
            }

            // Si no es admin, verifica si es estudiante
            Estudiante estudiante = estudianteDAO.buscarPorCedula(cedula);

            if (estudiante == null) {
                vista.mostrarMensaje(
                        "Usuario no registrado.",
                        "Acceso denegado",
                        JOptionPane.ERROR_MESSAGE
                );

                vista.limpiarCedula();
                vista.enfocarCedula();
                return;
            }

            vista.mostrarMensaje(
                    "Bienvenido/a " + estudiante.getNombres() + " "
                    + estudiante.getApellidos(),
                    "Inicio de sesión correcto",
                    JOptionPane.INFORMATION_MESSAGE
            );

            abrirFrmInicioEstudiante();

        } catch (MongoException e) {
            vista.mostrarMensaje(
                    "No se pudo conectar con MongoDB.\n"
                    + "Verifica que el servidor MongoDB esté iniciado.",
                    "Error de conexión",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}