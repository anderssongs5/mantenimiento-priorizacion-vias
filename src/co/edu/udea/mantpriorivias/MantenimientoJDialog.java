package co.edu.udea.mantpriorivias;

import co.edu.udea.mantpriorivias.archivos.ArchivoExcel;
import co.edu.udea.mantpriorivias.constantes.Constantes;
import co.edu.udea.mantpriorivias.entidades.InfoVia;
import co.edu.udea.mantpriorivias.entidades.Item;
import co.edu.udea.mantpriorivias.entidades.MantPriorViasInfo;
import co.edu.udea.mantpriorivias.entidades.Presupuesto;
import co.edu.udea.mantpriorivias.general.Util;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

public class MantenimientoJDialog extends javax.swing.JDialog {

    private final MantPriorViasInfo mantPriorViasInfo;
    private ArchivoExcel archivoExcel = new ArchivoExcel();
    private List<String> listaDaniosComboBox = new ArrayList<>();
    private String via;
    private Item itemVacio = new Item("Seleccione", "");
    private static final ImageIcon ERROR_IMAGE = new ImageIcon(Aplicacion.class
            .getResource("/co/edu/udea/mantpriorivias/recursos/imagenes/"
                    + "ic_dialog_error.png"));
    private static final ImageIcon WARNING_IMAGE = new ImageIcon(Aplicacion.class
            .getResource("/co/edu/udea/mantpriorivias/recursos/imagenes/"
                    + "ic_dialog_warning.png"));
    private final ListCellRenderer mantenimientosComboRenderer = new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list,
                Object value, int index, boolean isSelected,
                boolean cellHasFocus) {
            if (value instanceof Item) {
                setToolTipText(((Item) value).getItem());
                value = ((Item) value).getCodigo();
            } else {
                setToolTipText("");
                value = "";
            }
            return super.getListCellRendererComponent(list, value, index,
                    isSelected, cellHasFocus);
        }
    };

    /**
     * Creates new form CostosMantenimientoJDialog
     *
     * @param parent
     * @param modal
     * @param mantPriorViasInfo
     */
    public MantenimientoJDialog(java.awt.Frame parent, boolean modal,
            MantPriorViasInfo mantPriorViasInfo) {
        super(parent, modal);
        this.mantPriorViasInfo = mantPriorViasInfo;
        initComponents();

        this.setInformacionPresupuesto(this.mantPriorViasInfo.getPresupuesto());
        this.setCodigosVias(this.mantPriorViasInfo.getVias());

        this.setLocationRelativeTo(parent);
        this.setResizable(false);

        this.setTitle("Mantenimiento y Mejoramiento de Vías");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitulo = new javax.swing.JLabel();
        presupuestoTotlaInicialLabel = new javax.swing.JLabel();
        presupuestoTotalInicialTextField = new javax.swing.JTextField();
        porcentajeAdministracionLabel = new javax.swing.JLabel();
        porcentajeAdministracionTextField = new javax.swing.JTextField();
        porcentajeImprevistosLabel = new javax.swing.JLabel();
        porcentajeImprevistosTextField = new javax.swing.JTextField();
        porcentajeUtilidadesTextField = new javax.swing.JTextField();
        porcentajeUtilidadesLabel = new javax.swing.JLabel();
        presupuestoDisponibleLabel = new javax.swing.JLabel();
        presupuestoDisponibleTextField = new javax.swing.JTextField();
        codigosViasComboBox = new javax.swing.JComboBox();
        viasLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        daniosComboBox = new javax.swing.JComboBox();
        mantenimientosComboBox = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        archivoMenu = new javax.swing.JMenu();
        abrirAlternativasIntervencionMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        labelTitulo.setText("Priorización y Mantenimiento de Vías");

        presupuestoTotlaInicialLabel.setText("Presupuesto Total Inicial");

        presupuestoTotalInicialTextField.setEditable(false);
        presupuestoTotalInicialTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        porcentajeAdministracionLabel.setText("% Administración");

        porcentajeAdministracionTextField.setEditable(false);
        porcentajeAdministracionTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        porcentajeImprevistosLabel.setText("% Imprevistos");

        porcentajeImprevistosTextField.setEditable(false);
        porcentajeImprevistosTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        porcentajeUtilidadesTextField.setEditable(false);
        porcentajeUtilidadesTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        porcentajeUtilidadesLabel.setText("% Utilidades");

        presupuestoDisponibleLabel.setText("Presupuesto Disponible");

        presupuestoDisponibleTextField.setEditable(false);
        presupuestoDisponibleTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        codigosViasComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigosViasComboBoxActionPerformed(evt);
            }
        });

        viasLabel.setText("Vías");

        daniosComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daniosComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(daniosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(mantenimientosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(daniosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mantenimientosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(253, Short.MAX_VALUE))
        );

        archivoMenu.setText("Archivo");

        abrirAlternativasIntervencionMenuItem.setText("Abrir alternativas intervención");
        abrirAlternativasIntervencionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirAlternativasIntervencionMenuItemActionPerformed(evt);
            }
        });
        archivoMenu.add(abrirAlternativasIntervencionMenuItem);

        jMenuBar1.add(archivoMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(313, 313, 313)
                        .addComponent(labelTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(codigosViasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(viasLabel))
                                .addGap(30, 30, 30)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(presupuestoTotlaInicialLabel)
                                    .addComponent(presupuestoTotalInicialTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(66, 66, 66)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(porcentajeAdministracionLabel)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(porcentajeImprevistosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(105, 105, 105)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(porcentajeImprevistosLabel)
                                    .addComponent(porcentajeAdministracionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(75, 75, 75)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(porcentajeUtilidadesLabel)
                                    .addComponent(porcentajeUtilidadesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(presupuestoDisponibleLabel)
                                    .addComponent(presupuestoDisponibleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(presupuestoDisponibleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(presupuestoDisponibleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(presupuestoTotlaInicialLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(presupuestoTotalInicialTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(porcentajeAdministracionLabel)
                                    .addComponent(porcentajeImprevistosLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(porcentajeAdministracionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(porcentajeImprevistosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(porcentajeUtilidadesLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(porcentajeUtilidadesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(viasLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codigosViasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void abrirAlternativasIntervencionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirAlternativasIntervencionMenuItemActionPerformed
        String ruta = Util.getRutaTemporal();
        try {
            boolean resultado;
            resultado = this.archivoExcel.
                    descargarAlternativasIntervencion(new File(ruta));
            if (!resultado) {
                JOptionPane.showMessageDialog(this, "No se puede abrir el archivo"
                        + " de alternativas de intervención.\n\nPor favor "
                        + "conctátese con el administrador de la aplicación.",
                        "Error en archivo", JOptionPane.ERROR_MESSAGE, ERROR_IMAGE);
            } else {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(new File(ruta + "/"
                            + ArchivoExcel.ARCHIVO_ALTERNATIVAS_INTERVENCION));
                } else {
                    JOptionPane.showMessageDialog(this,
                            "No se puede abrir el archivo"
                            + " de alternativas de intervención.\n\nPor favor "
                            + "conctátese con el administrador de la aplicación.",
                            "No se puede abrir archivos",
                            JOptionPane.WARNING_MESSAGE, WARNING_IMAGE);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MantenimientoJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_abrirAlternativasIntervencionMenuItemActionPerformed

    private void codigosViasComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigosViasComboBoxActionPerformed
        this.via = (String) this.codigosViasComboBox.getSelectedItem();
        this.mantenimientosComboBox.removeAllItems();
        this.mantenimientosComboBox.addItem(this.itemVacio);
        this.mantenimientosComboBox.setRenderer(this.mantenimientosComboRenderer);
        System.out.println(this.via);

        List<String> danios = this.obtenerDaniosDadoUnaVia(this.via);
        if (danios.contains("89N")) {
            danios.remove("89N");
        }

        this.daniosComboBox.removeAllItems();
        this.listaDaniosComboBox.clear();

        this.daniosComboBox.addItem("Seleccione");
        this.listaDaniosComboBox.add("Seleccione");
        danios.stream().forEach(s -> {
            this.daniosComboBox.addItem(Constantes.NOMBRES_DANIOS.get(
                    s.substring(0, s.length() - 1)) + " - " + s.substring(2));
            this.listaDaniosComboBox.add(s);
        });
    }//GEN-LAST:event_codigosViasComboBoxActionPerformed

    private void daniosComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daniosComboBoxActionPerformed
        int posicionDanio = this.daniosComboBox.getSelectedIndex();
        if (this.listaDaniosComboBox != null && !this.listaDaniosComboBox.isEmpty()
                && posicionDanio >= 0) {
            String danioSeleccionado = this.listaDaniosComboBox.get(posicionDanio);
            String alternativasIntervencion = Constantes.ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.get(
                    danioSeleccionado);
            if (alternativasIntervencion != null) {
                List<String> mantenimientosMejoras = Util.tokenizar(
                        alternativasIntervencion, ",");
                List<Item> itemsObject = new ArrayList<>();
                this.mantPriorViasInfo.getItems().stream().forEach(i -> {
                    mantenimientosMejoras.stream().forEach(m -> {
                        if (m.equals(i.getCodigo())) {
                            itemsObject.add(i);
                        }
                    });
                });

                this.mantenimientosComboBox.removeAllItems();
                this.mantenimientosComboBox.addItem(this.itemVacio);
                itemsObject.stream().forEach(i -> {
                    this.mantenimientosComboBox.addItem(i);
                });
                this.mantenimientosComboBox.setRenderer(this.mantenimientosComboRenderer);
            } else {
                this.mantenimientosComboBox.removeAllItems();
                this.mantenimientosComboBox.addItem(this.itemVacio);
            }
        }
    }//GEN-LAST:event_daniosComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abrirAlternativasIntervencionMenuItem;
    private javax.swing.JMenu archivoMenu;
    private javax.swing.JComboBox codigosViasComboBox;
    private javax.swing.JComboBox daniosComboBox;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JComboBox mantenimientosComboBox;
    private javax.swing.JLabel porcentajeAdministracionLabel;
    private javax.swing.JTextField porcentajeAdministracionTextField;
    private javax.swing.JLabel porcentajeImprevistosLabel;
    private javax.swing.JTextField porcentajeImprevistosTextField;
    private javax.swing.JLabel porcentajeUtilidadesLabel;
    private javax.swing.JTextField porcentajeUtilidadesTextField;
    private javax.swing.JLabel presupuestoDisponibleLabel;
    private javax.swing.JTextField presupuestoDisponibleTextField;
    private javax.swing.JTextField presupuestoTotalInicialTextField;
    private javax.swing.JLabel presupuestoTotlaInicialLabel;
    private javax.swing.JLabel viasLabel;
    // End of variables declaration//GEN-END:variables

    public MantPriorViasInfo iniciarVentanta() {
        this.setVisible(true);

        return this.mantPriorViasInfo;
    }

    private void setInformacionPresupuesto(Presupuesto presupuesto) {
        this.presupuestoTotalInicialTextField.setText("$ "
                + presupuesto.getPresupuestoTotal());
        this.porcentajeAdministracionTextField.setText(String.valueOf(
                presupuesto.getPorcentajeAdministracion() * 100));
        this.porcentajeImprevistosTextField.setText(String.valueOf(
                presupuesto.getPorcentajeImprevistos() * 100));
        this.porcentajeUtilidadesTextField.setText(String.valueOf(
                presupuesto.getPorcentajeUtiliadades() * 100));
        this.presupuestoDisponibleTextField.setText("$ "
                + presupuesto.getPresupuestoDisponible());
    }

    private void setCodigosVias(List<InfoVia> vias) {
        this.codigosViasComboBox.addItem("Seleccione");
        vias.stream().forEach((v) -> {
            this.codigosViasComboBox.addItem(v.getVia().getCodigoVia().trim());
        });
    }

    private List<String> obtenerDaniosDadoUnaVia(String via) {
        List<String> danios = new ArrayList<>();

        for (InfoVia iv : mantPriorViasInfo.getVias()) {
            if (iv.getVia().getCodigoVia().equals(via)) {
                danios = iv.getDaniosSeleccionados();

                break;
            }
        }

        return danios;
    }
}
