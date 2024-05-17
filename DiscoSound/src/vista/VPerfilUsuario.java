package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import clase.Cliente;
import clase.Discoteca;
import clase.Entrada;
import controlador.Dao;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTextField;

/**
 * Clase VPerfilUsuario que representa la interfaz gráfica del perfil del usuario.
 * Extiende JDialog y maneja las acciones del usuario relacionadas con la visualización y compra de entradas.
 */
public class VPerfilUsuario extends JDialog implements ActionListener {

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
    private JTable table;
    private JTextField textNombre;
    private JTextField textDni;
    private Dao dao;
    private JButton btnComprarEntradas;
    private JButton btnCambiarDatos;
    private JButton btnCerrarSesion;
    private Cliente cli;
    private List<Discoteca> discotecas;

    /**
     * Constructor de la clase VPerfilUsuario.
     *
     * @param dao El objeto de acceso a datos.
     * @param cli El cliente para el perfil.
     */
    public VPerfilUsuario(Dao dao, Cliente cli) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(VPerfilUsuario.class.getResource("/img/logoapp.png")));
        this.dao = dao;
        this.cli = cli;
        setBounds(100, 100, 900, 645);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(255, 128, 0));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        // Tabla para mostrar las entradas compradas por el usuario.
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(584, 184, 220, 143);
        table = this.cargarTabla();
        scrollPane.setViewportView(table);
        contentPanel.setLayout(null);
        contentPanel.add(scrollPane);

        // Etiqueta para mostrar el título del perfil.
        JLabel lblPerfil = new JLabel("Perfil de usuario");
        lblPerfil.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        lblPerfil.setBackground(UIManager.getColor("Button.background"));
        lblPerfil.setBounds(339, 10, 191, 42);
        contentPanel.add(lblPerfil);

        // Etiquetas y campos de texto para mostrar el nombre y el DNI del usuario.
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNombre.setBounds(149, 157, 80, 13);
        contentPanel.add(lblNombre);

        JLabel lbldni = new JLabel("DNI");
        lbldni.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbldni.setBounds(149, 271, 45, 13);
        contentPanel.add(lbldni);

        // Botones para realizar acciones como comprar entradas, cambiar datos, etc.
        JLabel lblEntradas = new JLabel("Entradas compradas:");
        lblEntradas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEntradas.setBounds(584, 157, 165, 13);
        contentPanel.add(lblEntradas);

        btnComprarEntradas = new JButton("Comprar Entradas");
        btnComprarEntradas.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnComprarEntradas.setBackground(Color.LIGHT_GRAY);
        btnComprarEntradas.setBounds(29, 548, 165, 36);
        btnComprarEntradas.addActionListener(this);
        contentPanel.add(btnComprarEntradas);

        btnCambiarDatos = new JButton("Cambiar datos");
        btnCambiarDatos.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCambiarDatos.setBackground(Color.LIGHT_GRAY);
        btnCambiarDatos.setBounds(699, 548, 158, 36);
        btnCambiarDatos.addActionListener(this);
        contentPanel.add(btnCambiarDatos);

        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCerrarSesion.setBackground(Color.LIGHT_GRAY);
        btnCerrarSesion.setBounds(732, 30, 125, 57);
        btnCerrarSesion.addActionListener(this);
        contentPanel.add(btnCerrarSesion);

        textNombre = new JTextField();
        textNombre.setColumns(10);
        textNombre.setFont(new Font("Verdana", Font.PLAIN, 17));
        textNombre.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
        textNombre.setOpaque(false);
        textNombre.setBounds(149, 180, 127, 29);
        textNombre.setText(cli.getNombre());
        textNombre.setEditable(false);
        contentPanel.add(textNombre);

        textDni = new JTextField();
        textDni.setColumns(10);
        textDni.setFont(new Font("Verdana", Font.PLAIN, 17));
        textDni.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
        textDni.setOpaque(false);
        textDni.setBounds(149, 294, 127, 29);
        textDni.setText(cli.getDni());
        textDni.setEditable(false);
        contentPanel.add(textDni);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnComprarEntradas)) {
            comprarEntradas();
        } else if (e.getSource().equals(btnCambiarDatos)) {
            cambiarDatos();
        } else if (e.getSource().equals(btnCerrarSesion)) {
            cerrarSesion();
        }
    }

    /**
     * Método para cargar la tabla con las entradas compradas por el usuario.
     *
     * @return La tabla con las entradas compradas.
     */

	public JTable cargarTabla() {
		// Columnas
		int cantidadEntradas;
		String[] columnNames = { "Codigo Entrada", "Fecha", "Cantidad" };
		String[] fila = new String[3];
		DefaultTableModel model = new DefaultTableModel(null, columnNames);

		discotecas = dao.mostrarDiscotecas();

		for (Discoteca discoteca : discotecas) {
			for (Entrada entrada : discoteca.getEntradas()) {
				cantidadEntradas = dao.comprobarCompra(cli, entrada.getCodigoEntrada());
				if (cantidadEntradas != 0) {

					fila[0] = entrada.getCodigoEntrada();
					fila[1] = entrada.getFecha().toString();
					fila[2] = String.valueOf(cantidadEntradas);

					model.addRow(fila);
				}
			}

		}

		return new JTable(model);

	}

	private void cerrarSesion() {
		VInicio cerrarSesion = new VInicio(dao);
		cerrarSesion.setVisible(true);
		this.dispose();
	}

	private void cambiarDatos() {
		VCambiarDatos cambiarDatos = new VCambiarDatos(dao, cli);
		cambiarDatos.setVisible(true);
		this.dispose();

	}

	private void comprarEntradas() {

		if (dao.obtenerUltimoNumeroDeEntrada() > 0) {
			VCompraEntradas compraEntradas = new VCompraEntradas(dao, cli);
			compraEntradas.setVisible(true);
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "No hay ninguna entrada disponible");
		}
	}
}
