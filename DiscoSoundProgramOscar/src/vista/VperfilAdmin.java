package vista;

import java.awt.BorderLayout;
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

import javax.swing.JTextField;
import java.awt.Color;

public class VperfilAdmin extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField datoNombre;
	private Dao dao;
	private JButton btnNuevaEntrada;
	private JButton btnModificarEntrada;
	private JButton btnCerrarSesion;
	
	
	public VperfilAdmin(Dao dao) {
		setBounds(100, 100, 900, 645);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblPerfil.setBounds(356, 82, 112, 57);
		contentPane.add(lblPerfil);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombre.setBounds(57, 210, 106, 31);
		contentPane.add(lblNombre);
		
		datoNombre = new JTextField();
		datoNombre.setBackground(Color.LIGHT_GRAY);
		datoNombre.setBounds(57, 242, 176, 31);
		contentPane.add(datoNombre);
		datoNombre.setColumns(10);
		
		btnNuevaEntrada = new JButton("Nueva entrada");
		btnNuevaEntrada.setBackground(Color.LIGHT_GRAY);
		btnNuevaEntrada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNuevaEntrada.setBounds(36, 540, 127, 43);
		btnNuevaEntrada.addActionListener(this);
		contentPane.add(btnNuevaEntrada);
		
		btnModificarEntrada = new JButton("Modificar entrada");
		btnModificarEntrada.setBackground(Color.LIGHT_GRAY);
		btnModificarEntrada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificarEntrada.setBounds(706, 540, 141, 43);
		btnModificarEntrada.addActionListener(this);
		contentPane.add(btnModificarEntrada);
		
		btnCerrarSesion = new JButton("Cerrar sesion");
		btnCerrarSesion.setBackground(Color.LIGHT_GRAY);
		btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCerrarSesion.setBounds(732, 23, 115, 21);
		btnCerrarSesion.addActionListener(this);
		contentPane.add(btnCerrarSesion);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(btnNuevaEntrada)) {
			nuevaEntrada();
		} else if(e.getSource().equals(btnModificarEntrada)) {
			modificarEntrada();
		} else if(e.getSource().equals(btnCerrarSesion)) {
			cerrarSesion();
		}
	}
	private void cerrarSesion() {
		// TODO Auto-generated method stub
		VInicio cerrarSesion = new VInicio(dao);
		cerrarSesion.setVisible(true);
		this.dispose();
		
	}
	private void modificarEntrada() {
		// TODO Auto-generated method stub
		VModificarentrada modificarEntrada = new VModificarentrada(dao);
		modificarEntrada.setVisible(true);
		this.dispose();
	}
	private void nuevaEntrada() {
		// TODO Auto-generated method stub
		VNuevaentrada nuevaEntrada = new VNuevaentrada(dao);
		nuevaEntrada.setVisible(true);
		this.dispose();
	}
}
