package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clase.Usuario;
import controlador.Dao;

import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JTextField;

public class VPerfilUsuario extends JDialog implements ActionListener{


		private JPanel contentPane= new JPanel(){
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            Graphics2D g2d = (Graphics2D) g;

	            GradientPaint verticalGradient = new GradientPaint(0, 0, Color.getHSBColor(266.0f, 89.0f, 99.0f), 0, getHeight(), Color.getHSBColor(264.8f, 38.30f, 89.0f));
	            g2d.setPaint(verticalGradient);

	            g2d.fillRect(0, 0, getWidth(), getHeight());
	        }
	    };
	    
		private JTextField DatoNombre;
		private JTextField DatoDni;
		private Dao dao;
		private JButton btnComprarEntradas;
		private JButton btnDescuentosDisp;
		private JButton btnCambiarDatos;
		private JButton btnCerrarSesion;
		private Usuario usu;

		public VPerfilUsuario(Dao dao, Usuario usu) {
			
			setBounds(100, 100, 900, 645);
			contentPane.setBackground(new Color(255, 128, 0));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblPerfil = new JLabel("Perfil");
			lblPerfil.setBackground(new Color(240, 240, 240));
			lblPerfil.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
			lblPerfil.setBounds(375, 55, 63, 42);
			contentPane.add(lblPerfil);
			
			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNombre.setBounds(74, 191, 80, 13);
			contentPane.add(lblNombre);
			
			JLabel lbldni = new JLabel("Dni");
			lbldni.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lbldni.setBounds(74, 305, 45, 13);
			contentPane.add(lbldni);
			
			JLabel lblentradas = new JLabel("Entradas");
			lblentradas.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblentradas.setBounds(508, 191, 80, 13);
			contentPane.add(lblentradas);
			
			btnComprarEntradas = new JButton("Comprar Entradas");
			btnComprarEntradas.setBackground(Color.LIGHT_GRAY);
			btnComprarEntradas.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnComprarEntradas.setBounds(33, 542, 168, 42);
			btnComprarEntradas.addActionListener(this);
			contentPane.add(btnComprarEntradas);
			
			btnDescuentosDisp = new JButton("Descuentos disponibles");
			btnDescuentosDisp.setBackground(Color.LIGHT_GRAY);
			btnDescuentosDisp.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnDescuentosDisp.setBounds(248, 542, 201, 42);
			btnDescuentosDisp.addActionListener(this);
			contentPane.add(btnDescuentosDisp);
			
			btnCambiarDatos = new JButton("Cambiar datos");
			btnCambiarDatos.setBackground(Color.LIGHT_GRAY);
			btnCambiarDatos.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnCambiarDatos.setBounds(496, 542, 159, 42);
			btnCambiarDatos.addActionListener(this);
			contentPane.add(btnCambiarDatos);
			
			btnCerrarSesion = new JButton("Cerrar Sesion");
			btnCerrarSesion.setBackground(Color.LIGHT_GRAY);
			btnCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnCerrarSesion.setBounds(708, 542, 131, 42);
			btnCerrarSesion.addActionListener(this);
			contentPane.add(btnCerrarSesion);
			
			DatoNombre = new JTextField();
			DatoNombre.setBackground(Color.LIGHT_GRAY);
			DatoNombre.setBounds(74, 214, 127, 29);
			contentPane.add(DatoNombre);
			DatoNombre.setColumns(10);
			
			DatoDni = new JTextField();
			DatoDni.setBackground(Color.LIGHT_GRAY);
			DatoDni.setBounds(74, 328, 127, 29);
			contentPane.add(DatoDni);
			DatoDni.setColumns(10);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnComprarEntradas)) {
				comprarEntradas();
			} else if (e.getSource().equals(btnDescuentosDisp)) {
				descuentosDisp();
			} else if (e.getSource().equals(btnCambiarDatos)) {
				cambiarDatos();
			} else if (e.getSource().equals(btnCerrarSesion)) {
				cerrarSesion();
			}

		}

		public JTable cargarTabla() {
			// Columnas
			String[] columnNames = { "Codigo Entrada", "Fecha" };
			String[] fila = new String[2];
			DefaultTableModel model = new DefaultTableModel(null, columnNames);

			return new JTable(model);

		}

		private void cerrarSesion() {
			VInicio cerrarSesion = new VInicio(dao, usu);
			cerrarSesion.setVisible(true);
			this.dispose();
		}

		private void cambiarDatos() {
			VCambiarDatos cambiarDatos = new VCambiarDatos(dao, usu);
			cambiarDatos.setVisible(true);
			this.dispose();

		}

		private void descuentosDisp() {
			VDescuentosDisponibles descuentosDisp = new VDescuentosDisponibles(dao, usu);
			descuentosDisp.setVisible(true);
			this.dispose();
		}

		private void comprarEntradas() {
			VCompraEntradas compraEntradas = new VCompraEntradas(dao, usu);
			compraEntradas.setVisible(true);
			this.dispose();
		}
	}
