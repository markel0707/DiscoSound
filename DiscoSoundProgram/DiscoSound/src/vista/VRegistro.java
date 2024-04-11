package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import modelo.Dao;

public class VRegistro extends JDialog {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_6;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private Dao dao;

	private static final long serialVersionUID = 1L;
	public VRegistro(Dao dao) {
		this.dao=dao;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 645);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField.setColumns(10);
		textField.setBounds(288, 57, 172, 25);
		contentPane.add(textField);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(288, 34, 93, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("REGISTRO");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 0, 76, 25);
		contentPane.add(lblNewLabel_1);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Hombre");
		chckbxNewCheckBox.setBackground(UIManager.getColor("CheckBox.shadow"));
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxNewCheckBox.setBounds(288, 498, 93, 21);
		contentPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxMujer = new JCheckBox("Mujer");
		chckbxMujer.setBackground(UIManager.getColor("CheckBox.shadow"));
		chckbxMujer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxMujer.setBounds(288, 521, 93, 21);
		contentPane.add(chckbxMujer);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblApellido.setBounds(288, 93, 93, 25);
		contentPane.add(lblApellido);
		
		textField_6 = new JTextField();
		textField_6.setBackground(Color.LIGHT_GRAY);
		textField_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_6.setColumns(10);
		textField_6.setBounds(288, 114, 172, 25);
		contentPane.add(textField_6);
		
		JLabel lblNewLabel_8_1 = new JLabel("DNI");
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_8_1.setBounds(288, 160, 93, 13);
		contentPane.add(lblNewLabel_8_1);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(288, 183, 172, 25);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_8_1_1 = new JLabel("Fecha de nacimiento");
		lblNewLabel_8_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_8_1_1.setBounds(288, 228, 172, 13);
		contentPane.add(lblNewLabel_8_1_1);
		
		textField_2 = new JTextField();
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(288, 251, 172, 25);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_8_1_1_1 = new JLabel("Email");
		lblNewLabel_8_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_8_1_1_1.setBounds(288, 286, 93, 13);
		contentPane.add(lblNewLabel_8_1_1_1);
		
		textField_3 = new JTextField();
		textField_3.setBackground(Color.LIGHT_GRAY);
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_3.setColumns(10);
		textField_3.setBounds(288, 309, 172, 25);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setBackground(Color.LIGHT_GRAY);
		textField_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_4.setColumns(10);
		textField_4.setBounds(288, 378, 172, 25);
		contentPane.add(textField_4);
		
		JLabel lblNewLabel_8_1_1_1_1 = new JLabel("Usuario");
		lblNewLabel_8_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_8_1_1_1_1.setBounds(288, 355, 93, 13);
		contentPane.add(lblNewLabel_8_1_1_1_1);
		
		JLabel lblNewLabel_8_1_1_1_1_1 = new JLabel("Contraseña");
		lblNewLabel_8_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_8_1_1_1_1_1.setBounds(288, 421, 93, 13);
		contentPane.add(lblNewLabel_8_1_1_1_1_1);
		
		textField_5 = new JTextField();
		textField_5.setBackground(Color.LIGHT_GRAY);
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField_5.setColumns(10);
		textField_5.setBounds(288, 444, 172, 25);
		contentPane.add(textField_5);
		
		JLabel lblNewLabel_8_1_1_1_1_1_1 = new JLabel("Genero");
		lblNewLabel_8_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_8_1_1_1_1_1_1.setBounds(288, 479, 93, 13);
		contentPane.add(lblNewLabel_8_1_1_1_1_1_1);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAtras.setBounds(23, 577, 85, 21);
		contentPane.add(btnAtras);
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSiguiente.setBounds(775, 577, 101, 21);
		contentPane.add(btnSiguiente);
	}

}
