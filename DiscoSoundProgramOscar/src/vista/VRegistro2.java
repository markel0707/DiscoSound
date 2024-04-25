package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clase.Cliente;
import clase.Usuario;
import controlador.Dao;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.UIManager;

public class VRegistro2 extends JDialog implements ActionListener {

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
	private Dao dao;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textContraseina;
	private ButtonGroup bgGenero = new ButtonGroup();
	private JCheckBox checkBoxHombre;
	private JCheckBox checkBoxMujer;
	private JButton btnAtras;
	private JButton btnSiguiente;
	private Usuario usu;

	public VRegistro2(Dao dao, Usuario usu) {
		this.dao=dao;
		this.usu=usu;
		setBounds(100, 100, 900, 645);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 128, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		textNombre = new JTextField();
		textNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		textNombre.setColumns(10);
		textNombre.setBackground(Color.LIGHT_GRAY);
		textNombre.setBounds(334, 144, 172, 25);
		contentPanel.add(textNombre);

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(334, 121, 93, 13);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("REGISTRO");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(0, 0, 76, 25);
		contentPanel.add(lblNewLabel_1);

		checkBoxHombre = new JCheckBox("Hombre");
		checkBoxHombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkBoxHombre.setBackground(UIManager.getColor("Button.shadow"));
		checkBoxHombre.setBounds(334, 393, 93, 21);
		contentPanel.add(checkBoxHombre);

		checkBoxMujer = new JCheckBox("Mujer");
		checkBoxMujer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkBoxMujer.setBackground(UIManager.getColor("Button.shadow"));
		checkBoxMujer.setBounds(334, 416, 93, 21);
		contentPanel.add(checkBoxMujer);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblApellido.setBounds(334, 179, 93, 25);
		contentPanel.add(lblApellido);

		textApellido = new JTextField();
		textApellido.setFont(new Font("Tahoma", Font.BOLD, 16));
		textApellido.setColumns(10);
		textApellido.setBackground(Color.LIGHT_GRAY);
		textApellido.setBounds(334, 214, 172, 25);
		contentPanel.add(textApellido);

		JLabel lblContraseina = new JLabel("Contraseña");
		lblContraseina.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblContraseina.setBounds(334, 317, 93, 13);
		contentPanel.add(lblContraseina);

		textContraseina = new JTextField();
		textContraseina.setFont(new Font("Tahoma", Font.BOLD, 16));
		textContraseina.setColumns(10);
		textContraseina.setBackground(Color.LIGHT_GRAY);
		textContraseina.setBounds(334, 339, 172, 25);
		contentPanel.add(textContraseina);

		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGenero.setBounds(334, 374, 93, 13);
		contentPanel.add(lblGenero);

		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAtras.setBounds(13, 577, 85, 21);
		btnAtras.addActionListener(this);
		contentPanel.add(btnAtras);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSiguiente.setBounds(765, 577, 101, 21);
		btnSiguiente.addActionListener(this);
		contentPanel.add(btnSiguiente);

		bgGenero.add(checkBoxHombre);
		bgGenero.add(checkBoxMujer);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(btnSiguiente)) {
			siguiente();
		} else if (e.getSource().equals(btnAtras)) {
			atras();
		}
	};

	private void siguiente() {

		usu.setNombre(textNombre.getText());
		usu.setApellido(textApellido.getText());
		usu.setContraseina(textContraseina.getText());
		if (checkBoxHombre.isSelected()) {
			((Cliente) usu).setGenero("hombre");
		} else if (checkBoxMujer.isSelected()) {
			((Cliente) usu).setGenero("mujer");
		}
		if (usu.getContraseina().equals("abcd*1234")) {
			JOptionPane.showMessageDialog(this, "No puedes usar esta contraseña");
		} else {
			dao.registro(usu);
			VPerfilUsuario siguiente = new VPerfilUsuario(dao, usu);
			siguiente.setVisible(true);
			this.dispose();
		}

	}

	private void atras() {
		VRegistro1 atras = new VRegistro1(dao);
		atras.setVisible(true);
		this.dispose();
	}

}