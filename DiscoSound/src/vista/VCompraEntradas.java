package vista;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clase.Cliente;
import clase.Discoteca;
import clase.Entrada;
import controlador.Dao;
import java.awt.Color;

public class VCompraEntradas extends JDialog implements ActionListener {

	
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
	private Cliente cli;
	private JTable table;
	private JScrollPane scrollPane;
	private List<Discoteca> discotecas;
	private JButton btnInicio;

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
		btnInicio.setBounds(316, 470, 110, 44);
		btnInicio.addActionListener(this);
		contentPanel.add(btnInicio);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(82, 173, 626, 263);
		contentPanel.add(scrollPane);
		table = this.cargarTabla();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comprar();
			}

		});

		scrollPane.setViewportView(table);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(btnInicio)) {
			inicio();
		}

	}

	public JTable cargarTabla() {

		// Columnas
		String[] columnNames = { "Codigo Entrada", "Nombre discoteca", "Fecha", "Precio", "Evento", "DJ" };
		String[] fila = new String[6];
		DefaultTableModel model = new DefaultTableModel(null, columnNames);

//	entradas = dao.mostrarEntradas();
		discotecas = dao.mostrarDiscotecas();

		for (Discoteca discoteca : discotecas) {
			for (Entrada entrada : discoteca.getEntradas()) {
				if (dao.comprobarEntradaComprada(cli, entrada.getCodigoEntrada())) {

					fila[0] = entrada.getCodigoEntrada();
					fila[1] = discoteca.getNombre();
					fila[2] = entrada.getFecha().toString();
					if (cli.getGenero().equals("mujer")) {

						fila[3] = String.valueOf(entrada.getDiferenciaPrecioMujer() + entrada.getPrecio());

					} else {
						fila[3] = String.valueOf(entrada.getPrecio());
					}

					fila[4] = entrada.getNombreEvento();
					fila[5] = entrada.getNombreDJ();

					model.addRow(fila);
				}
			}

		}

		return new JTable(model);

	}

	private void inicio() {

		VPerfilUsuario inicio = new VPerfilUsuario(dao, cli);
		inicio.setVisible(true);
		this.dispose();
	}

	private void comprar() {
		Entrada entradaNueva = new Entrada();
		entradaNueva.setCodigoEntrada((String) table.getModel().getValueAt(table.getSelectedRow(), 0));
		entradaNueva.setPrecio(Float.parseFloat((String) table.getModel().getValueAt(table.getSelectedRow(), 3)));
		
		// Parsear la fecha que estaba guardada como string a localDate
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fechaString = (String) table.getModel().getValueAt(table.getSelectedRow(), 2);
		LocalDate fecha = LocalDate.parse(fechaString, formato);
		
		entradaNueva.setFecha(fecha);

		VMetodoPago prueba = new VMetodoPago(dao, cli, entradaNueva);
		prueba.setVisible(true);
		this.dispose();

	}
}
