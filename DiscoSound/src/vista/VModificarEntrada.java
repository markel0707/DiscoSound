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
 * Clase VModificarEntrada que representa la interfaz gráfica para modificar entradas.
 * Extiende JDialog y maneja las acciones del usuario relacionadas con la selección y modificación de entradas.
 */
public class VModificarEntrada extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel() {
        private static final long serialVersionUID = 1L;

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
    private JButton btnSiguiente;
    private JScrollPane scrollPane;

    /**
     * Constructor de la clase VModificarEntrada.
     *
     * @param dao   El objeto de acceso a datos.
     * @param admin El administrador que realiza la modificación.
     */
    public VModificarEntrada(Dao dao, Administrador admin) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(VModificarEntrada.class.getResource("/img/logoapp.png")));
        this.dao = dao;
        this.admin = admin;
        setBounds(100, 100, 900, 645);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(255, 168, 0));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblModificarEntrada = new JLabel("Modificar Entrada");
        lblModificarEntrada.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
        lblModificarEntrada.setBounds(302, 10, 291, 54);
        contentPanel.add(lblModificarEntrada);

        JLabel lblEntradas = new JLabel("Seleccione la entrada que desea modificar:");
        lblEntradas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEntradas.setBounds(291, 173, 349, 33);
        contentPanel.add(lblEntradas);

        btnAtras = new JButton("Atras");
        btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAtras.setBounds(29, 548, 110, 36);
        contentPanel.add(btnAtras);
        btnAtras.addActionListener(this);

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSiguiente.setBounds(747, 548, 110, 36);
        contentPanel.add(btnSiguiente);
        btnSiguiente.addActionListener(this);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(101, 216, 681, 267);
        contentPanel.add(scrollPane);

        table = this.cargarTabla();
        scrollPane.setViewportView(table);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                seleccionar();
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
     * Método para seleccionar una entrada de la tabla y abrir la ventana de modificación.
     */
    private void seleccionar() {
        Entrada entra = new Entrada();
        entra.setCodigoEntrada((String) table.getValueAt(table.getSelectedRow(), 0));
        VModificarEntrada2 modificar2 = new VModificarEntrada2(dao, admin, entra);
        modificar2.setVisible(true);
        this.dispose();
    }

    /**
     * Método para cargar la tabla con las entradas disponibles.
     *
     * @return JTable con los datos de las entradas.
     */
    private JTable cargarTabla() {
        List<Entrada> entradas;
        String[] columnasNombres = { "Código entradas", "Nombre Evento", "Nombre DJ", "Precio", "Categoria",
                "Cantidad Consumo", "Fechas", "Entradas compradas" };
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
     * Método para volver a la ventana anterior.
     */
    private void atras() {
        VPerfilAdmin atras = new VPerfilAdmin(dao, admin);
        atras.setVisible(true);
        this.dispose();
    }
}