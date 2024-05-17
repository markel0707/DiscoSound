package vista;

import java.awt.BorderLayout;


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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.border.MatteBorder;

/**
 * Esta clase representa la segunda ventana de registro para nuevos usuarios en el sistema.
 * Permite al usuario ingresar información adicional, como nombre, apellido, contraseña y género.
 * Proporciona validaciones para asegurar que los datos ingresados sean correctos.
 * También gestiona la navegación entre ventanas, permitiendo al usuario avanzar al siguiente paso del registro o regresar a la ventana anterior.
 */

public class VRegistro2 extends JDialog implements ActionListener {

	/**
	 * 
	 */
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
	private ButtonGroup bgGenero = new ButtonGroup();
	private JCheckBox checkBoxHombre;
	private JCheckBox checkBoxMujer;
	private JButton btnAtras;
	private JButton btnSiguiente;
	private Usuario usu;
	private JTextField textNombre;
	private JTextField textApellido;
	private JPasswordField textContraseina;
	private JLabel lblRegistro;
	
    /**
     * Crea una nueva ventana de registro.
     * @param dao El objeto DAO utilizado para acceder a la capa de datos.
     * @param usu El objeto de usuario que se está registrando.
     */

	public VRegistro2(Dao dao, Usuario usu) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VRegistro2.class.getResource("/img/logoapp.png")));
		setTitle("Registro");
		this.dao = dao;
		this.usu = usu;
		setBounds(100, 100, 900, 645);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 128, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(334, 121, 93, 13);
		contentPanel.add(lblNewLabel);

		checkBoxHombre = new JCheckBox("Hombre");
		checkBoxHombre.setOpaque(false);
		checkBoxHombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkBoxHombre.setBackground(UIManager.getColor("Button.shadow"));
		checkBoxHombre.setBounds(334, 416, 93, 21);
		contentPanel.add(checkBoxHombre);

		checkBoxMujer = new JCheckBox("Mujer");
		checkBoxMujer.setOpaque(false);
		checkBoxMujer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkBoxMujer.setBackground(UIManager.getColor("Button.shadow"));
		checkBoxMujer.setBounds(334, 440, 93, 21);
		contentPanel.add(checkBoxMujer);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblApellido.setBounds(334, 213, 93, 25);
		contentPanel.add(lblApellido);

		JLabel lblContraseina = new JLabel("Contraseña");
		lblContraseina.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContraseina.setBounds(334, 309, 127, 21);
		contentPanel.add(lblContraseina);

		JLabel lblGenero = new JLabel("Género");
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGenero.setBounds(334, 396, 93, 13);
		contentPanel.add(lblGenero);

		btnAtras = new JButton("Atras");
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAtras.setBounds(29, 548, 110, 36);
		btnAtras.addActionListener(this);
		contentPanel.add(btnAtras);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSiguiente.setBounds(747, 548, 110, 36);
		btnSiguiente.addActionListener(this);
		contentPanel.add(btnSiguiente);

		bgGenero.add(checkBoxHombre);
		bgGenero.add(checkBoxMujer);
		
		textNombre = new JTextField();
		textNombre.setOpaque(false);
		textNombre.setForeground(Color.WHITE);
		textNombre.setFont(new Font("Verdana", Font.PLAIN, 17));
		textNombre.setColumns(10);
		textNombre.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		textNombre.setBounds(334, 145, 204, 30);
		contentPanel.add(textNombre);
		
		textApellido = new JTextField();
		textApellido.setOpaque(false);
		textApellido.setForeground(Color.WHITE);
		textApellido.setFont(new Font("Verdana", Font.PLAIN, 17));
		textApellido.setColumns(10);
		textApellido.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		textApellido.setBounds(334, 249, 204, 30);
		contentPanel.add(textApellido);
		
		textContraseina = new JPasswordField();
		textContraseina.setOpaque(false);
		textContraseina.setForeground(Color.WHITE);
		textContraseina.setFont(new Font("Verdana", Font.PLAIN, 17));
		textContraseina.setColumns(10);
		textContraseina.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		textContraseina.setBounds(334, 341, 204, 30);
		contentPanel.add(textContraseina);
		
		lblRegistro = new JLabel("Registro");
		lblRegistro.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblRegistro.setBounds(360, 10, 147, 54);
		contentPanel.add(lblRegistro);

	}
	
    /**
     * Maneja los eventos de acción generados por los botones de la interfaz.
     * @param e El evento de acción generado.
     */

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(btnSiguiente)) {
			siguiente();
		} else if (e.getSource().equals(btnAtras)) {
			atras();
		}
	};

	
    /**
     * Realiza las validaciones necesarias y avanza al siguiente paso del registro si los datos son válidos.
     */
	private void siguiente() {

		boolean genero = false;
		usu.setNombre(textNombre.getText());
		usu.setApellido(textApellido.getText());
		String contra = "";
		for (int i = 0; i < textContraseina.getPassword().length; i++) {
			contra 
			= contra + String.valueOf(textContraseina.getPassword()[i]);

		}
		usu.setContraseina(contra);

		if (checkBoxHombre.isSelected()) {
			((Cliente) usu).setGenero("hombre");
			genero = true;
		} else if (checkBoxMujer.isSelected()) {
			((Cliente) usu).setGenero("mujer");
			genero = true;
		}

		if (usu.getNombre().equals("") || usu.getApellido().equals("") || usu.getContraseina().equals("")
				|| !genero) {
			JOptionPane.showMessageDialog(this, "Faltan campos por rellenar");
		} else {
			
				dao.registro(usu);
				
				VInicio siguiente = new VInicio(dao);
				siguiente.setVisible(true);
				this.dispose();
			
		}

	}
    /**
     * Registra al usuario en el sistema si todos los campos obligatorios están completos y el género ha sido seleccionado.
     * Si el registro es exitoso, avanza a la ventana de inicio del sistema.
     */

	private void atras() {
		VRegistro1 atras = new VRegistro1(dao);
		atras.setVisible(true);
		this.dispose();
	}
}
