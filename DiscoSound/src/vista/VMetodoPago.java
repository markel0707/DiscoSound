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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import clase.Cliente;
import clase.Compra;
import clase.Entrada;
import controlador.Dao;

/**
 * Clase VMetodoPago que representa la interfaz gráfica del método de pago.
 * Extiende JDialog y maneja las acciones del usuario relacionadas con la compra de entradas.
 */
public class VMetodoPago extends JDialog implements ActionListener {

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

    // Atributos

    private JTextField textCantidadEntradas;
    private JButton btnRestar;
    private JButton btnSumar;
    private JButton btnAtras;
    private JButton btnPagar;
    private int contador = 0;
    private JLabel lblCantidadEntradas;
    private Entrada entrada;
    private Dao dao;
    private Cliente cli;
    private JLabel lblMetodoPago;
    private JLabel lblTelefono;
    private JLabel lblPagar;
    private JTextField textTelefono;
    private JTextField textImporte;
    private JLabel lblPrecioTotal_1;
    private JTextField textAforo;
    private JTextField textMetodoPago;

    /**
     * Constructor de la clase VMetodoPago.
     *
     * @param dao    El objeto de acceso a datos.
     * @param cli    El cliente que realiza la compra.
     * @param entrada La entrada que se va a comprar.
     */
    public VMetodoPago(Dao dao, Cliente cli, Entrada entrada) {
        this.entrada = entrada;
        this.dao = dao;
        this.cli = cli;
        setBounds(100, 100, 900, 645);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(255, 128, 0));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        btnAtras = new JButton("Atras");
        btnAtras.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAtras.setBounds(29, 548, 110, 36);
        btnAtras.addActionListener(this);
        contentPanel.add(btnAtras);

        btnPagar = new JButton("Pagar");
        btnPagar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnPagar.setBounds(747, 548, 110, 36);
        btnPagar.addActionListener(this);
        contentPanel.add(btnPagar);

        lblMetodoPago = new JLabel("Metodo de pago");
        lblMetodoPago.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblMetodoPago.setBounds(108, 122, 229, 32);
        contentPanel.add(lblMetodoPago);

        lblCantidadEntradas = new JLabel("Cantidad de entradas");
        lblCantidadEntradas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCantidadEntradas.setBounds(108, 251, 229, 32);
        contentPanel.add(lblCantidadEntradas);

        textCantidadEntradas = new JTextField();
        textCantidadEntradas.setHorizontalAlignment(SwingConstants.CENTER);
        textCantidadEntradas.setBounds(149, 293, 42, 42);
        textCantidadEntradas.setText(String.valueOf(contador));
        textCantidadEntradas.setColumns(10);
        textCantidadEntradas.setEditable(false);
        contentPanel.add(textCantidadEntradas);

        btnSumar = new JButton("+");
        btnSumar.setBounds(189, 293, 42, 42);
        btnSumar.addActionListener(this);
        contentPanel.add(btnSumar);

        btnRestar = new JButton("-");
        btnRestar.setBounds(108, 293, 42, 42);
        btnRestar.addActionListener(this);
        contentPanel.add(btnRestar);

        lblTelefono = new JLabel("Teléfono");
        lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTelefono.setBounds(558, 122, 139, 32);
        contentPanel.add(lblTelefono);

        JLabel lblEntradasDisponibles = new JLabel("Entradas disponibles:");
        lblEntradasDisponibles.setHorizontalAlignment(SwingConstants.CENTER);
        lblEntradasDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEntradasDisponibles.setBounds(108, 398, 161, 32);
        contentPanel.add(lblEntradasDisponibles);

        lblPagar = new JLabel("Pagar");
        lblPagar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
        lblPagar.setBounds(385, 10, 104, 54);
        contentPanel.add(lblPagar);

        textTelefono = new JTextField();
        textTelefono.setOpaque(false);
        textTelefono.setForeground(Color.WHITE);
        textTelefono.setFont(new Font("Verdana", Font.PLAIN, 17));
        textTelefono.setColumns(10);
        textTelefono.setBorder(new MatteBorder(0, 0, 2, 0, Color.WHITE));
        textTelefono.setBounds(558, 161, 204, 30);
        contentPanel.add(textTelefono);

        JLabel lblPrecioTotal = new JLabel("Precio total:");
        lblPrecioTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPrecioTotal.setBounds(558, 303, 139, 32);
        contentPanel.add(lblPrecioTotal);

