package co.edu.udea.mantpriorivias.gui;

import co.edu.udea.mantpriorivias.archivos.ArchivoExcel;
import co.edu.udea.mantpriorivias.archivos.ArchivoTextoPlano;
import co.edu.udea.mantpriorivias.general.Constantes;
import co.edu.udea.mantpriorivias.negocio.entidades.Alternativa;
import co.edu.udea.mantpriorivias.dto.InfoVia;
import co.edu.udea.mantpriorivias.negocio.entidades.Item;
import co.edu.udea.mantpriorivias.dto.MantPriorViasInfo;
import co.edu.udea.mantpriorivias.negocio.entidades.Presupuesto;
import co.edu.udea.mantpriorivias.dto.Progreso;
import co.edu.udea.mantpriorivias.dto.ResumenMantenimiento;
import co.edu.udea.mantpriorivias.dto.ResumenMejora;
import co.edu.udea.mantpriorivias.negocio.entidades.Unidad;
import co.edu.udea.mantpriorivias.general.Util;
import co.edu.udea.mantpriorivias.general.progreso.UtilProgreso;
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
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

public class PriorizacionIntervencionesJDialog extends javax.swing.JDialog {

    private final MantPriorViasInfo mantPriorViasInfo;
    private ArchivoExcel archivoExcel = new ArchivoExcel();
    private List<String> listaDaniosComboBox = new ArrayList<>();
    private List<Alternativa> alternativasMantenimiento;
    private List<Alternativa> alternativasMejorasTSR;
    private List<Alternativa> alternativasMejorasEA;
    private List<ResumenMantenimiento> viasResumenMantenimiento;
    private List<ResumenMejora> viasResumenMejora;
    private List<Item> tratamientosSuperficialesRiegos = new ArrayList<>();
    private List<Item> estabilizacionAfirmados = new ArrayList<>();
    private ArchivoTextoPlano creadorArchivoTextoPlano = new ArchivoTextoPlano();
    private static final JFileChooser FILE_CHOOSER_EXPORTAR;
    private String resumen = null;
    private String via;
    private Unidad unidadMantenimiento;
    private Unidad unidadMejoraTSR;
    private Unidad unidadMejoraEA;
    private boolean tieneMejoras;
    private int posicionDanio;
    private double presupuestoActual;
    private double presupuestoAdicional;
    private double valorUnitarioMantenimiento;
    private double valorUnitarioMejoraEA;
    private double valorUnitarioMejoraTSR;
    private Item itemSeleccionadoMantenimiento;
    private Item itemSeleccionadoMejoraEA;
    private Item itemSeleccionadoMejoraTSR;
    private Item itemVacio = new Item("Seleccione", "");
    private static final ImageIcon ERROR_IMAGE = new ImageIcon(Inicio.class
            .getResource("/co/edu/udea/mantpriorivias/gui/imagenes/"
                    + "ic_dialog_error.png"));
    private static final ImageIcon WARNING_IMAGE = new ImageIcon(Inicio.class
            .getResource("/co/edu/udea/mantpriorivias/gui/imagenes/"
                    + "ic_dialog_warning.png"));
    private static final ImageIcon INFORMATION_IMAGE = new ImageIcon(Inicio.class
            .getResource("/co/edu/udea/mantpriorivias/gui/imagenes/"
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

    static {
        FILE_CHOOSER_EXPORTAR = new JFileChooser();
        FILE_CHOOSER_EXPORTAR.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        FILE_CHOOSER_EXPORTAR.setDialogTitle("Seleccionar directorio...");
    }

    /**
     * Creates new form PriorizacionMantenimientoJDialog
     *
     * @param parent
     * @param modal
     * @param mantPriorViasInfo
     * @param viasResumenMantenimiento
     * @param viasResumenMejora
     * @param alternativasMantenimiento
     * @param alternativasMejorasTSR
     * @param alternativasMejorasEA
     */
    public PriorizacionIntervencionesJDialog(java.awt.Frame parent, boolean modal,
            MantPriorViasInfo mantPriorViasInfo,
            List<ResumenMantenimiento> viasResumenMantenimiento,
            List<ResumenMejora> viasResumenMejora,
            List<Alternativa> alternativasMantenimiento,
            List<Alternativa> alternativasMejorasTSR,
            List<Alternativa> alternativasMejorasEA) {
        super(parent, modal);
        this.mantPriorViasInfo = mantPriorViasInfo;
        if (viasResumenMantenimiento == null) {
            this.viasResumenMantenimiento = new ArrayList<>();
        } else {
            this.viasResumenMantenimiento = viasResumenMantenimiento;
        }

        if (viasResumenMejora == null) {
            this.viasResumenMejora = new ArrayList<>();
        } else {
            this.viasResumenMejora = viasResumenMejora;
        }

        if (alternativasMantenimiento == null) {
            this.alternativasMantenimiento = new ArrayList<>();
        } else {
            this.alternativasMantenimiento = alternativasMantenimiento;
        }

        if (alternativasMejorasTSR == null) {
            this.alternativasMejorasTSR = new ArrayList<>();
        } else {
            this.alternativasMejorasTSR = alternativasMejorasTSR;
        }

        if (alternativasMejorasEA == null) {
            this.alternativasMejorasEA = new ArrayList<>();
        } else {
            this.alternativasMejorasEA = alternativasMejorasEA;
        }

        initComponents();

        this.setInformacionPresupuesto(this.mantPriorViasInfo.getPresupuesto());
        this.setCodigosVias(this.mantPriorViasInfo.getVias());

        this.setLocationRelativeTo(parent);
        this.setResizable(false);

        this.setTitle("Mantenimiento y Mejoramiento de Vías");

        this.restringirValoresCampos();
        this.establecerColorPresupuestoActual();

        this.setMejoras();
        this.tratamientosSuperficialesRiegosComboBox.setRenderer(this.comboRenderer);
        this.estabilizacionAfirmadosComboBox.setRenderer(this.comboRenderer);

        this.mostrarResumen();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tituloLabel = new javax.swing.JLabel();
        presupuestoTotlaInicialLabel = new javax.swing.JLabel();
        presupuestoTotalInicialTextField = new javax.swing.JTextField();
        porcentajeAdministracionLabel = new javax.swing.JLabel();
        porcentajeAdministracionTextField = new javax.swing.JTextField();
        porcentajeImprevistosLabel = new javax.swing.JLabel();
        porcentajeImprevistosTextField = new javax.swing.JTextField();
        porcentajeUtilidadesLabel = new javax.swing.JLabel();
        porcentajeUtilidadesTextField = new javax.swing.JTextField();
        presupuestoDisponibleLabel = new javax.swing.JLabel();
        presupuestoDisponibleTextField = new javax.swing.JTextField();
        presupuestoActualLabel = new javax.swing.JLabel();
        presupuestoActualTextField = new javax.swing.JTextField();
        presupuestoAdicionalLabel = new javax.swing.JLabel();
        presupuestoAdicionalTextField = new javax.swing.JTextField();
        viasLabel = new javax.swing.JLabel();
        codigosViasComboBox = new javax.swing.JComboBox();
        daniosAsociadosLabel = new javax.swing.JLabel();
        daniosComboBox = new javax.swing.JComboBox();
        tabbedPane = new javax.swing.JTabbedPane();
        mantenimientosPanel = new javax.swing.JPanel();
        mantenimientosRecomendadosLabel = new javax.swing.JLabel();
        mantenimientosComboBox = new javax.swing.JComboBox();
        costoUnitarioMantenimientoLabel = new javax.swing.JLabel();
        precioMantenimientoMejoraTextField = new javax.swing.JTextField();
        unidadMedidaMantenimientoLabel = new javax.swing.JLabel();
        unidadMedidaMantenimientoTextField = new javax.swing.JTextField();
        cantidadMantenimientosLabel = new javax.swing.JLabel();
        cantidadMantenimientoTextField = new javax.swing.JTextField();
        aplicarMantenimientoButton = new javax.swing.JButton();
        mejoramientosPanel = new javax.swing.JPanel();
        tratamientosSuperficialesRiegosLabel = new javax.swing.JLabel();
        tratamientosSuperficialesRiegosComboBox = new javax.swing.JComboBox();
        costoUnitarioMejoraTSRLabel = new javax.swing.JLabel();
        precioMejoraTSRTextField = new javax.swing.JTextField();
        unidadMedidaMejoraTRSLabel = new javax.swing.JLabel();
        unidadMedidaMejoraTSRTextField = new javax.swing.JTextField();
        cantidadTratamientosSuperficialesRiegosLabel = new javax.swing.JLabel();
        cantidadTratamientosSuperficialesRiegosTextField = new javax.swing.JTextField();
        estabilizacionAfirmadosLabel = new javax.swing.JLabel();
        estabilizacionAfirmadosComboBox = new javax.swing.JComboBox();
        costoUnitarioMejoraEALabel = new javax.swing.JLabel();
        precioMejoraEATextField = new javax.swing.JTextField();
        unidadMedidaMejoraEALabel = new javax.swing.JLabel();
        unidadMedidaMejoraEATextField = new javax.swing.JTextField();
        cantidadEstabilizacionAfirmadosLabel = new javax.swing.JLabel();
        cantidadEstabilizacionAfirmadosTextField = new javax.swing.JTextField();
        aplicarMejorasButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        scrollPaneResumenTextArea = new javax.swing.JScrollPane();
        resumenTextArea = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        archivoMenu = new javax.swing.JMenu();
        abrirAlternativasIntervencionMenuItem = new javax.swing.JMenuItem();
        exportarResumenMenu = new javax.swing.JMenu();
        txtMenuItem = new javax.swing.JMenuItem();
        guardarProgresoMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tituloLabel.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        tituloLabel.setText("Priorización y Mantenimiento de Vías");

        presupuestoTotlaInicialLabel.setText("Presupuesto Total Inicial");

        presupuestoTotalInicialTextField.setEditable(false);
        presupuestoTotalInicialTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        porcentajeAdministracionLabel.setText("% Administración");

        porcentajeAdministracionTextField.setEditable(false);
        porcentajeAdministracionTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        porcentajeImprevistosLabel.setText("% Imprevistos");

        porcentajeImprevistosTextField.setEditable(false);
        porcentajeImprevistosTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        porcentajeUtilidadesLabel.setText("% Utilidades");

        porcentajeUtilidadesTextField.setEditable(false);
        porcentajeUtilidadesTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        presupuestoDisponibleLabel.setText("Presupuesto Disponible");

        presupuestoDisponibleTextField.setEditable(false);
        presupuestoDisponibleTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        presupuestoActualLabel.setText("Presupuesto actual");

        presupuestoActualTextField.setEditable(false);
        presupuestoActualTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        presupuestoAdicionalLabel.setText("Presupuesto adicional");

        presupuestoAdicionalTextField.setEditable(false);
        presupuestoAdicionalTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        viasLabel.setText("Vías");

        codigosViasComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigosViasComboBoxActionPerformed(evt);
            }
        });

