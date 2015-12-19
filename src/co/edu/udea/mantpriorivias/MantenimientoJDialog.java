package co.edu.udea.mantpriorivias;

import co.edu.udea.mantpriorivias.archivos.ArchivoExcel;
import co.edu.udea.mantpriorivias.archivos.ArchivoTextoPlano;
import co.edu.udea.mantpriorivias.constantes.Constantes;
import static co.edu.udea.mantpriorivias.constantes.Constantes.NOMBRES_DANIOS;
import co.edu.udea.mantpriorivias.entidades.Alternativa;
import co.edu.udea.mantpriorivias.entidades.InfoVia;
import co.edu.udea.mantpriorivias.entidades.Item;
import co.edu.udea.mantpriorivias.entidades.MantPriorViasInfo;
import co.edu.udea.mantpriorivias.entidades.Presupuesto;
import co.edu.udea.mantpriorivias.entidades.ResumenMantenimiento;
import co.edu.udea.mantpriorivias.entidades.ResumenMejora;
import co.edu.udea.mantpriorivias.general.Util;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private List<Alternativa> alternativasMantenimiento = new ArrayList<>();
    private List<Alternativa> alternativasMejorasTSR = new ArrayList<>();
    private List<Alternativa> alternativasMejorasEA = new ArrayList<>();
    private List<ResumenMantenimiento> viasResumenMantenimiento = new ArrayList<>();
    private List<ResumenMejora> viasResumenMejora = new ArrayList<>();
    private List<Item> tratamientosSuperficialesRiegos = new ArrayList<>();
    private List<Item> estabilizacionAfirmados = new ArrayList<>();
    private ArchivoTextoPlano creadorArchivoTextoPlano = new ArchivoTextoPlano();
    private String resumen = null;
    private String via;
    private String unidadMantenimiento;
    private String unidadMejoraTSR;
    private String unidadMejoraEA;
    private boolean tieneMejoras;
    private int posicionDanio;
    private double presupuestoActual;
    private double presupuestoAdicional = 0.0;
    private double valorUnitarioMantenimiento;
    private double valorUnitarioMejoraEA;
    private double valorUnitarioMejoraTSR;
    private Item itemSeleccionadoMantenimiento;
    private Item itemSeleccionadoMejoraEA;
    private Item itemSeleccionadoMejoraTSR;
    private Item itemVacio = new Item("Seleccione", "");
    private static final ImageIcon ERROR_IMAGE = new ImageIcon(Aplicacion.class
            .getResource("/co/edu/udea/mantpriorivias/recursos/imagenes/"
                    + "ic_dialog_error.png"));
    private static final ImageIcon WARNING_IMAGE = new ImageIcon(Aplicacion.class
            .getResource("/co/edu/udea/mantpriorivias/recursos/imagenes/"
                    + "ic_dialog_warning.png"));
    private static final ImageIcon INFORMATION_IMAGE = new ImageIcon(Aplicacion.class
            .getResource("/co/edu/udea/mantpriorivias/recursos/imagenes/"
                    + "info_48.png"));
    private final ListCellRenderer comboRenderer = new DefaultListCellRenderer() {
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

        this.restringirValoresCampos();
        this.aplicarMantenimientoButton.setEnabled(false);
        this.presupuestoActualTextField.setBackground(Color.green);
        this.presupuestoAdicionalTextField.setText(
                String.valueOf(this.presupuestoAdicional));

        this.setMejoras();
        this.tratamientosSuperficialesRiegosComboBox.setRenderer(this.comboRenderer);
        this.estabilizacionAfirmadosComboBox.setRenderer(this.comboRenderer);
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
        porcentajeUtilidadesTextField = new javax.swing.JTextField();
        porcentajeUtilidadesLabel = new javax.swing.JLabel();
        presupuestoDisponibleLabel = new javax.swing.JLabel();
        presupuestoDisponibleTextField = new javax.swing.JTextField();
        codigosViasComboBox = new javax.swing.JComboBox();
        viasLabel = new javax.swing.JLabel();
        presupuestoActualTextField = new javax.swing.JTextField();
        presupuestoActualLabel = new javax.swing.JLabel();
        presupuestoAdicionalLabel = new javax.swing.JLabel();
        presupuestoAdicionalTextField = new javax.swing.JTextField();
        porcentajeImprevistosTextField = new javax.swing.JTextField();
        daniosAsociadosLabel = new javax.swing.JLabel();
        daniosComboBox = new javax.swing.JComboBox();
        costoUnitarioMantenimientoLabel = new javax.swing.JLabel();
        precioMantenimientoMejoraTextField = new javax.swing.JTextField();
        unidadMedidaMantenimientoLabel = new javax.swing.JLabel();
        unidadMedidaMantenimientoTextField = new javax.swing.JTextField();
        cantidadMantenimientoTextField = new javax.swing.JTextField();
        cantidadMantenimientosLabel = new javax.swing.JLabel();
        mantenimientosRecomendadosLabel = new javax.swing.JLabel();
        mantenimientosComboBox = new javax.swing.JComboBox();
        aplicarMantenimientoButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        resumenTextArea = new javax.swing.JTextArea();
        tratamientosSuperficialesRiegosComboBox = new javax.swing.JComboBox();
        tratamientosSuperficialesRiegosLabel = new javax.swing.JLabel();
        estabilizacionAfirmadosLabel = new javax.swing.JLabel();
        estabilizacionAfirmadosComboBox = new javax.swing.JComboBox();
        cantidadTratamientosSuperficialesRiegosTextField = new javax.swing.JTextField();
        cantidadTratamientosSuperficialesRiegosLabel = new javax.swing.JLabel();
        cantidadEstabilizacionAfirmadosTextField = new javax.swing.JTextField();
        cantidadEstabilizacionAfirmadosLabel = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        aplicarMejorasButton = new javax.swing.JButton();
        costoUnitarioMejoraTSRLabel = new javax.swing.JLabel();
        precioMejoraTSRTextField = new javax.swing.JTextField();
        unidadMedidaMejoraTRSLabel = new javax.swing.JLabel();
        unidadMedidaMejoraTSRTextField = new javax.swing.JTextField();
        precioMejoraEATextField = new javax.swing.JTextField();
        costoUnitarioMejoraTSRLabel1 = new javax.swing.JLabel();
        unidadMedidaMejoraEATextField = new javax.swing.JTextField();
        unidadMedidaMejoraTRSLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        archivoMenu = new javax.swing.JMenu();
        abrirAlternativasIntervencionMenuItem = new javax.swing.JMenuItem();
        exportarMenu = new javax.swing.JMenu();
        txtMenuItem = new javax.swing.JMenuItem();
        docxMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1575, 677));
        setResizable(false);

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        labelTitulo.setText("Priorización y Mantenimiento de Vías");

        presupuestoTotlaInicialLabel.setText("Presupuesto Total Inicial");

        presupuestoTotalInicialTextField.setEditable(false);
        presupuestoTotalInicialTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        porcentajeAdministracionLabel.setText("% Administración");

        porcentajeAdministracionTextField.setEditable(false);
        porcentajeAdministracionTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        porcentajeImprevistosLabel.setText("% Imprevistos");

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

        presupuestoActualTextField.setEditable(false);
        presupuestoActualTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        presupuestoActualLabel.setText("Presupuesto actual");

        presupuestoAdicionalLabel.setText("Presupuesto adicional");

        presupuestoAdicionalTextField.setEditable(false);
        presupuestoAdicionalTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        porcentajeImprevistosTextField.setEditable(false);
        porcentajeImprevistosTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        daniosAsociadosLabel.setText("Daños asociados a la vía");

        daniosComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daniosComboBoxActionPerformed(evt);
            }
        });

        costoUnitarioMantenimientoLabel.setText("Costo unitario");

        precioMantenimientoMejoraTextField.setEditable(false);
        precioMantenimientoMejoraTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precioMantenimientoMejoraTextFieldActionPerformed(evt);
            }
        });

        unidadMedidaMantenimientoLabel.setText("Unidad de medida ");

        unidadMedidaMantenimientoTextField.setEditable(false);
        unidadMedidaMantenimientoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unidadMedidaMantenimientoTextFieldActionPerformed(evt);
            }
        });

        cantidadMantenimientoTextField.setEditable(false);
        cantidadMantenimientoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadMantenimientoTextFieldActionPerformed(evt);
            }
        });

        cantidadMantenimientosLabel.setText("Cantidad");

        mantenimientosRecomendadosLabel.setText("Mantenimientos recomendados");

        mantenimientosComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mantenimientosComboBoxActionPerformed(evt);
            }
        });

        aplicarMantenimientoButton.setText("Aplicar Mantenimiento");
        aplicarMantenimientoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aplicarMantenimientoButtonActionPerformed(evt);
            }
        });

        resumenTextArea.setColumns(20);
        resumenTextArea.setRows(5);
        jScrollPane1.setViewportView(resumenTextArea);

        tratamientosSuperficialesRiegosComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tratamientosSuperficialesRiegosComboBoxActionPerformed(evt);
            }
        });

        tratamientosSuperficialesRiegosLabel.setText("Tratamientos Superficiales o Riegos");

        estabilizacionAfirmadosLabel.setText("Estabilización de Afirmados");

        estabilizacionAfirmadosComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estabilizacionAfirmadosComboBoxActionPerformed(evt);
            }
        });

        cantidadTratamientosSuperficialesRiegosTextField.setEditable(false);
        cantidadTratamientosSuperficialesRiegosTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadTratamientosSuperficialesRiegosTextFieldActionPerformed(evt);
            }
        });

        cantidadTratamientosSuperficialesRiegosLabel.setText("Cantidad");

        cantidadEstabilizacionAfirmadosTextField.setEditable(false);
        cantidadEstabilizacionAfirmadosTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadEstabilizacionAfirmadosTextFieldActionPerformed(evt);
            }
        });

        cantidadEstabilizacionAfirmadosLabel.setText("Cantidad");

        aplicarMejorasButton.setText("Aplicar Mejora(s)");
        aplicarMejorasButton.setEnabled(false);
        aplicarMejorasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aplicarMejorasButtonActionPerformed(evt);
            }
        });

        costoUnitarioMejoraTSRLabel.setText("Costo unitario");

        precioMejoraTSRTextField.setEditable(false);

        unidadMedidaMejoraTRSLabel.setText("Unidad de medida ");

        unidadMedidaMejoraTSRTextField.setEditable(false);
        unidadMedidaMejoraTSRTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unidadMedidaMejoraTSRTextFieldActionPerformed(evt);
            }
        });

        precioMejoraEATextField.setEditable(false);

        costoUnitarioMejoraTSRLabel1.setText("Costo unitario");

        unidadMedidaMejoraEATextField.setEditable(false);
        unidadMedidaMejoraEATextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unidadMedidaMejoraEATextFieldActionPerformed(evt);
            }
        });

        unidadMedidaMejoraTRSLabel1.setText("Unidad de medida ");

        archivoMenu.setText("Archivo");

        abrirAlternativasIntervencionMenuItem.setText("Abrir alternativas intervención");
        abrirAlternativasIntervencionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirAlternativasIntervencionMenuItemActionPerformed(evt);
            }
        });
        archivoMenu.add(abrirAlternativasIntervencionMenuItem);

        exportarMenu.setText("Exportar");

        txtMenuItem.setText("TXT");
        txtMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMenuItemActionPerformed(evt);
            }
        });
        exportarMenu.add(txtMenuItem);

        docxMenuItem.setText("Docx");
        exportarMenu.add(docxMenuItem);

        archivoMenu.add(exportarMenu);

        jMenuBar1.add(archivoMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(385, 385, 385)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(presupuestoActualTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(presupuestoActualLabel)))
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(presupuestoAdicionalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(presupuestoAdicionalLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(313, 313, 313)
                .addComponent(labelTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(119, 119, 119)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(presupuestoTotlaInicialLabel)
                                .addComponent(presupuestoTotalInicialTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(66, 66, 66)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(porcentajeAdministracionLabel)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(porcentajeAdministracionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(105, 105, 105)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(porcentajeImprevistosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(porcentajeImprevistosLabel))
                                    .addGap(75, 75, 75)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(porcentajeUtilidadesLabel)
                                        .addComponent(porcentajeUtilidadesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(costoUnitarioMantenimientoLabel)
                                .addComponent(cantidadMantenimientosLabel)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(precioMantenimientoMejoraTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cantidadMantenimientoTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(daniosAsociadosLabel)
                                .addComponent(daniosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(codigosViasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(viasLabel))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(unidadMedidaMantenimientoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(mantenimientosRecomendadosLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(mantenimientosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(unidadMedidaMantenimientoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(268, 268, 268)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(337, 337, 337)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tratamientosSuperficialesRiegosLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(unidadMedidaMejoraTRSLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(unidadMedidaMejoraTSRTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tratamientosSuperficialesRiegosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(29, 29, 29)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(precioMejoraTSRTextField)
                                            .addComponent(costoUnitarioMejoraTSRLabel)
                                            .addComponent(cantidadTratamientosSuperficialesRiegosLabel)
                                            .addComponent(cantidadTratamientosSuperficialesRiegosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(estabilizacionAfirmadosLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(estabilizacionAfirmadosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(unidadMedidaMejoraEATextField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(unidadMedidaMejoraTRSLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(30, 30, 30)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cantidadEstabilizacionAfirmadosLabel)
                                            .addComponent(cantidadEstabilizacionAfirmadosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(precioMejoraEATextField)
                                            .addComponent(costoUnitarioMejoraTSRLabel1))))))
                        .addGap(87, 87, 87)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(presupuestoDisponibleLabel)
                    .addComponent(presupuestoDisponibleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(aplicarMejorasButton)
                            .addComponent(aplicarMantenimientoButton))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(presupuestoDisponibleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(presupuestoDisponibleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(porcentajeAdministracionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(porcentajeImprevistosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(porcentajeUtilidadesLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(porcentajeUtilidadesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(presupuestoActualLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(presupuestoActualTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(presupuestoAdicionalLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(presupuestoAdicionalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(viasLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codigosViasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(daniosAsociadosLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(daniosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(mantenimientosRecomendadosLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(mantenimientosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(costoUnitarioMantenimientoLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(precioMantenimientoMejoraTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cantidadMantenimientoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(unidadMedidaMantenimientoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(aplicarMantenimientoButton, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cantidadMantenimientosLabel)
                                        .addComponent(unidadMedidaMantenimientoLabel)))))
                        .addGap(17, 17, 17)
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tratamientosSuperficialesRiegosLabel)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(tratamientosSuperficialesRiegosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(unidadMedidaMejoraTRSLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(unidadMedidaMejoraTSRTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(costoUnitarioMejoraTSRLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(precioMejoraTSRTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cantidadTratamientosSuperficialesRiegosLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cantidadTratamientosSuperficialesRiegosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(aplicarMejorasButton))))
                        .addGap(32, 32, 32)
                        .addComponent(estabilizacionAfirmadosLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estabilizacionAfirmadosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(unidadMedidaMejoraTRSLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unidadMedidaMejoraEATextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(costoUnitarioMejoraTSRLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(precioMejoraEATextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(cantidadEstabilizacionAfirmadosLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantidadEstabilizacionAfirmadosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(252, Short.MAX_VALUE))
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
                            + "contáctese con el administrador de la aplicación.",
                            "No se puede abrir archivos",
                            JOptionPane.WARNING_MESSAGE, WARNING_IMAGE);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MantenimientoJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_abrirAlternativasIntervencionMenuItemActionPerformed

    private void codigosViasComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigosViasComboBoxActionPerformed
        this.setDaniosAsociados();

        this.tratamientosSuperficialesRiegosComboBox.removeAllItems();
        this.tratamientosSuperficialesRiegosComboBox.addItem(this.itemVacio);
        this.estabilizacionAfirmadosComboBox.removeAllItems();
        this.estabilizacionAfirmadosComboBox.addItem(this.itemVacio);
        if (this.via != null && !this.via.equals("Seleccione")) {
            this.tratamientosSuperficialesRiegos.stream().forEach(t -> {
                this.tratamientosSuperficialesRiegosComboBox.addItem(t);
            });

            this.estabilizacionAfirmados.stream().forEach(t -> {
                this.estabilizacionAfirmadosComboBox.addItem(t);
            });
        }

        this.itemSeleccionadoMejoraEA = null;
        this.itemSeleccionadoMejoraTSR = null;
        this.aplicarMejorasButton.setEnabled(false);
    }//GEN-LAST:event_codigosViasComboBoxActionPerformed

    private void daniosComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daniosComboBoxActionPerformed
        this.cargarMantenimientos();
    }//GEN-LAST:event_daniosComboBoxActionPerformed

    private void cantidadMantenimientoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadMantenimientoTextFieldActionPerformed
    }//GEN-LAST:event_cantidadMantenimientoTextFieldActionPerformed

    private void mantenimientosComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mantenimientosComboBoxActionPerformed
        int posicionItemSeleccionado = this.mantenimientosComboBox.getSelectedIndex();
        if (posicionItemSeleccionado <= 0) {
            this.cantidadMantenimientoTextField.setEditable(false);
            this.cantidadMantenimientoTextField.setText("");
            this.precioMantenimientoMejoraTextField.setText("");
            this.unidadMedidaMantenimientoTextField.setText("");
            this.aplicarMantenimientoButton.setEnabled(false);
        } else {
            this.aplicarMantenimientoButton.setEnabled(true);
            this.cantidadMantenimientoTextField.setEditable(true);
            this.itemSeleccionadoMantenimiento = (Item) this.mantenimientosComboBox.
                    getSelectedItem();
            this.valorUnitarioMantenimiento = this.buscarPrecioDadoCodigoItem(
                    this.itemSeleccionadoMantenimiento.getCodigo());
            this.unidadMantenimiento = this.buscarUnidadDadoCodigoItem(
                    this.itemSeleccionadoMantenimiento.getCodigo());
            this.precioMantenimientoMejoraTextField.setText("$ "
                    + String.valueOf(this.valorUnitarioMantenimiento));
            this.unidadMedidaMantenimientoTextField.setText(this.unidadMantenimiento);

            Alternativa mantenimiento = this.buscarMantenimientoAplicadoDadoViaItemMantenimiento(
                    this.via, this.listaDaniosComboBox.get(this.posicionDanio),
                    this.itemSeleccionadoMantenimiento.getCodigo());

            if (mantenimiento != null) {
                this.cantidadMantenimientoTextField.setText(
                        String.valueOf(mantenimiento.getCantidad()).replace(".0", ""));
            } else {
                this.cantidadMantenimientoTextField.setText("");
            }
        }
    }//GEN-LAST:event_mantenimientosComboBoxActionPerformed

    private void aplicarMantenimientoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aplicarMantenimientoButtonActionPerformed
        Alternativa mantenimiento = this.buscarMantenimientoAplicadoDadoViaItemMantenimiento(
                this.via, this.listaDaniosComboBox.get(this.posicionDanio),
                this.itemSeleccionadoMantenimiento.getCodigo());

        if (this.cantidadMantenimientoTextField.getText() == null
                || this.cantidadMantenimientoTextField.getText().trim().isEmpty()) {
            if (mantenimiento == null) {
                JOptionPane.showMessageDialog(this,
                        "No se ha agregado el mantenimiento porque no se ha ingresado"
                        + " la cantidad", "No se aplicó mantenimiento.",
                        JOptionPane.WARNING_MESSAGE, WARNING_IMAGE);

                return;
            } else {
                this.alternativasMantenimiento.remove(mantenimiento);
                if (this.presupuestoActual == 0.0) {
                    double t = mantenimiento.getCantidad()
                            * this.buscarPrecioDadoCodigoItem(mantenimiento.getItem());
                    if (t < this.presupuestoAdicional) {
                        this.presupuestoAdicional = this.presupuestoAdicional - t;
                    } else if (t == this.presupuestoAdicional) {
                        this.presupuestoAdicional = 0.0;
                    } else {
                        double p = t - this.presupuestoAdicional;
                        this.presupuestoAdicional = 0;
                        this.presupuestoActual += p;
                    }
                } else {
                    this.presupuestoActual = this.presupuestoActual + (mantenimiento.getCantidad()
                            * this.buscarPrecioDadoCodigoItem(mantenimiento.getItem()));
                }

                this.establecerPresupuesto();
                JOptionPane.showMessageDialog(this,
                        "Se ha eliminado el mantenimiento aplicado correctamente",
                        "Mantenimiento aplicado eliminado",
                        JOptionPane.INFORMATION_MESSAGE, INFORMATION_IMAGE);

                this.eliminarAlternativaResumenMantenimiento(mantenimiento);

                return;
            }
        }

        if (!Util.isNumerico(this.cantidadMantenimientoTextField.getText())) {
            JOptionPane.showMessageDialog(this,
                    "El valor ingresado no es un número válido", "Valor no numérico",
                    JOptionPane.WARNING_MESSAGE, WARNING_IMAGE);

            return;
        }

        if (Util.isNumerico(this.cantidadMantenimientoTextField.getText())
                && Double.parseDouble(this.cantidadMantenimientoTextField.getText()) <= 0) {
            JOptionPane.showMessageDialog(this,
                    "El valor ingresado debe ser mayor que cero", "Valor no válido",
                    JOptionPane.WARNING_MESSAGE, WARNING_IMAGE);

            return;
        }

        double cantidad = Double.parseDouble(
                this.cantidadMantenimientoTextField.getText().trim());
        if (mantenimiento != null) {
            if (mantenimiento.getCantidad() == cantidad) {
                JOptionPane.showMessageDialog(this,
                        "No se ha aplicado el mantenimiento porque ya existe uno con "
                        + "los mismos valores", "Mantenimiento no aplicado",
                        JOptionPane.WARNING_MESSAGE, WARNING_IMAGE);

                return;
            } else {
                this.alternativasMantenimiento.remove(mantenimiento);
                double cantidadTemp = cantidad;
                cantidad = cantidadTemp - mantenimiento.getCantidad();
                mantenimiento.setCantidad(cantidadTemp);
                double precioMantenimiento = Math.abs(cantidad * this.valorUnitarioMantenimiento);
                if (cantidad < 0) {
                    if (this.presupuestoAdicional > 0) {
                        double t = this.presupuestoAdicional - precioMantenimiento;
                        if (t > 0) {
                            this.presupuestoAdicional = t;
                        } else if (t == 0) {
                            this.presupuestoAdicional = 0;
                        } else {
                            this.presupuestoAdicional = 0;
                            this.presupuestoActual += Math.abs(t);
                        }
                    } else {
                        this.presupuestoActual += precioMantenimiento;
                    }
                } else {
                    if (this.presupuestoAdicional > 0) {
                        this.presupuestoAdicional += precioMantenimiento;
                    } else {
                        double t = this.presupuestoActual - precioMantenimiento;
                        if (t > 0) {
                            this.presupuestoActual = t;
                        } else if (t == 0) {
                            this.presupuestoActual = 0;
                        } else {
                            this.presupuestoActual = 0;
                            this.presupuestoAdicional = Math.abs(t);
                        }
                    }
                }
                this.alternativasMantenimiento.add(mantenimiento);

                this.establecerPresupuesto();
                this.agregarAlternativaResumenMantenimiento(mantenimiento);

                return;
            }
        }

        if (this.presupuestoAdicional > 0) {
            this.presupuestoAdicional += cantidad * this.valorUnitarioMantenimiento;
        } else {
            double t = this.presupuestoActual - cantidad * this.valorUnitarioMantenimiento;
            if (t >= 0) {
                this.presupuestoActual = t;
            } else {
                this.presupuestoActual = 0;
                this.presupuestoAdicional = Math.abs(t);
            }
        }

        this.establecerPresupuesto();

        Alternativa alternativa = new Alternativa();
        alternativa.setCodigoVia(this.via);
        alternativa.setItem(this.itemSeleccionadoMantenimiento.getCodigo());
        alternativa.setDanio(this.listaDaniosComboBox.get(this.posicionDanio));
        alternativa.setCantidad(cantidad);
        this.alternativasMantenimiento.add(alternativa);

        this.agregarAlternativaResumenMantenimiento(alternativa);
    }//GEN-LAST:event_aplicarMantenimientoButtonActionPerformed

    private void unidadMedidaMantenimientoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unidadMedidaMantenimientoTextFieldActionPerformed
    }//GEN-LAST:event_unidadMedidaMantenimientoTextFieldActionPerformed

    private void cantidadTratamientosSuperficialesRiegosTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadTratamientosSuperficialesRiegosTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadTratamientosSuperficialesRiegosTextFieldActionPerformed

    private void cantidadEstabilizacionAfirmadosTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadEstabilizacionAfirmadosTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadEstabilizacionAfirmadosTextFieldActionPerformed

    private void aplicarMejorasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aplicarMejorasButtonActionPerformed
        Alternativa mejoraTSR = null;
        if (this.itemSeleccionadoMejoraTSR != null) {
            mejoraTSR = this.buscarMejoraTSRAplicadaDadoViaMejora(via,
                    this.itemSeleccionadoMejoraTSR.getCodigo());
        }
        Alternativa mejoraEA = null;
        if (this.itemSeleccionadoMejoraEA != null) {
            mejoraEA = this.buscarMejoraEAAplicadaDadoViaMejora(via,
                    this.itemSeleccionadoMejoraEA.getCodigo());
        }

        boolean mejoraTSREliminada = false;
        boolean mejoraEAEliminada = false;
        if ((this.cantidadTratamientosSuperficialesRiegosTextField.getText() == null
                || this.cantidadTratamientosSuperficialesRiegosTextField.getText().trim().isEmpty())
                && (this.cantidadEstabilizacionAfirmadosTextField.getText() == null
                || this.cantidadEstabilizacionAfirmadosTextField.getText().trim().isEmpty())
                && mejoraEA == null && mejoraTSR == null) {
            JOptionPane.showMessageDialog(this,
                    "No se han agregado las mejoras porque no se han ingresado"
                    + " las cantidades", "No se aplicaron mejoras.",
                    JOptionPane.WARNING_MESSAGE, WARNING_IMAGE);

            return;
        } else {
            double totalARecuperar = 0.0;
            if (mejoraEA != null
                    && (this.cantidadEstabilizacionAfirmadosTextField.getText() == null
                    || this.cantidadEstabilizacionAfirmadosTextField.getText().trim().isEmpty())) {
                this.eliminarAlternativaResumenMejoras(mejoraEA);
                this.alternativasMejorasEA.remove(mejoraEA);
                totalARecuperar += mejoraEA.getCantidad()
                        * this.buscarPrecioDadoCodigoItem(mejoraEA.getItem());
                mejoraEAEliminada = true;
            }

            if (mejoraTSR != null
                    && (this.cantidadTratamientosSuperficialesRiegosTextField.getText() == null
                    || this.cantidadTratamientosSuperficialesRiegosTextField.getText().trim().isEmpty())) {
                this.eliminarAlternativaResumenMejoras(mejoraTSR);
                this.alternativasMejorasTSR.remove(mejoraTSR);
                totalARecuperar += mejoraTSR.getCantidad()
                        * this.buscarPrecioDadoCodigoItem(mejoraTSR.getItem());
                mejoraTSREliminada = true;
            }

            if (mejoraEAEliminada || mejoraTSREliminada) {
                if (this.presupuestoActual == 0.0) {
                    if (totalARecuperar < this.presupuestoAdicional) {
                        this.presupuestoAdicional = this.presupuestoAdicional - totalARecuperar;
                    } else if (totalARecuperar == this.presupuestoAdicional) {
                        this.presupuestoAdicional = 0.0;
                    } else {
                        double p = totalARecuperar - this.presupuestoAdicional;
                        this.presupuestoAdicional = 0.0;
                        this.presupuestoActual += p;
                    }
                } else {
                    this.presupuestoActual += totalARecuperar;
                }

                this.establecerPresupuesto();
                JOptionPane.showMessageDialog(this,
                        "Se han eliminado las mejoras correctamente.",
                        "Mejoras aplicadas eliminadas",
                        JOptionPane.INFORMATION_MESSAGE, INFORMATION_IMAGE);
            }
        }

        boolean actualizadaEA = false;
        boolean igualEA = false;
        if (!mejoraEAEliminada
                && this.cantidadEstabilizacionAfirmadosTextField != null
                && !this.cantidadEstabilizacionAfirmadosTextField.getText().trim().isEmpty()) {
            if (!Util.isNumerico(this.cantidadEstabilizacionAfirmadosTextField.getText())) {
                JOptionPane.showMessageDialog(this,
                        "Por favor verique que las cantidades ingresadas sean numéricas.",
                        "Cantidades no válidas",
                        JOptionPane.WARNING_MESSAGE, WARNING_IMAGE);

                return;
            }

            double cantidad = Double.parseDouble(
                    this.cantidadEstabilizacionAfirmadosTextField.getText().trim());
            if (mejoraEA != null) {
                if (mejoraEA.getCantidad() == cantidad) {
                    igualEA = true;
                } else {
                    this.alternativasMejorasEA.remove(mejoraEA);
                    double cantidadTemp = cantidad;
                    cantidad = cantidadTemp - mejoraEA.getCantidad();
                    mejoraEA.setCantidad(cantidadTemp);
                    double precioMejora = Math.abs(cantidad * this.valorUnitarioMejoraEA);
                    if (cantidad < 0) {
                        if (this.presupuestoAdicional > 0) {
                            double t = this.presupuestoAdicional - precioMejora;
                            if (t > 0) {
                                this.presupuestoAdicional = t;
                            } else if (t == 0) {
                                this.presupuestoAdicional = 0;
                            } else {
                                this.presupuestoAdicional = 0;
                                this.presupuestoActual += Math.abs(t);
                            }
                        } else {
                            this.presupuestoActual += precioMejora;
                        }
                    } else {
                        if (this.presupuestoAdicional > 0) {
                            this.presupuestoAdicional += precioMejora;
                        } else {
                            double t = this.presupuestoActual - precioMejora;
                            if (t > 0) {
                                this.presupuestoActual = t;
                            } else if (t == 0) {
                                this.presupuestoActual = 0;
                            } else {
                                this.presupuestoActual = 0;
                                this.presupuestoAdicional = Math.abs(t);
                            }
                        }
                    }
                    this.alternativasMejorasEA.add(mejoraEA);
                    this.agregarAlternativaResumenMejoras(mejoraEA);

                    this.establecerPresupuesto();
                    actualizadaEA = true;
                }
            }

            if (!actualizadaEA && !mejoraEAEliminada && !igualEA) {
                if (this.presupuestoAdicional > 0) {
                    this.presupuestoAdicional += cantidad * this.valorUnitarioMejoraEA;
                } else {
                    double t = this.presupuestoActual - cantidad * this.valorUnitarioMejoraEA;
                    if (t >= 0) {
                        this.presupuestoActual = t;
                    } else {
                        this.presupuestoActual = 0;
                        this.presupuestoAdicional = Math.abs(t);
                    }
                }

                this.establecerPresupuesto();

                Alternativa alternativa = new Alternativa();
                alternativa.setCodigoVia(this.via);
                alternativa.setItem(this.itemSeleccionadoMejoraEA.getCodigo());
                alternativa.setCantidad(cantidad);
                this.alternativasMejorasEA.add(alternativa);

                this.agregarAlternativaResumenMejoras(alternativa);
            }
        }

        boolean actualizadaTSR = false;
        boolean igualTSR = false;
        if (!mejoraTSREliminada
                && this.cantidadTratamientosSuperficialesRiegosTextField != null
                && !this.cantidadTratamientosSuperficialesRiegosTextField.getText().trim().isEmpty()) {
            if (!Util.isNumerico(this.cantidadTratamientosSuperficialesRiegosTextField.getText())) {
                JOptionPane.showMessageDialog(this,
                        "Por favor verique que las cantidades ingresadas sean numéricas.",
                        "Cantidades no válidas",
                        JOptionPane.WARNING_MESSAGE, WARNING_IMAGE);

                return;
            }
            double cantidad = Double.parseDouble(
                    this.cantidadTratamientosSuperficialesRiegosTextField.getText().trim());
            if (mejoraTSR != null) {
                if (mejoraTSR.getCantidad() == cantidad) {
                    igualTSR = true;
                } else {
                    this.alternativasMejorasTSR.remove(mejoraTSR);
                    double cantidadTemp = cantidad;
                    cantidad = cantidadTemp - mejoraTSR.getCantidad();
                    mejoraTSR.setCantidad(cantidadTemp);
                    double precioMejora = Math.abs(cantidad * this.valorUnitarioMejoraTSR);
                    if (cantidad < 0) {
                        if (this.presupuestoAdicional > 0) {
                            double t = this.presupuestoAdicional - precioMejora;
                            if (t > 0) {
                                this.presupuestoAdicional = t;
                            } else if (t == 0) {
                                this.presupuestoAdicional = 0;
                            } else {
                                this.presupuestoAdicional = 0;
                                this.presupuestoActual += Math.abs(t);
                            }
                        } else {
                            this.presupuestoActual += precioMejora;
                        }
                    } else {
                        if (this.presupuestoAdicional > 0) {
                            this.presupuestoAdicional += precioMejora;
                        } else {
                            double t = this.presupuestoActual - precioMejora;
                            if (t > 0) {
                                this.presupuestoActual = t;
                            } else if (t == 0) {
                                this.presupuestoActual = 0;
                            } else {
                                this.presupuestoActual = 0;
                                this.presupuestoAdicional = Math.abs(t);
                            }
                        }
                    }
                    this.alternativasMejorasTSR.add(mejoraTSR);

                    this.establecerPresupuesto();
                    actualizadaTSR = true;

                    this.agregarAlternativaResumenMejoras(mejoraTSR);
                }
            }

            if (!actualizadaTSR && !mejoraTSREliminada && !igualTSR) {
                if (this.presupuestoAdicional > 0) {
                    this.presupuestoAdicional += cantidad * this.valorUnitarioMejoraTSR;
                } else {
                    double t = this.presupuestoActual - cantidad * this.valorUnitarioMejoraTSR;
                    if (t >= 0) {
                        this.presupuestoActual = t;
                    } else {
                        this.presupuestoActual = 0;
                        this.presupuestoAdicional = Math.abs(t);
                    }
                }

                this.establecerPresupuesto();

                Alternativa alternativa = new Alternativa();
                alternativa.setCodigoVia(this.via);
                alternativa.setItem(this.itemSeleccionadoMejoraTSR.getCodigo());
                alternativa.setCantidad(cantidad);
                this.alternativasMejorasTSR.add(alternativa);

                this.agregarAlternativaResumenMejoras(alternativa);
            }
        }
    }//GEN-LAST:event_aplicarMejorasButtonActionPerformed

    private void unidadMedidaMejoraTSRTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unidadMedidaMejoraTSRTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unidadMedidaMejoraTSRTextFieldActionPerformed

    private void unidadMedidaMejoraEATextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unidadMedidaMejoraEATextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unidadMedidaMejoraEATextFieldActionPerformed

    private void tratamientosSuperficialesRiegosComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tratamientosSuperficialesRiegosComboBoxActionPerformed
        int posicionItemSeleccionado = this.tratamientosSuperficialesRiegosComboBox.getSelectedIndex();
        if (posicionItemSeleccionado <= 0) {
            this.itemSeleccionadoMejoraTSR = null;
            this.cantidadTratamientosSuperficialesRiegosTextField.setEditable(false);
            this.cantidadTratamientosSuperficialesRiegosTextField.setText("");
            this.precioMejoraTSRTextField.setText("");
            this.unidadMedidaMejoraTSRTextField.setText("");

            if (this.itemSeleccionadoMejoraTSR == null
                    && this.itemSeleccionadoMejoraEA == null) {
                this.aplicarMejorasButton.setEnabled(false);
            }
        } else {
            this.aplicarMejorasButton.setEnabled(true);
            this.cantidadTratamientosSuperficialesRiegosTextField.setEditable(true);
            this.itemSeleccionadoMejoraTSR = (Item) this.tratamientosSuperficialesRiegosComboBox.
                    getSelectedItem();
            this.valorUnitarioMejoraTSR = this.buscarPrecioDadoCodigoItem(
                    this.itemSeleccionadoMejoraTSR.getCodigo());
            this.unidadMejoraTSR = this.buscarUnidadDadoCodigoItem(
                    this.itemSeleccionadoMejoraTSR.getCodigo());
            this.precioMejoraTSRTextField.setText("$ "
                    + String.valueOf(this.itemSeleccionadoMejoraTSR.getValorUnitario()));
            this.unidadMedidaMejoraTSRTextField.setText(this.unidadMejoraTSR);

            Alternativa mejora = this.buscarMejoraTSRAplicadaDadoViaMejora(via,
                    this.itemSeleccionadoMejoraTSR.getCodigo());

            if (mejora != null) {
                this.cantidadTratamientosSuperficialesRiegosTextField.setText(
                        String.valueOf(mejora.getCantidad()).replace(".0", ""));
            } else {
                this.cantidadTratamientosSuperficialesRiegosTextField.setText("");
            }
        }
    }//GEN-LAST:event_tratamientosSuperficialesRiegosComboBoxActionPerformed

    private void precioMantenimientoMejoraTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precioMantenimientoMejoraTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precioMantenimientoMejoraTextFieldActionPerformed

    private void estabilizacionAfirmadosComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estabilizacionAfirmadosComboBoxActionPerformed
        int posicionItemSeleccionado = this.estabilizacionAfirmadosComboBox.getSelectedIndex();
        if (posicionItemSeleccionado <= 0) {
            this.itemSeleccionadoMejoraEA = null;
            this.cantidadEstabilizacionAfirmadosTextField.setEditable(false);
            this.cantidadEstabilizacionAfirmadosTextField.setText("");
            this.precioMejoraEATextField.setText("");
            this.unidadMedidaMejoraEATextField.setText("");

            if (this.itemSeleccionadoMejoraTSR == null
                    && this.itemSeleccionadoMejoraEA == null) {
                this.aplicarMejorasButton.setEnabled(false);
            }
        } else {
            this.aplicarMejorasButton.setEnabled(true);
            this.cantidadEstabilizacionAfirmadosTextField.setEditable(true);
            this.itemSeleccionadoMejoraEA = (Item) this.estabilizacionAfirmadosComboBox.
                    getSelectedItem();
            this.valorUnitarioMejoraEA = this.buscarPrecioDadoCodigoItem(
                    this.itemSeleccionadoMejoraEA.getCodigo());
            this.unidadMejoraEA = this.buscarUnidadDadoCodigoItem(
                    this.itemSeleccionadoMejoraEA.getCodigo());
            this.precioMejoraEATextField.setText("$ "
                    + String.valueOf(this.itemSeleccionadoMejoraEA.getValorUnitario()));
            this.unidadMedidaMejoraEATextField.setText(this.unidadMejoraEA);

            Alternativa mejora = this.buscarMejoraEAAplicadaDadoViaMejora(
                    this.via, this.itemSeleccionadoMejoraEA.getCodigo());

            if (mejora != null) {
                this.cantidadEstabilizacionAfirmadosTextField.setText(
                        String.valueOf(mejora.getCantidad()).replace(".0", ""));
            } else {
                this.cantidadEstabilizacionAfirmadosTextField.setText("");
            }
        }
    }//GEN-LAST:event_estabilizacionAfirmadosComboBoxActionPerformed

    private void txtMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMenuItemActionPerformed
        this.estructurarInformacionTXT();
    }//GEN-LAST:event_txtMenuItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abrirAlternativasIntervencionMenuItem;
    private javax.swing.JButton aplicarMantenimientoButton;
    private javax.swing.JButton aplicarMejorasButton;
    private javax.swing.JMenu archivoMenu;
    private javax.swing.JLabel cantidadEstabilizacionAfirmadosLabel;
    private javax.swing.JTextField cantidadEstabilizacionAfirmadosTextField;
    private javax.swing.JTextField cantidadMantenimientoTextField;
    private javax.swing.JLabel cantidadMantenimientosLabel;
    private javax.swing.JLabel cantidadTratamientosSuperficialesRiegosLabel;
    private javax.swing.JTextField cantidadTratamientosSuperficialesRiegosTextField;
    private javax.swing.JComboBox codigosViasComboBox;
    private javax.swing.JLabel costoUnitarioMantenimientoLabel;
    private javax.swing.JLabel costoUnitarioMejoraTSRLabel;
    private javax.swing.JLabel costoUnitarioMejoraTSRLabel1;
    private javax.swing.JLabel daniosAsociadosLabel;
    private javax.swing.JComboBox daniosComboBox;
    private javax.swing.JMenuItem docxMenuItem;
    private javax.swing.JComboBox estabilizacionAfirmadosComboBox;
    private javax.swing.JLabel estabilizacionAfirmadosLabel;
    private javax.swing.JMenu exportarMenu;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JComboBox mantenimientosComboBox;
    private javax.swing.JLabel mantenimientosRecomendadosLabel;
    private javax.swing.JLabel porcentajeAdministracionLabel;
    private javax.swing.JTextField porcentajeAdministracionTextField;
    private javax.swing.JLabel porcentajeImprevistosLabel;
    private javax.swing.JTextField porcentajeImprevistosTextField;
    private javax.swing.JLabel porcentajeUtilidadesLabel;
    private javax.swing.JTextField porcentajeUtilidadesTextField;
    private javax.swing.JTextField precioMantenimientoMejoraTextField;
    private javax.swing.JTextField precioMejoraEATextField;
    private javax.swing.JTextField precioMejoraTSRTextField;
    private javax.swing.JLabel presupuestoActualLabel;
    private javax.swing.JTextField presupuestoActualTextField;
    private javax.swing.JLabel presupuestoAdicionalLabel;
    private javax.swing.JTextField presupuestoAdicionalTextField;
    private javax.swing.JLabel presupuestoDisponibleLabel;
    private javax.swing.JTextField presupuestoDisponibleTextField;
    private javax.swing.JTextField presupuestoTotalInicialTextField;
    private javax.swing.JLabel presupuestoTotlaInicialLabel;
    private javax.swing.JTextArea resumenTextArea;
    private javax.swing.JComboBox tratamientosSuperficialesRiegosComboBox;
    private javax.swing.JLabel tratamientosSuperficialesRiegosLabel;
    private javax.swing.JMenuItem txtMenuItem;
    private javax.swing.JLabel unidadMedidaMantenimientoLabel;
    private javax.swing.JTextField unidadMedidaMantenimientoTextField;
    private javax.swing.JTextField unidadMedidaMejoraEATextField;
    private javax.swing.JLabel unidadMedidaMejoraTRSLabel;
    private javax.swing.JLabel unidadMedidaMejoraTRSLabel1;
    private javax.swing.JTextField unidadMedidaMejoraTSRTextField;
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
        this.presupuestoActualTextField.setText("$ "
                + presupuesto.getPresupuestoDisponible());

        this.presupuestoActual = presupuesto.getPresupuestoDisponible();
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

        List<String> daniosVia = new ArrayList<>(danios);

        return daniosVia;
    }

    private void restringirValoresCampos() {
        this.cantidadMantenimientoTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE) || c == KeyEvent.VK_PERIOD)) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });

        this.cantidadTratamientosSuperficialesRiegosTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE) || c == KeyEvent.VK_PERIOD)) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });

        this.cantidadEstabilizacionAfirmadosTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE) || c == KeyEvent.VK_PERIOD)) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
    }

    private double buscarPrecioDadoCodigoItem(String codigoItem) {
        double precio = 0.0;
        for (Item i : this.mantPriorViasInfo.getItems()) {
            if (i.getCodigo().equals(codigoItem)) {
                precio = i.getValorUnitario();
            }
        }

        return precio;
    }

    private String buscarUnidadDadoCodigoItem(String codigoItem) {
        String u = "";
        for (Item i : this.mantPriorViasInfo.getItems()) {
            if (i.getCodigo().equals(codigoItem)) {
                u = i.getUnidad();
            }
        }

        return u;
    }

    private Alternativa buscarMantenimientoAplicadoDadoViaItemMantenimiento(String via,
            String danio, String item) {
        Alternativa alternativa = null;
        for (Alternativa a : this.alternativasMantenimiento) {
            if (via.equals(a.getCodigoVia()) && danio.equals(a.getDanio())
                    && item.equals(a.getItem())) {
                return a;
            }
        }

        return alternativa;
    }

    private void establecerColorPresupuestoActual() {
        double porcentaje = this.presupuestoActual
                / this.mantPriorViasInfo.getPresupuesto().getPresupuestoDisponible();
        if (porcentaje > 0.6) {
            // Verde
            this.presupuestoActualTextField.setBackground(Color.green);
        } else if (porcentaje > 0.2 && porcentaje <= 0.6) {
            // Naranja
            this.presupuestoActualTextField.setBackground(Color.orange);
        } else {
            // Rojo
            this.presupuestoActualTextField.setBackground(Color.red);
        }
    }

    private void establecerPresupuesto() {
        this.establecerColorPresupuestoActual();
        this.presupuestoActualTextField.setText("$ " + this.presupuestoActual);
        this.presupuestoAdicionalTextField.setText("$ " + this.presupuestoAdicional);
    }

    private void agregarAlternativaResumenMantenimiento(Alternativa alternativa) {
        int posicion = this.contieneViaMantenimiento(alternativa.getCodigoVia());
        if (posicion < 0) {
            ResumenMantenimiento r = new ResumenMantenimiento();
            r.setVia(alternativa.getCodigoVia());
            r.getDaniosPorVia().add(alternativa.getDanio());
            List<Alternativa> a = new ArrayList<>();
            a.add(alternativa);
            r.getAlternativasPorDanio().add(a);
            this.viasResumenMantenimiento.add(r);
        } else {
            ResumenMantenimiento r = this.viasResumenMantenimiento.get(posicion);
            if (!r.getDaniosPorVia().contains(alternativa.getDanio())) {
                r.getDaniosPorVia().add(alternativa.getDanio());
                List<Alternativa> a = new ArrayList<>();
                a.add(alternativa);
                r.getAlternativasPorDanio().add(a);
            } else {
                int pd = r.getDaniosPorVia().indexOf(alternativa.getDanio());
                List<Alternativa> alts = r.getAlternativasPorDanio().get(pd);
                int posicionAlternativa = this.contieneAlternativa(alts, alternativa);
                if (posicionAlternativa < 0) {
                    alts.add(alternativa);
                } else {
                    alts.set(posicionAlternativa, alternativa);
                }
            }
        }

        this.mostrarResumen();
    }

    private void eliminarAlternativaResumenMantenimiento(Alternativa alternativa) {
        int posVia = this.contieneViaMantenimiento(alternativa.getCodigoVia());
        ResumenMantenimiento r = this.viasResumenMantenimiento.get(posVia);
        int pd = r.getDaniosPorVia().indexOf(alternativa.getDanio());
        int pa = r.getAlternativasPorDanio().get(pd).indexOf(alternativa);
        if (r.getAlternativasPorDanio().get(pd).size() == 1) {
            r.getAlternativasPorDanio().remove(pa);
            r.getDaniosPorVia().remove(pd);
            if (0 == r.getDaniosPorVia().size()) {
                this.viasResumenMantenimiento.remove(posVia);
            }
        } else {
            r.getAlternativasPorDanio().get(pd).remove(pa);
        }

        this.mostrarResumen();
    }

    private int contieneAlternativa(List<Alternativa> alternativas, Alternativa alternativa) {
        for (int i = 0; i < alternativas.size(); i++) {
            Alternativa a = alternativas.get(i);
            if (a.getCodigoVia().equals(alternativa.getCodigoVia())
                    && a.getDanio().equals(alternativa.getDanio())
                    && a.getItem().equals(alternativa.getItem())) {
                return i;
            }
        }

        return -1;
    }

    private int contieneViaMantenimiento(String via) {
        for (int i = 0; i < this.viasResumenMantenimiento.size(); i++) {
            if (this.viasResumenMantenimiento.get(i).getVia().equals(via)) {

                return i;
            }
        }

        return -1;
    }

    private void mostrarResumen() {
        String separadorLinea = System.getProperty("line.separator");
        this.resumen = "RESUMEN" + separadorLinea + "-----------------------"
                + separadorLinea + separadorLinea;
        List<String> viasImpresas = new ArrayList<>();
        for (int i = 0; i < this.viasResumenMantenimiento.size(); i++) {
            String v = this.viasResumenMantenimiento.get(i).getVia();
            this.resumen += v;
            this.resumen += separadorLinea;
            for (int j = 0; j < this.viasResumenMantenimiento.get(i).
                    getDaniosPorVia().size(); j++) {
                this.resumen += "        * " + this.viasResumenMantenimiento.get(i).getDaniosPorVia().get(j);
                this.resumen += separadorLinea;
                List<Alternativa> alternativas = this.viasResumenMantenimiento.get(i)
                        .getAlternativasPorDanio().get(j);
                for (int k = 0; k < alternativas.size(); k++) {
                    this.resumen += "                - " + "Mantenimiento: " + alternativas.get(k).getItem()
                            + separadorLinea;
                    this.resumen += "                 Cantidad: "
                            + alternativas.get(k).getCantidad() + separadorLinea;
                    this.resumen += "         Costo : $ " + (alternativas.get(k).getCantidad()
                            * this.buscarPrecioDadoCodigoItem(alternativas.get(k).getItem()));
                    this.resumen += separadorLinea + separadorLinea;
                }
            }

            int posicion = this.contieneViaMejora(v);
            if (posicion >= 0) {
                List<Alternativa> alternativas = this.viasResumenMejora.
                        get(posicion).getAlternativas();
                for (int j = 0; j < alternativas.size(); j++) {
                    viasImpresas.add(v);
                    this.resumen += "        * Mejoramiento: " + alternativas.get(j).getItem() + separadorLinea;
                    this.resumen += "          Cantidad: " + alternativas.get(j).getCantidad() + separadorLinea;
                    this.resumen += "          Costo: $ " + (alternativas.get(j).getCantidad()
                            * this.buscarPrecioDadoCodigoItem(alternativas.get(j).getItem()));
                    this.resumen += separadorLinea + separadorLinea;
                }
            }

            this.resumen += separadorLinea + separadorLinea;
        }

        for (int i = 0; i < this.viasResumenMejora.size(); i++) {
            if (!viasImpresas.contains(this.viasResumenMejora.get(i).getVia())) {
                this.resumen += this.viasResumenMejora.get(i).getVia();
                this.resumen += separadorLinea;
                List<Alternativa> alternativas = this.viasResumenMejora.get(i).getAlternativas();
                for (int j = 0; j < alternativas.size(); j++) {
                    this.resumen += "        * Mejoramiento: " + alternativas.get(j).getItem() + separadorLinea;
                    this.resumen += "          Cantidad: " + alternativas.get(j).getCantidad() + separadorLinea;
                    this.resumen += "          Costo: $ " + (alternativas.get(j).getCantidad()
                            * this.buscarPrecioDadoCodigoItem(alternativas.get(j).getItem())) + separadorLinea;
                    this.resumen += separadorLinea;
                }
                this.resumen += separadorLinea + separadorLinea;
            }
        }

        this.resumenTextArea.setText(this.resumen);
    }

    private void setDaniosAsociados() {
        this.via = (String) this.codigosViasComboBox.getSelectedItem();
        if (this.codigosViasComboBox.getSelectedIndex() > 0) {
            this.tieneMejoras = this.contieneViaMejora(this.via) >= 0;
        }
        this.mantenimientosComboBox.removeAllItems();
        this.mantenimientosComboBox.addItem(this.itemVacio);
        this.mantenimientosComboBox.setRenderer(this.comboRenderer);

        List<String> danios = this.obtenerDaniosDadoUnaVia(this.via);
        this.eliminarDanios(danios);

        this.daniosComboBox.removeAllItems();
        this.listaDaniosComboBox.clear();

        this.daniosComboBox.addItem("Seleccione");
        this.listaDaniosComboBox.add("Seleccione");
        danios.stream().forEach(s -> {
            this.daniosComboBox.addItem(Constantes.NOMBRES_DANIOS.get(
                    s.substring(0, s.length() - 1)) + " - " + s.substring(2));
            this.listaDaniosComboBox.add(s);
        });
    }

    private void setMejoras() {
        for (int i = Constantes.TSR_INICIO; i <= Constantes.TSR_FINAL; i++) {
            for (int j = 0; j < this.mantPriorViasInfo.getItems().size(); j++) {
                if (("ME" + i).equals(this.mantPriorViasInfo.getItems().get(j).getCodigo())) {
                    this.tratamientosSuperficialesRiegos.add(
                            this.mantPriorViasInfo.getItems().get(j));
                }
            }
        }

        for (int i = Constantes.EA_INICIO; i <= Constantes.EA_FINAL; i++) {
            for (int j = 0; j < this.mantPriorViasInfo.getItems().size(); j++) {
                if (("ME" + i).equals(this.mantPriorViasInfo.getItems().get(j).getCodigo())) {
                    this.estabilizacionAfirmados.add(
                            this.mantPriorViasInfo.getItems().get(j));
                }
            }
        }
    }

    private Alternativa buscarMejoraEAAplicadaDadoViaMejora(String via, String item) {
        Alternativa alternativa = null;
        for (Alternativa a : this.alternativasMejorasEA) {
            if (via.equals(a.getCodigoVia()) && item.equals(a.getItem())) {
                return a;
            }
        }

        return alternativa;
    }

    private Alternativa buscarMejoraTSRAplicadaDadoViaMejora(String via, String item) {
        Alternativa alternativa = null;
        for (Alternativa a : this.alternativasMejorasTSR) {
            if (via.equals(a.getCodigoVia()) && item.equals(a.getItem())) {
                return a;
            }
        }

        return alternativa;
    }

    private void agregarAlternativaResumenMejoras(Alternativa mejora) {
        int posicion = this.contieneViaMejora(mejora.getCodigoVia());
        if (posicion < 0) {
            ResumenMejora resumenMejora = new ResumenMejora();
            resumenMejora.setVia(mejora.getCodigoVia());
            List<Alternativa> alternativas = new ArrayList<>();
            alternativas.add(mejora);
            resumenMejora.setAlternativas(alternativas);
            this.viasResumenMejora.add(resumenMejora);
            this.eliminarMantenimientosAsociados(mejora.getCodigoVia());
            this.tieneMejoras = true;
            this.setDaniosAsociados();
        } else {
            ResumenMejora rm = this.viasResumenMejora.get(posicion);
            int pos = this.contieneMejora(rm.getAlternativas(), mejora.getItem());
            if (pos < 0) {
                rm.getAlternativas().add(mejora);
            } else {
                rm.getAlternativas().set(pos, mejora);
            }
        }

        this.mostrarResumen();
    }

    private void eliminarAlternativaResumenMejoras(Alternativa mejora) {
        int posicion = this.contieneViaMejora(mejora.getCodigoVia());
        this.viasResumenMejora.get(posicion).getAlternativas().remove(mejora);
        if (this.viasResumenMejora.get(posicion).getAlternativas().isEmpty()) {
            this.viasResumenMejora.remove(posicion);
            this.tieneMejoras = false;
            this.setDaniosAsociados();
        }

        this.mostrarResumen();
    }

    private int contieneViaMejora(String codigoVia) {
        for (int i = 0; i < this.viasResumenMejora.size(); i++) {
            if (this.viasResumenMejora.get(i).getVia().equals(codigoVia)) {

                return i;
            }
        }

        return -1;
    }

    private int contieneMejora(List<Alternativa> alternativas, String alternativa) {
        for (int i = 0; i < alternativas.size(); i++) {
            if (alternativas.get(i).getItem().equals(alternativa)) {

                return i;
            }
        }

        return -1;
    }

    private void cargarMantenimientos() {
        this.posicionDanio = this.daniosComboBox.getSelectedIndex();
        if (this.listaDaniosComboBox != null && !this.listaDaniosComboBox.isEmpty()
                && this.posicionDanio > 0) {
            String danioSeleccionado = this.listaDaniosComboBox.get(this.posicionDanio);
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
                this.mantenimientosComboBox.setRenderer(this.comboRenderer);
            } else {
                this.mantenimientosComboBox.removeAllItems();
                this.mantenimientosComboBox.addItem(this.itemVacio);
            }
        }
    }

    private void eliminarDanios(List<String> danios) {
        if (danios.contains("89N")) {
            danios.remove("89N");
        }

        // Se eliminan los daños 82, 83, 90 y 91
        if (!danios.isEmpty()) {
            if (this.tieneMejoras) {
                List<String> daniosABorrar = new ArrayList<>();
                danios.stream().filter((danio) -> (!Constantes.DANIOS_PERMANECEN_POR_MEJORAS.contains(
                        danio.substring(0, danio.length() - 1)))).forEach((danio) -> {
                            daniosABorrar.add(danio);
                        });

                if (!daniosABorrar.isEmpty()) {
                    daniosABorrar.stream().forEach((daniosABorrar1) -> {
                        danios.remove(daniosABorrar1);
                    });
                }
            }
        }
    }

    private void eliminarMantenimientosAsociados(String codigoVia) {
        int posicion = this.contieneViaMantenimiento(codigoVia);
        if (posicion >= 0) {
            double dineroRecuperar = 0.0;
            ResumenMantenimiento rm = this.viasResumenMantenimiento.get(posicion);
            List<String> danios = rm.getDaniosPorVia();
            for (int i = 0; i < danios.size(); i++) {
                String danio = danios.get(i);
                if (!Constantes.DANIOS_PERMANECEN_POR_MEJORAS.contains(danio.
                        substring(0, danio.length() - 1))) {
                    List<Alternativa> alternativas = rm.getAlternativasPorDanio().get(i);
                    dineroRecuperar = alternativas.stream().map((a) -> a.getCantidad()
                            * this.buscarPrecioDadoCodigoItem(a.getItem())).reduce(dineroRecuperar, (accumulator, _item) -> accumulator + _item);
                    rm.getDaniosPorVia().remove(i);
                    rm.getAlternativasPorDanio().remove(i);
                }
            }

            if (dineroRecuperar > 0.0) {
                this.recuperarDinero(dineroRecuperar);
                this.establecerPresupuesto();
            }

            if (rm.getDaniosPorVia().isEmpty()) {
                this.viasResumenMantenimiento.remove(posicion);
            }
        }

    }

    private void recuperarDinero(double dinero) {
        if (this.presupuestoAdicional > 0.0) {
            double p = this.presupuestoAdicional - dinero;
            if (p >= 0.0) {
                this.presupuestoAdicional = p;
            } else {
                this.presupuestoAdicional = 0.0;
                this.presupuestoActual += Math.abs(p);
            }
        } else {
            this.presupuestoActual += dinero;
        }
    }

    private String estructurarInformacionTXT() {
        String separadorLinea = System.getProperty("line.separator");
        String texto = this.resumen + separadorLinea + separadorLinea;

        texto += "Daños" + separadorLinea;
        texto += "Código  Nombre" + separadorLinea;
        for (Map.Entry<String, String> entry : Constantes.NOMBRES_DANIOS.entrySet()) {
            String codigo = entry.getKey();
            String nombre = entry.getValue();
            texto += " " + codigo + "     " + nombre + separadorLinea;
        }
        texto += separadorLinea + separadorLinea;

        texto += "Mantenimientos y Mejoramientos" + separadorLinea;
        texto += "Código  Nombre                                                  "
                + "                                   " + "Precio ($)" + separadorLinea;
        for (Item i : this.mantPriorViasInfo.getItems()) {
            texto += " " + i.getCodigo();
            if(i.getCodigo().length() == 3){
                texto += "    ";
            } else {
                texto += "   ";
            }
            
            texto += i.getItem();
            int t = 91 - i.getItem().length();
            String s = "";
            for(int j = 0; j < t; j++){
                s += " ";
            }
            texto += s;
            
            texto += i.getValorUnitario() + separadorLinea;
        }
        texto += separadorLinea + separadorLinea;

        return texto;
    }
}
