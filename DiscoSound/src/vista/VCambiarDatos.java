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
import javax.swing.border.MatteBorder;

import clase.Cliente;
import controlador.Dao;

/**
 * Clase VCambiarDatos que representa una interfaz gráfica para cambiar los datos de un cliente.
 * Extiende JDialog y maneja acciones del usuario relacionadas con la modificación de datos de un cliente.
 */
public class VCambiarDatos extends JDialog implements ActionListener {

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

    private Dao dao;
    private Cliente cli;
    private JButton btnGuardar;
    private JButton btnAtras;
    private JTextField textNombre;
    private JTextField textContraseina;
    private JTextField textEmail;
    private JTextField textApellido;

    /**
     * Constructor de la clase VCambiarDatos.
     *
     * @param dao El objeto de acceso a datos.
     * @param cli El cliente cuyos datos se van a modificar.
     */
    public VCambiarDatos(Dao dao, Cliente cli) {
        this.dao = dao;
        this.cli = cli;

        // Configuración de la ventana
        setBounds(100, 100, 900, 645);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(255, 168, 0));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblCambiarDatos = new JLabel("Cambiar datos");
        lblCambiarDatos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
        lblCambiarDatos.setBounds(330, 10, 233, 54);
        contentPanel.add(lblCambiarDatos);

        JLabel lblNombre = new JLabel("NOMBRE");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNombre.setBounds(120, 146, 96, 13);
        contentPanel.add(lblNombre);

        JLabel lblcontrasena = new JLabel("CONTRASEÑA");
        lblcontrasena.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblcontrasena.setBounds(120, 285, 150, 20);
        contentPanel.add(lblcontrasena);

        JLabel lblEmail = new JLabel("EMAIL");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEmail.setBounds(569, 146, 96, 13);
        contentPanel.add(lblEmail);

        JLabel lblApellido = new JLabel("APELLIDO");
        lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblApellido.setBounds(569, 289, 96, 13);
        contentPanel.add(lblApellido);

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

        textNombre = new JTextField();
        textNombre.setOpaque(false);
        textNombre.setForeground(Color.WHITE);
        textNombre.setFont(new Font("Verdana", Font.PLAIN, 17));
        textNombre.setColumns(10);
        textNombre.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
        textNombre.setBounds(120, 169, 204, 30);
        textNombre.setText(cli.getNombre());
        contentPanel.add(textNombre);

        textContraseina = new JTextField();
        textContraseina.setOpaque(false);
        textContraseina.setForeground(Color.WHITE);
        textContraseina.setFont(new Font("Verdana", Font.PLAIN, 17));
        textContraseina.setColumns(10);
        textContraseina.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
        textContraseina.setBounds(120, 308, 204, 30);
        textContraseina.setText(cli.getContraseina());
        contentPanel.add(textContraseina);

        textEmail = new JTextField();
        textEmail.setOpaque(false);
        textEmail.setForeground(Color.WHITE);
        textEmail.setFont(new Font("Verdana", Font.PLAIN, 17));
        textEmail.setColumns(10);
        textEmail.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
        textEmail.setBounds(570, 169, 204, 30);
        textEmail.setText(cli.getEmail());
        contentPanel.add(textEmail);

        textApellido = new JTextField();
        textApellido.setOpaque(false);
        textApellido.setForeground(Color.WHITE);
        textApellido.setFont(new Font("Verdana", Font.PLAIN, 17));
        textApellido.setColumns(10);
        textApellido.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
        textApellido.setBounds(569, 308, 204, 30);
        textApellido.setText(cli.getApellido());
        contentPanel.add(textApellido);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnGuardar)) {
            guardar();
        } else if (e.getSource().equals(btnAtras)) {
            atras();
        }
    }

    /**
     * Método para regresar a la vista del perfil del usuario.
     */
    private void atras() {
        VPerfilUsuario atras = new VPerfilUsuario(dao, cli);
        atras.setVisible(true);
        this.dispose();
    }

    /**
     * Método para guardar los datos modificados del cliente.
     * Actualiza los datos del cliente en la base de datos.
     */
    private void guardar() {
        cli.setNombre(textNombre.getText());
        cli.setEmail(textEmail.getText());
        cli.setApellido(textApellido.getText());
        cli.setContraseina(textContraseina.getText());

        dao.cambiarDatos(cli);

        JOptionPane.showMessageDialog(this, "Datos cambiados");
        VPerfilUsuario guardar = new VPerfilUsuario(dao, cli);
        guardar.setVisible(true);
        this.dispose();
    }
}
