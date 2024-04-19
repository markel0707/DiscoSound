package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Dao;

public class VmetodoPago extends JDialog implements ActionListener{

		private JPanel contentPane;
		private Dao dao;
		private JButton btnPagar;
		private JButton btnAtras;
		public VmetodoPago(Dao dao) {
			
			setBounds(100, 100, 900, 645);
			contentPane = new JPanel();
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
			case 0:
				
				break;
			case 1:
				atras();
				break;
			}
		}

		private void atras() {
			// TODO Auto-generated method stub
			VdescuentosDisponibles atras = new VdescuentosDisponibles(dao);
			atras.setVisible(true);
			this.dispose();
		}
		

	}

