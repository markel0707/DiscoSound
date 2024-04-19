package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Dao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import java.awt.Color;

public class VNuevaentrada extends JDialog implements ActionListener{

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private Dao dao;
	private JButton btnAtras;
	private JButton btnGuardar;

	
	public VNuevaentrada(Dao dao) {
		setBounds(100, 100, 900, 645);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 168, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblnuevaentrada = new JLabel("Nueva Entrada");
		lblnuevaentrada.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblnuevaentrada.setBounds(275, 28, 233, 54);
		contentPanel.add(lblnuevaentrada);
		
		JLabel lblNombreDJ = new JLabel("Cantidad consumiciones");
		lblNombreDJ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreDJ.setBounds(138, 269, 189, 13);
		contentPanel.add(lblNombreDJ);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(138, 292, 96, 19);
		contentPanel.add(textField);
		
		JLabel lblcontrasena = new JLabel("Precio");
		lblcontrasena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcontrasena.setBounds(138, 332, 96, 13);
		contentPanel.add(lblcontrasena);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(138, 355, 96, 19);
		contentPanel.add(textField);
		
		JLabel lbldifprecio = new JLabel("Diferencia de precio mujer");
		lbldifprecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldifprecio.setBounds(562, 125, 189, 13);
		contentPanel.add(lbldifprecio);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(562, 149, 96, 19);
		contentPanel.add(textField);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCategoria.setBounds(562, 203, 96, 13);
		contentPanel.add(lblCategoria);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(562, 226, 96, 19);
		contentPanel.add(textField);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBounds(618, 515, 96, 38);
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
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(138, 149, 96, 19);
		contentPanel.add(textField);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(138, 227, 96, 19);
		contentPanel.add(textField);
		
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
			VperfilAdmin perfilad =new VperfilAdmin(dao);
			perfilad.setVisible(true);
			this.dispose();
		}
		
		
		private void guardar() {
			JOptionPane.showMessageDialog(this, "Datos Guardado");
			VperfilAdmin guardar = new VperfilAdmin(dao);
			guardar.setVisible(true);
			this.dispose();
		}
		}