        daniosAsociadosLabel.setText("Daños asociados a la vía");

        daniosComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daniosComboBoxActionPerformed(evt);
            }
        });

        mantenimientosRecomendadosLabel.setText("Mantenimientos recomendados");

        mantenimientosComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mantenimientosComboBoxActionPerformed(evt);
            }
        });

        costoUnitarioMantenimientoLabel.setText("Costo unitario");

        precioMantenimientoMejoraTextField.setEditable(false);

        unidadMedidaMantenimientoLabel.setText("Unidad de medida");

        unidadMedidaMantenimientoTextField.setEditable(false);

        cantidadMantenimientosLabel.setText("Cantidad");

        cantidadMantenimientoTextField.setEditable(false);
        cantidadMantenimientoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadMantenimientoTextFieldActionPerformed(evt);
            }
        });

        aplicarMantenimientoButton.setText("Aplicar Mantenimiento");
        aplicarMantenimientoButton.setEnabled(false);
        aplicarMantenimientoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aplicarMantenimientoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mantenimientosPanelLayout = new javax.swing.GroupLayout(mantenimientosPanel);
        mantenimientosPanel.setLayout(mantenimientosPanelLayout);
        mantenimientosPanelLayout.setHorizontalGroup(
            mantenimientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mantenimientosPanelLayout.createSequentialGroup()
                .addGroup(mantenimientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mantenimientosPanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(mantenimientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(unidadMedidaMantenimientoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mantenimientosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(unidadMedidaMantenimientoLabel)
                            .addComponent(mantenimientosRecomendadosLabel))
                        .addGap(18, 18, 18)
                        .addGroup(mantenimientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cantidadMantenimientoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precioMantenimientoMejoraTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cantidadMantenimientosLabel)
                            .addComponent(costoUnitarioMantenimientoLabel)))
                    .addGroup(mantenimientosPanelLayout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(aplicarMantenimientoButton)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        mantenimientosPanelLayout.setVerticalGroup(
            mantenimientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mantenimientosPanelLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(mantenimientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mantenimientosRecomendadosLabel)
                    .addComponent(costoUnitarioMantenimientoLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mantenimientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mantenimientosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(precioMantenimientoMejoraTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(mantenimientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unidadMedidaMantenimientoLabel)
                    .addComponent(cantidadMantenimientosLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mantenimientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unidadMedidaMantenimientoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantidadMantenimientoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(aplicarMantenimientoButton)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Mantenimientos", mantenimientosPanel);

        tratamientosSuperficialesRiegosLabel.setText("Tratamientos Superficiales o Riegos");

        tratamientosSuperficialesRiegosComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tratamientosSuperficialesRiegosComboBoxActionPerformed(evt);
            }
        });

        costoUnitarioMejoraTSRLabel.setText("Costo unitario");

        precioMejoraTSRTextField.setEditable(false);

        unidadMedidaMejoraTRSLabel.setText("Unidad de medida");

        unidadMedidaMejoraTSRTextField.setEditable(false);

        cantidadTratamientosSuperficialesRiegosLabel.setText("Cantidad");

        cantidadTratamientosSuperficialesRiegosTextField.setEditable(false);

        estabilizacionAfirmadosLabel.setText("Estabilización de Afirmados");

        estabilizacionAfirmadosComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estabilizacionAfirmadosComboBoxActionPerformed(evt);
            }
        });

        costoUnitarioMejoraEALabel.setText("Costo unitario");

        precioMejoraEATextField.setEditable(false);

        unidadMedidaMejoraEALabel.setText("Unidad de medida");

        unidadMedidaMejoraEATextField.setEditable(false);

        cantidadEstabilizacionAfirmadosLabel.setText("Cantidad");

        aplicarMejorasButton.setText("Aplicar Mejora(s)");
        aplicarMejorasButton.setEnabled(false);
        aplicarMejorasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aplicarMejorasButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mejoramientosPanelLayout = new javax.swing.GroupLayout(mejoramientosPanel);
        mejoramientosPanel.setLayout(mejoramientosPanelLayout);
        mejoramientosPanelLayout.setHorizontalGroup(
            mejoramientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mejoramientosPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(aplicarMejorasButton)
                .addGap(159, 159, 159))
            .addGroup(mejoramientosPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(mejoramientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mejoramientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(unidadMedidaMejoraEATextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(unidadMedidaMejoraTSRTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tratamientosSuperficialesRiegosLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tratamientosSuperficialesRiegosComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(unidadMedidaMejoraTRSLabel, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(estabilizacionAfirmadosLabel, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(estabilizacionAfirmadosComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(unidadMedidaMejoraEALabel))
                .addGap(18, 35, Short.MAX_VALUE)
                .addGroup(mejoramientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cantidadEstabilizacionAfirmadosLabel)
                    .addComponent(costoUnitarioMejoraEALabel)
                    .addComponent(cantidadTratamientosSuperficialesRiegosLabel)
                    .addComponent(costoUnitarioMejoraTSRLabel)
                    .addComponent(precioMejoraTSRTextField)
                    .addComponent(cantidadTratamientosSuperficialesRiegosTextField)
                    .addComponent(precioMejoraEATextField)
                    .addComponent(cantidadEstabilizacionAfirmadosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
            .addComponent(jSeparator1)
        );
        mejoramientosPanelLayout.setVerticalGroup(
            mejoramientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mejoramientosPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mejoramientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mejoramientosPanelLayout.createSequentialGroup()
                        .addComponent(tratamientosSuperficialesRiegosLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tratamientosSuperficialesRiegosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(unidadMedidaMejoraTRSLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unidadMedidaMejoraTSRTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mejoramientosPanelLayout.createSequentialGroup()
                        .addComponent(costoUnitarioMejoraTSRLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(precioMejoraTSRTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cantidadTratamientosSuperficialesRiegosLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantidadTratamientosSuperficialesRiegosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mejoramientosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mejoramientosPanelLayout.createSequentialGroup()
                        .addComponent(estabilizacionAfirmadosLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estabilizacionAfirmadosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(unidadMedidaMejoraEALabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unidadMedidaMejoraEATextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mejoramientosPanelLayout.createSequentialGroup()
                        .addComponent(costoUnitarioMejoraEALabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(precioMejoraEATextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cantidadEstabilizacionAfirmadosLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantidadEstabilizacionAfirmadosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(aplicarMejorasButton)
                .addContainerGap())
        );

        tabbedPane.addTab("Mejoramientos", mejoramientosPanel);

        resumenTextArea.setEditable(false);
        resumenTextArea.setColumns(20);
        resumenTextArea.setRows(5);
        scrollPaneResumenTextArea.setViewportView(resumenTextArea);

        archivoMenu.setText("Archivo");
        archivoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archivoMenuActionPerformed(evt);
            }
        });

        abrirAlternativasIntervencionMenuItem.setText("Abrir alternativas intervención");
        abrirAlternativasIntervencionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirAlternativasIntervencionMenuItemActionPerformed(evt);
            }
        });
        archivoMenu.add(abrirAlternativasIntervencionMenuItem);

        exportarResumenMenu.setText("Exportar resumen");

        txtMenuItem.setText("TXT");
        txtMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMenuItemActionPerformed(evt);
            }
        });
        exportarResumenMenu.add(txtMenuItem);

        archivoMenu.add(exportarResumenMenu);

        guardarProgresoMenuItem.setText("Guardar progreso");
        guardarProgresoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarProgresoMenuItemActionPerformed(evt);
            }
        });
        archivoMenu.add(guardarProgresoMenuItem);

        menuBar.add(archivoMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viasLabel)
                    .addComponent(codigosViasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(daniosAsociadosLabel)
                    .addComponent(daniosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(scrollPaneResumenTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addComponent(tituloLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(presupuestoActualLabel)
                            .addComponent(presupuestoActualTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(presupuestoAdicionalLabel)
                            .addComponent(presupuestoAdicionalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(presupuestoTotlaInicialLabel)
                            .addComponent(presupuestoTotalInicialTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(porcentajeAdministracionLabel)
                            .addComponent(porcentajeAdministracionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(porcentajeImprevistosLabel)
                            .addComponent(porcentajeImprevistosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(porcentajeUtilidadesLabel)
                            .addComponent(porcentajeUtilidadesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(presupuestoDisponibleLabel)
                            .addComponent(presupuestoDisponibleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(presupuestoTotlaInicialLabel)
                    .addComponent(porcentajeAdministracionLabel)
                    .addComponent(porcentajeImprevistosLabel)
                    .addComponent(porcentajeUtilidadesLabel)
                    .addComponent(presupuestoDisponibleLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(presupuestoTotalInicialTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(porcentajeAdministracionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(porcentajeImprevistosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(porcentajeUtilidadesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(presupuestoDisponibleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(presupuestoActualLabel)
                    .addComponent(presupuestoAdicionalLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(presupuestoActualTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(presupuestoAdicionalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(viasLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(codigosViasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(daniosAsociadosLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(daniosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tabbedPane))
                    .addComponent(scrollPaneResumenTextArea))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void archivoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archivoMenuActionPerformed
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
            } else if (Desktop.isDesktopSupported()) {
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
        } catch (IOException ex) {
            Logger.getLogger(PriorizacionIntervencionesJDialog.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_archivoMenuActionPerformed

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
            this.valorUnitarioMantenimiento = Util.formatearValorOperacion(
                    this.buscarPrecioDadoCodigoItem(this.itemSeleccionadoMantenimiento.getCodigo()));
            this.unidadMantenimiento = this.buscarUnidadDadoCodigoItem(
                    this.itemSeleccionadoMantenimiento.getCodigo());
            this.precioMantenimientoMejoraTextField.setText("$ "
                    + String.valueOf(Util.formatearValorVista(this.valorUnitarioMantenimiento)));
            this.unidadMedidaMantenimientoTextField.setText(
                    this.unidadMantenimiento.getUnidad() + " - "
                    + this.unidadMantenimiento.getNombre());

            Alternativa mantenimiento = this.buscarMantenimientoAplicadoDadoViaItemMantenimiento(
                    this.via, this.listaDaniosComboBox.get(this.posicionDanio),
                    this.itemSeleccionadoMantenimiento.getCodigo());

            if (mantenimiento != null) {
                String valor = String.valueOf(Util.
                        formatearValorOperacion(mantenimiento.getCantidad())).replace(".", ",");
                if (valor.endsWith(",0")) {
                    valor = valor.replace(",0", "");
                }
                this.cantidadMantenimientoTextField.setText(valor);
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
                    double t = Util.formatearValorOperacion(mantenimiento.getCantidad()
                            * this.buscarPrecioDadoCodigoItem(mantenimiento.getItem()));
                    if (t < this.presupuestoAdicional) {
                        this.presupuestoAdicional = Util.
                                formatearValorOperacion(this.presupuestoAdicional - t);
                    } else if (t == this.presupuestoAdicional) {
                        this.presupuestoAdicional = 0.0;
                    } else {
                        double p = t - this.presupuestoAdicional;
                        this.presupuestoAdicional = 0.0;
                        this.presupuestoActual = Util.
                                formatearValorOperacion(this.presupuestoActual + p);
                    }
                } else {
                    this.presupuestoActual = Util.
                            formatearValorOperacion(this.presupuestoActual + (mantenimiento.getCantidad()
                                    * this.buscarPrecioDadoCodigoItem(mantenimiento.getItem())));
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

        if (this.cantidadMantenimientoTextField.getText().contains(".")
                || !Util.isNumerico(this.cantidadMantenimientoTextField.getText().
                        replace(",", "."))) {
            JOptionPane.showMessageDialog(this,
                    "El valor ingresado no es un número válido", "Valor no numérico",
                    JOptionPane.WARNING_MESSAGE, WARNING_IMAGE);

            return;
        }

        if (Util.isNumerico(this.cantidadMantenimientoTextField.getText())
                && Double.parseDouble(this.cantidadMantenimientoTextField.
                        getText().replace(",", ".")) <= 0) {
            JOptionPane.showMessageDialog(this,
                    "El valor ingresado debe ser mayor que cero", "Valor no válido",
                    JOptionPane.WARNING_MESSAGE, WARNING_IMAGE);

            return;
        }

        double cantidad = Util.formatearValorOperacion(
                Double.parseDouble(this.cantidadMantenimientoTextField.getText().
                        trim().replace(",", ".")));
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
                cantidad = Util.formatearValorOperacion(cantidadTemp - mantenimiento.getCantidad());
                mantenimiento.setCantidad(cantidadTemp);
                double precioMantenimiento = Util.
                        formatearValorOperacion(Math.abs(cantidad
                                        * this.valorUnitarioMantenimiento));
                if (cantidad < 0) {
                    if (this.presupuestoAdicional > 0) {
                        double t = Util.formatearValorOperacion(
                                this.presupuestoAdicional - precioMantenimiento);
                        if (t > 0) {
                            this.presupuestoAdicional = t;
                        } else if (t == 0) {
                            this.presupuestoAdicional = 0;
                        } else {
                            this.presupuestoAdicional = 0;
                            this.presupuestoActual = Util.formatearValorOperacion(
                                    this.presupuestoActual + Math.abs(t));
                        }
                    } else {
                        this.presupuestoActual = Util.formatearValorOperacion(
                                this.presupuestoActual + precioMantenimiento);
                    }
                } else if (this.presupuestoAdicional > 0) {
                    this.presupuestoAdicional = Util.formatearValorOperacion(
                            this.presupuestoAdicional + precioMantenimiento);
                } else {
                    double t = Util.formatearValorOperacion(
                            this.presupuestoActual - precioMantenimiento);
                    if (t > 0) {
                        this.presupuestoActual = t;
                    } else if (t == 0) {
                        this.presupuestoActual = 0;
                    } else {
                        this.presupuestoActual = 0;
                        this.presupuestoAdicional = Math.abs(t);
                    }
                }
                this.alternativasMantenimiento.add(mantenimiento);

                this.establecerPresupuesto();
                this.agregarAlternativaResumenMantenimiento(mantenimiento);

                return;
            }
        }

        if (this.presupuestoAdicional > 0) {
            this.presupuestoAdicional = Util.formatearValorOperacion(
                    this.presupuestoAdicional + cantidad * this.valorUnitarioMantenimiento);
        } else {
            double t = Util.formatearValorOperacion(this.presupuestoActual
                    - cantidad * this.valorUnitarioMantenimiento);
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
                totalARecuperar = Util.formatearValorOperacion(totalARecuperar
                        + mejoraEA.getCantidad()
                        * this.buscarPrecioDadoCodigoItem(mejoraEA.getItem()));
                mejoraEAEliminada = true;
            }

            if (mejoraTSR != null
                    && (this.cantidadTratamientosSuperficialesRiegosTextField.getText() == null
                    || this.cantidadTratamientosSuperficialesRiegosTextField.getText().trim().isEmpty())) {
                this.eliminarAlternativaResumenMejoras(mejoraTSR);
                this.alternativasMejorasTSR.remove(mejoraTSR);
                totalARecuperar = Util.formatearValorOperacion(totalARecuperar
                        + mejoraTSR.getCantidad()
                        * this.buscarPrecioDadoCodigoItem(mejoraTSR.getItem()));
                mejoraTSREliminada = true;
            }

            if (mejoraEAEliminada || mejoraTSREliminada) {
                if (this.presupuestoActual == 0.0) {
                    if (totalARecuperar < this.presupuestoAdicional) {
                        this.presupuestoAdicional = Util.formatearValorOperacion(
                                this.presupuestoAdicional - totalARecuperar);
                    } else if (totalARecuperar == this.presupuestoAdicional) {
                        this.presupuestoAdicional = 0.0;
                    } else {
                        double p = Util.formatearValorOperacion(totalARecuperar
                                - this.presupuestoAdicional);
                        this.presupuestoAdicional = 0.0;
                        this.presupuestoActual = Util.formatearValorOperacion(
                                this.presupuestoActual + p);
                    }
                } else {
                    this.presupuestoActual = Util.formatearValorOperacion(
                            this.presupuestoActual + totalARecuperar);
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
                && this.cantidadEstabilizacionAfirmadosTextField.getText() != null
                && !this.cantidadEstabilizacionAfirmadosTextField.getText().trim().isEmpty()) {
            if (this.cantidadEstabilizacionAfirmadosTextField.getText().contains(".")
                    || !Util.isNumerico(this.cantidadEstabilizacionAfirmadosTextField.
                            getText().replace(",", "."))) {
                JOptionPane.showMessageDialog(this,
                        "Por favor verique que las cantidades ingresadas sean numéricas.",
                        "Cantidades no válidas",
                        JOptionPane.WARNING_MESSAGE, WARNING_IMAGE);

                return;
            }

            double cantidad = Util.formatearValorOperacion(Double.parseDouble(
                    this.cantidadEstabilizacionAfirmadosTextField.getText().
                    trim().replace(",", ".")));
            if (mejoraEA != null) {
                if (mejoraEA.getCantidad() == cantidad) {
                    igualEA = true;
                } else {
                    this.alternativasMejorasEA.remove(mejoraEA);
                    double cantidadTemp = cantidad;
                    cantidad = Util.formatearValorOperacion(cantidadTemp - mejoraEA.getCantidad());
                    mejoraEA.setCantidad(cantidadTemp);
                    double precioMejora = Util.formatearValorOperacion(
                            Math.abs(cantidad * this.valorUnitarioMejoraEA));
                    if (cantidad < 0.0) {
                        if (this.presupuestoAdicional > 0.0) {
                            double t = Util.formatearValorOperacion(this.presupuestoAdicional - precioMejora);
                            if (t > 0.0) {
                                this.presupuestoAdicional = t;
                            } else if (t == 0.0) {
                                this.presupuestoAdicional = 0.0;
                            } else {
                                this.presupuestoAdicional = 0.0;
                                this.presupuestoActual = Util.formatearValorOperacion(
                                        this.presupuestoActual + Math.abs(t));
                            }
                        } else {
                            this.presupuestoActual = Util.formatearValorOperacion(
                                    this.presupuestoActual + precioMejora);
                        }
                    } else if (this.presupuestoAdicional > 0.0) {
                        this.presupuestoAdicional = Util.formatearValorOperacion(
                                this.presupuestoAdicional + precioMejora);
                    } else {
                        double t = Util.formatearValorOperacion(this.presupuestoActual - precioMejora);
                        if (t > 0.0) {
                            this.presupuestoActual = t;
                        } else if (t == 0.0) {
                            this.presupuestoActual = 0.0;
                        } else {
                            this.presupuestoActual = 0.0;
                            this.presupuestoAdicional = Util.formatearValorOperacion(Math.abs(t));
                        }
                    }
                    this.alternativasMejorasEA.add(mejoraEA);
                    this.agregarAlternativaResumenMejoras(mejoraEA);

                    this.establecerPresupuesto();
                    actualizadaEA = true;
                }
            }

            if (!actualizadaEA && !mejoraEAEliminada && !igualEA) {
                if (this.presupuestoAdicional > 0.0) {
                    this.presupuestoAdicional = Util.formatearValorOperacion(
                            this.presupuestoAdicional + cantidad * this.valorUnitarioMejoraEA);
                } else {
                    double t = Util.formatearValorOperacion(this.presupuestoActual
                            - cantidad * this.valorUnitarioMejoraEA);
                    if (t >= 0) {
                        this.presupuestoActual = t;
                    } else {
                        this.presupuestoActual = 0.0;
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
            if (this.cantidadTratamientosSuperficialesRiegosTextField.getText().contains(".")
                    || !Util.isNumerico(this.cantidadTratamientosSuperficialesRiegosTextField.
                            getText().replace(",", "."))) {
                JOptionPane.showMessageDialog(this,
                        "Por favor verique que las cantidades ingresadas sean numéricas.",
                        "Cantidades no válidas",
                        JOptionPane.WARNING_MESSAGE, WARNING_IMAGE);

                return;
            }
            double cantidad = Util.formatearValorOperacion(Double.parseDouble(
                    this.cantidadTratamientosSuperficialesRiegosTextField.
                    getText().trim().replace(",", ".")));
            if (mejoraTSR != null) {
                if (mejoraTSR.getCantidad() == cantidad) {
                    igualTSR = true;
                } else {
                    this.alternativasMejorasTSR.remove(mejoraTSR);
                    double cantidadTemp = cantidad;
                    cantidad = Util.formatearValorOperacion(cantidadTemp - mejoraTSR.getCantidad());
                    mejoraTSR.setCantidad(cantidadTemp);
                    double precioMejora = Util.formatearValorOperacion(
                            Math.abs(cantidad * this.valorUnitarioMejoraTSR));
                    if (cantidad < 0.0) {
                        if (this.presupuestoAdicional > 0.0) {
                            double t = Util.formatearValorOperacion(this.presupuestoAdicional - precioMejora);
                            if (t > 0.0) {
                                this.presupuestoAdicional = t;
                            } else if (t == 0.0) {
                                this.presupuestoAdicional = 0.0;
                            } else {
                                this.presupuestoAdicional = 0.0;
                                this.presupuestoActual = Util.formatearValorOperacion(
                                        this.presupuestoActual + Math.abs(t));
                            }
                        } else {
                            this.presupuestoActual = Util.formatearValorOperacion(
                                    this.presupuestoActual + precioMejora);
                        }
                    } else if (this.presupuestoAdicional > 0.0) {
                        this.presupuestoAdicional = Util.formatearValorOperacion(
                                this.presupuestoAdicional + precioMejora);
                    } else {
                        double t = Util.formatearValorOperacion(this.presupuestoActual - precioMejora);
                        if (t > 0.0) {
                            this.presupuestoActual = t;
                        } else if (t == 0.0) {
                            this.presupuestoActual = 0.0;
                        } else {
                            this.presupuestoActual = 0.0;
                            this.presupuestoAdicional = Math.abs(t);
                        }
                    }
                    this.alternativasMejorasTSR.add(mejoraTSR);

                    this.establecerPresupuesto();
                    actualizadaTSR = true;

                    this.agregarAlternativaResumenMejoras(mejoraTSR);
                }
            }

            if (!actualizadaTSR && !mejoraTSREliminada && !igualTSR) {
                if (this.presupuestoAdicional > 0.0) {
                    this.presupuestoAdicional = Util.formatearValorOperacion(
                            this.presupuestoAdicional + cantidad * this.valorUnitarioMejoraTSR);
                } else {
                    double t = Util.formatearValorOperacion(this.presupuestoActual
                            - cantidad * this.valorUnitarioMejoraTSR);
                    if (t >= 0.0) {
                        this.presupuestoActual = t;
                    } else {
                        this.presupuestoActual = 0.0;
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
            this.valorUnitarioMejoraTSR = Util.formatearValorOperacion(
                    this.buscarPrecioDadoCodigoItem(this.itemSeleccionadoMejoraTSR.
                            getCodigo()));
            this.unidadMejoraTSR = this.buscarUnidadDadoCodigoItem(
                    this.itemSeleccionadoMejoraTSR.getCodigo());
            this.precioMejoraTSRTextField.setText("$ "
                    + Util.formatearValorVista(this.valorUnitarioMejoraTSR));
            this.unidadMedidaMejoraTSRTextField.setText(
                    this.unidadMejoraTSR.getUnidad() + " - "
                    + this.unidadMejoraTSR.getNombre());

            Alternativa mejora = this.buscarMejoraTSRAplicadaDadoViaMejora(via,
                    this.itemSeleccionadoMejoraTSR.getCodigo());

            if (mejora != null) {
                String valor = String.valueOf(Util.formatearValorOperacion(mejora.
                        getCantidad())).replace(".", ",");
                if (valor.endsWith(",0")) {
                    valor = valor.replace(",0", "");
                }
                this.cantidadTratamientosSuperficialesRiegosTextField.setText(valor);
            } else {
                this.cantidadTratamientosSuperficialesRiegosTextField.setText("");
            }
        }
    }//GEN-LAST:event_tratamientosSuperficialesRiegosComboBoxActionPerformed

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
            this.valorUnitarioMejoraEA = Util.formatearValorOperacion(
                    this.buscarPrecioDadoCodigoItem(this.itemSeleccionadoMejoraEA.getCodigo()));
            this.unidadMejoraEA = this.buscarUnidadDadoCodigoItem(
                    this.itemSeleccionadoMejoraEA.getCodigo());
            this.precioMejoraEATextField.setText("$ "
                    + Util.formatearValorVista(valorUnitarioMejoraEA));
            this.unidadMedidaMejoraEATextField.setText(
                    this.unidadMejoraEA.getUnidad() + " - "
                    + this.unidadMejoraEA.getNombre());

            Alternativa mejora = this.buscarMejoraEAAplicadaDadoViaMejora(
                    this.via, this.itemSeleccionadoMejoraEA.getCodigo());

            if (mejora != null) {
                String valor = String.valueOf(Util.formatearValorOperacion(mejora.
                        getCantidad())).replace(".", ",");
                if (valor.endsWith(",0")) {
                    valor = valor.replace(",0", "");
                }
                this.cantidadEstabilizacionAfirmadosTextField.setText(valor);
            } else {
                this.cantidadEstabilizacionAfirmadosTextField.setText("");
            }
        }
    }//GEN-LAST:event_estabilizacionAfirmadosComboBoxActionPerformed

    private void txtMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMenuItemActionPerformed
        String texto = this.estructurarInformacionTXT();
        File file = this.seleccionarDirectorio();
        if (file != null) {
            if (Util.isDirectorioValido(file)) {
                if (this.creadorArchivoTextoPlano.crearArchivo(texto,
                        file.getAbsolutePath() + "/Resumen.txt")) {
                    JOptionPane.showMessageDialog(this, "Se ha descargado "
                            + "satisfactoriamente el archivo de resumen \n"
                            + "Resumen.txt en el directorio "
                            + "seleccionado.", "Arhivo resumen descargado",
                            JOptionPane.INFORMATION_MESSAGE, INFORMATION_IMAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Se ha generado "
                            + "un error descargando el archivo de resumen. "
                            + "Por favor verifique que el directorio exista\n y "
                            + "que un archivo con el nombre "
                            + "Resumen.txt no esté abierto.",
                            "Error descargando archivo",
                            JOptionPane.ERROR_MESSAGE, ERROR_IMAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor seleccione un "
                        + "directorio válido.",
                        "Directorio inválido",
                        JOptionPane.WARNING_MESSAGE, WARNING_IMAGE);
            }
        }
    }//GEN-LAST:event_txtMenuItemActionPerformed

    private void abrirAlternativasIntervencionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirAlternativasIntervencionMenuItemActionPerformed
        String ruta = Util.getRutaTemporal();
        try {
            boolean resultado;
            resultado = this.archivoExcel.
                    descargarAlternativasIntervencion(new File(ruta));
            if (!resultado) {
                JOptionPane.showMessageDialog(this, "No se puede descargar el archivo"
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
            Logger.getLogger(PriorizacionIntervencionesJDialog.class.getName()).
                    log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Se ha presentado un error abriendo el archivo"
                    + " de alternativas de intervención.\n\nPor favor "
                    + "contáctese con el administrador de la aplicación.",
                    "No se puede abrir archivos",
                    JOptionPane.ERROR_MESSAGE, ERROR_IMAGE);
        }
    }//GEN-LAST:event_abrirAlternativasIntervencionMenuItemActionPerformed

    private void guardarProgresoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarProgresoMenuItemActionPerformed
        File file = this.seleccionarDirectorio();
        if (file != null) {
            if (Util.isDirectorioValido(file)) {
                this.mantPriorViasInfo.getPresupuesto().setPresupuestoActual(
                        this.presupuestoActual);
                this.mantPriorViasInfo.getPresupuesto().setPresupuestoAdicional(
                        this.presupuestoAdicional);
                Progreso progreso = new Progreso();
                progreso.setMantPriorViasInfo(this.mantPriorViasInfo);
                progreso.setViasResumenMantenimiento(this.viasResumenMantenimiento);
                progreso.setViasResumenMejora(this.viasResumenMejora);
                progreso.setAlternativasMantenimiento(this.alternativasMantenimiento);
                progreso.setAlternativasMejorasTSR(this.alternativasMejorasTSR);
                progreso.setAlternativasMejorasEA(this.alternativasMejorasEA);
                if (UtilProgreso.guardarProgreso(progreso, file.getAbsolutePath())) {
                    JOptionPane.showMessageDialog(this, "Se ha guardado "
                            + "satisfactoriamente el archivo de progreso \n"
                            + "progreso.ser en el directorio "
                            + "seleccionado.", "Arhivo progreso guardado",
                            JOptionPane.INFORMATION_MESSAGE, INFORMATION_IMAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Se ha generado "
                            + "un error guardando el archivo de progreso. "
                            + "Por favor verifique que el directorio exista\n y "
                            + "que un archivo con el nombre "
                            + "progreso.ser no esté abierto.",
                            "Error guardando archivo",
                            JOptionPane.ERROR_MESSAGE, ERROR_IMAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor seleccione un "
                        + "directorio válido.",
                        "Directorio inválido",
                        JOptionPane.WARNING_MESSAGE, WARNING_IMAGE);
            }
        }
    }//GEN-LAST:event_guardarProgresoMenuItemActionPerformed

    private void cantidadMantenimientoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadMantenimientoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadMantenimientoTextFieldActionPerformed

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
    private javax.swing.JLabel costoUnitarioMejoraEALabel;
    private javax.swing.JLabel costoUnitarioMejoraTSRLabel;
    private javax.swing.JLabel daniosAsociadosLabel;
    private javax.swing.JComboBox daniosComboBox;
    private javax.swing.JComboBox estabilizacionAfirmadosComboBox;
    private javax.swing.JLabel estabilizacionAfirmadosLabel;
    private javax.swing.JMenu exportarResumenMenu;
    private javax.swing.JMenuItem guardarProgresoMenuItem;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox mantenimientosComboBox;
    private javax.swing.JPanel mantenimientosPanel;
    private javax.swing.JLabel mantenimientosRecomendadosLabel;
    private javax.swing.JPanel mejoramientosPanel;
    private javax.swing.JMenuBar menuBar;
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
    private javax.swing.JScrollPane scrollPaneResumenTextArea;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JLabel tituloLabel;
    private javax.swing.JComboBox tratamientosSuperficialesRiegosComboBox;
    private javax.swing.JLabel tratamientosSuperficialesRiegosLabel;
    private javax.swing.JMenuItem txtMenuItem;
    private javax.swing.JLabel unidadMedidaMantenimientoLabel;
    private javax.swing.JTextField unidadMedidaMantenimientoTextField;
    private javax.swing.JLabel unidadMedidaMejoraEALabel;
    private javax.swing.JTextField unidadMedidaMejoraEATextField;
    private javax.swing.JLabel unidadMedidaMejoraTRSLabel;
    private javax.swing.JTextField unidadMedidaMejoraTSRTextField;
    private javax.swing.JLabel viasLabel;
    // End of variables declaration//GEN-END:variables

    public MantPriorViasInfo iniciarVentanta() {
        this.setVisible(true);

        return this.mantPriorViasInfo;
    }

    private void setInformacionPresupuesto(Presupuesto presupuesto) {
        this.presupuestoTotalInicialTextField.setText("$ "
                + Util.formatearValorVista(presupuesto.getPresupuestoTotal()));
        this.porcentajeAdministracionTextField.setText(String.valueOf(
                Util.formatearValorVista(presupuesto.getPorcentajeAdministracion() * 100)));
        this.porcentajeImprevistosTextField.setText(String.valueOf(
                Util.formatearValorVista(presupuesto.getPorcentajeImprevistos() * 100)));
        this.porcentajeUtilidadesTextField.setText(String.valueOf(
                Util.formatearValorVista(presupuesto.getPorcentajeUtilidades() * 100)));
        this.presupuestoDisponibleTextField.setText("$ "
                + Util.formatearValorVista(presupuesto.getPresupuestoDisponible()));
        this.presupuestoActualTextField.setText("$ "
                + Util.formatearValorVista(presupuesto.getPresupuestoActual()));
        this.presupuestoAdicionalTextField.setText("$ "
                + Util.formatearValorVista(presupuesto.getPresupuestoAdicional()));

        this.presupuestoActual = Util.formatearValorOperacion(
                presupuesto.getPresupuestoActual());
        this.presupuestoAdicional = Util.formatearValorOperacion(
                presupuesto.getPresupuestoAdicional());
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
                        || (c == KeyEvent.VK_DELETE) || c == KeyEvent.VK_COMMA)) {
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
                        || (c == KeyEvent.VK_DELETE) || c == KeyEvent.VK_COMMA)) {
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
                        || (c == KeyEvent.VK_DELETE) || c == KeyEvent.VK_COMMA)) {
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

    private Unidad buscarUnidadDadoCodigoItem(String codigoItem) {
        Unidad u = null;
        for (Item i : this.mantPriorViasInfo.getItems()) {
            if (i.getCodigo().equals(codigoItem)) {
                u = i.getUnidad();
                break;
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
        this.presupuestoActualTextField.setText("$ " + Util.formatearValorVista(
                this.presupuestoActual));
        this.presupuestoAdicionalTextField.setText("$ " + Util.formatearValorVista(
                this.presupuestoAdicional));
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
                    Unidad u = this.obtenerUnidadDadoItem(alternativas.get(k).getItem());
                    this.resumen += "                  Cantidad: "
                            + Util.formatearValorVista(alternativas.get(k).getCantidad())
                            + " " + u.getUnidad() + " (" + u.getNombre() + ")"
                            + separadorLinea;
                    this.resumen += "                  Costo : $ " + Util.
                            formatearValorVista((alternativas.get(k).getCantidad()
                                    * this.buscarPrecioDadoCodigoItem(alternativas.
                                            get(k).getItem())));
                    this.resumen += separadorLinea + separadorLinea;
                }
            }

            int posicion = this.contieneViaMejora(v);
            if (posicion >= 0) {
                List<Alternativa> alternativas = this.viasResumenMejora.
                        get(posicion).getAlternativas();
                for (int j = 0; j < alternativas.size(); j++) {
                    viasImpresas.add(v);
                    Unidad u = this.obtenerUnidadDadoItem(alternativas.get(j).getItem());
                    this.resumen += "        * Mejoramiento: " + alternativas.
                            get(j).getItem() + separadorLinea;
                    this.resumen += "          Cantidad: " + Util.formatearValorVista(
                            alternativas.get(j).getCantidad()) + " " + u.getUnidad()
                            + " (" + u.getNombre() + ")" + separadorLinea;
                    this.resumen += "          Costo: $ " + Util.formatearValorVista(
                            (alternativas.get(j).getCantidad()
                            * this.buscarPrecioDadoCodigoItem(alternativas.get(j).getItem())));
                    this.resumen += separadorLinea + separadorLinea;
                }
            }
        }

        for (int i = 0; i < this.viasResumenMejora.size(); i++) {
            if (!viasImpresas.contains(this.viasResumenMejora.get(i).getVia())) {
                this.resumen += this.viasResumenMejora.get(i).getVia();
                this.resumen += separadorLinea;
                List<Alternativa> alternativas = this.viasResumenMejora.get(i).getAlternativas();
                for (int j = 0; j < alternativas.size(); j++) {
                    Unidad u = this.obtenerUnidadDadoItem(alternativas.get(j).getItem());
                    this.resumen += "        * Mejoramiento: " + alternativas.get(j).getItem() + separadorLinea;
                    this.resumen += "          Cantidad: " + Util.formatearValorVista(
                            alternativas.get(j).getCantidad()) + " "
                            + u.getUnidad() + " (" + u.getNombre() + ")"
                            + separadorLinea;
                    this.resumen += "          Costo: $ " + Util.formatearValorVista(
                            alternativas.get(j).getCantidad()
                            * this.buscarPrecioDadoCodigoItem(alternativas.
                                    get(j).getItem())) + separadorLinea;
                    this.resumen += separadorLinea;
                }
                this.resumen += separadorLinea + separadorLinea;
            }
        }

        this.resumen += "Costo total intervenciones: $ "
                + (Util.formatearValorVista(
                        this.mantPriorViasInfo.getPresupuesto().getPresupuestoDisponible()
                        - this.presupuestoActual + this.presupuestoAdicional));

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
        String texto = "";
        if (this.resumen != null) {
            texto = this.resumen + separadorLinea + separadorLinea + separadorLinea;
        }

        texto += "Presupuesto" + separadorLinea;
        texto += "  * Presupuesto Total Inicial: $"
                + this.mantPriorViasInfo.getPresupuesto().getPresupuestoTotal() + separadorLinea;
        texto += "  * Porcentaje Administación: "
                + (this.mantPriorViasInfo.getPresupuesto().getPorcentajeAdministracion() * 100) + " %" + separadorLinea;
        texto += "  * Porcentaje Imprevistos: "
                + (this.mantPriorViasInfo.getPresupuesto().getPorcentajeImprevistos() * 100) + " %" + separadorLinea;
        texto += "  * Porcentaje Utilidades: "
                + (this.mantPriorViasInfo.getPresupuesto().getPorcentajeUtilidades() * 100) + " %" + separadorLinea;
        texto += "  * Presupuesto Disponible: $"
                + this.mantPriorViasInfo.getPresupuesto().getPresupuestoDisponible();
        texto += "  * Presupuesto Actual: $" + this.presupuestoActual + separadorLinea;
        texto += "  * Presupuesto Adicional para Intervención: $"
                + this.presupuestoAdicional;
        texto += separadorLinea + separadorLinea;

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
            if (i.getCodigo().length() == 3) {
                texto += "    ";
            } else {
                texto += "   ";
            }

            texto += i.getItem();
            int t = 91 - i.getItem().length();
            String s = "";
            for (int j = 0; j < t; j++) {
                s += " ";
            }
            texto += s;

            texto += i.getValorUnitario() + separadorLinea;
        }
        texto += separadorLinea + separadorLinea;

        return texto;
    }

    private Unidad obtenerUnidadDadoItem(String item) {
        Unidad unidad = null;
        for (Item i : this.mantPriorViasInfo.getItems()) {
            if (i.getCodigo().equals(item)) {
                unidad = i.getUnidad();
                break;
            }
        }

        return unidad;
    }

    private File seleccionarDirectorio() {
        int retorno = FILE_CHOOSER_EXPORTAR.showOpenDialog(this);
        if (JFileChooser.APPROVE_OPTION == retorno) {
            return FILE_CHOOSER_EXPORTAR.getSelectedFile();
        }

        return null;
    }
}
