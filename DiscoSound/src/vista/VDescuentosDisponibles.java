package vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clase.Cliente;
import controlador.Dao;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JScrollPane;

public class VDescuentosDisponibles extends JDialog implements ActionListener {

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
	private JButton btnInicio;
	private JTable table;

	public VDescuentosDisponibles(Dao dao, Cliente cli) {
		this.dao = dao;
		this.cli = cli;
		setBounds(100, 100, 900, 645);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 128, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lbldescuentos = new JLabel("Descuentos disponibles");
		lbldescuentos.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbldescuentos.setBounds(249, 34, 382, 50);
		contentPanel.add(lbldescuentos);

		btnInicio = new JButton("Inicio");
		btnInicio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInicio.setBounds(412, 529, 85, 21);
		btnInicio.addActionListener(this);
		contentPanel.add(btnInicio);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(166, 202, 342, 172);
		contentPanel.add(scrollPane);
		table = this.cargarTabla();
		scrollPane.setViewportView(table);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnInicio)) {
			inicio();
		}
	}

	public JTable cargarTabla() {
		// Columnas
		String[] columnNames = { "Nombre discoteca", "Tipo descuento", "Cantida de descuento" };
		String[] fila = new String[3];
		DefaultTableModel model = new DefaultTableModel(null, columnNames);

		return new JTable(model);

	}

	private void inicio() {
		VPerfilUsuario siguiente = new VPerfilUsuario(dao, cli);
		siguiente.setVisible(true);
		this.dispose();

	}
}
