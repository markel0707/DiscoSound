package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clase.Usuario;
import controlador.Dao;

public class VCompraEntradas extends JDialog implements ActionListener{

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
		private Dao dao;
		private JButton btnInicio;
		private JButton btnPrueba;
		private Usuario usu;
		public VCompraEntradas(Dao dao, Usuario usu) {
			setBounds(100, 100, 900, 645);
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
			VMetodoPago prueba = new VMetodoPago(dao, usu);
			prueba.setVisible(true);
			this.dispose();
			
		}
		private void inicio() {
			// TODO Auto-generated method stub
			VPerfilUsuario inicio = new VPerfilUsuario(dao, usu);
			inicio.setVisible(true);
			this.dispose();		
			}
	}