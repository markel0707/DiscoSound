package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clase.Cliente;
import clase.Discoteca;
import clase.Entrada;
import controlador.Dao;

/**
 * Clase VCompraEntradas que representa una interfaz gráfica para que los clientes compren entradas de discoteca.
 * Extiende JDialog y maneja las acciones del usuario relacionadas con la compra de entradas.
 */
public class VCompraEntradas extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel() {
        private static final long serialVersionUID = 1L;

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
    private Cliente cli;
    private JTable table;
    private JScrollPane scrollPane;
    private List<Discoteca> discotecas;
    private JButton btnInicio;

    /**
     * Constructor de la clase VCompraEntradas.
     *
     * @param dao El objeto de acceso a datos.
     * @param cli El cliente que va a comprar las entradas.
     */
    public VCompraEntradas(Dao dao, Cliente cli) {
        this.dao = dao;
        this.cli = cli;
        setBounds(100, 100, 900, 645);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(255, 128, 0));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblponerNombre = new JLabel("");
        lblponerNombre.setBounds(183, 223, 0, 0);
        contentPanel.add(lblponerNombre);

        btnInicio = new JButton("INICIO");
        btnInicio.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnInicio.setBounds(365, 548, 134, 36);
        btnInicio.addActionListener(this);
        contentPanel.add(btnInicio);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(101, 188, 681, 267);
        contentPanel.add(scrollPane);
        table = this.cargarTabla();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                comprar();
            }
        });

        scrollPane.setViewportView(table);

        JLabel lblComprarEntrada = new JLabel("Comprar entrada");
        lblComprarEntrada.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
        lblComprarEntrada.setBounds(303, 10, 276, 54);
        contentPanel.add(lblComprarEntrada);

        JLabel lblSeleccioneLaEntrada = new JLabel("Seleccione la entrada que desa comprar:");
        lblSeleccioneLaEntrada.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSeleccioneLaEntrada.setBounds(101, 145, 349, 33);
        contentPanel.add(lblSeleccioneLaEntrada);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnInicio)) {
            inicio();
        }
    }

    /**
     * Método para cargar la tabla de entradas disponibles.
     *
     * @return JTable La tabla con las entradas disponibles.
     */
    public JTable cargarTabla() {
        String[] columnNames = { "Codigo Entrada", "Nombre discoteca", "Fecha", "Precio", "Evento", "DJ", "Categoría", "Edad media" };
        String[] fila = new String[8];
        DefaultTableModel model = new DefaultTableModel(null, columnNames);

        discotecas = dao.mostrarDiscotecas();

        for (Discoteca discoteca : discotecas) {
            for (Entrada entrada : discoteca.getEntradas()) {
                if (dao.comprobarEntradaComprada(cli, entrada.getCodigoEntrada())) {
                    fila[0] = entrada.getCodigoEntrada();
                    fila[1] = discoteca.getNombre();
                    fila[2] = entrada.getFecha().toString();
                    if (cli.getGenero().equals("mujer")) {
                        fila[3] = String.valueOf(Float.sum(entrada.getDiferenciaPrecioMujer(), entrada.getPrecio()));
                    } else {
                        fila[3] = String.valueOf(entrada.getPrecio());
                    }
                    fila[4] = entrada.getNombreEvento();
                    fila[5] = entrada.getNombreDJ();
                    fila[6] = entrada.getCategoria();
                    fila[7] = dao.calcularEdadMediaEntrada(entrada.getCodigoEntrada());
                    model.addRow(fila);
                }
            }
        }

        return new JTable(model);
    }

    /**
     * Método para regresar a la vista del perfil del usuario.
     */
    private void inicio() {
        VPerfilUsuario inicio = new VPerfilUsuario(dao, cli);
        inicio.setVisible(true);
        this.dispose();
    }

    /**
     * Método para gestionar la compra de una entrada seleccionada.
     */
    private void comprar() {
        Entrada entradaNueva = new Entrada();
        entradaNueva.setCodigoEntrada((String) table.getModel().getValueAt(table.getSelectedRow(), 0));
        entradaNueva.setPrecio(Float.parseFloat((String) table.getModel().getValueAt(table.getSelectedRow(), 3)));

        // Parsear la fecha que estaba guardada como string a LocalDate
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaString = (String) table.getModel().getValueAt(table.getSelectedRow(), 2);
        LocalDate fecha = LocalDate.parse(fechaString, formato);
        entradaNueva.setFecha(fecha);

        if (dao.calculoCantidadEntradasDisponibles(entradaNueva).equals("0")) {
            JOptionPane.showMessageDialog(this, "Aforo completo");
        } else {
            VMetodoPago metodoPago = new VMetodoPago(dao, cli, entradaNueva);
            metodoPago.setVisible(true);
            this.dispose();
        }
    }
}
