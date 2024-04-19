package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Dao;

public class VInicio extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnIniciarSesion;
	private JButton btnRegistrar;
	private Dao dao;

	
	public VInicio(Dao dao) {
		this.dao=dao;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 645);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBackground(new Color(192, 192, 192));
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRegistrar.setBounds(335, 291, 185, 52);
		btnRegistrar.addActionListener(this);
		contentPane.add(btnRegistrar);
		
		btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setBackground(new Color(192, 192, 192));
		btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnIniciarSesion.setBounds(335, 392, 185, 52);
		btnIniciarSesion.addActionListener(this);
		contentPane.add(btnIniciarSesion);
		
		JLabel lblNewLabel = new JLabel("DiscoSound");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(335, 75, 185, 79);
		contentPane.add(lblNewLabel);
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(btnIniciarSesion)) {
			iniciarSesion();
		}else if(e.getSource().equals(btnRegistrar)) {
			registrar();
		}
	}


	private void iniciarSesion() {
		ViniciarSesion iniciarSesion =new ViniciarSesion(dao);
		iniciarSesion.setVisible(true);
		this.dispose();
		
	}


	private void registrar() {
		VRegistro1 registro=new VRegistro1(dao);
		registro.setVisible(true);
		this.dispose();
		
	}
}
