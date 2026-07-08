package controlador;

import dao.CandidatoDAO;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import modelo.Candidato;
import vista.FrmCandidato;

public class ControladorCandidato {

    private FrmCandidato vista;
    private CandidatoDAO dao;
    private File fotoSeleccionada;

    public ControladorCandidato(FrmCandidato vista) {

        this.vista = vista;
        this.dao = new CandidatoDAO();

        eventos();
    }

    private void eventos() {

        vista.getBtnSeleccionarFoto().addActionListener(e -> seleccionarFoto());

        vista.getBtnGuardar().addActionListener(e -> guardar());

    }

    private void seleccionarFoto() {

        JFileChooser selector = new JFileChooser();

        int opcion = selector.showOpenDialog(vista);

        if (opcion == JFileChooser.APPROVE_OPTION) {

            fotoSeleccionada = selector.getSelectedFile();

            ImageIcon icono = new ImageIcon(fotoSeleccionada.getAbsolutePath());

            Image imagen = icono.getImage().getScaledInstance(
                    vista.getLblFoto().getWidth(),
                    vista.getLblFoto().getHeight(),
                    Image.SCALE_SMOOTH);

            vista.getLblFoto().setIcon(new ImageIcon(imagen));

        }

    }

    private void guardar() {

        String cedula = vista.getTxtCedula().getText().trim();
        String nombres = vista.getTxtNombres().getText().trim();
        String apellidos = vista.getTxtApellidos().getText().trim();
        String lista = vista.getTxtLista().getText().trim();
        String propuestas = vista.getTxtPropuestas().getText().trim();

        if (cedula.isEmpty()
                || nombres.isEmpty()
                || apellidos.isEmpty()
                || lista.isEmpty()
                || propuestas.isEmpty()) {

            JOptionPane.showMessageDialog(vista,
                    "Complete todos los campos.");

            return;
        }

        if (fotoSeleccionada == null) {

            JOptionPane.showMessageDialog(vista,
                    "Seleccione una fotografía.");

            return;
        }
Candidato existente = dao.buscarPorCedula(cedula);

if (existente != null) {

    JOptionPane.showMessageDialog(
            vista,
            "Ya existe un candidato con esa cédula."
    );

    return;
}
        Candidato candidato = new Candidato(
                cedula,
                nombres,
                apellidos,
                lista,
                propuestas,
                fotoSeleccionada.getAbsolutePath()
        );

        boolean guardado = dao.guardar(candidato);

        if (guardado) {

            JOptionPane.showMessageDialog(vista,
                    "Candidato registrado correctamente.");

            limpiar();

        } else {

            JOptionPane.showMessageDialog(vista,
                    "No se pudo guardar el candidato.");

        }

    }

    private void limpiar() {

        vista.getTxtCedula().setText("");
        vista.getTxtNombres().setText("");
        vista.getTxtApellidos().setText("");
        vista.getTxtLista().setText("");
        vista.getTxtPropuestas().setText("");

        vista.getLblFoto().setIcon(null);

        fotoSeleccionada = null;

    }

}