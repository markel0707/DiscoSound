package ventanadisco;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class VdescuentosDisponibles extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	private Dao dao;
	
	public VdescuentosDisponibles(Dao dao) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 645);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbldescuentos = new JLabel("Descuentos disponibles");
		lbldescuentos.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbldescuentos.setBounds(218, 23, 382, 50);
		contentPane.add(lbldescuentos);
		
		JButton btnInicio = new JButton("Inicio");
		btnInicio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInicio.setBounds(392, 545, 85, 21);
		contentPane.add(btnInicio);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}