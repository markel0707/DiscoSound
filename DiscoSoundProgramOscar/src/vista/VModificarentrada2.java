package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.Dao;

public class VModificarentrada2 extends JDialog implements ActionListener {

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Dao dao;
	private JButton btnAtras;
	private JButton btnGuardar;

	
	public VModificarentrada2(Dao dao) {
		this.dao = dao;
		setBounds(100, 100, 900, 645);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 168, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblnuevaentrada = new JLabel("Modificar Entrada");
		lblnuevaentrada.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblnuevaentrada.setBounds(275, 28, 283, 54);
		contentPanel.add(lblnuevaentrada);
		
		JLabel lblNombreDJ = new JLabel("Cantidad consumiciones");
		lblNombreDJ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreDJ.setBounds(138, 269, 189, 13);
		contentPanel.add(lblNombreDJ);
		
		JTextField textField = new JTextField();
		textField.setColumns(10);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(138, 292, 96, 19);
		contentPanel.add(textField);
		
		JLabel lblcontrasena = new JLabel("Precio");
		lblcontrasena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcontrasena.setBounds(138, 332, 96, 13);
		contentPanel.add(lblcontrasena);
		
		JTextField textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setColumns(10);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(138, 355, 96, 19);
		contentPanel.add(textField_1);
		
		JLabel lbldifprecio = new JLabel("Diferencia de precio mujer");
		lbldifprecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldifprecio.setBounds(562, 125, 189, 25);
		contentPanel.add(lbldifprecio);
		
		JTextField textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setBounds(562, 149, 96, 19);
		contentPanel.add(textField_2);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBounds(604, 515, 133, 38);
		btnGuardar.addActionListener(this);
		contentPanel.add(btnGuardar);
		
		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAtras.setBounds(138, 515, 85, 38);
		btnAtras.addActionListener(this);
		contentPanel.add(btnAtras);
		
		JLabel lblTipoDeEvento = new JLabel("Tipo de evento");
		lblTipoDeEvento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTipoDeEvento.setBounds(138, 127, 170, 13);
		contentPanel.add(lblTipoDeEvento);
		
		JTextField textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBackground(Color.LIGHT_GRAY);
		textField_4.setBounds(138, 149, 96, 19);
		contentPanel.add(textField_4);
		
		JTextField textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBackground(Color.LIGHT_GRAY);
		textField_5.setBounds(138, 227, 96, 19);
		contentPanel.add(textField_5);
		
		JLabel lblNombreDJ_2 = new JLabel("Nombre DJ (Opcional)");
		lblNombreDJ_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreDJ_2.setBounds(138, 203, 170, 13);
		contentPanel.add(lblNombreDJ_2);	
	}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnAtras)) {
				atras();
			}else if(e.getSource().equals(btnGuardar)) {
				guardar();
			}
		}
		
		
		private void atras() {
			VModificarentrada modificar =new VModificarentrada(dao);
			modificar.setVisible(true);
			this.dispose();
		}
		
		
		private void guardar() {
			JOptionPane.showMessageDialog(this, "Datos Guardado");
			VperfilAdmin guardar = new VperfilAdmin(dao);
			guardar.setVisible(true);
			this.dispose();
			
		}
		}

	