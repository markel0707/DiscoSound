package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clase.Usuario;
import controlador.Dao;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import java.awt.Color;

public class VCambiarDatos extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            GradientPaint verticalGradient = new GradientPaint(0, 0, Color.getHSBColor(266.0f, 89.0f, 99.0f), 0, getHeight(), Color.getHSBColor(264.8f, 38.30f, 89.0f));
            g2d.setPaint(verticalGradient);

            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    };
	private JTextField datoNombre;
	private JTextField datoContrasena;
	private JTextField DatoEmail;
	private JTextField datoApellido;
	private Dao dao;
	private JButton btnGuardar;
	private JButton btnAtras;
	private Usuario usu;
	
	public VCambiarDatos(Dao dao, Usuario usu) {
		setBounds(100, 100, 900, 645);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 168, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCambiarDatos = new JLabel("Cambiar datos");
		lblCambiarDatos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblCambiarDatos.setBounds(315, 11, 233, 54);
		contentPanel.add(lblCambiarDatos);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombre.setBounds(178, 108, 96, 13);
		contentPanel.add(lblNombre);
		
		datoNombre = new JTextField();
		datoNombre.setColumns(10);
		datoNombre.setBackground(Color.LIGHT_GRAY);
		datoNombre.setBounds(178, 132, 96, 19);
		contentPanel.add(datoNombre);
		
		JLabel lblcontrasena = new JLabel("Contraseña");
		lblcontrasena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcontrasena.setBounds(178, 274, 96, 13);
		contentPanel.add(lblcontrasena);
		
		datoContrasena = new JTextField();
		datoContrasena.setFont(new Font("Tahoma", Font.PLAIN, 14));
		datoContrasena.setColumns(10);
		datoContrasena.setBackground(Color.LIGHT_GRAY);
		datoContrasena.setBounds(178, 298, 96, 19);
		contentPanel.add(datoContrasena);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(695, 108, 96, 13);
		contentPanel.add(lblEmail);
		
		DatoEmail = new JTextField();
		DatoEmail.setColumns(10);
		DatoEmail.setBackground(Color.LIGHT_GRAY);
		DatoEmail.setBounds(644, 132, 96, 19);
		contentPanel.add(DatoEmail);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblApellido.setBounds(667, 274, 96, 13);
		contentPanel.add(lblApellido);
		
		datoApellido = new JTextField();
		datoApellido.setColumns(10);
		datoApellido.setBackground(Color.LIGHT_GRAY);
		datoApellido.setBounds(644, 299, 96, 19);
		contentPanel.add(datoApellido);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBounds(644, 498, 133, 38);
		btnGuardar.addActionListener(this);
		contentPanel.add(btnGuardar);
		
		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAtras.setBounds(178, 498, 85, 38);
		btnAtras.addActionListener(this);
		contentPanel.add(btnAtras);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnGuardar)) {
			guardar();
		} else if (e.getSource().equals(btnAtras)) {
			atras();
		}
	}
	private void atras() {
		// TODO Auto-generated method stub
		VPerfilUsuario atras = new VPerfilUsuario(dao, usu);
		atras.setVisible(true);
		this.dispose();
	}
	private void guardar() {
		// TODO Auto-generated method stub
		VPerfilUsuario guardar = new VPerfilUsuario(dao, usu);
		guardar.setVisible(true);
		this.dispose();
	}
}
