package ventanadisco;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Dao;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.UIManager;

public class Vregistro2 extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private Dao dao;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textUsuario;
	private JTextField textContraseña;
	
	public Vregistro2(Dao dao) {
		setBounds(100, 100, 900, 645);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 128, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textNombre = new JTextField();
			textNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
			textNombre.setColumns(10);
			textNombre.setBackground(Color.LIGHT_GRAY);
			textNombre.setBounds(334, 144, 172, 25);
			contentPanel.add(textNombre);
		}
		{
			JLabel lblNewLabel = new JLabel("Nombre");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNewLabel.setBounds(334, 121, 93, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("REGISTRO");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_1.setBounds(0, 0, 76, 25);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JCheckBox CheckBoxHombre = new JCheckBox("Hombre");
			CheckBoxHombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
			CheckBoxHombre.setBackground(UIManager.getColor("Button.shadow"));
			CheckBoxHombre.setBounds(334, 393, 93, 21);
			contentPanel.add(CheckBoxHombre);
		}
		{
			JCheckBox CheckBoxMujer = new JCheckBox("Mujer");
			CheckBoxMujer.setFont(new Font("Tahoma", Font.PLAIN, 14));
			CheckBoxMujer.setBackground(UIManager.getColor("Button.shadow"));
			CheckBoxMujer.setBounds(334, 416, 93, 21);
			contentPanel.add(CheckBoxMujer);
		}
		{
			JLabel lblApellido = new JLabel("Apellido");
			lblApellido.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblApellido.setBounds(334, 179, 93, 25);
			contentPanel.add(lblApellido);
		}
		{
			textApellido = new JTextField();
			textApellido.setFont(new Font("Tahoma", Font.BOLD, 16));
			textApellido.setColumns(10);
			textApellido.setBackground(Color.LIGHT_GRAY);
			textApellido.setBounds(334, 214, 172, 25);
			contentPanel.add(textApellido);
		}
		{
			textUsuario = new JTextField();
			textUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
			textUsuario.setColumns(10);
			textUsuario.setBackground(Color.LIGHT_GRAY);
			textUsuario.setBounds(334, 282, 172, 25);
			contentPanel.add(textUsuario);
		}
		{
			JLabel lblNewLabel_8_1_1_1_1 = new JLabel("Usuario");
			lblNewLabel_8_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNewLabel_8_1_1_1_1.setBounds(334, 259, 93, 13);
			contentPanel.add(lblNewLabel_8_1_1_1_1);
		}
		{
			JLabel lblNewLabel_8_1_1_1_1_1 = new JLabel("Contraseña");
			lblNewLabel_8_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNewLabel_8_1_1_1_1_1.setBounds(334, 317, 93, 13);
			contentPanel.add(lblNewLabel_8_1_1_1_1_1);
		}
		{
			textContraseña = new JTextField();
			textContraseña.setFont(new Font("Tahoma", Font.BOLD, 16));
			textContraseña.setColumns(10);
			textContraseña.setBackground(Color.LIGHT_GRAY);
			textContraseña.setBounds(334, 339, 172, 25);
			contentPanel.add(textContraseña);
		}
		{
			JLabel lblNewLabel_8_1_1_1_1_1_1 = new JLabel("Genero");
			lblNewLabel_8_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNewLabel_8_1_1_1_1_1_1.setBounds(334, 374, 93, 13);
			contentPanel.add(lblNewLabel_8_1_1_1_1_1_1);
		}
		{
			JButton btnAtras = new JButton("Atras");
			btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnAtras.setBounds(13, 577, 85, 21);
			contentPanel.add(btnAtras);
		}
		{
			JButton btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnSiguiente.setBounds(765, 577, 101, 21);
			contentPanel.add(btnSiguiente);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
