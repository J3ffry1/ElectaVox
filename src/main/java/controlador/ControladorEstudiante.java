package controlador;

import dao.EstudianteDAO;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import modelo.Estudiante;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vista.FrmEstudiante;

public class ControladorEstudiante {

    private FrmEstudiante vista;
    private EstudianteDAO dao;

    public ControladorEstudiante(FrmEstudiante vista) {
        this.vista = vista;
        this.dao = new EstudianteDAO();

        eventos();
    }

    private void eventos() {
        vista.getBtnGuardar().addActionListener(e -> guardar());
        vista.getBtnImportarExcel().addActionListener(e -> importarExcel());
    }

    private void guardar() {

        String cedula = vista.getTxtCedula().getText().trim();
        String nombres = vista.getTxtNombres().getText().trim();
        String apellidos = vista.getTxtApellidos().getText().trim();
        String correo = vista.getTxtCorreo().getText().trim();
        String curso = vista.getTxtCurso().getText().trim();
        String genero = vista.getTxtGenero().getText().trim();

        if (cedula.isEmpty()
                || nombres.isEmpty()
                || apellidos.isEmpty()
                || correo.isEmpty()
                || curso.isEmpty()
                || genero.isEmpty()) {

            JOptionPane.showMessageDialog(vista, "Complete todos los campos.");
            return;
        }

        if (!cedula.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(vista,
                    "La cédula debe tener exactamente 10 números.");
            return;
        }

        Estudiante existente = dao.buscarPorCedula(cedula);

        if (existente != null) {
            JOptionPane.showMessageDialog(vista,
                    "Ya existe un estudiante con esa cédula.");
            return;
        }

        Estudiante estudiante = new Estudiante(
                cedula,
                nombres,
                apellidos,
                correo,
                curso,
                genero
        );

        boolean guardado = dao.guardar(estudiante);

        if (guardado) {
            JOptionPane.showMessageDialog(vista,
                    "Estudiante guardado correctamente.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vista,
                    "No se pudo guardar el estudiante.");
        }
    }

    private void importarExcel() {

        JFileChooser selector = new JFileChooser();

        int opcion = selector.showOpenDialog(vista);

        if (opcion != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File archivo = selector.getSelectedFile();

        try (FileInputStream fis = new FileInputStream(archivo);
             Workbook libro = new XSSFWorkbook(fis)) {

            Sheet hoja = libro.getSheetAt(0);

            int cantidad = 0;

            for (int i = 1; i <= hoja.getLastRowNum(); i++) {

                Row fila = hoja.getRow(i);

                if (fila == null) {
                    continue;
                }

                Estudiante estudiante = new Estudiante();

                estudiante.setCedula(fila.getCell(0).toString().trim());
                estudiante.setNombres(fila.getCell(1).toString().trim());
                estudiante.setApellidos(fila.getCell(2).toString().trim());
                estudiante.setCorreo(fila.getCell(3).toString().trim());
                estudiante.setCurso(fila.getCell(4).toString().trim());
                estudiante.setGenero(fila.getCell(5).toString().trim());

                if (dao.buscarPorCedula(estudiante.getCedula()) == null) {

                    boolean guardado = dao.guardar(estudiante);

                    if (guardado) {
                        cantidad++;
                    }
                }
            }

            JOptionPane.showMessageDialog(
                    vista,
                    "Se importaron " + cantidad + " estudiantes correctamente."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Error al importar Excel:\n" + e.getMessage()
            );

            e.printStackTrace();
        }
    }

    private void limpiarCampos() {

        vista.getTxtCedula().setText("");
        vista.getTxtNombres().setText("");
        vista.getTxtApellidos().setText("");
        vista.getTxtCorreo().setText("");
        vista.getTxtCurso().setText("");
        vista.getTxtGenero().setText("");

        vista.getTxtCedula().requestFocus();
    }
}