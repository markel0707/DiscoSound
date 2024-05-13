package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clase.Administrador;
import controlador.Dao;

public class VPerfilAdmin extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPane = new JPanel() {
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
	private JTextField datoNombre;
	private Dao dao;
	private JButton btnNuevaEntrada;
	private JButton btnModificarEntrada;
	private JButton btnCerrarSesion;
	private Administrador admin;
	private JButton btnBorrarEntrada;

	public VPerfilAdmin(Dao dao, Administrador admin) {
		this.dao = dao;
		this.admin = admin;
		setBounds(100, 100, 900, 645);
		contentPane.setBackground(new Color(255, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPerfil = new JLabel("PERFIL DE ADMINISTRADOR");
		lblPerfil.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblPerfil.setBounds(43, 30, 490, 57);
		contentPane.add(lblPerfil);

		datoNombre = new JTextField();
		datoNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		datoNombre.setColumns(10);
		datoNombre.setBounds(262, 229, 331, 67);
		datoNombre.setText("Administrador de " + admin.getNomDiscoteca() + " " + admin.getNombre());
		contentPane.add(datoNombre);

		btnNuevaEntrada = new JButton("Nueva entrada");
		btnNuevaEntrada.setBackground(Color.LIGHT_GRAY);
		btnNuevaEntrada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNuevaEntrada.setBounds(29, 548, 134, 36);
		btnNuevaEntrada.addActionListener(this);
		contentPane.add(btnNuevaEntrada);

		btnModificarEntrada = new JButton("Modificar entrada");
		btnModificarEntrada.setBackground(Color.LIGHT_GRAY);
		btnModificarEntrada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificarEntrada.setBounds(720, 548, 137, 36);
		btnModificarEntrada.addActionListener(this);
		contentPane.add(btnModificarEntrada);

		btnCerrarSesion = new JButton("Cerrar sesion");
		btnCerrarSesion.setBackground(Color.LIGHT_GRAY);
		btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCerrarSesion.setBounds(732, 30, 125, 57);
		btnCerrarSesion.addActionListener(this);
		contentPane.add(btnCerrarSesion);

		btnBorrarEntrada = new JButton("Borrar entrada");
		btnBorrarEntrada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBorrarEntrada.setBackground(Color.LIGHT_GRAY);
		btnBorrarEntrada.setBounds(365, 548, 134, 36);
		btnBorrarEntrada.addActionListener(this);
		contentPane.add(btnBorrarEntrada);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(btnNuevaEntrada)) {
			nuevaEntrada();
		} else if (e.getSource().equals(btnModificarEntrada)) {
			modificarEntrada();
		} else if (e.getSource().equals(btnCerrarSesion)) {
			cerrarSesion();
		} else if (e.getSource().equals(btnBorrarEntrada)) {
			borrarEntrada();
		}
	}

	private void borrarEntrada() {
		// TODO Auto-generated method stub
		VBorrarEntrada borrarEntrada = new VBorrarEntrada(dao, admin);
		borrarEntrada.setVisible(true);
		this.dispose();
		
	}

	private void cerrarSesion() {
		// TODO Auto-generated method stub
		VInicio cerrarSesion = new VInicio(dao);
		cerrarSesion.setVisible(true);
		this.dispose();

	}

	private void modificarEntrada() {
		// TODO Auto-generated method stub
		VModificarEntrada modificarEntrada = new VModificarEntrada(dao, admin);
		modificarEntrada.setVisible(true);
		this.dispose();
	}

	private void nuevaEntrada() {
		// TODO Auto-generated method stub
		VNuevaEntrada nuevaEntrada = new VNuevaEntrada(dao, admin);
		nuevaEntrada.setVisible(true);
		this.dispose();
	}
}
