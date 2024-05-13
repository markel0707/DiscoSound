package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clase.Administrador;
import clase.Entrada;
import controlador.Dao;

public class VModificarEntrada2 extends JDialog implements ActionListener {

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
	private JButton btnAtras;
	private JButton btnGuardar;
	private Administrador admin;
	private Entrada entra;
	private JTextField textEvento;
	private JTextField textConsumis;
	private JTextField textDj;
	private JTextField textPrecio;
	private JTextField textDiferenciaPrecio;

	public VModificarEntrada2(Dao dao, Administrador admin, Entrada entra) {
		this.admin = admin;
		this.dao = dao;
		this.entra = entra;
		setBounds(100, 100, 900, 645);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 168, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblnuevaentrada = new JLabel("Modificar Entrada");
		lblnuevaentrada.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblnuevaentrada.setBounds(275, 28, 283, 54);
		contentPanel.add(lblnuevaentrada);

		JLabel lblNombreDJ = new JLabel("Cantidad consumiciones");
		lblNombreDJ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreDJ.setBounds(138, 269, 189, 13);
		contentPanel.add(lblNombreDJ);

		textConsumis = new JTextField();
		textConsumis.setColumns(10);
		textConsumis.setBackground(Color.LIGHT_GRAY);
		textConsumis.setBounds(138, 292, 96, 19);
		contentPanel.add(textConsumis);

		JLabel lblcontrasena = new JLabel("Precio");
		lblcontrasena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcontrasena.setBounds(138, 332, 96, 13);
		contentPanel.add(lblcontrasena);

		textPrecio = new JTextField();
		textPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPrecio.setColumns(10);
		textPrecio.setBackground(Color.LIGHT_GRAY);
		textPrecio.setBounds(138, 355, 96, 19);
		contentPanel.add(textPrecio);

		JLabel lbldifprecio = new JLabel("Diferencia de precio mujer");
		lbldifprecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldifprecio.setBounds(562, 125, 189, 25);
		contentPanel.add(lbldifprecio);

		textDiferenciaPrecio = new JTextField();
		textDiferenciaPrecio.setColumns(10);
		textDiferenciaPrecio.setBackground(Color.LIGHT_GRAY);
		textDiferenciaPrecio.setBounds(562, 149, 96, 19);
		contentPanel.add(textDiferenciaPrecio);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBounds(604, 515, 133, 38);
		btnGuardar.addActionListener(this);
		contentPanel.add(btnGuardar);

		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAtras.setBounds(138, 515, 85, 38);
		btnAtras.addActionListener(this);
		contentPanel.add(btnAtras);

		JLabel lblTipoDeEvento = new JLabel("Tipo de evento");
		lblTipoDeEvento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTipoDeEvento.setBounds(138, 127, 170, 13);
		contentPanel.add(lblTipoDeEvento);

		textEvento = new JTextField();
		textEvento.setColumns(10);
		textEvento.setBackground(Color.LIGHT_GRAY);
		textEvento.setBounds(138, 149, 96, 19);
		contentPanel.add(textEvento);

		textDj = new JTextField();
		textDj.setColumns(10);
		textDj.setBackground(Color.LIGHT_GRAY);
		textDj.setBounds(138, 227, 96, 19);
		contentPanel.add(textDj);

		JLabel lblNombreDJ_2 = new JLabel("Nombre DJ (Opcional)");
		lblNombreDJ_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreDJ_2.setBounds(138, 203, 170, 13);
		contentPanel.add(lblNombreDJ_2);
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
		VModificarEntrada modificar = new VModificarEntrada(dao, admin);
		modificar.setVisible(true);
		this.dispose();
	}

	private boolean soloNums(String cantidadConsumo) {

//		KeyEvent evt = null;
//		int key = evt.getKeyChar(); 
//		
//		boolean numeros = key >= 48 && key <= 57;

//		evt.consume();
		try {
			Integer.parseInt(cantidadConsumo);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "La cantidad de consumiciones debe ser un numero");
			textConsumis.setText("");
			return false;
		}

		return true;

	}

	private boolean soloFloats(String precio) {

//		KeyEvent evt = null;
//		int key = evt.getKeyChar(); 
//		
//		boolean numeros = key >= 48 && key <= 57;

//		evt.consume();

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

//		KeyEvent evt = null;
//		int key = evt.getKeyChar(); 
//		
//		boolean numeros = key >= 48 && key <= 57;

//		evt.consume();

		try {
			Float.parseFloat(diferenciaPrecio);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El campo deferencia de precio debe ser numerico");
			textDiferenciaPrecio.setText("");
			return false;
		} catch (NullPointerException e1) {
			JOptionPane.showMessageDialog(this, "El campo diferencia de precio no puede estar vacío");
			textDiferenciaPrecio.setText("");
			return false;
		}

		return true;

	}

	private void guardar() {
		entra.setNombreEvento(textEvento.getText());

		entra.setNombreDJ(textDj.getText());

		if (soloNums(textConsumis.getText()) && soloFloats(textPrecio.getText())
				&& soloFloatsDiff(textDiferenciaPrecio.getText())) {

			entra.setCantidadConsumo(Integer.parseInt(textConsumis.getText()));

			entra.setPrecio(Float.parseFloat(textPrecio.getText()));

			entra.setDiferenciaPrecioMujer(Float.parseFloat(textDiferenciaPrecio.getText()));

			dao.modificarEntrada(entra);
			JOptionPane.showMessageDialog(this, "Datos Guardado");
			VPerfilAdmin guardar = new VPerfilAdmin(dao, admin);
			guardar.setVisible(true);
			this.dispose();
		}
	}
}
