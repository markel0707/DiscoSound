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

import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import com.toedter.calendar.JCalendar;

public class VRegistro1 extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Dao dao;
	private JTextField textDni;
	private JTextField textEmail;
	private JButton btnSiguiente;
	private JButton btnAtras;
	
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
		btnAtras.setBounds(23, 558, 85, 21);
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAtras.addActionListener(this);
		contentPanel.add(btnAtras);
		
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBounds(751, 558, 101, 21);
		btnSiguiente.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSiguiente.addActionListener(this);
		contentPanel.add(btnSiguiente);
		
		JCalendar calendario = new JCalendar();
		calendario.setBounds(287, 225, 187, 134);
		contentPanel.add(calendario);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnSiguiente)) {
			siguiente();
		}
		
	}

	private void siguiente() {
		VRegistro2 registro=new VRegistro2(dao);
		registro.setVisible(true);
		this.dispose();
		
	}
}
