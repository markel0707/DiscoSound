package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Dao;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import java.awt.Color;


	public class ViniciarSesion extends JFrame implements ActionListener{

		private JPanel contentPane;
		private JTextField datoUser;
		private JTextField datoContrasena;
		private Dao dao;
		public ViniciarSesion(Dao dao) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 900, 645);
			contentPane = new JPanel();
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
			
			JButton btnAtras = new JButton("Atras");
			btnAtras.setBackground(Color.LIGHT_GRAY);
			btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnAtras.setBounds(21, 554, 85, 21);
			contentPane.add(btnAtras);
			
			JButton btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setBackground(Color.LIGHT_GRAY);
			btnSiguiente.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnSiguiente.setBounds(751, 554, 101, 21);
			contentPane.add(btnSiguiente);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
