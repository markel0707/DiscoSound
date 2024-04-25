package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clase.Usuario;
import controlador.Dao;
import java.awt.Toolkit;

public class VInicio extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                GradientPaint verticalGradient = new GradientPaint(0, 0, Color.getHSBColor(266.0f, 89.0f, 99.0f), 0, getHeight(), Color.getHSBColor(264.8f, 38.30f, 89.0f));
                g2d.setPaint(verticalGradient);

                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };;
	private JButton btnIniciarSesion;
	private JButton btnRegistrar;
	private Dao dao;
	
	private Graphics2D g2d;
	private Usuario usu;
	


	
	public VInicio(Dao dao, Usuario usu) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\1dam\\Desktop\\DiscoSound\\DiscoSound\\DiscoSoundProgramOscar\\src\\img\\logoApp\\logoapp.png"));
		this.dao=dao;
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 872, 654);
        
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setBackground(new Color(192, 192, 192));
		btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnIniciarSesion.setBounds(335, 392, 185, 52);
		btnIniciarSesion.addActionListener(this);
		contentPane.add(btnIniciarSesion);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBackground(new Color(192, 192, 192));
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRegistrar.setBounds(335, 291, 185, 52);
		btnRegistrar.addActionListener(this);
		contentPane.add(btnRegistrar);
		

		
		
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
		VIniciarSesion iniciarSesion =new VIniciarSesion(dao, usu);
		iniciarSesion.setVisible(true);
		this.dispose();
		
	}


	private void registrar() {
		VRegistro1 registro=new VRegistro1(dao);
		registro.setVisible(true);
		this.dispose();
		
	}
}