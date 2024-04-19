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

public class VcompraEntradas extends JDialog implements ActionListener{

		private JPanel contentPane;
		private Dao dao;
		private JButton btnInicio;
		private JButton btnPrueba;
		public VcompraEntradas(Dao dao) {
			setBounds(100, 100, 900, 645);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(255, 128, 0));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblponerNombre = new JLabel("");
			lblponerNombre.setBounds(369, 40, 45, 13);
			contentPane.add(lblponerNombre);
			
			btnInicio = new JButton("INICIO");
			btnInicio.setBounds(420, 490, 89, 40);
			btnInicio.addActionListener(this);
			contentPane.add(btnInicio);
			
			btnPrueba = new JButton("Prueba");
			btnPrueba.setBounds(120, 490, 89, 40);
			btnPrueba.addActionListener(this);
			contentPane.add(btnPrueba);
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource().equals(btnInicio)) {
				inicio();
			} else if(e.getSource().equals(btnPrueba)) {
				prueba();
			}
			
		}
		private void prueba() {
			// TODO Auto-generated method stub
			VmetodoPago prueba = new VmetodoPago(dao);
			prueba.setVisible(true);
			this.dispose();
			
		}
		private void inicio() {
			// TODO Auto-generated method stub
			VperfilUsuario inicio = new VperfilUsuario(dao);
			inicio.setVisible(true);
			this.dispose();		
			}
	}