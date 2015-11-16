package co.edu.udea.mantpriorivias;

import co.edu.udea.mantpriorivias.archivos.ArchivoTextoPlano;
import co.edu.udea.mantpriorivias.constantes.Constantes;
import co.edu.udea.mantpriorivias.entidades.Item;
import co.edu.udea.mantpriorivias.entidades.MantPriorViasInfo;
import co.edu.udea.mantpriorivias.general.Util;
import co.edu.udea.mantpriorivias.validadores.ValidadorPriorizacion;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class CostosMantenimientoJDialog extends javax.swing.JDialog {

    private final ValidadorPriorizacion validadorPriorizacion = new ValidadorPriorizacion();
    private final MantPriorViasInfo mantPriorViasInfo;
    private final JComboBox comboBox = new JComboBox();
    private final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    private final DefaultTableModel modeloTablaItems = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column > 1;
        }
    };
    private static final ImageIcon ERROR_IMAGE = new ImageIcon(Aplicacion.class
            .getResource("/co/edu/udea/mantpriorivias/recursos/imagenes/"
                    + "ic_dialog_error.png"));
    private static final ImageIcon WARNING_IMAGE = new ImageIcon(Aplicacion.class
            .getResource("/co/edu/udea/mantpriorivias/recursos/imagenes/"
                    + "ic_dialog_warning.png"));

    /**
     * Creates new form CostosMantenimientoJDialog
     *
     * @param parent
     * @param modal
     * @param mantPriorViasInfo
     */
    public CostosMantenimientoJDialog(java.awt.Frame parent, boolean modal,
            MantPriorViasInfo mantPriorViasInfo) {
        super(parent, modal);
        this.mantPriorViasInfo = mantPriorViasInfo;
        initComponents();

        Constantes.UNIDADES_POSIBLES.stream().forEach((s) -> {
            this.comboBox.addItem(s);
        });
        this.renderer.setToolTipText("Clic para abrir opciones");

        this.setLocationRelativeTo(parent);
        this.setResizable(false);
        this.armarTablaCostosMantenimiento();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPaneTabla = new javax.swing.JScrollPane();
        tablaAlternativasMantenimiento = new javax.swing.JTable();
        labelTitulo = new javax.swing.JLabel();
        labelIngresoInfo = new javax.swing.JLabel();
        botonSiguiente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaAlternativasMantenimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Unidad", "Valor Unitario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPaneTabla.setViewportView(tablaAlternativasMantenimiento);
        if (tablaAlternativasMantenimiento.getColumnModel().getColumnCount() > 0) {
            tablaAlternativasMantenimiento.getColumnModel().getColumn(0).setResizable(false);
            tablaAlternativasMantenimiento.getColumnModel().getColumn(1).setResizable(false);
            tablaAlternativasMantenimiento.getColumnModel().getColumn(2).setResizable(false);
            tablaAlternativasMantenimiento.getColumnModel().getColumn(3).setResizable(false);
        }

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        labelTitulo.setText("Mantenimiento y Priorización de Vías");

        labelIngresoInfo.setText("Por favor ingrese la información correspondiente a cada una de las alternativas de mantenimiento:");

        botonSiguiente.setText("Siguiente");
        botonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSiguienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelTitulo)
                        .addGap(252, 252, 252))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(scrollPaneTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(labelIngresoInfo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(355, 355, 355)
                        .addComponent(botonSiguiente)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(labelIngresoInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(botonSiguiente)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSiguienteActionPerformed
        String separadorLinea = System.getProperty("line.separator");
        String resultadoValidacion = this.validarInformacionItems();
        if (!resultadoValidacion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Existen errores en la información.\n\n"
                    + "A continuación se mostrará los errores presentados.",
                    "Información con Errores", JOptionPane.ERROR_MESSAGE,
                    ERROR_IMAGE);
            resultadoValidacion = "Existen los siguientes errores en "
                    + "la información ingresada:" + separadorLinea
                    + separadorLinea + resultadoValidacion;

            String rutaTemporal = Util.getRutaTemporal();
            String fullPath = rutaTemporal + "Errores_Items.txt";
            ArchivoTextoPlano creadorArchivoTextoPlano
                    = new ArchivoTextoPlano();
            if (creadorArchivoTextoPlano.crearArchivo(resultadoValidacion,
                    fullPath)) {
                File archivoErrores = new File(fullPath);
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop desktop = Desktop.getDesktop();
                        desktop.open(archivoErrores);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this,
                                "Se han presentado errores en al información; sin embargo, "
                                + "el archivo de errores no se puede abrir. "
                                + "Por favor búsquelo en la "
                                + "siguiente ruta:\n" + fullPath,
                                "No se puede abrir archivo",
                                JOptionPane.WARNING_MESSAGE, WARNING_IMAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Se han presentado errores en al información; sin embargo, "
                            + "el archivo de errores no se puede abrir. "
                            + "Por favor búsquelo en la "
                            + "siguiente ruta:\n" + fullPath,
                            "No se puede abrir archivo",
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
            this.dispose();
        }
    }//GEN-LAST:event_botonSiguienteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonSiguiente;
    private javax.swing.JLabel labelIngresoInfo;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JScrollPane scrollPaneTabla;
    private javax.swing.JTable tablaAlternativasMantenimiento;
    // End of variables declaration//GEN-END:variables

    private void armarTablaCostosMantenimiento() {
        List<String> daniosSeleccionadosUnicos = this.validadorPriorizacion.
                obtenerValoresUnicosDanios(this.mantPriorViasInfo.getVias());
        List<String> codigosItemsMantenimiento
                = this.obtenerCodigosMantenimientoUnicos(daniosSeleccionadosUnicos);
        List<Item> itemsMantenimiento = this.obtenerInfoItemsMantenimient(
                codigosItemsMantenimiento);

        String[][] datosItemsMantenimiento
                = this.crearTablaItemsMantenimiento(itemsMantenimiento);
        this.modeloTablaItems.setDataVector(datosItemsMantenimiento,
                Constantes.NOMBRES_COLUMNAS);
        this.tablaAlternativasMantenimiento.setModel(this.modeloTablaItems);

        TableColumn tc;
        tc = this.tablaAlternativasMantenimiento.getColumn(
                Constantes.NOMBRES_COLUMNAS[0]);
        tc.setResizable(false);
        tc.setMaxWidth(70);
        tc.setMinWidth(70);

        tc = this.tablaAlternativasMantenimiento.getColumn(
                Constantes.NOMBRES_COLUMNAS[1]);
        tc.setResizable(false);
        tc.setMaxWidth(402);
        tc.setMinWidth(402);

        tc = this.tablaAlternativasMantenimiento.getColumn(
                Constantes.NOMBRES_COLUMNAS[2]);
        tc.setMaxWidth(125);
        tc.setMinWidth(125);
        tc.setCellEditor(new DefaultCellEditor(this.comboBox));
        tc.setCellRenderer(this.renderer);

        tc = this.tablaAlternativasMantenimiento.getColumn(
                Constantes.NOMBRES_COLUMNAS[3]);
        tc.setMaxWidth(125);
        tc.setMinWidth(125);
    }

    private List<String> obtenerCodigosMantenimientoUnicos(List<String> daniosSeleccionados) {
        List<String> codigosMantenimiento = new ArrayList<>();
        for (String ds : daniosSeleccionados) {
            String s = Constantes.ALTERNATIVAS_INTERVENCION_MANTENIMIENTO.get(ds);
            List<String> valoresObtenidos = Util.tokenizar(s, ",");
            for (String vo : valoresObtenidos) {
                codigosMantenimiento.add(vo);
            }
        }

        codigosMantenimiento = codigosMantenimiento.stream().distinct().collect(
                Collectors.toList());

        return codigosMantenimiento;
    }

    private List<Item> obtenerInfoItemsMantenimient(List<String> codigosItemsMantenimiento) {
        List<Item> infoItems = new ArrayList<>();
        codigosItemsMantenimiento.stream().forEach(item -> {
            infoItems.add(Constantes.ITEMS.get(item));
        });

        return infoItems;
    }

    private String[][] crearTablaItemsMantenimiento(List<Item> itemsMantenimiento) {
        String[][] datos = new String[itemsMantenimiento.size()
                + Constantes.CANTIDAD_ITEMS_MEJORAS][Constantes.CANTIDAD_COLUMNAS];
        int mejora = 1;
        while (mejora <= Constantes.CANTIDAD_ITEMS_MEJORAS) {
            Item item = Constantes.ITEMS.get("ME" + mejora);
            datos[mejora - 1][0] = item.getCodigo();
            datos[mejora - 1][1] = item.getItem();

            mejora++;
        }

        int i = mejora - 1;
        while (i < datos.length) {
            datos[i][0] = itemsMantenimiento.get(i - Constantes.CANTIDAD_ITEMS_MEJORAS).getCodigo();
            datos[i][1] = itemsMantenimiento.get(i - Constantes.CANTIDAD_ITEMS_MEJORAS).getItem();

            i++;
        }

        return datos;
    }

    private String validarInformacionItems() {
        String errores = "";
        String separadorLinea = System.getProperty("line.separator");
        this.mantPriorViasInfo.setItems(new ArrayList<>());
        for (int fila = 0; fila < this.tablaAlternativasMantenimiento.getRowCount(); fila++) {
            String codigo = (String) this.tablaAlternativasMantenimiento.getValueAt(fila, 0);
            String item = (String) this.tablaAlternativasMantenimiento.getValueAt(fila, 1);
            String unidad = (String) this.tablaAlternativasMantenimiento.getValueAt(fila, 2);
            String valorUnitario = (String) this.tablaAlternativasMantenimiento.getValueAt(fila, 3);

            if (unidad == null || unidad.trim().isEmpty()
                    || !Constantes.UNIDADES_POSIBLES.contains(unidad.trim())
                    || valorUnitario == null || valorUnitario.trim().isEmpty()
                    || !Util.isNumerico(valorUnitario)
                    || (Util.isNumerico(valorUnitario)
                    && Double.parseDouble(valorUnitario.replaceAll(",", ".")) <= 0)) {
                if ((unidad == null || unidad.trim().isEmpty()
                        || !Constantes.UNIDADES_POSIBLES.contains(unidad.trim()))
                        && (valorUnitario == null || valorUnitario.trim().isEmpty()
                        || !Util.isNumerico(valorUnitario)
                        || (Util.isNumerico(valorUnitario)
                        && Double.parseDouble(valorUnitario) <= 0))) {
                    if (Util.isNumerico(valorUnitario)
                            && Double.parseDouble(valorUnitario.replaceAll(",", ".")) <= 0) {
                        errores += "* " + codigo + ": no tiene unidad ni valor unitario, "
                                + "ninguno de los dos es válido o no tiene unidad "
                                + "válida y el valor es menor o igual a cero."
                                + separadorLinea;
                        continue;
                    }
                    errores += "* " + codigo + ": no tiene unidad ni valor unitario o "
                            + "ninguno de los dos es válido." + separadorLinea;
                    continue;
                }

                if (unidad == null || unidad.trim().isEmpty()
                        || !Constantes.UNIDADES_POSIBLES.contains(unidad.trim())) {
                    errores += "* " + codigo + ": no tiene unidad o es no válido." + separadorLinea;
                    continue;
                }

                if (valorUnitario == null || valorUnitario.trim().isEmpty()
                        || !Util.isNumerico(valorUnitario)) {
                    errores += "* " + codigo + ": no tiene valor unitario o es no "
                            + "válido." + separadorLinea;
                } else if (Util.isNumerico(valorUnitario)
                        && Double.parseDouble(valorUnitario.replaceAll(",", ".")) <= 0) {
                    errores += "* " + codigo + ": el valor unitario es menor o igual "
                            + "que cero." + separadorLinea;
                }
            } else {
                this.mantPriorViasInfo.getItems().add(new Item(codigo, item,
                        unidad, valorUnitario));
            }
        }

        return errores;
    }

    public MantPriorViasInfo iniciarVentanta() {
        this.setVisible(true);

        return null;
    }
}
