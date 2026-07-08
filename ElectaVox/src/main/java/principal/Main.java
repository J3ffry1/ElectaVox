package principal;

import controlador.ControladorLogin;

import conexion.InicializadorMongo;
import vista.FrmLogin;

public class Main {

    public static void main(String[] args) {

        InicializadorMongo.inicializar();

        FrmLogin vistaLogin = new FrmLogin();

        vistaLogin.setLocationRelativeTo(null);
        vistaLogin.setVisible(true);
    }
}
