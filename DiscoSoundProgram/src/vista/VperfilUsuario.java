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
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JTextField;

public class VperfilUsuario extends JDialog implements ActionListener{


		private JPanel contentPane;
		private JTextField DatoNombre;
		private JTextField DatoDni;
		private Dao dao;

		public VperfilUsuario(Dao dao) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 900, 645);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(255, 128, 0));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblPerfil = new JLabel("Perfil");
			lblPerfil.setBackground(new Color(240, 240, 240));
			lblPerfil.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
			lblPerfil.setBounds(375, 55, 63, 42);
			contentPane.add(lblPerfil);
			
			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNombre.setBounds(74, 191, 80, 13);
			contentPane.add(lblNombre);
			
			JLabel lbldni = new JLabel("Dni");
			lbldni.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lbldni.setBounds(74, 305, 45, 13);
			contentPane.add(lbldni);
			
			JLabel lblentradas = new JLabel("Entradas");
			lblentradas.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblentradas.setBounds(508, 191, 80, 13);
			contentPane.add(lblentradas);
			
			JButton btnComprarEntradas = new JButton("Comprar Entradas");
			btnComprarEntradas.setBackground(Color.LIGHT_GRAY);
			btnComprarEntradas.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnComprarEntradas.setBounds(33, 542, 168, 42);
			contentPane.add(btnComprarEntradas);
			
			JButton btnDescuentosDisp = new JButton("Descuentos disponibles");
			btnDescuentosDisp.setBackground(Color.LIGHT_GRAY);
			btnDescuentosDisp.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnDescuentosDisp.setBounds(248, 542, 201, 42);
			contentPane.add(btnDescuentosDisp);
			
			JButton btnCambiarDatos = new JButton("Cambiar datos");
			btnCambiarDatos.setBackground(Color.LIGHT_GRAY);
			btnCambiarDatos.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnCambiarDatos.setBounds(496, 542, 159, 42);
			contentPane.add(btnCambiarDatos);
			
			JButton btnCerrarSesion = new JButton("Cerrar Sesion");
			btnCerrarSesion.setBackground(Color.LIGHT_GRAY);
			btnCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnCerrarSesion.setBounds(708, 542, 131, 42);
			contentPane.add(btnCerrarSesion);
			
			DatoNombre = new JTextField();
			DatoNombre.setBackground(Color.LIGHT_GRAY);
			DatoNombre.setBounds(74, 214, 127, 29);
			contentPane.add(DatoNombre);
			DatoNombre.setColumns(10);
			
			DatoDni = new JTextField();
			DatoDni.setBackground(Color.LIGHT_GRAY);
			DatoDni.setBounds(74, 328, 127, 29);
			contentPane.add(DatoDni);
			DatoDni.setColumns(10);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
