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
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JTextField;

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
	private JButton btnDescuentosDisp;
	private JButton btnCambiarDatos;
	private JButton btnCerrarSesion;
	private Cliente cli;
	private List<Discoteca> discotecas;

	public VPerfilUsuario(Dao dao, Cliente cli) {
		this.dao = dao;
		this.cli = cli;
		setBounds(100, 100, 900, 645);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 128, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(583, 206, 220, 143);
		table = this.cargarTabla();
		scrollPane.setViewportView(table);
		contentPanel.setLayout(null);
		contentPanel.add(scrollPane);

		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblPerfil.setBackground(UIManager.getColor("Button.background"));
		lblPerfil.setBounds(450, 21, 63, 42);
		contentPanel.add(lblPerfil);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombre.setBounds(149, 157, 80, 13);
		contentPanel.add(lblNombre);

		JLabel lbldni = new JLabel("Dni");
		lbldni.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldni.setBounds(149, 271, 45, 13);
		contentPanel.add(lbldni);

		JLabel lblentradas = new JLabel("Entradas");
		lblentradas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblentradas.setBounds(583, 157, 80, 13);
		contentPanel.add(lblentradas);

		btnComprarEntradas = new JButton("Comprar Entradas");
		btnComprarEntradas.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnComprarEntradas.setBackground(Color.LIGHT_GRAY);
		btnComprarEntradas.setBounds(108, 508, 168, 42);
		btnComprarEntradas.addActionListener(this);
		contentPanel.add(btnComprarEntradas);

		btnDescuentosDisp = new JButton("Descuentos disponibles");
		btnDescuentosDisp.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDescuentosDisp.setBackground(Color.LIGHT_GRAY);
		btnDescuentosDisp.setBounds(323, 508, 201, 42);
		btnDescuentosDisp.addActionListener(this);
		contentPanel.add(btnDescuentosDisp);

		btnCambiarDatos = new JButton("Cambiar datos");
		btnCambiarDatos.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCambiarDatos.setBackground(Color.LIGHT_GRAY);
		btnCambiarDatos.setBounds(571, 508, 159, 42);
		btnCambiarDatos.addActionListener(this);
		contentPanel.add(btnCambiarDatos);

		btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCerrarSesion.setBackground(Color.LIGHT_GRAY);
		btnCerrarSesion.setBounds(745, 508, 131, 42);
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
		} else if (e.getSource().equals(btnDescuentosDisp)) {
			descuentosDisp();
		} else if (e.getSource().equals(btnCambiarDatos)) {
			cambiarDatos();
		} else if (e.getSource().equals(btnCerrarSesion)) {
			cerrarSesion();
		}

	}

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

	private void descuentosDisp() {
		VDescuentosDisponibles descuentosDisp = new VDescuentosDisponibles(dao, cli);
		descuentosDisp.setVisible(true);
		this.dispose();
	}

	private void comprarEntradas() {
		VCompraEntradas compraEntradas = new VCompraEntradas(dao, cli);
		compraEntradas.setVisible(true);
		this.dispose();
	}
}
