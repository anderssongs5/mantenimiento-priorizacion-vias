package co.edu.udea.mantpriorivias;

import co.edu.udea.mantpriorivias.archivos.ArchivoTextoPlano;
import co.edu.udea.mantpriorivias.archivos.ArchivoExcel;
import co.edu.udea.mantpriorivias.entidades.InfoVia;
import co.edu.udea.mantpriorivias.entidades.MantPriorViasInfo;
import co.edu.udea.mantpriorivias.general.Util;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Aplicacion extends javax.swing.JFrame {

    private static final JFileChooser FILE_CHOOSER_PLANTILLA;
    private static final JFileChooser FILE_CHOOSER_DIRECTORIO_PLANTILLA;
    private static final JFileChooser FILE_CHOOSER_ALTERNATIVAS_INTERVENCION;
    private File archivoSelecciondo;
    private File directorioSeleccionado;
    private final ArchivoExcel archivoExcel = new ArchivoExcel();
    private static final ImageIcon ERROR_IMAGE = new ImageIcon(Aplicacion.class
            .getResource("/co/edu/udea/mantpriorivias/recursos/imagenes/"
                    + "ic_dialog_error.png"));
    private static final ImageIcon WARNING_IMAGE = new ImageIcon(Aplicacion.class
            .getResource("/co/edu/udea/mantpriorivias/recursos/imagenes/"
                    + "ic_dialog_warning.png"));
    private static final ImageIcon INFORMATION_IMAGE = new ImageIcon(Aplicacion.class
            .getResource("/co/edu/udea/mantpriorivias/recursos/imagenes/"
                    + "info_48.png"));

    static {
        FILE_CHOOSER_PLANTILLA = new JFileChooser();
        FILE_CHOOSER_PLANTILLA.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FILE_CHOOSER_PLANTILLA.setDialogTitle("Seleccionar archivo a procesar...");

        FILE_CHOOSER_DIRECTORIO_PLANTILLA = new JFileChooser();
        FILE_CHOOSER_DIRECTORIO_PLANTILLA.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        FILE_CHOOSER_PLANTILLA.setDialogTitle("Seleccionar directorio...");

        FILE_CHOOSER_ALTERNATIVAS_INTERVENCION = new JFileChooser();
        FILE_CHOOSER_ALTERNATIVAS_INTERVENCION.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        FILE_CHOOSER_ALTERNATIVAS_INTERVENCION.setDialogTitle("Seleccionar directorio...");
    }

    public Aplicacion() {
        initComponents();

        this.setTitle("Priorización y Mantenimiento de Vías");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitulo = new javax.swing.JLabel();
        labelSeleccioneArchivo = new javax.swing.JLabel();
        buttonSeleccionarArchivo = new javax.swing.JButton();
        barraMenu = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        menuItemCargarArchivo = new javax.swing.JMenuItem();
        menuItemDescargarPlantilla = new javax.swing.JMenuItem();
        menuItemDescargarAlternativasIntervencion = new javax.swing.JMenuItem();
        menuAyuda = new javax.swing.JMenu();
        menuItemAcercade = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        labelTitulo.setText("Priorización y Mantenimiento de Vías");

        labelSeleccioneArchivo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelSeleccioneArchivo.setText("Seleccione el archivo a analizar:");

        buttonSeleccionarArchivo.setText("Seleccionar");
        buttonSeleccionarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSeleccionarArchivoActionPerformed(evt);
            }
        });

        menuArchivo.setText("Archivo");

        menuItemCargarArchivo.setText("Cargar archivo");
        menuItemCargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCargarArchivoActionPerformed(evt);
            }
        });
        menuArchivo.add(menuItemCargarArchivo);

        menuItemDescargarPlantilla.setText("Descargar plantilla");
        menuItemDescargarPlantilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemDescargarPlantillaActionPerformed(evt);
            }
        });
        menuArchivo.add(menuItemDescargarPlantilla);

        menuItemDescargarAlternativasIntervencion.setText("Descargar alternativas de intervención");
        menuItemDescargarAlternativasIntervencion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemDescargarAlternativasIntervencionActionPerformed(evt);
            }
        });
        menuArchivo.add(menuItemDescargarAlternativasIntervencion);

        barraMenu.add(menuArchivo);

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
        this.cargarArchivoPlantilla();
    }//GEN-LAST:event_buttonSeleccionarArchivoActionPerformed

    private void menuItemCargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCargarArchivoActionPerformed
        this.cargarArchivoPlantilla();
    }//GEN-LAST:event_menuItemCargarArchivoActionPerformed

    private void menuItemDescargarPlantillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemDescargarPlantillaActionPerformed
        this.descargarPlantilla();
    }//GEN-LAST:event_menuItemDescargarPlantillaActionPerformed

    private void menuItemDescargarAlternativasIntervencionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemDescargarAlternativasIntervencionActionPerformed
        this.descargarAlternativasIntervencion();
    }//GEN-LAST:event_menuItemDescargarAlternativasIntervencionActionPerformed

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
    private javax.swing.JLabel labelSeleccioneArchivo;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JMenuItem menuItemAcercade;
    private javax.swing.JMenuItem menuItemCargarArchivo;
    private javax.swing.JMenuItem menuItemDescargarAlternativasIntervencion;
    private javax.swing.JMenuItem menuItemDescargarPlantilla;
    // End of variables declaration//GEN-END:variables

    private void descargarPlantilla() {
        int retorno = FILE_CHOOSER_DIRECTORIO_PLANTILLA.showOpenDialog(this);
        if (JFileChooser.APPROVE_OPTION == retorno) {
            this.directorioSeleccionado = FILE_CHOOSER_DIRECTORIO_PLANTILLA.
                    getSelectedFile();
            if (Util.isDirectorioValido(this.directorioSeleccionado)) {
                try {
                    boolean correcto = this.archivoExcel.descargarPlantilla(
                            this.directorioSeleccionado);
                    if (correcto) {
                        JOptionPane.showMessageDialog(this, "Se ha descargado "
                                + "satisfactoriamente la plantilla \n"
                                + "Plantilla_MantPriorVias.xlsx en el directorio "
                                + "seleccionado.", "Plantilla descargada",
                                JOptionPane.INFORMATION_MESSAGE, INFORMATION_IMAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Se ha generado "
                                + "un error descargando la plantilla. "
                                + "Por favor verifique que el directorio exista\n y "
                                + "que un archivo con el nombre "
                                + "Plantilla_MantPriorVias.xlsx no esté abierto.",
                                "Error descargando plantilla",
                                JOptionPane.ERROR_MESSAGE, ERROR_IMAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Se ha generado "
                            + "un error descargando la plantilla.\n\n"
                            + "Por favor contáctese con el encargado de la "
                            + "aplicación para dar solución al error.",
                            "Error descargando plantilla",
                            JOptionPane.ERROR_MESSAGE, ERROR_IMAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor seleccione un "
                        + "directorio válido.",
                        "Directorio inválido",
                        JOptionPane.WARNING_MESSAGE, WARNING_IMAGE);
            }
        }
    }

    public void descargarAlternativasIntervencion() {
        int retorno = FILE_CHOOSER_ALTERNATIVAS_INTERVENCION.showOpenDialog(this);
        if (JFileChooser.APPROVE_OPTION == retorno) {
            this.directorioSeleccionado = FILE_CHOOSER_ALTERNATIVAS_INTERVENCION.
                    getSelectedFile();
            if (Util.isDirectorioValido(this.directorioSeleccionado)) {
                try {
                    boolean correcto = this.archivoExcel.descargarAlternativasIntervencion(
                            this.directorioSeleccionado);
                    if (correcto) {
                        JOptionPane.showMessageDialog(this, "Se ha descargado "
                                + "satisfactoriamente la plantilla \n"
                                + "Alternativas_Intervencion.xlsx en el directorio "
                                + "seleccionado.", "Plantilla descargada",
                                JOptionPane.INFORMATION_MESSAGE, INFORMATION_IMAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Se ha generado "
                                + "un error descargando la plantilla. "
                                + "Por favor verifique que el directorio exista\n y "
                                + "que un archivo con el nombre "
                                + "Alternativas_Intervencion.xlsx no esté abierto.",
                                "Error descargando plantilla",
                                JOptionPane.ERROR_MESSAGE, ERROR_IMAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Se ha generado "
                            + "un error descargando la las aternativas de intervención.\n\n"
                            + "Por favor contáctese con el encargado de la "
                            + "aplicación para dar solución al error.",
                            "Error descargando plantilla",
                            JOptionPane.ERROR_MESSAGE, ERROR_IMAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor seleccione un "
                        + "directorio válido.",
                        "Directorio inválido",
                        JOptionPane.WARNING_MESSAGE, WARNING_IMAGE);
            }
        }
    }

    private void cargarArchivoPlantilla() {
        int retorno = FILE_CHOOSER_PLANTILLA.showOpenDialog(this);
        if (JFileChooser.APPROVE_OPTION == retorno) {
            this.archivoSelecciondo = FILE_CHOOSER_PLANTILLA.getSelectedFile();
            if (this.archivoExcel.validarArchivo(this.archivoSelecciondo)) {
                try {
                    MantPriorViasInfo mantPriorViasInfo = this.archivoExcel.
                            leerPlantilla(this.archivoSelecciondo);
                    if (mantPriorViasInfo.tieneErrores()) {
                        JOptionPane.showMessageDialog(this, "El archivo contiene "
                                + "errores.\nA continuación se le indicará cuáles "
                                + "son los errores para poder continuar con el proceso.\n"
                                + "Una vez corregidos, por favor inténtelo de nuevo.\n",
                                "Archivo con Errores", JOptionPane.WARNING_MESSAGE,
                                WARNING_IMAGE);
                        String rutaTemporal = Util.getRutaTemporal();
                        String fullPath = rutaTemporal + "Errores_Plantilla.txt";
                        ArchivoTextoPlano creadorArchivoTextoPlano
                                = new ArchivoTextoPlano();

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
                                        "Se han presentado errores en al información; sin embargo, "
                                        + "el archivo de errores no se puede abrir. "
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
                    } else {
                        System.out.println("");

                        Collections.sort(mantPriorViasInfo.getVias(), (InfoVia o1, InfoVia o2) -> {
                            Double sum1 = o1.getSumatoriaValores();
                            Double sum2 = o2.getSumatoriaValores();
                            return sum2.compareTo(sum1);
                        });

                        mantPriorViasInfo = this.abrirPantallaCostosMantenimiento(mantPriorViasInfo);

                        if (mantPriorViasInfo == null) {
                            JOptionPane.showMessageDialog(this,
                                    "Usted ha decidido cerrar la ventana de "
                                    + "los ítems de mantenimiento y mejoramiento.\n"
                                    + "Por lo tanto no podrá continuar con el proceso.",
                                    "No se puede continuar con proceso",
                                    JOptionPane.WARNING_MESSAGE, WARNING_IMAGE);
                        } else {
                            this.abrirPantallaMantenimientoYMejoramiento(mantPriorViasInfo);
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
    }

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

        boolean erroresVias = false;
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
            erroresVias = true;
        } else if (mantPriorViasInfo.getVias().isEmpty()) {
            informacion += "Errores en hoja Priorización o errores en vías:";
            informacion += separadorLinea;
            informacion += separadorLinea;
            informacion += "    * No existe ninguna vía.";
            erroresVias = true;
        }

        if (mantPriorViasInfo.getErroresHojaPriorizacion() != null
                && !mantPriorViasInfo.getErroresHojaPriorizacion().isEmpty()) {
            if (!erroresVias) {
                informacion += "Errores en hoja Priorización o errores en vías:";
                informacion += separadorLinea;
                informacion += separadorLinea;
            }
            informacion += mantPriorViasInfo.getErroresHojaPriorizacion().trim()
                    + separadorLinea;
        }

        /*if (!informacion.isEmpty()) {
         informacion += separadorLinea;
         informacion += separadorLinea;
         informacion += "====================================================";
         informacion += separadorLinea;
         informacion += separadorLinea;
         }

         boolean erroresItems = false;
         if (mantPriorViasInfo.existenItemsConErrores()) {
         informacion += "Errores en hoja Costos de mantenimiento:";
         informacion += separadorLinea;
         informacion += separadorLinea;
         for (InfoItem ii : mantPriorViasInfo.getItems()) {
         if (ii != null && ii.getErroresItem() != null
         && !ii.getErroresItem().trim().isEmpty()) {
         informacion += "Número de fila: " + ii.getFila() + separadorLinea;
         String codItem = (ii.getItem().getCodigo() != null)
         ? ii.getItem().getCodigo().trim() : "";
         informacion += "Código del ítem: " + codItem + separadorLinea;
         String item = (ii.getItem().getItem() != null)
         ? ii.getItem().getItem() : "";
         informacion += "Nombre del ítem: " + item + separadorLinea;
         informacion += "Errores: " + separadorLinea + ii.getErroresItem();
         informacion += separadorLinea;
         informacion += separadorLinea;
         }
         }
         erroresItems = true;
         } else if (mantPriorViasInfo.getItems().isEmpty()) {
         informacion += "Errores en hoja Costos de mantenimiento:";
         informacion += separadorLinea;
         informacion += separadorLinea;
         informacion += "    * No existe ningún ítem de mantenimiento.";
         erroresItems = true;
         }

         if (mantPriorViasInfo.getErroresHojaCostosMantenimiento() != null
         && !mantPriorViasInfo.getErroresHojaCostosMantenimiento().isEmpty()) {
         if (!erroresItems) {
         informacion += "Errores en hoja Costos de mantenimiento:";
         informacion += separadorLinea;
         informacion += separadorLinea;
         }
         informacion += mantPriorViasInfo.getErroresHojaCostosMantenimiento().trim()
         + separadorLinea;
         }*/
        return informacion;
    }

    private MantPriorViasInfo abrirPantallaCostosMantenimiento(MantPriorViasInfo mantPriorViasInfo) {
        CostosMantenimientoJDialog costosMantenimientoJDialog
                = new CostosMantenimientoJDialog(this, true, mantPriorViasInfo);

        return costosMantenimientoJDialog.iniciarVentanta();
    }

    private void abrirPantallaMantenimientoYMejoramiento(MantPriorViasInfo mantPriorViasInfo) {
        MantenimientoJDialog mantenimientoJDialog = new MantenimientoJDialog(this, 
                true, mantPriorViasInfo);
        
        mantenimientoJDialog.iniciarVentanta();
    }
}
