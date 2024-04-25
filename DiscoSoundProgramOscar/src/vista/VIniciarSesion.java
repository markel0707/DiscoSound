package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clase.Administrador;
import clase.Usuario;
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


	public class VIniciarSesion extends JFrame implements ActionListener{

		private JPanel contentPane= new JPanel(){
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            Graphics2D g2d = (Graphics2D) g;

	            GradientPaint verticalGradient = new GradientPaint(0, 0, Color.getHSBColor(266.0f, 89.0f, 99.0f), 0, getHeight(), Color.getHSBColor(264.8f, 38.30f, 89.0f));
	            g2d.setPaint(verticalGradient);

	            g2d.fillRect(0, 0, getWidth(), getHeight());
	        }
	    };
		private JTextField datoUser;
		private JTextField datoContrasena;
		private Dao dao;
		private JButton btnAtras;
		private JButton btnSiguiente;
		private Usuario usu;

		public VIniciarSesion(Dao dao, Usuario usu) {
			this.dao=dao;
			setBounds(100, 100, 900, 645);
			contentPane.setBackground(new Color(255, 128, 0));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblInicioSesion = new JLabel("Iniciar Sesion");
			lblInicioSesion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
			lblInicioSesion.setBounds(297, 107, 233, 30);
			contentPane.add(lblInicioSesion);
			
			datoUser = new JTextField();
			datoUser.setBackground(Color.LIGHT_GRAY);
			datoUser.setBounds(315, 205, 204, 30);
			contentPane.add(datoUser);
			datoUser.setColumns(10);
			
			JLabel lblUser = new JLabel("Nombre de usuario");
			lblUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblUser.setBounds(315, 170, 152, 25);
			contentPane.add(lblUser);
			
			JLabel lblcontrasena = new JLabel("Contraseña");
			lblcontrasena.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblcontrasena.setBounds(315, 245, 152, 20);
			contentPane.add(lblcontrasena);
			
			datoContrasena = new JTextField();
			datoContrasena.setBackground(Color.LIGHT_GRAY);
			datoContrasena.setColumns(10);
			datoContrasena.setBounds(315, 275, 204, 30);
			contentPane.add(datoContrasena);
			
			btnAtras = new JButton("Atras");
			btnAtras.setBackground(Color.LIGHT_GRAY);
			btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnAtras.setBounds(21, 554, 85, 21);
			btnAtras.addActionListener(this);
			contentPane.add(btnAtras);
			
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setBackground(Color.LIGHT_GRAY);
			btnSiguiente.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnSiguiente.setBounds(751, 554, 101, 21);
			btnSiguiente.addActionListener(this);
			contentPane.add(btnSiguiente);
		}
		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource().equals(btnSiguiente)) {
				siguiente();
			} else if (e.getSource().equals(btnAtras)) {
				atras();
			}
		};

		private void siguiente() {
			usu = new Usuario();
			usu.setNomUsu(datoUser.getText());
			usu.setContraseina(datoContrasena.getText());

			usu = dao.inicioSesion(usu);
			if (usu != null) {
				if (usu instanceof Administrador) {
					VPerfilAdmin siguiente = new VPerfilAdmin(dao, usu);
					siguiente.setVisible(true);
					this.dispose();
				} else {
					VPerfilUsuario siguiente = new VPerfilUsuario(dao, usu);
					siguiente.setVisible(true);
					this.dispose();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Usuario o Password incorrecta");
			}

		}

		private void atras() {
			VInicio atras = new VInicio(dao, usu);
			atras.setVisible(true);
			this.dispose();
		}

	}