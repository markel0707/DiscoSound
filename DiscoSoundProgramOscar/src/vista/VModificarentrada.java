package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Dao;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class VModificarentrada extends JDialog implements ActionListener{


	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JButton btnSiguiente;
	private JButton btnAtras;
	private Dao dao;

	
	public VModificarentrada(Dao dao) {
		this.dao = dao;
		setBounds(100, 100, 900, 645);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 168, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblModificarEntrada = new JLabel("Modificar Entrada");
			lblModificarEntrada.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
			lblModificarEntrada.setBounds(261, 39, 294, 54);
			contentPanel.add(lblModificarEntrada);
		}
		{
			JLabel lblEntradas = new JLabel("Entradas");
			lblEntradas.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblEntradas.setBounds(76, 173, 85, 15);
			contentPanel.add(lblEntradas);
		}
		{
			JLabel lblCodigoEntrdas = new JLabel("Codigo entrada");
			lblCodigoEntrdas.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblCodigoEntrdas.setBounds(563, 173, 128, 28);
			contentPanel.add(lblCodigoEntrdas);
		}
		{
			btnAtras = new JButton("Atras");
			btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnAtras.setBounds(25, 544, 85, 38);
			btnAtras.addActionListener(this);
			contentPanel.add(btnAtras);
		}
		{
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnSiguiente.setBounds(725, 544, 107, 38);
			btnSiguiente.addActionListener(this);
			contentPanel.add(btnSiguiente);
		}
		{
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBackground(Color.LIGHT_GRAY);
			textField.setBounds(563, 198, 96, 19);
			contentPanel.add(textField);
		}
	}	
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnSiguiente)) {
				siguiente();
			}else if(e.getSource().equals(btnAtras)) {
				atras();
			}
		}


		private void siguiente() {
			VModificarentrada2 modificar2 =new VModificarentrada2(dao);
			modificar2.setVisible(true);
			this.dispose();
		}


		private void atras() {
			VperfilAdmin atras = new VperfilAdmin(dao);
			atras.setVisible(true);
			this.dispose();
			
		}
	}
	