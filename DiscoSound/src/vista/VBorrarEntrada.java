package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clase.Administrador;
import clase.Entrada;
import controlador.Dao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Clase VBorrarEntrada que representa una interfaz gráfica para borrar entradas.
 * Extiende JDialog y maneja acciones del usuario relacionadas con la eliminación de entradas.
 */
public class VBorrarEntrada extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint verticalGradient = new GradientPaint(0, 0, Color.getHSBColor(266.0f, 89.0f, 99.0f), 0,
                    getHeight(), Color.getHSBColor(264.8f, 38.30f, 89.0f));
            g2d.setPaint(verticalGradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    private Dao dao;
    private Administrador admin;
    private JTable table;
    private JButton btnAtras;
    private JScrollPane scrollPane;

    /**
     * Constructor de la clase VBorrarEntrada.
     *
     * @param dao  El objeto de acceso a datos.
     * @param admin El administrador que está usando la interfaz.
     */
    public VBorrarEntrada(Dao dao, Administrador admin) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(VBorrarEntrada.class.getResource("/img/logoapp.png")));
        this.dao = dao;
        this.admin = admin;

        // Configuración de la ventana
        setBounds(100, 100, 900, 645);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(255, 168, 0));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblBorrarEntrada = new JLabel("Borrar Entrada");
        lblBorrarEntrada.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
        lblBorrarEntrada.setBounds(316, 10, 291, 54);
        contentPanel.add(lblBorrarEntrada);

        JLabel lblEntradas = new JLabel("Seleccione la entrada que desea modificar:");
        lblEntradas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEntradas.setBounds(291, 173, 349, 33);
        contentPanel.add(lblEntradas);

        btnAtras = new JButton("Atras");
        btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAtras.setBounds(29, 548, 110, 36);
        contentPanel.add(btnAtras);
        btnAtras.addActionListener(this);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(101, 216, 681, 267);
        contentPanel.add(scrollPane);

        table = this.cargarTabla();
        scrollPane.setViewportView(table);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                borrar();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnAtras)) {
            atras();
        }
    }

    /**
     * Método para manejar el borrado de una entrada seleccionada en la tabla.
     * Muestra un cuadro de diálogo de confirmación antes de proceder con el borrado.
     */
    private void borrar() {
        Entrada entra = new Entrada();
        entra.setCodigoEntrada((String) table.getValueAt(table.getSelectedRow(), 0));

        int resp = JOptionPane.showConfirmDialog(null, "¿Desea borrar la entrada?",
                "Confirmación de borrado de entrada", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        switch (resp) {
            case JOptionPane.OK_OPTION:
                borrarEntrada(entra);
                break;
            case JOptionPane.CANCEL_OPTION:
                break;
        }
    }

    /**
     * Método para borrar una entrada específica.
     *
     * @param entra La entrada a ser borrada.
     */
    private void borrarEntrada(Entrada entra) {
        dao.borrarEntrada(entra);
        JOptionPane.showMessageDialog(this, "Entrada borrada exitosamente");
        VPerfilAdmin atras = new VPerfilAdmin(dao, admin);
        atras.setVisible(true);
        this.dispose();
    }

    /**
     * Método para cargar los datos de las entradas en la tabla.
     *
     * @return Un objeto JTable con los datos de las entradas.
     */
    private JTable cargarTabla() {
        List<Entrada> entradas;
        String[] columnasNombres = { "Código entradas", "Nombre Evento", "Nombre DJ", "Precio", "Categoria",
                "Cantidad Consumo", "Fechas", "Entradas vendidas" };

        String[] fila = new String[8];
        DefaultTableModel tableModel = new DefaultTableModel(null, columnasNombres);
        entradas = dao.sacarEntradas();

        for (Entrada entrada : entradas) {
            if (admin.getNomDiscoteca().substring(0, 2).equalsIgnoreCase(entrada.getCodigoEntrada().substring(0, 2))) {
                fila[0] = entrada.getCodigoEntrada();
                fila[1] = entrada.getNombreEvento().toString();
                fila[2] = entrada.getNombreDJ().toString();
                fila[3] = String.valueOf(entrada.getPrecio());
                fila[4] = entrada.getCategoria().toString();
                fila[5] = String.valueOf(entrada.getCantidadConsumo());
                fila[6] = entrada.getFecha().toString();
                fila[7] = String.valueOf(dao.cantidadEntradasVendidas(entrada.getCodigoEntrada()));
                tableModel.addRow(fila);
            }
        }

        return new JTable(tableModel);
    }

    /**
     * Método para regresar a la vista de perfil del administrador.
     */
    private void atras() {
        VPerfilAdmin atras = new VPerfilAdmin(dao, admin);
        atras.setVisible(true);
        this.dispose();
    }
}