        textImporte = new JTextField();
        textImporte.setOpaque(false);
        textImporte.setEditable(false);
        textImporte.setForeground(Color.WHITE);
        textImporte.setFont(new Font("Verdana", Font.PLAIN, 17));
        textImporte.setColumns(10);
        textImporte.setBorder(new MatteBorder(0, 0, 2, 0, Color.WHITE));
        textImporte.setBounds(653, 304, 78, 30);
        textImporte.setText(String.valueOf(contador * Math.round(entrada.getPrecio())));
        contentPanel.add(textImporte);

        lblPrecioTotal_1 = new JLabel("€");
        lblPrecioTotal_1.setForeground(Color.WHITE);
        lblPrecioTotal_1.setFont(new Font("Verdana", Font.PLAIN, 17));
        lblPrecioTotal_1.setBounds(718, 291, 139, 54);
        contentPanel.add(lblPrecioTotal_1);

        textAforo = new JTextField();
        textAforo.setOpaque(false);
        textAforo.setEditable(false);
        textAforo.setForeground(Color.WHITE);
        textAforo.setFont(new Font("Verdana", Font.PLAIN, 17));
        textAforo.setColumns(10);
        textAforo.setBorder(new MatteBorder(0, 0, 2, 0, Color.WHITE));
        textAforo.setBounds(279, 399, 115, 30);
        textAforo.setText(dao.calculoCantidadEntradasDisponibles(entrada));
        contentPanel.add(textAforo);

        textMetodoPago = new JTextField();
        textMetodoPago.setOpaque(false);
        textMetodoPago.setEditable(false);
        textMetodoPago.setText("Bizum");
        textMetodoPago.setForeground(Color.WHITE);
        textMetodoPago.setFont(new Font("Verdana", Font.PLAIN, 17));
        textMetodoPago.setColumns(10);
        textMetodoPago.setBorder(new MatteBorder(0, 0, 2, 0, Color.WHITE));
        textMetodoPago.setBounds(108, 164, 204, 30);
        contentPanel.add(textMetodoPago);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnAtras)) {
            atras();
        } else if (e.getSource().equals(btnPagar)) {
            pagar();
        } else if (e.getSource().equals(btnSumar)) {
            sumar();
        } else if (e.getSource().equals(btnRestar)) {
            restar();
        }
    }

    /**
     * Método para sumar una entrada.
     */
    private void sumar() {
        contador = Integer.parseInt(textCantidadEntradas.getText());
        contador++;
        if (contador <= Integer.parseInt(textAforo.getText())) {
            textCantidadEntradas.setText(String.valueOf(contador));
            textImporte.setText(String.valueOf(contador * Math.round(entrada.getPrecio())));
        }
    }

    /**
     * Método para restar una entrada.
     */
    private void restar() {
        contador = Integer.parseInt(textCantidadEntradas.getText());
        if (contador > 1) {
            contador--;
            textCantidadEntradas.setText(String.valueOf(contador));
            textImporte.setText(String.valueOf(contador * Math.round(entrada.getPrecio())));
        }
    }

    /**
     * Método para procesar el pago.
     */
    private void pagar() {
        if (comprobarTelefono()) {
            int resp = JOptionPane.showConfirmDialog(null, "¿Desea realizar la compra?", "Confirmación de pago",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (resp == JOptionPane.OK_OPTION) {
                procesarPago();
            }
        }
    }

    /**
     * Método para realizar las acciones necesarias tras la confirmación del pago.
     */
    private void procesarPago() {
        Compra compra = new Compra();
        compra.setCantidadEntradas(Integer.parseInt(textCantidadEntradas.getText()));
        compra.setPrecioTotal(Float.parseFloat(textImporte.getText()));
        compra.setMetodoPago(textMetodoPago.getText());
        compra.setTelefono(Integer.parseInt(textTelefono.getText()));
        dao.pagar(cli, entrada, compra);
        JOptionPane.showMessageDialog(this, "Entrada/s compradas");
        VPerfilUsuario inicio = new VPerfilUsuario(dao, cli);
        inicio.setVisible(true);
        this.dispose();
    }

    /**
     * Método para comprobar la validez del número de teléfono.
     *
     * @return true si el número de teléfono es válido, false en caso contrario.
     */
    private boolean comprobarTelefono() {
        try {
            if (textTelefono.getText().length() == 9) {
                Integer.parseInt(textTelefono.getText());
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Esto no es un número de teléfono");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Esto no es un número de teléfono");
        }
        return false;
    }

    /**
     * Método para volver a la ventana anterior.
     */
    private void atras() {
        VCompraEntradas atras = new VCompraEntradas(dao, cli);
        atras.setVisible(true);
        this.dispose();
    }
}

