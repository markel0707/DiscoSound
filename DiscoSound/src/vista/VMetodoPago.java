	package vista;

import java.awt.BorderLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clase.Cliente;
import clase.Compra;
import clase.Entrada;
import controlador.Dao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

public class VMetodoPago extends JDialog implements ActionListener {

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
	private JTextField textCantidad;
	private JTextField textTelefono;
	private JTextField textPrecio;
	private JTextField textCantidadEntradas;
	private JComboBox comboMetodoPago;
	private JButton btnRestar;
	private JButton btnSumar;
	private JButton btnAtras;
	private JButton btnSiguiente;
	private int contador = 1;
	private JLabel lblCantidadEntradas;
	private Entrada entrada;
	private Dao dao;
	private Cliente cli;
	private JLabel lblMetodoPago;
	private JLabel lblTelefono;
	private JLabel lblPrecio;

	public VMetodoPago(Dao dao, Cliente cli, Entrada entrada) {
		this.entrada = entrada;
		this.dao = dao;
		this.cli = cli;
		setBounds(100, 100, 900, 645);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 128, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnAtras = new JButton("Atras");
		btnAtras.setBounds(160, 490, 109, 44);
		btnAtras.addActionListener(this);
		contentPanel.add(btnAtras);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBounds(634, 490, 101, 44);
		btnSiguiente.addActionListener(this);
		contentPanel.add(btnSiguiente);

		lblMetodoPago = new JLabel("Seleccione el metodo de pago");
		lblMetodoPago.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMetodoPago.setBounds(130, 67, 229, 32);
		contentPanel.add(lblMetodoPago);

		comboMetodoPago = new JComboBox();
		comboMetodoPago.setModel(new DefaultComboBoxModel(new String[] { "Bizum", "Google Pay" }));
		comboMetodoPago.setSelectedIndex(-1);
		comboMetodoPago.setBounds(130, 110, 139, 30);
		contentPanel.add(comboMetodoPago);

		lblCantidadEntradas = new JLabel("Cantidad de entradas");
		lblCantidadEntradas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCantidadEntradas.setBounds(98, 251, 229, 32);
		contentPanel.add(lblCantidadEntradas);

		textCantidad = new JTextField();
		textCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		textCantidad.setBounds(149, 313, 42, 42);
		textCantidad.setText(String.valueOf(contador));
		textCantidad.setColumns(10);
		textCantidad.setEditable(false);
		contentPanel.add(textCantidad);

		btnSumar = new JButton("+");
		btnSumar.setBounds(190, 313, 42, 42);
		btnSumar.addActionListener(this);
		contentPanel.add(btnSumar);

		btnRestar = new JButton("-");
		btnRestar.setBounds(108, 313, 42, 42);
		btnRestar.addActionListener(this);
		contentPanel.add(btnRestar);

		lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTelefono.setBounds(558, 62, 139, 32);
		contentPanel.add(lblTelefono);

		textTelefono = new JTextField();
		textTelefono.setBounds(558, 110, 139, 30);
		textTelefono.setColumns(10);
		contentPanel.add(textTelefono);

		textPrecio = new JTextField();
		textPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		textPrecio.setColumns(10);
		textPrecio.setBounds(280, 422, 59, 30);
		textPrecio.setText(String.valueOf(contador * Math.round(entrada.getPrecio())));
		textPrecio.setColumns(10);
		textPrecio.setBorder(null);
		textPrecio.setEditable(false);
		textPrecio.setOpaque(false);
		contentPanel.add(textPrecio);

		lblPrecio = new JLabel("Precio");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrecio.setBounds(280, 379, 59, 32);
		contentPanel.add(lblPrecio);
		
		JLabel lblEntradasDisponibles = new JLabel("Entradas disponibles");
		lblEntradasDisponibles.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntradasDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEntradasDisponibles.setBounds(440, 379, 161, 32);
		contentPanel.add(lblEntradasDisponibles);
		
		textCantidadEntradas = new JTextField();
		textCantidadEntradas.setHorizontalAlignment(SwingConstants.CENTER);
		textCantidadEntradas.setColumns(10);
		textCantidadEntradas.setBounds(498, 422, 59, 30);
		textCantidadEntradas.setText(dao.calculoCantidadEntradasDisponibles(entrada));
		textCantidadEntradas.setColumns(10);
		textCantidadEntradas.setBorder(null);
		textCantidadEntradas.setEditable(false);
		textCantidadEntradas.setOpaque(false);
		contentPanel.add(textCantidadEntradas);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnAtras)) {
			atras();
		} else if (e.getSource().equals(btnSiguiente)) {
			pagar();
		} else if (e.getSource().equals(btnSumar)) {
			sumar(contador);
		} else if (e.getSource().equals(btnRestar)) {
			restar(contador);
		}
	}

	private void sumar(int contador) {
		contador = Integer.parseInt(textCantidad.getText());
		contador++;
		textCantidad.setText(String.valueOf(contador));

		textPrecio.setText(String.valueOf((contador * Math.round(entrada.getPrecio()))));
	}

	private void restar(int contador) {
		contador = Integer.parseInt(textCantidad.getText());
		if (contador > 1) {
			contador--;
			textCantidad.setText(String.valueOf(contador));
			textPrecio.setText(String.valueOf(contador * Math.round(entrada.getPrecio())));
		}

	}

	private void pagar() {

		int resp = JOptionPane.showConfirmDialog(null, "¿Desea realizar la compra?", "Confirmación de pago",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		switch (resp) {
		case JOptionPane.OK_OPTION:
			procesarPago();
			break;
		case JOptionPane.CANCEL_OPTION:
			break;
		}
	}

	private void procesarPago() {
		Compra compra = new Compra();
		compra.setCantidadEntradas(Integer.parseInt(textCantidad.getText()));
		compra.setPrecioTotal(Float.parseFloat(textPrecio.getText()));

		if (comprobarMetodoPago() && comprobarTelefono()) {
			compra.setMetodoPago(comboMetodoPago.getSelectedItem().toString());
			compra.setTelefono(Integer.parseInt(textTelefono.getText()));
			dao.pagar(cli, entrada, compra);
			JOptionPane.showMessageDialog(this, "Entrada/s compradas");
			VPerfilUsuario inicio = new VPerfilUsuario(dao, cli);
			inicio.setVisible(true);
			this.dispose();
		}
	}

	private boolean comprobarMetodoPago() {
		if (comboMetodoPago.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Debes introducir un metodo de pago");
			return false;
		}

		return true;
	}

	private boolean comprobarTelefono() {

		try {
			if (textTelefono.getText().length() == 9) {
				Integer.parseInt(textTelefono.getText());
				return true;
			} else {
				JOptionPane.showMessageDialog(this, "Esto no es un numero de telefono");

			}

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Esto no es un numero de telefono");
		}

		return false;
	}

	private void atras() {

		VDescuentosDisponibles atras = new VDescuentosDisponibles(dao, cli);
		atras.setVisible(true);
		this.dispose();
	}
}
