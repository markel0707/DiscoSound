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
import javax.swing.border.MatteBorder;

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
	private JButton btnAtras;
	private JButton btnGuardar;
	private JCalendar calendario;
	private JDateChooser dateChooser;
	private JComboBox<String> comboBoxCategoria;
	private JTextField textNombreEvento;
	private JTextField textNombreDJ;
	private JTextField textCantidadConsumis;
	private JTextField textPrecio;
	private JTextField textDiferenciaPrecioMujer;

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
		lblnuevaentrada.setBounds(334, 10, 233, 54);
		contentPanel.add(lblnuevaentrada);

		JLabel lblNombreDJ = new JLabel("Cantidad de consumiciones");
		lblNombreDJ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreDJ.setBounds(104, 249, 251, 20);
		contentPanel.add(lblNombreDJ);

		JLabel lblcontrasena = new JLabel("Precio");
		lblcontrasena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcontrasena.setBounds(104, 315, 96, 20);
		contentPanel.add(lblcontrasena);

		JLabel lbldifprecio = new JLabel("Diferencia de precio mujer");
		lbldifprecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldifprecio.setBounds(583, 110, 251, 20);
		contentPanel.add(lbldifprecio);

		JLabel lblCategoria = new JLabel("Categoría");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCategoria.setBounds(583, 186, 178, 20);
		contentPanel.add(lblCategoria);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBounds(747, 548, 110, 36);
		btnGuardar.addActionListener(this);
		contentPanel.add(btnGuardar);

		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAtras.setBounds(29, 548, 110, 36);
		btnAtras.addActionListener(this);
		contentPanel.add(btnAtras);

		JLabel lblNombreEvento = new JLabel("Nombre de evento");
		lblNombreEvento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreEvento.setBounds(104, 110, 170, 20);
		contentPanel.add(lblNombreEvento);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(583, 272, 204, 30);
		contentPanel.add(dateChooser);

		JLabel lblNombreDJ_2 = new JLabel("Nombre DJ (opcional)");
		lblNombreDJ_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreDJ_2.setBounds(104, 186, 204, 20);
		contentPanel.add(lblNombreDJ_2);

		JLabel lblNewLabel = new JLabel("Fecha");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(583, 249, 110, 20);
		contentPanel.add(lblNewLabel);

		String[] opciones = { "General", "VIP" };
		comboBoxCategoria = new JComboBox<>(opciones);
		comboBoxCategoria.setSelectedIndex(-1);
		comboBoxCategoria.setBounds(583, 218, 96, 21);
		contentPanel.add(comboBoxCategoria);

		textNombreEvento = new JTextField();
		textNombreEvento.setOpaque(false);
		textNombreEvento.setForeground(Color.WHITE);
		textNombreEvento.setFont(new Font("Verdana", Font.PLAIN, 17));
		textNombreEvento.setColumns(10);
		textNombreEvento.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		textNombreEvento.setBounds(104, 133, 204, 30);
		contentPanel.add(textNombreEvento);

		textNombreDJ = new JTextField();
		textNombreDJ.setOpaque(false);
		textNombreDJ.setForeground(Color.WHITE);
		textNombreDJ.setFont(new Font("Verdana", Font.PLAIN, 17));
		textNombreDJ.setColumns(10);
		textNombreDJ.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		textNombreDJ.setBounds(104, 209, 204, 30);
		contentPanel.add(textNombreDJ);

		textCantidadConsumis = new JTextField();
		textCantidadConsumis.setOpaque(false);
		textCantidadConsumis.setForeground(Color.WHITE);
		textCantidadConsumis.setFont(new Font("Verdana", Font.PLAIN, 17));
		textCantidadConsumis.setColumns(10);
		textCantidadConsumis.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		textCantidadConsumis.setBounds(104, 272, 204, 30);
		contentPanel.add(textCantidadConsumis);

		textPrecio = new JTextField();
		textPrecio.setOpaque(false);
		textPrecio.setForeground(Color.WHITE);
		textPrecio.setFont(new Font("Verdana", Font.PLAIN, 17));
		textPrecio.setColumns(10);
		textPrecio.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		textPrecio.setBounds(104, 338, 204, 30);
		contentPanel.add(textPrecio);

		textDiferenciaPrecioMujer = new JTextField();
		textDiferenciaPrecioMujer.setOpaque(false);
		textDiferenciaPrecioMujer.setForeground(Color.WHITE);
		textDiferenciaPrecioMujer.setFont(new Font("Verdana", Font.PLAIN, 17));
		textDiferenciaPrecioMujer.setColumns(10);
		textDiferenciaPrecioMujer.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		textDiferenciaPrecioMujer.setBounds(583, 133, 204, 30);
		contentPanel.add(textDiferenciaPrecioMujer);
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

	private boolean soloNums(String cantidadConsumo) throws NumberFormatException {

		try {
			Integer.parseInt(cantidadConsumo);
		} catch (NumberFormatException e) {
			textCantidadConsumis.setText("");
			return false;
		}

		return true;

	}

	private boolean soloFloats(String precio) throws NumberFormatException, NullPointerException {

		try {
			Float.parseFloat(precio);
		} catch (NumberFormatException e) {
			textPrecio.setText("");
			throw new NumberFormatException();
		} catch (NullPointerException e1) {

			textPrecio.setText("");
			throw new NullPointerException();
		}

		return true;

	}

	private boolean soloFloatsDiff(String diferenciaPrecio) throws NumberFormatException, NullPointerException {

		try {
			Float.parseFloat(diferenciaPrecio);
			
		} catch (NumberFormatException e) {
			textDiferenciaPrecioMujer.setText("");
			throw new NumberFormatException();
		} catch (NullPointerException e1) {	
			textDiferenciaPrecioMujer.setText("");
			throw new NullPointerException();
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

	private boolean comboBoxLleno(String opcion) {
		if (opcion == null || opcion.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Seleccione una opción para la categoría");
			return false;
		}
		return true;
	}

	private void guardar() {
		String fecha;

		Entrada ent = new Entrada();

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

		// setear datos
		ent.setNombreDJ(textNombreDJ.getText());
		ent.setNombreEvento(textNombreEvento.getText());

		try {
			if (soloNums(textCantidadConsumis.getText()) && soloFloats(textPrecio.getText())
					&& soloFloatsDiff(textDiferenciaPrecioMujer.getText()) && fechasCorrecta(fecha)
					&& comboBoxLleno((String) comboBoxCategoria.getSelectedItem())) {

				ent.setCategoria((String) comboBoxCategoria.getSelectedItem());
				ent.setFecha(LocalDate.parse(fecha));
				ent.setCantidadConsumo(Integer.parseInt(textCantidadConsumis.getText()));
				ent.setDiferenciaPrecioMujer(Float.parseFloat(textDiferenciaPrecioMujer.getText()));
				ent.setPrecio(Float.parseFloat(textPrecio.getText()));
				ent.setCodigoEntrada(generarCodigo());
				dao.registroEntrada(ent, admin);

				JOptionPane.showMessageDialog(this, "Datos Guardado");
				VPerfilAdmin guardar = new VPerfilAdmin(dao, admin);
				guardar.setVisible(true);
				this.dispose();
			}

		} catch (NumberFormatException n) {
			JOptionPane.showMessageDialog(this, "Hay campos que deben ser numericos");
			
		} catch (NullPointerException n1) {
			JOptionPane.showMessageDialog(this, "Faltan campos por rellenar");
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
