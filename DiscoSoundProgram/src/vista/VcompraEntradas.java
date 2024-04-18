package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Dao;
import java.awt.Font;

public class VcompraEntradas extends JDialog implements ActionListener{

		private JPanel contentPane;
		private Dao dao;
		public VcompraEntradas(Dao dao) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 900, 645);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(255, 128, 0));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblponerNombre = new JLabel("");
			lblponerNombre.setBounds(369, 40, 45, 13);
			contentPane.add(lblponerNombre);
			
			JButton btnInicio = new JButton("Inicio");
			btnInicio.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnInicio.setBounds(356, 529, 133, 38);
			contentPane.add(btnInicio);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

	}