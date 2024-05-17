package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import controlador.Dao;
import javax.swing.ImageIcon;

/**
 * Clase VInicio que representa la interfaz gráfica de la ventana principal de inicio.
 * Extiende JFrame y maneja las acciones del usuario relacionadas con el inicio de sesión y registro.
 */
public class VInicio extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnIniciarSesion;
    private JButton btnRegistrar;
    private Dao dao;
    private JTextField textFieldUsuariosTotales;
    private JLabel lblNewLabel_2;

    /**
     * Constructor de la clase VInicio.
     *
     * @param dao El objeto de acceso a datos.
     */
    public VInicio(Dao dao) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(VInicio.class.getResource("/img/logoapp.png")));
        this.dao = dao;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 645);
        contentPane = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint verticalGradient = new GradientPaint(0, 0, Color.getHSBColor(266.0f, 89.0f, 99.0f), 0,
                        getHeight(), Color.getHSBColor(264.8f, 38.30f, 89.0f));
                g2d.setPaint(verticalGradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(new Color(192, 192, 192));
        btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnRegistrar.setBounds(335, 391, 185, 52);
        btnRegistrar.setBorder(new BevelBorder(BevelBorder.RAISED));
        btnRegistrar.addActionListener(this);
        contentPane.add(btnRegistrar);

        btnIniciarSesion = new JButton("Iniciar Sesion");
        btnIniciarSesion.setBackground(new Color(192, 192, 192));
        btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnIniciarSesion.setBounds(335, 278, 185, 52);
        btnIniciarSesion.setBorder(new BevelBorder(BevelBorder.RAISED));
        btnIniciarSesion.addActionListener(this);
        contentPane.add(btnIniciarSesion);

        JLabel lblNewLabel_1 = new JLabel("Usuarios totales:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(605, 27, 107, 14);
        contentPane.add(lblNewLabel_1);

        textFieldUsuariosTotales = new JTextField();
        textFieldUsuariosTotales.setOpaque(false);
        textFieldUsuariosTotales.setForeground(Color.WHITE);
        textFieldUsuariosTotales.setFont(new Font("Verdana", Font.PLAIN, 17));
        textFieldUsuariosTotales.setColumns(10);
        textFieldUsuariosTotales.setBorder(new MatteBorder(0, 0, 2, 0, Color.WHITE));
        textFieldUsuariosTotales.setBounds(712, 18, 162, 30);
        textFieldUsuariosTotales.setText(String.valueOf(dao.calcularNumeroTotalUsuarios()));
        contentPane.add(textFieldUsuariosTotales);

        lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(VInicio.class.getResource("/img/logo.png")));
        lblNewLabel_2.setBounds(201, 52, 413, 134);
        contentPane.add(lblNewLabel_2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnIniciarSesion)) {
            iniciarSesion();
        } else if (e.getSource().equals(btnRegistrar)) {
            registrar();
        }
    }

    /**
     * Método para abrir la ventana de inicio de sesión.
     */
    private void iniciarSesion() {
        VIniciarSesion iniciarSesion = new VIniciarSesion(dao);
        iniciarSesion.setVisible(true);
        this.dispose();
    }

    /**
     * Método para abrir la ventana de registro.
     */
    private void registrar() {
        VRegistro1 registro = new VRegistro1(dao);
        registro.setVisible(true);
        this.dispose();
    }
}
