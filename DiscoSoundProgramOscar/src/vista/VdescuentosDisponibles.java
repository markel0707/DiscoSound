package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Dao;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VdescuentosDisponibles extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            GradientPaint verticalGradient = new GradientPaint(0, 0, Color.getHSBColor(266.0f, 89.0f, 99.0f), 0, getHeight(), Color.getHSBColor(264.8f, 38.30f, 89.0f));
            g2d.setPaint(verticalGradient);

            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    };
	private JPanel contentPane;
	private Dao dao;
	private JButton btnInicio;
	
	public VdescuentosDisponibles(Dao dao) {
		setBounds(100, 100, 900, 645);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbldescuentos = new JLabel("Descuentos disponibles");
		lbldescuentos.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbldescuentos.setBounds(218, 23, 382, 50);
		contentPane.add(lbldescuentos);
		
		btnInicio = new JButton("Inicio");
		btnInicio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInicio.setBounds(392, 545, 85, 21);
		btnInicio.addActionListener(this);
		contentPane.add(btnInicio);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnInicio)) {
			inicio();
		}
	}

	private void inicio() {
		// TODO Auto-generated method stub
		VperfilUsuario inicio = new VperfilUsuario(dao);
		inicio.setVisible(true);
		this.dispose();
	}

}