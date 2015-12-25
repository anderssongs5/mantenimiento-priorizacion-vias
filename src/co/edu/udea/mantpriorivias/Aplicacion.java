package co.edu.udea.mantpriorivias;

import co.edu.udea.mantpriorivias.seguridad.Seguridad;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Aplicacion {

    private static final ImageIcon ERROR_IMAGE = new ImageIcon(Inicio.class
            .getResource("/co/edu/udea/mantpriorivias/recursos/imagenes/"
                    + "ic_dialog_error.png"));

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            boolean intentar = false;
            do {
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Ingrese la contraseña:");
                JPasswordField pass = new JPasswordField(10);
                panel.add(label);
                panel.add(pass);
                String[] options = new String[]{"OK", "Cancelar"};
                int option = JOptionPane.showOptionDialog(null, panel, "Constraseña",
                        JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                if (option == 0) {
                    char[] password = pass.getPassword();
                    StringBuilder sb = new StringBuilder();
                    sb.append(password);
//                    sb.append("PMVias*UdeA*2015");
                    if (password != null
                            && Seguridad.puedeContinuar(sb.toString())) {
                        Inicio inicio = new Inicio();
                        inicio.setResizable(false);
                        inicio.setLocationRelativeTo(null);
                        inicio.setVisible(true);
                        break;
                    } else {
                        intentar = JOptionPane.showConfirmDialog(null, "La contraseña es incorrecta\n"
                                + "¿Desea volver a intentarlo?", "Contraseña incorrecta",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                                == JOptionPane.YES_OPTION;
                    }
                }
            } while (intentar);
        } catch (IOException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Se ha presentado un error irrecuperable"
                    + "en el sistema.\n\nPor favor conctátese con el administrador.",
                    "Error irrecuperable", JOptionPane.ERROR_MESSAGE, ERROR_IMAGE);
        }
    }
}
