package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import clase.Administrador;
import clase.Cliente;
import clase.Usuario;
import controlador.Dao;

/**
 * Clase VIniciarSesion que representa la interfaz gráfica para el inicio de sesión.
 * Extiende JDialog y maneja las acciones del usuario relacionadas con el inicio de sesión.
 */
public class VIniciarSesion extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel() {

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

    private JButton btnSiguiente;
    private JButton btnAtras;
    private JTextField textUser;
    private Dao dao;
    private Usuario usu;
    private JPasswordField textContraseina;
    private JLabel lblInicioDeSesin;

    /**
     * Constructor de la clase VIniciarSesion.
     *
     * @param dao El objeto de acceso a datos.
     */
    public VIniciarSesion(Dao dao) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(VModificarEntrada.class.getResource("/img/logoapp.png")));
        setTitle("Iniciar Sesion");
        this.dao = dao;
        setBounds(100, 100, 900, 645);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(255, 128, 0));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        textUser = new JTextField();
        textUser.setForeground(Color.WHITE);
        textUser.setColumns(10);
        textUser.setFont(new Font("Verdana", Font.PLAIN, 17));
        textUser.setBorder(new MatteBorder(0, 0, 2, 0, Color.WHITE));
        textUser.setOpaque(false);
        textUser.setBounds(304, 250, 204, 30);
        contentPanel.add(textUser);

        JLabel lblUser = new JLabel("Nombre de usuario");
        lblUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblUser.setBounds(304, 214, 152, 25);
        contentPanel.add(lblUser);

        JLabel lblcontrasena = new JLabel("Contraseña");
        lblcontrasena.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblcontrasena.setBounds(304, 307, 152, 20);
        contentPanel.add(lblcontrasena);

        btnAtras = new JButton("Atras");
        btnAtras.setBackground(new Color(192, 192, 192));
        btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAtras.setBounds(29, 548, 110, 36);
        btnAtras.setBorder(new BevelBorder(BevelBorder.RAISED));
        btnAtras.addActionListener(this);
        contentPanel.add(btnAtras);

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSiguiente.setBackground(new Color(192, 192, 192));
        btnSiguiente.setBounds(747, 548, 110, 36);
        btnSiguiente.setBorder(new BevelBorder(BevelBorder.RAISED));
        btnSiguiente.addActionListener(this);
        contentPanel.add(btnSiguiente);

        textContraseina = new JPasswordField();
        textContraseina.setOpaque(false);
        textContraseina.setForeground(Color.WHITE);
        textContraseina.setFont(new Font("Verdana", Font.PLAIN, 17));
        textContraseina.setColumns(10);
        textContraseina.setBorder(new MatteBorder(0, 0, 2, 0, Color.WHITE));
        textContraseina.setBounds(304, 338, 204, 30);
        contentPanel.add(textContraseina);

        lblInicioDeSesin = new JLabel("Inicio de sesión");
        lblInicioDeSesin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
        lblInicioDeSesin.setBounds(280, 10, 249, 54);
        contentPanel.add(lblInicioDeSesin);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnSiguiente)) {
            siguiente();
        } else if (e.getSource().equals(btnAtras)) {
            atras();
        }
    }

    /**
     * Método para validar el inicio de sesión y dirigir al usuario a la ventana correspondiente.
     */
    private void siguiente() {
        usu = new Usuario();
        usu = dao.inicioSesion(textUser.getText(), textContraseina.getPassword());

        if (usu != null) {
            if (usu instanceof Administrador) {
                VPerfilAdmin siguiente = new VPerfilAdmin(dao, (Administrador) usu);
                siguiente.setVisible(true);
                this.dispose();
            } else {
                VPerfilUsuario siguiente = new VPerfilUsuario(dao, (Cliente) usu);
                siguiente.setVisible(true);
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o Password incorrecta");
        }
    }

    /**
     * Método para regresar a la ventana de inicio.
     */
    private void atras() {
        VInicio atras = new VInicio(dao);
        atras.setVisible(true);
        this.dispose();
    }
}

