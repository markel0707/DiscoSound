package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Dao;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import com.toedter.calendar.JCalendar;

import clase.Usuario;

public class VRegistro1 extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Dao dao;
	private JTextField textDni;
	private JTextField textEmail;
	private JButton btnSiguiente;
	private JButton btnAtras;
	private JCalendar calendario;

	public VRegistro1(Dao dao) {
		setBounds(100, 100, 900, 645);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 128, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("REGISTRO");
		lblNewLabel_1.setBounds(10, 10, 76, 25);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPanel.add(lblNewLabel_1);

		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(288, 30, 93, 13);
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPanel.add(lblDni);

		textDni = new JTextField();
		textDni.setBounds(288, 53, 172, 25);
		textDni.setFont(new Font("Tahoma", Font.BOLD, 16));
		textDni.setColumns(10);
		textDni.setBackground(Color.LIGHT_GRAY);
		contentPanel.add(textDni);

		JLabel lblFechaNac = new JLabel("Fecha de nacimiento");
		lblFechaNac.setBounds(288, 188, 172, 13);
		lblFechaNac.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPanel.add(lblFechaNac);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(288, 113, 93, 13);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPanel.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setBounds(288, 133, 172, 25);
		textEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		textEmail.setColumns(10);
		textEmail.setBackground(Color.LIGHT_GRAY);
		contentPanel.add(textEmail);

		btnAtras = new JButton("Atras");
		btnAtras.setBounds(23, 587, 85, 21);
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAtras.addActionListener(this);
		contentPanel.add(btnAtras);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBounds(775, 587, 101, 21);
		btnSiguiente.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSiguiente.addActionListener(this);
		contentPanel.add(btnSiguiente);

		calendario = new JCalendar();
		calendario.setBounds(287, 225, 187, 134);
		contentPanel.add(calendario);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnSiguiente)) {
			siguiente();
		}

	}

	private void siguiente() {
		String fecha;

		LocalDate ahora;
		Usuario usu = new Usuario();
		int mes = calendario.getCalendar().get(Calendar.MONTH);
		mes = mes + 1;
		String mesString = Integer.toString(mes);

		if (mes < 10) {
			mesString = 0 + mesString;
			System.out.println(mesString);
		}

		fecha = calendario.getCalendar().get(Calendar.YEAR) + "-" + mesString + "-"
				+ calendario.getCalendar().get(Calendar.DAY_OF_MONTH);
		usu.setFechaNac(LocalDate.parse(fecha));

		if (comprobarDni(textDni.getText()) && comprobarEmail(textEmail.getText())
				&& comprobarEdad(usu.getFechaNac())) {
			
			usu.setDni(textDni.getText());
			usu.setEmail(textEmail.getText());
			
			VRegistro2 registro = new VRegistro2(dao, usu);
			registro.setVisible(true);
			this.dispose();

		}

	}

	private boolean comprobarEdad(LocalDate fechaNac) {

		Period periodo = Period.between(fechaNac, LocalDate.now());
		System.out.println("Año" + periodo.getYears() + "Mes" + periodo.getMonths() + "dia" + periodo.getDays());

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
				System.out.println("El DNI es valido");
				return true;
			} else {
				System.out.println("DNI no valido");
				JOptionPane.showMessageDialog(this, "DNI no valido (Los numeros no corresponden con la letra)");
				return false;
			}

		} else {
			JOptionPane.showMessageDialog(this, "DNI no valido ");
			return false;
		}

	}

}
