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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import clase.Administrador;
import controlador.Dao;

/**
 * Clase VPerfilAdmin que representa la interfaz gráfica del perfil del administrador.
 * Extiende JDialog y maneja las acciones del usuario relacionadas con la administración de entradas.
 */
public class VPerfilAdmin extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPane = new JPanel() {
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
    private JButton btnNuevaEntrada;
    private JButton btnModificarEntrada;
    private JButton btnCerrarSesion;
    private Administrador admin;
    private JButton btnBorrarEntrada;
    private JTextField textFieldNombre;

    /**
     * Constructor de la clase VPerfilAdmin.
     *
     * @param dao   El objeto de acceso a datos.
     * @param admin El administrador para el perfil.
     */
    public VPerfilAdmin(Dao dao, Administrador admin) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(VPerfilAdmin.class.getResource("/img/logoapp.png")));
        this.dao = dao;
        this.admin = admin;
        setBounds(100, 100, 900, 645);
        contentPane.setBackground(new Color(255, 128, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Botones para realizar acciones como crear nueva entrada, modificar entrada, etc.
        btnNuevaEntrada = new JButton("Nueva entrada");
        btnNuevaEntrada.setBackground(Color.LIGHT_GRAY);
        btnNuevaEntrada.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNuevaEntrada.setBounds(29, 548, 134, 36);
        btnNuevaEntrada.addActionListener(this);
        contentPane.add(btnNuevaEntrada);

        btnModificarEntrada = new JButton("Modificar entrada");
        btnModificarEntrada.setBackground(Color.LIGHT_GRAY);
        btnModificarEntrada.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnModificarEntrada.setBounds(699, 548, 158, 36);
        btnModificarEntrada.addActionListener(this);
        contentPane.add(btnModificarEntrada);

        btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.setBackground(Color.LIGHT_GRAY);
        btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCerrarSesion.setBounds(732, 30, 125, 57);
        btnCerrarSesion.addActionListener(this);
        contentPane.add(btnCerrarSesion);

        btnBorrarEntrada = new JButton("Borrar entrada");
        btnBorrarEntrada.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnBorrarEntrada.setBackground(Color.LIGHT_GRAY);
        btnBorrarEntrada.setBounds(365, 548, 134, 36);
        btnBorrarEntrada.addActionListener(this);
        contentPane.add(btnBorrarEntrada);

        // Etiqueta para mostrar el nombre del administrador y la discoteca.
        JLabel lblPerfil = new JLabel("Perfil de administrador");
        lblPerfil.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        lblPerfil.setBackground(UIManager.getColor("Button.background"));
        lblPerfil.setBounds(298, 35, 271, 42);
        contentPane.add(lblPerfil);

        // Campo de texto para mostrar el nombre del administrador y la discoteca.
        textFieldNombre = new JTextField();
        textFieldNombre.setText("Administrador de " + admin.getNomDiscoteca() + " " + admin.getNombre());
        textFieldNombre.setOpaque(false);
        textFieldNombre.setFont(new Font("Verdana", Font.PLAIN, 17));
        textFieldNombre.setEditable(false);
        textFieldNombre.setColumns(10);
        textFieldNombre.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
        textFieldNombre.setBounds(230, 227, 386, 29);
        textFieldNombre.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(textFieldNombre);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnNuevaEntrada)) {
            nuevaEntrada();
        } else if (e.getSource().equals(btnModificarEntrada)) {
            modificarEntrada();
        } else if (e.getSource().equals(btnCerrarSesion)) {
            cerrarSesion();
        } else if (e.getSource().equals(btnBorrarEntrada)) {
            borrarEntrada();
        }
    }

    /**
     * Método para abrir la ventana de creación de una nueva entrada.
     */
    private void nuevaEntrada() {
        VNuevaEntrada nuevaEntrada = new VNuevaEntrada(dao, admin);
        nuevaEntrada.setVisible(true);
        this.dispose();
    }

    /**
     * Método para abrir la ventana de modificación de una entrada existente.
     */
    private void modificarEntrada() {
        if (!dao.comprobarExistenciaEntradas(admin)) {
            JOptionPane.showMessageDialog(this, "No hay ninguna entrada introducida");
            return;
        }
        VModificarEntrada modificarEntrada = new VModificarEntrada(dao, admin);
        modificarEntrada.setVisible(true);
        this.dispose();
    }

    /**
     * Método para cerrar la sesión del administrador y volver a la ventana de inicio.
     */
    private void cerrarSesion() {
        VInicio cerrarSesion = new VInicio(dao);
        cerrarSesion.setVisible(true);
        this.dispose();
    }

    /**
     * Método para abrir la ventana de borrado de una entrada existente.
     */
    private void borrarEntrada() {
        if (!dao.comprobarExistenciaEntradas(admin)) {
            JOptionPane.showMessageDialog(this, "No hay ninguna entrada introducida");
            return;
        }
        VBorrarEntrada borrarEntrada = new VBorrarEntrada(dao, admin);
        borrarEntrada.setVisible(true);
        this.dispose();
    }
}