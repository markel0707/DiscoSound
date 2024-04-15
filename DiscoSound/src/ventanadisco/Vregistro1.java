package ventanadisco;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Dao;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.UIManager;

public class Vregistro1 extends JDialog implements ActionListener{

		private JPanel contentPane;
		private JTextField textDni;
		private JTextField textFechaNac;
		private JTextField textEmail;
		private Dao dao;
		public Vregistro1(Dao dao) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 900, 645);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(255, 128, 0));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("REGISTRO");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_1.setBounds(10, 0, 76, 25);
			contentPane.add(lblNewLabel_1);
			
			JLabel lblNewLabel_8_1 = new JLabel("DNI");
			lblNewLabel_8_1.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNewLabel_8_1.setBounds(316, 233, 93, 13);
			contentPane.add(lblNewLabel_8_1);
			
			textDni = new JTextField();
			textDni.setBackground(Color.LIGHT_GRAY);
			textDni.setFont(new Font("Tahoma", Font.BOLD, 16));
			textDni.setColumns(10);
			textDni.setBounds(316, 256, 172, 25);
			contentPane.add(textDni);
			
			JLabel lblNewLabel_8_1_1 = new JLabel("Fecha de nacimiento");
			lblNewLabel_8_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNewLabel_8_1_1.setBounds(316, 303, 172, 13);
			contentPane.add(lblNewLabel_8_1_1);
			
			textFechaNac = new JTextField();
			textFechaNac.setBackground(Color.LIGHT_GRAY);
			textFechaNac.setFont(new Font("Tahoma", Font.BOLD, 16));
			textFechaNac.setColumns(10);
			textFechaNac.setBounds(316, 326, 172, 25);
			contentPane.add(textFechaNac);
			
			JLabel lblNewLabel_8_1_1_1 = new JLabel("Email");
			lblNewLabel_8_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNewLabel_8_1_1_1.setBounds(316, 373, 93, 13);
			contentPane.add(lblNewLabel_8_1_1_1);
			
			textEmail = new JTextField();
			textEmail.setBackground(Color.LIGHT_GRAY);
			textEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
			textEmail.setColumns(10);
			textEmail.setBounds(315, 396, 172, 25);
			contentPane.add(textEmail);
			
			JButton btnAtras = new JButton("Atras");
			btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnAtras.setBounds(23, 577, 85, 21);
			contentPane.add(btnAtras);
			
			JButton btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnSiguiente.setBounds(775, 577, 101, 21);
			contentPane.add(btnSiguiente);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}