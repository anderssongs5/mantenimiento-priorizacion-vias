package co.edu.udea.mantpriorivias;

import co.edu.udea.mantpriorivias.archivos.CreadorArchivoTextoPlano;
import co.edu.udea.mantpriorivias.archivos.LectorArchivoExcel;
import co.edu.udea.mantpriorivias.entidades.InfoVia;
import co.edu.udea.mantpriorivias.entidades.MantPriorViasInfo;
import co.edu.udea.mantpriorivias.general.Util;
import co.edu.udea.mantpriorivias.validadores.ValidadorVia;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Aplicacion extends javax.swing.JFrame {

    private static final JFileChooser FILE_CHOOSER;
    private File archivoSelecciondo;
    private static final ImageIcon ERROR_IMAGE = new ImageIcon(Aplicacion.class
            .getResource("/co/edu/udea/mantpriorivias/recursos/imagenes/"
                    + "ic_dialog_error.png"));
    private static final ImageIcon WARNING_IMAGE = new ImageIcon(Aplicacion.class
            .getResource("/co/edu/udea/mantpriorivias/recursos/imagenes/"
                    + "ic_dialog_warning.png"));

    static {
        FILE_CHOOSER = new JFileChooser();
        FILE_CHOOSER.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FILE_CHOOSER.setDialogTitle("Seleccionar archivo a procesar...");
    }

    public Aplicacion() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitulo = new javax.swing.JLabel();
        labelSeleccioneArchivo = new javax.swing.JLabel();
        buttonSeleccionarArchivo = new javax.swing.JButton();
        barraMenu = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuAyuda = new javax.swing.JMenu();
        menuItemAcercade = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        labelTitulo.setText("Mantenimiento y Priorización de Vías");

        labelSeleccioneArchivo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelSeleccioneArchivo.setText("Seleccione el archivo a analizar:");

        buttonSeleccionarArchivo.setText("Seleccionar");
        buttonSeleccionarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSeleccionarArchivoActionPerformed(evt);
            }
        });

        jMenu1.setText("File");
        barraMenu.add(jMenu1);

        menuAyuda.setText("Ayuda");
        menuAyuda.setContentAreaFilled(false);
        menuAyuda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        menuItemAcercade.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        menuItemAcercade.setText("Acerca de...");
        menuItemAcercade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAcercadeActionPerformed(evt);
            }
        });
        menuAyuda.add(menuItemAcercade);

        barraMenu.add(menuAyuda);

        setJMenuBar(barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelTitulo)
                        .addGap(76, 76, 76))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelSeleccioneArchivo)
                        .addGap(219, 219, 219))))
            .addGroup(layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(buttonSeleccionarArchivo)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(labelTitulo)
                .addGap(72, 72, 72)
                .addComponent(labelSeleccioneArchivo)
                .addGap(29, 29, 29)
                .addComponent(buttonSeleccionarArchivo)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemAcercadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAcercadeActionPerformed
        new AcercadeJDialog(this, true).setVisible(true);
    }//GEN-LAST:event_menuItemAcercadeActionPerformed

    private void buttonSeleccionarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSeleccionarArchivoActionPerformed
        LectorArchivoExcel lectorArchivoExcel = new LectorArchivoExcel();
        int retorno = FILE_CHOOSER.showOpenDialog(this);
        if (JFileChooser.APPROVE_OPTION == retorno) {
            this.archivoSelecciondo = FILE_CHOOSER.getSelectedFile();
            if (lectorArchivoExcel.validarArchivo(this.archivoSelecciondo)) {
                try {
                    MantPriorViasInfo mantPriorViasInfo = lectorArchivoExcel.
                            leerArchivo(this.archivoSelecciondo);
                    if (mantPriorViasInfo.tieneErrores()) {
                        JOptionPane.showMessageDialog(this, "El archivo contiene "
                                + "errores.\nA continuación se le indicará cuáles "
                                + "son los errores para poder continuar con el proceso.\n"
                                + "Una vez corregidos, por favor inténtelo de nuevo.\n",
                                "Archivo con Errores", JOptionPane.WARNING_MESSAGE,
                                WARNING_IMAGE);
                        String rutaTemporal = Util.getRutaTemporal();
                        String fullPath = rutaTemporal + "Errores_Plantilla.txt";
                        CreadorArchivoTextoPlano creadorArchivoTextoPlano
                                = new CreadorArchivoTextoPlano();

                        String contenidoErrores = this.estructurarInformacionError(
                                mantPriorViasInfo);
                        if (creadorArchivoTextoPlano.crearArchivo(contenidoErrores,
                                fullPath)) {
                            File archivoErrores = new File(fullPath);
                            if (Desktop.isDesktopSupported()) {
                                Desktop desktop = Desktop.getDesktop();
                                desktop.open(archivoErrores);
                            } else {
                                JOptionPane.showMessageDialog(this,
                                        "El archivo de errores no se puede abrir. "
                                        + "Por favor búsquelo en la "
                                        + "siguiente ruta:\n" + fullPath,
                                        "No se puede abrir archivos",
                                        JOptionPane.WARNING_MESSAGE, WARNING_IMAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Se ha generado "
                                    + "un error creando el archivo de errores.\n"
                                    + "Por favor contáctese con el administrador"
                                    + " del sistema.", "Error generando archivo",
                                    JOptionPane.ERROR_MESSAGE, ERROR_IMAGE);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Aplicacion.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "El archivo no es válido. Por "
                        + "favor verifique que el archivo exista y tenga extensión "
                        + "XLSX.", "Archivo inválido", JOptionPane.ERROR_MESSAGE,
                        ERROR_IMAGE);
            }
        }

    }//GEN-LAST:event_buttonSeleccionarArchivoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info
//                    : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }

//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (javax.swing.UnsupportedLookAndFeelException |
                ClassNotFoundException | InstantiationException |
                IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Aplicacion.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            Aplicacion aplicacion = new Aplicacion();
            aplicacion.setResizable(false);
            aplicacion.setLocationRelativeTo(null);
            aplicacion.setVisible(true);

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JButton buttonSeleccionarArchivo;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JLabel labelSeleccioneArchivo;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JMenuItem menuItemAcercade;
    // End of variables declaration//GEN-END:variables

    private String estructurarInformacionError(MantPriorViasInfo mantPriorViasInfo) {
        String informacion = "";
        String separadorLinea = System.getProperty("line.separator");
        if (mantPriorViasInfo.getErroresArchivo() != null
                && !mantPriorViasInfo.getErroresArchivo().isEmpty()) {
            informacion += "Errores del archivo:" + separadorLinea;
            informacion = mantPriorViasInfo.getErroresArchivo().stream().map((s)
                    -> "    * " + s.trim() + separadorLinea).reduce(informacion,
                            String::concat);
        }

        if (!informacion.isEmpty()) {
            informacion += separadorLinea;
            informacion += separadorLinea;
            informacion += "====================================================";
            informacion += separadorLinea;
            informacion += separadorLinea;
        }

        if (mantPriorViasInfo.getErroresHojaPresupuesto() != null
                && !mantPriorViasInfo.getErroresHojaPresupuesto().isEmpty()) {
            informacion += "Errores en hoja Presupuesto:" + separadorLinea;
            informacion = mantPriorViasInfo.getErroresHojaPresupuesto().stream()
                    .map((s) -> "    * " + s.trim() + separadorLinea).reduce(
                            informacion, String::concat);
        }

        if (!informacion.isEmpty()) {
            informacion += separadorLinea;
            informacion += separadorLinea;
            informacion += "====================================================";
            informacion += separadorLinea;
            informacion += separadorLinea;
        }

        if (mantPriorViasInfo.existenViasConErrores()) {
            informacion += "Errores en hoja Priorización o errores en vías:";
            informacion += separadorLinea;
            informacion += separadorLinea;
            for (InfoVia iv : mantPriorViasInfo.getVias()) {
                if (iv.getErroresVia() != null && !iv.getErroresVia().isEmpty()) {
                    informacion += "Número de fila: " + iv.getFilaVia() + separadorLinea;
                    String codVia = (iv.getVia().getCodigoVia() != null)
                            ? iv.getVia().getCodigoVia() : "";
                    informacion += "Código de la vía: " + codVia + separadorLinea;
                    informacion += "Errores: " + separadorLinea + iv.getErroresVia();
                    informacion += separadorLinea;
                    informacion += separadorLinea;
                }
            }
        }

        if (ValidadorVia.existenViasConCodigoRepetido(mantPriorViasInfo.getVias())) {
            informacion += "*** Existen códigos de vías repetidos. Tenga en cuenta "
                    + "que cada código de vía debe ser único. ***" + separadorLinea;
        }

        return informacion;
    }
}
