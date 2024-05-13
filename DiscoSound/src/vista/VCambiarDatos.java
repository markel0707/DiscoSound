package vista;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clase.Cliente;
import controlador.Dao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import java.awt.Color;

public class VCambiarDatos extends JDialog implements ActionListener {

	/**
	 * 
	 */
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
	private JTextField textNombre;
	private JTextField textContraseina;
	private JTextField textEmail;
	private JTextField textApellido;
	private Dao dao;
	private JButton btnGuardar;
	private JButton btnAtras;
	private Cliente cli;

	public VCambiarDatos(Dao dao, Cliente cli) {
		this.dao = dao;
		this.cli = cli;
		setBounds(100, 100, 900, 645);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 168, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblCambiarDatos = new JLabel("Cambiar datos");
		lblCambiarDatos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblCambiarDatos.setBounds(315, 11, 233, 54);
		contentPanel.add(lblCambiarDatos);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombre.setBounds(178, 108, 96, 13);
		contentPanel.add(lblNombre);

		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBackground(Color.LIGHT_GRAY);
		textNombre.setBounds(178, 132, 96, 19);
		textNombre.setText(cli.getNombre());
		contentPanel.add(textNombre);

		JLabel lblcontrasena = new JLabel("Contraseña");
		lblcontrasena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcontrasena.setBounds(178, 274, 96, 13);
		contentPanel.add(lblcontrasena);

		textContraseina = new JTextField();
		textContraseina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textContraseina.setColumns(10);
		textContraseina.setBackground(Color.LIGHT_GRAY);
		textContraseina.setBounds(178, 298, 96, 19);
		textContraseina.setText(cli.getContraseina());
		contentPanel.add(textContraseina);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(695, 108, 96, 13);
		contentPanel.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBackground(Color.LIGHT_GRAY);
		textEmail.setBounds(644, 132, 96, 19);
		textEmail.setText(cli.getEmail());
		contentPanel.add(textEmail);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblApellido.setBounds(667, 274, 96, 13);
		contentPanel.add(lblApellido);

		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBackground(Color.LIGHT_GRAY);
		textApellido.setBounds(644, 299, 96, 19);
		textApellido.setText(cli.getApellido());
		contentPanel.add(textApellido);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBounds(644, 498, 133, 38);
		btnGuardar.addActionListener(this);
		contentPanel.add(btnGuardar);

		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAtras.setBounds(178, 498, 85, 38);
		btnAtras.addActionListener(this);
		contentPanel.add(btnAtras);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnGuardar)) {
			guardar();
		} else if (e.getSource().equals(btnAtras)) {
			atras();
		}
	}

	private void atras() {

		VPerfilUsuario atras = new VPerfilUsuario(dao, cli);
		atras.setVisible(true);
		this.dispose();
	}

	private void guardar() {

		cli.setNombre(textNombre.getText());
		cli.setEmail(textEmail.getText());
		cli.setApellido(textApellido.getText());
		cli.setContraseina(textContraseina.getText());

		dao.cambiarDatos(cli);

		JOptionPane.showMessageDialog(this, "Datos cambiados");
		VPerfilUsuario guardar = new VPerfilUsuario(dao, cli);
		guardar.setVisible(true);
		this.dispose();
	}
}
