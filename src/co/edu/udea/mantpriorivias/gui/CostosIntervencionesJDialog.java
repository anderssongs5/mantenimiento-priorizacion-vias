package co.edu.udea.mantpriorivias.gui;

import co.edu.udea.mantpriorivias.archivos.ArchivoTextoPlano;
import co.edu.udea.mantpriorivias.general.Constantes;
import co.edu.udea.mantpriorivias.negocio.entidades.Item;
import co.edu.udea.mantpriorivias.dto.MantPriorViasInfo;
import co.edu.udea.mantpriorivias.negocio.entidades.Unidad;
import co.edu.udea.mantpriorivias.general.Util;
import co.edu.udea.mantpriorivias.negocio.validadores.ValidadorCostosIntervenciones;
import co.edu.udea.mantpriorivias.negocio.validadores.ValidadorPriorizacion;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class CostosIntervencionesJDialog extends javax.swing.JDialog {

    private final ValidadorPriorizacion validadorPriorizacion = new ValidadorPriorizacion();
    private final ValidadorCostosIntervenciones validadorIntervencion = new ValidadorCostosIntervenciones();
    private final MantPriorViasInfo mantPriorViasInfo;
    private List<Item> items = new ArrayList<>();
    private final JComboBox comboBox = new JComboBox();
    private final DefaultTableModel modeloTablaItems = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column > 1;
        }
    };
    private static final ImageIcon ERROR_IMAGE = new ImageIcon(Inicio.class
            .getResource("/co/edu/udea/mantpriorivias/gui/imagenes/"
                    + "ic_dialog_error.png"));
    private static final ImageIcon WARNING_IMAGE = new ImageIcon(Inicio.class
            .getResource("/co/edu/udea/mantpriorivias/gui/imagenes/"
                    + "ic_dialog_warning.png"));
    private final ListCellRenderer comboRenderer = new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list,
                Object value, int index, boolean isSelected,
                boolean cellHasFocus) {
            if (value instanceof Unidad) {
                setToolTipText(((Unidad) value).getNombre());
                value = ((Unidad) value).getUnidad();
            } else {
                setToolTipText("");
                value = "";
            }
            return super.getListCellRendererComponent(list, value, index,
                    isSelected, cellHasFocus);
        }
    };
    TableCellRenderer tableRenderer = new DefaultTableCellRenderer() {
        @Override
        protected void setValue(Object value) {
            if (value instanceof Unidad) {
                setToolTipText(((Unidad) value).getNombre());
                value = ((Unidad) value).getUnidad();
            } else {
                setToolTipText("Clic para abrir opciones");
                value = "";
            }
            super.setValue(value);
        }

    };

    /**
     * Creates new form CostosMantenimientoJDialog
     *
     * @param parent
     * @param modal
     * @param mantPriorViasInfo
     */
    public CostosIntervencionesJDialog(java.awt.Frame parent, boolean modal,
            MantPriorViasInfo mantPriorViasInfo) {
        super(parent, modal);
        this.mantPriorViasInfo = mantPriorViasInfo;
        initComponents();

        Constantes.UNIDADES_POSIBLES.stream().forEach((s) -> {
            this.comboBox.addItem(s);
        });
        this.comboBox.setRenderer(this.comboRenderer);

        this.setLocationRelativeTo(parent);
        this.setResizable(false);
        this.armarTablaCostosMantenimiento();

        this.setTitle("Alternativas de Intervención");
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
        tablaAlternativasIntervencion = new javax.swing.JTable();
        labelTitulo = new javax.swing.JLabel();
        labelIngresoInfo = new javax.swing.JLabel();
        botonSiguiente = new javax.swing.JButton();
        notaDecimalesLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaAlternativasIntervencion.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollPaneTabla.setViewportView(tablaAlternativasIntervencion);
        if (tablaAlternativasIntervencion.getColumnModel().getColumnCount() > 0) {
            tablaAlternativasIntervencion.getColumnModel().getColumn(0).setResizable(false);
            tablaAlternativasIntervencion.getColumnModel().getColumn(1).setResizable(false);
            tablaAlternativasIntervencion.getColumnModel().getColumn(2).setResizable(false);
            tablaAlternativasIntervencion.getColumnModel().getColumn(3).setResizable(false);
        }

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        labelTitulo.setText("Priorización y Mantenimiento de Vías");

        labelIngresoInfo.setText("Por favor ingrese la información correspondiente a cada una de las alternativas de mantenimiento:");

        botonSiguiente.setText("Siguiente");
        botonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSiguienteActionPerformed(evt);
            }
        });

        notaDecimalesLabel.setText("Nota: Recuerde que los valores decimales van separados por coma: 15,7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(notaDecimalesLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(labelTitulo)
                            .addGap(252, 252, 252))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(scrollPaneTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(labelIngresoInfo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(354, 354, 354)
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
                .addComponent(scrollPaneTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addGap(4, 4, 4)
                .addComponent(notaDecimalesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonSiguiente)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSiguienteActionPerformed
        String separadorLinea = Util.getSeparadorLinea();
        this.mantPriorViasInfo.setItems(new ArrayList<>());
        Object[][] datos = this.obtenerDatos();
        String resultadoValidacion = this.validadorIntervencion.
                validarInformacionItems(this.items, datos);
        if (!resultadoValidacion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Existen errores en la información.\n\n"
                    + "A continuación se mostrará los errores presentados.",
                    "Información con Errores", JOptionPane.ERROR_MESSAGE,
                    ERROR_IMAGE);
            resultadoValidacion = "Existen los siguientes errores en "
                    + "la información ingresada:" + separadorLinea
                    + separadorLinea + resultadoValidacion;

            String rutaTemporal = Util.getRutaTemporal();
            String fullPath = rutaTemporal + "/Errores_Items.txt";
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
            this.mantPriorViasInfo.setItems(this.items);
            this.dispose();
        }
    }//GEN-LAST:event_botonSiguienteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonSiguiente;
    private javax.swing.JLabel labelIngresoInfo;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel notaDecimalesLabel;
    private javax.swing.JScrollPane scrollPaneTabla;
    private javax.swing.JTable tablaAlternativasIntervencion;
    // End of variables declaration//GEN-END:variables

    private void armarTablaCostosMantenimiento() {
        List<String> daniosSeleccionadosUnicos = this.validadorPriorizacion.
                obtenerValoresUnicosDanios(this.mantPriorViasInfo.getVias());
        List<Item> itemsMantenimiento = this.validadorIntervencion.
                obtenerCodigosMantenimientoUnicos(daniosSeleccionadosUnicos);

        String[][] datosItemsMantenimiento
                = this.crearTablaItemsMantenimiento(itemsMantenimiento);
        this.modeloTablaItems.setDataVector(datosItemsMantenimiento,
                Constantes.NOMBRES_COLUMNAS);
        this.tablaAlternativasIntervencion.setModel(this.modeloTablaItems);

        TableColumn tc;
        tc = this.tablaAlternativasIntervencion.getColumn(
                Constantes.NOMBRES_COLUMNAS[0]);
        tc.setResizable(false);
        tc.setMaxWidth(70);
        tc.setMinWidth(70);

        tc = this.tablaAlternativasIntervencion.getColumn(
                Constantes.NOMBRES_COLUMNAS[1]);
        tc.setResizable(false);
        tc.setMaxWidth(402);
        tc.setMinWidth(402);

        tc = this.tablaAlternativasIntervencion.getColumn(
                Constantes.NOMBRES_COLUMNAS[2]);
        tc.setMaxWidth(125);
        tc.setMinWidth(125);
        this.comboBox.setRenderer(this.comboRenderer);
        tc.setCellEditor(new DefaultCellEditor(this.comboBox));
        tc.setCellRenderer(this.tableRenderer);

        tc = this.tablaAlternativasIntervencion.getColumn(
                Constantes.NOMBRES_COLUMNAS[3]);
        tc.setMaxWidth(125);
        tc.setMinWidth(125);
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

    public MantPriorViasInfo iniciarVentanta() {
        this.setVisible(true);

        return this.mantPriorViasInfo;
    }

    private Object[][] obtenerDatos() {
        Object[][] datos = new String[this.tablaAlternativasIntervencion.getRowCount()][4];
        for (int fila = 0; fila < this.tablaAlternativasIntervencion.getRowCount(); fila++) {
            String codigo = (String) this.tablaAlternativasIntervencion.getValueAt(fila, 0);
            String item = (String) this.tablaAlternativasIntervencion.getValueAt(fila, 1);
            Unidad unidad = (Unidad) this.tablaAlternativasIntervencion.getValueAt(fila, 2);
            String valorUnitario = (String) this.tablaAlternativasIntervencion.getValueAt(fila, 3);

            datos[fila][0] = codigo;
            datos[fila][1] = item;
            datos[fila][2] = unidad;
            datos[fila][3] = valorUnitario;
        }

        return datos;
    }
}
