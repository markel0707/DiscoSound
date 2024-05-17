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
import javax.swing.border.MatteBorder;

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
	private Entrada entradaCompleta;
	private JLabel lblNombreEvento;
	private JLabel lblNombreDJ_1;
	private JLabel lblNombreDJ;
	private JLabel lblcontrasena;
	private JLabel lbldifprecio;
	private JTextField textEvento;
	private JTextField textDj;
	private JTextField textConsumis;
	private JTextField textPrecio;
	private JTextField textDiferenciaPrecio;

	public VModificarEntrada2(Dao dao, Administrador admin, Entrada entra) {
		this.admin = admin;
		this.dao = dao;
		this.entra = entra;
		this.entradaCompleta = dao.obtenerEntrada(entra);
		setBounds(100, 100, 900, 645);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 168, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblnuevaentrada = new JLabel("Modificar Entrada");
		lblnuevaentrada.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblnuevaentrada.setBounds(304, 24, 283, 54);
		contentPanel.add(lblnuevaentrada);

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

		lblNombreEvento = new JLabel("Nombre de evento");
		lblNombreEvento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreEvento.setBounds(104, 110, 170, 13);
		contentPanel.add(lblNombreEvento);

		lblNombreDJ_1 = new JLabel("Nombre DJ (opcional)");
		lblNombreDJ_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreDJ_1.setBounds(104, 199, 204, 13);
		contentPanel.add(lblNombreDJ_1);

		lblNombreDJ = new JLabel("Cantidad de consumiciones");
		lblNombreDJ.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreDJ.setBounds(104, 264, 251, 13);
		contentPanel.add(lblNombreDJ);

		lblcontrasena = new JLabel("Precio");
		lblcontrasena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcontrasena.setBounds(583, 199, 96, 13);
		contentPanel.add(lblcontrasena);

		lbldifprecio = new JLabel("Diferencia precio mujer");
		lbldifprecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldifprecio.setBounds(583, 110, 251, 13);
		contentPanel.add(lbldifprecio);

		textEvento = new JTextField();
		textEvento.setOpaque(false);
		textEvento.setForeground(Color.WHITE);
		textEvento.setFont(new Font("Verdana", Font.PLAIN, 17));
		textEvento.setColumns(10);
		textEvento.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		textEvento.setBounds(104, 133, 204, 30);
		textEvento.setText(entradaCompleta.getNombreEvento());
		contentPanel.add(textEvento);

		textDj = new JTextField();
		textDj.setOpaque(false);
		textDj.setForeground(Color.WHITE);
		textDj.setFont(new Font("Verdana", Font.PLAIN, 17));
		textDj.setColumns(10);
		textDj.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		textDj.setBounds(104, 209, 204, 30);
		textDj.setText(entradaCompleta.getNombreDJ());
		contentPanel.add(textDj);

		textConsumis = new JTextField();
		textConsumis.setOpaque(false);
		textConsumis.setForeground(Color.WHITE);
		textConsumis.setFont(new Font("Verdana", Font.PLAIN, 17));
		textConsumis.setColumns(10);
		textConsumis.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		textConsumis.setBounds(104, 272, 204, 30);
		textConsumis.setText(String.valueOf(entradaCompleta.getCantidadConsumo()));
		contentPanel.add(textConsumis);

		textPrecio = new JTextField();
		textPrecio.setOpaque(false);
		textPrecio.setForeground(Color.WHITE);
		textPrecio.setFont(new Font("Verdana", Font.PLAIN, 17));
		textPrecio.setColumns(10);
		textPrecio.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		textPrecio.setBounds(583, 209, 204, 30);
		textPrecio.setText(String.valueOf(entradaCompleta.getPrecio()));
		contentPanel.add(textPrecio);

		textDiferenciaPrecio = new JTextField();
		textDiferenciaPrecio.setOpaque(false);
		textDiferenciaPrecio.setForeground(Color.WHITE);
		textDiferenciaPrecio.setFont(new Font("Verdana", Font.PLAIN, 17));
		textDiferenciaPrecio.setColumns(10);
		textDiferenciaPrecio.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		textDiferenciaPrecio.setBounds(583, 133, 204, 30);
		textDiferenciaPrecio.setText(String.valueOf(entradaCompleta.getDiferenciaPrecioMujer()));
		contentPanel.add(textDiferenciaPrecio);
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

	private boolean soloNums(String cantidadConsumo)throws NumberFormatException {
		try {
			Integer.parseInt(cantidadConsumo);
		} catch (NumberFormatException e) {
			textConsumis.setText("");
			throw new NumberFormatException();
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

	public boolean soloFloatsDiff(String diferenciaPrecio) throws NumberFormatException, NullPointerException {
		try {
			Float.parseFloat(diferenciaPrecio);
		} catch (NumberFormatException e) {
			textDiferenciaPrecio.setText("");
			throw new NumberFormatException();

		} catch (NullPointerException e1) {
			textDiferenciaPrecio.setText("");
			throw new NullPointerException();
		}
		return true;
	}

	private void guardar() {
		entra.setNombreEvento(textEvento.getText());

		entra.setNombreDJ(textDj.getText());

		try {
			if (soloNums(textConsumis.getText()) && soloFloats(textPrecio.getText())
					&& soloFloatsDiff(textDiferenciaPrecio.getText())) {

				entra.setCantidadConsumo(Integer.parseInt(textConsumis.getText()));
				entra.setPrecio(Float.parseFloat(textPrecio.getText()));
				entra.setDiferenciaPrecioMujer(Float.parseFloat(textDiferenciaPrecio.getText()));

				dao.modificarEntrada(entra);
				JOptionPane.showMessageDialog(this, "Entrada modificada");
				VPerfilAdmin guardar = new VPerfilAdmin(dao, admin);
				guardar.setVisible(true);
				this.dispose();
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Hay campos que deberian ser numericos");
			
		} catch (NullPointerException e1) {
			JOptionPane.showMessageDialog(this, "Los campos no pueden estar vacíos");
			
		}
	}
}
