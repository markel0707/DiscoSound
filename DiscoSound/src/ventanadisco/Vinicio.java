package ventanadisco;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Dao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class Vinicio extends JDialog implements ActionListener{

		private JPanel contentPane;
		private Dao dao;
		public Vinicio(Dao dao) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 900, 645);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(255, 128, 0));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JButton btnRegistrar = new JButton("Registrar");
			btnRegistrar.setBackground(new Color(192, 192, 192));
			btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnRegistrar.setBounds(335, 291, 185, 52);
			contentPane.add(btnRegistrar);
			
			JButton btnInicioSesion = new JButton("Iniciar Sesion");
			btnInicioSesion.setBackground(new Color(192, 192, 192));
			btnInicioSesion.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnInicioSesion.setBounds(335, 392, 185, 52);
			contentPane.add(btnInicioSesion);
			
			JLabel lblDiscoSound = new JLabel("DiscoSound");
			lblDiscoSound.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
			lblDiscoSound.setBounds(318, 76, 192, 79);
			contentPane.add(lblDiscoSound);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
