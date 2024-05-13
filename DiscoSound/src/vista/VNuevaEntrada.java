package vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clase.Administrador;
import clase.Cliente;
import clase.Entrada;
import controlador.Dao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Calendar;

import javax.swing.JTextField;
import java.awt.Color;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.JComboBox;

public class VNuevaEntrada extends JDialog implements ActionListener {

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
	private Administrador admin;
	private JTextField textCantidadConsumis;
	private JTextField textPrecio;
	private JTextField textDiferenciaPrecioMujer;
	private JTextField textNombreEvento;
	private JTextField textNombreDJ;
	private JButton btnAtras;
	private JButton btnGuardar;
	private JCalendar calendario;
	private JDateChooser dateChooser;
	private JComboBox<String> comboBoxCategoria;

	public VNuevaEntrada(Dao dao, Administrador admin) {
		this.dao = dao;
		this.admin = admin;
		setBounds(100, 100, 900, 645);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 168, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblnuevaentrada = new JLabel("Nueva Entrada");
		lblnuevaentrada.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblnuevaentrada.setBounds(181, 11, 233, 54);
		contentPanel.add(lblnuevaentrada);

		JLabel lblNombreDJ = new JLabel("Cantidad consumiciones");
		lblNombreDJ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreDJ.setBounds(44, 252, 189, 13);
		contentPanel.add(lblNombreDJ);

		textCantidadConsumis = new JTextField();
		textCantidadConsumis.setColumns(10);
		textCantidadConsumis.setBackground(Color.LIGHT_GRAY);
		textCantidadConsumis.setBounds(44, 275, 96, 19);
		contentPanel.add(textCantidadConsumis);

		JLabel lblcontrasena = new JLabel("Precio");
		lblcontrasena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcontrasena.setBounds(44, 315, 96, 13);
		contentPanel.add(lblcontrasena);

		textPrecio = new JTextField();
		textPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPrecio.setColumns(10);
		textPrecio.setBackground(Color.LIGHT_GRAY);
		textPrecio.setBounds(44, 338, 96, 19);
		contentPanel.add(textPrecio);

		JLabel lbldifprecio = new JLabel("Diferencia de precio mujer");
		lbldifprecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldifprecio.setBounds(468, 108, 189, 13);
		contentPanel.add(lbldifprecio);

		textDiferenciaPrecioMujer = new JTextField();
		textDiferenciaPrecioMujer.setColumns(10);
		textDiferenciaPrecioMujer.setBackground(Color.LIGHT_GRAY);
		textDiferenciaPrecioMujer.setBounds(468, 132, 96, 19);
		contentPanel.add(textDiferenciaPrecioMujer);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCategoria.setBounds(468, 186, 96, 13);
		contentPanel.add(lblCategoria);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBounds(524, 498, 96, 38);
		btnGuardar.addActionListener(this);
		contentPanel.add(btnGuardar);

		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAtras.setBounds(44, 498, 85, 38);
		btnAtras.addActionListener(this);
		contentPanel.add(btnAtras);

		JLabel lblNombreEvento = new JLabel("Nombre de evento");
		lblNombreEvento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreEvento.setBounds(44, 110, 170, 13);
		contentPanel.add(lblNombreEvento);

		textNombreEvento = new JTextField();
		textNombreEvento.setColumns(10);
		textNombreEvento.setBackground(Color.LIGHT_GRAY);
		textNombreEvento.setBounds(44, 132, 96, 19);
		contentPanel.add(textNombreEvento);

		textNombreDJ = new JTextField();
		textNombreDJ.setColumns(10);
		textNombreDJ.setBackground(Color.LIGHT_GRAY);
		textNombreDJ.setBounds(44, 210, 96, 19);
		contentPanel.add(textNombreDJ);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(468, 296, 189, 30);
		contentPanel.add(dateChooser);

		JLabel lblNombreDJ_2 = new JLabel("Nombre DJ (Opcional)");
		lblNombreDJ_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreDJ_2.setBounds(44, 186, 170, 13);
		contentPanel.add(lblNombreDJ_2);

		JLabel lblNewLabel = new JLabel("Fecha");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(468, 276, 45, 13);
		contentPanel.add(lblNewLabel);

		String[] opciones = { "General", "VIP" };
		comboBoxCategoria = new JComboBox<>(opciones);
		comboBoxCategoria.setSelectedIndex(-1);
		comboBoxCategoria.setBounds(468, 209, 96, 21);
		contentPanel.add(comboBoxCategoria);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAtras)) {
			atras();
		} else if (e.getSource().equals(btnGuardar)) {
			guardar();
		}
	}

	private void atras() {
		VPerfilAdmin perfilad = new VPerfilAdmin(dao, admin);
		perfilad.setVisible(true);
		this.dispose();
	}

	private boolean soloNums(String cantidadConsumo) {

		try {
			Integer.parseInt(cantidadConsumo);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "La cantidad de consumiciones debe ser un numero");
			textCantidadConsumis.setText("");
			return false;
		}

		return true;

	}

	private boolean soloFloats(String precio) {

		try {
			Float.parseFloat(precio);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El campo precio debe ser numerico");
			textPrecio.setText("");
			return false;
		} catch (NullPointerException e1) {
			JOptionPane.showMessageDialog(this, "El campo precio no puede estar vacío");
			textPrecio.setText("");
			return false;
		}

		return true;

	}

	private boolean soloFloatsDiff(String diferenciaPrecio) {

		try {
			Float.parseFloat(diferenciaPrecio);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El campo deferencia de precio debe ser numerico");
			textDiferenciaPrecioMujer.setText("");
			return false;
		} catch (NullPointerException e1) {
			JOptionPane.showMessageDialog(this, "El campo diferencia de precio no puede estar vacío");
			textDiferenciaPrecioMujer.setText("");
			return false;
		}

		return true;

	}

	private boolean fechasCorrecta(String fechas) {

		try {
			// Obtener la fecha actual
			LocalDate fechaActual = LocalDate.now();

			// Parsear la fecha introducida
			LocalDate fechaIngresada = LocalDate.parse(fechas);

			// Comparar la fecha introducida con la fecha actual
			if (fechaIngresada.isBefore(fechaActual)) {
				JOptionPane.showMessageDialog(this, "La fecha introducida no puede ser anterior al día de hoy");
				return false;
			}
		} catch (DateTimeParseException e) {
			System.out.println(fechas);
			JOptionPane.showMessageDialog(this, "El campo fecha debe tener el siguiente formato (aaaa-mm-dd) ");
			return false;
		}

		return true;
	}

	private void guardar() {
		String fecha;

		Entrada ent = new Entrada();

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

		// setear datos
		ent.setNombreDJ(textNombreDJ.getText());
		ent.setNombreEvento(textNombreEvento.getText());
		ent.setCategoria((String) comboBoxCategoria.getSelectedItem());

		if (soloNums(textCantidadConsumis.getText()) && soloFloats(textPrecio.getText())
				&& soloFloatsDiff(textDiferenciaPrecioMujer.getText()) && fechasCorrecta(fecha)) {

			ent.setFecha(LocalDate.parse(fecha));

			ent.setCantidadConsumo(Integer.parseInt(textCantidadConsumis.getText()));

			ent.setDiferenciaPrecioMujer(Float.parseFloat(textDiferenciaPrecioMujer.getText()));

			ent.setPrecio(Float.parseFloat(textPrecio.getText()));

			ent.setCodigoEntrada(generarCodigo());

			dao.registroEntrada(ent, admin);

			// Salir de la aplicacion

			JOptionPane.showMessageDialog(this, "Datos Guardado");
			VPerfilAdmin guardar = new VPerfilAdmin(dao, admin);
			guardar.setVisible(true);
			this.dispose();
		}
	}

	private String generarCodigo() {
		// Este metodo sirve para generar el código de la entrada usando las dos
		// primeras letras de la discoteca seguidas de un guion y un numero
		// autoincrementado

		String dosPrimerasLetras = obtenerDosPrimerasLetrasNombreDiscoteca();
		int ultimoNumero = dao.obtenerUltimoNumeroDeEntrada();
		String numeroFormateado = String.format("%04d", ultimoNumero + 1);
		String codigo = dosPrimerasLetras.toUpperCase() + "-" + numeroFormateado;

		return codigo;

	}

	private String obtenerDosPrimerasLetrasNombreDiscoteca() {
		String nombreDiscoteca = admin.getNomDiscoteca();
		String dosPrimerasLetras = nombreDiscoteca.substring(0, Math.min(nombreDiscoteca.length(), 2));
		return dosPrimerasLetras;
	}
}
