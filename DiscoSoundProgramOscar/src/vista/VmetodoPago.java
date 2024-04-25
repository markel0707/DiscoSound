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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Dao;

public class VmetodoPago extends JDialog implements ActionListener{

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
		private JButton btnPagar;
		private JButton btnAtras;
		public VmetodoPago(Dao dao) {
			
			setBounds(100, 100, 900, 645);
			contentPane.setBackground(new Color(255, 128, 0));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			btnAtras = new JButton("Atras");
			btnAtras.setBounds(160, 490, 109, 44);
			btnAtras.addActionListener(this);
			contentPane.add(btnAtras);
			
			btnPagar = new JButton("Pagar");
			btnPagar.setBounds(634, 490, 101, 44);
			btnPagar.addActionListener(this);
			contentPane.add(btnPagar);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource().equals(btnAtras)) {
				atras();
			} else if(e.getSource().equals(btnPagar)) {
				pagar();
			}
		}
		private void pagar() {
			// TODO Auto-generated method stub
			
			int resp=JOptionPane.showConfirmDialog(null, "¿Desea realizar la compra?",
	                "Confirmación de pago", JOptionPane.OK_CANCEL_OPTION,
	                JOptionPane.INFORMATION_MESSAGE);
			switch(resp) {
			case JOptionPane.OK_OPTION:
				procesarPago();
				break;
			case JOptionPane.CANCEL_OPTION:
				atras();
				break;
			}
		}

		private void procesarPago() {
			// TODO Auto-generated method stub
			
		}
		private void atras() {
			// TODO Auto-generated method stub
			VdescuentosDisponibles atras = new VdescuentosDisponibles(dao);
			atras.setVisible(true);
			this.dispose();
		}
		

	}

