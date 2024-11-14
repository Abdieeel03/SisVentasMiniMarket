package com.grupo1.main;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.grupo1.controller.Controlador;
import com.grupo1.views.VtnInicio;
import com.grupo1.views.VtnLogin;
import javax.swing.SwingUtilities;

/**
 *
 * @author Abdiel
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatMacLightLaf.setup();
            VtnInicio vtnInicio = new VtnInicio();
            VtnLogin vtnLogin = new VtnLogin();
            Controlador controlador = new Controlador(vtnInicio, vtnLogin);
            vtnLogin.setVisible(true);
        });
    }

}
