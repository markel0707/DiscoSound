package vista;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Dao;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import clase.Cliente;
import clase.Usuario;
import javax.swing.border.MatteBorder;

public class VRegistro1 extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel() {
		private static final long serialVersionUID = 1L;
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;

			GradientPaint verticalGradient = new GradientPaint(0, 0, Color.getHSBColor(266.0f, 89.0f, 99.0f), 0,
					getHeight(), Color.getHSBColor(264.8f, 38.30f, 89.0f));
			g2d.setPaint(verticalGradient);

			g2d.fillRect(0, 0, getWidth(), getHeight());
		}
	};
	private Dao dao;
	private JButton btnSiguiente;
	private JButton btnAtras;
	private JCalendar calendario;
	private Usuario usu;
	private JDateChooser dateChooser;
	private JTextField textDni;
	private JTextField textEmail;
	private JTextField textUsuario;

	public VRegistro1(Dao dao) {
		setTitle("Registro");
		this.dao = dao;
		setBounds(100, 100, 900, 645);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 128, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(331, 142, 93, 13);
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPanel.add(lblDni);

		JLabel lblFechaNac = new JLabel("FECHA DE NACIMIENTO");
		lblFechaNac.setBounds(331, 385, 201, 13);
		lblFechaNac.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPanel.add(lblFechaNac);

		JLabel lblEmail = new JLabel("ÉMAIL");
		lblEmail.setBounds(331, 231, 93, 13);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPanel.add(lblEmail);

		btnAtras = new JButton("Atras");
		btnAtras.setBounds(29, 548, 110, 36);
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAtras.addActionListener(this);
		contentPanel.add(btnAtras);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBounds(747, 548, 110, 36);
		btnSiguiente.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSiguiente.addActionListener(this);
		contentPanel.add(btnSiguiente);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(331, 419, 189, 30);
		contentPanel.add(dateChooser);

		JLabel lblNomUsu = new JLabel("USUARIO");
		lblNomUsu.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNomUsu.setBounds(331, 311, 93, 13);
		contentPanel.add(lblNomUsu);
		
		textDni = new JTextField();
		textDni.setOpaque(false);
		textDni.setForeground(Color.WHITE);
		textDni.setFont(new Font("Verdana", Font.PLAIN, 17));
		textDni.setColumns(10);
		textDni.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		textDni.setBounds(331, 168, 204, 30);
		contentPanel.add(textDni);
		
		textEmail = new JTextField();
		textEmail.setOpaque(false);
		textEmail.setForeground(Color.WHITE);
		textEmail.setFont(new Font("Verdana", Font.PLAIN, 17));
		textEmail.setColumns(10);
		textEmail.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		textEmail.setBounds(331, 255, 204, 30);
		contentPanel.add(textEmail);
		
		textUsuario = new JTextField();
		textUsuario.setOpaque(false);
		textUsuario.setForeground(Color.WHITE);
		textUsuario.setFont(new Font("Verdana", Font.PLAIN, 17));
		textUsuario.setColumns(10);
		textUsuario.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		textUsuario.setBounds(331, 335, 204, 30);
		contentPanel.add(textUsuario);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnSiguiente)) {
			siguiente();
		} else if (e.getSource().equals(btnAtras)) {
			atras();
		}
	}

	private void siguiente() {
		String fecha;

		usu = new Cliente();
		
		while (dateChooser.getDate() == null) {
			JOptionPane.showMessageDialog(this, "El calendario no puede estar vacío");
			return;
		}
		
		int dia = dateChooser.getCalendar().get(Calendar.DAY_OF_MONTH);
		int mes = dateChooser.getCalendar().get(Calendar.MONTH);
		mes = mes + 1;
		String mesString = Integer.toString(mes);
		String diaString = Integer.toString(dia);

		if (mes < 10) {
			mesString = 0 + mesString;
		}
		if (dia < 10) {
			diaString = 0 + diaString;
		}

		fecha = dateChooser.getCalendar().get(Calendar.YEAR) + "-" + mesString + "-" + diaString;
		usu.setFechaNac(LocalDate.parse(fecha));

		if (comprobarDni(textDni.getText()) && comprobarEmail(textEmail.getText())
				&& comprobarEdad(usu.getFechaNac())) {

			switch (dao.comprobarUsuario(textDni.getText(), textEmail.getText(), textUsuario.getText())) {
			case 0:
				JOptionPane.showMessageDialog(this, "El DNI ya existe");
				break;
			case 1:
				JOptionPane.showMessageDialog(this, "El email ya existe");
				break;
			case 2:
				JOptionPane.showMessageDialog(this, "El nombre de usuario ya existe");
				break;
			case 3:
				usu.setDni(textDni.getText());
				usu.setEmail(textEmail.getText());
				usu.setNomUsu(textUsuario.getText());
				if (!usu.getNomUsu().equals("")) {
					VRegistro2 registro = new VRegistro2(dao, usu);
					registro.setVisible(true);
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Faltan campos por rellenar");
				}

				break;

			}

		}

	}

	private boolean comprobarEdad(LocalDate fechaNac) {

		Period periodo = Period.between(fechaNac, LocalDate.now());

		if (periodo.getYears() >= 18) {
			return true;
		} else {
			JOptionPane.showMessageDialog(this, "Los menores de edad no pueden acceder");
			return false;
		}

	}

	private boolean comprobarEmail(String email) {

		Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
		Matcher matcher = pattern.matcher(email);

		if (matcher.matches()) {

			return true;
		} else {
			JOptionPane.showMessageDialog(this, "Email no valido");
			return false;
		}

	}

	private boolean comprobarDni(String dni) {
		char[] arrayLetra = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
				'H', 'L', 'C', 'K', 'E' };

		char letra;
		int dniNoLetra;
		if (dni.length() == 9) {

			letra = dni.charAt(8);
			letra = Character.toUpperCase(letra);

			dniNoLetra = Integer.parseInt(dni.substring(0, 8));

			dniNoLetra = dniNoLetra % 23;
			if (arrayLetra[dniNoLetra] == letra) {
				return true;
			} else {

				JOptionPane.showMessageDialog(this, "DNI no valido (Los numeros no corresponden con la letra)");
				return false;
			}

		} else {
			JOptionPane.showMessageDialog(this, "DNI no valido ");
			return false;
		}

	}

	private void atras() {
		VInicio atras = new VInicio(dao);
		atras.setVisible(true);
		this.dispose();
	}
}
