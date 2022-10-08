package iu.pagos;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import iu.MenuPrincipal;

public class VentanaDatos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbTitulo;
	private JLabel lbCuota;
	private JTextField txCuota;
	private JButton btAceptar;
	private JTextField txCuenta;
	private JLabel lbCuenta;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJustificante frame = new VentanaJustificante();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaDatos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/util/Run.png")));	
		setResizable(false);
		setTitle("Datos bancarios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 553, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbTitulo());
		contentPane.add(getLbCuota());
		contentPane.add(getTxCuota());
		contentPane.add(getBtAceptar());
		contentPane.add(getTxCuenta());
		contentPane.add(getLbCuenta());
		contentPane.add(getLblNewLabel());
	}

	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Datos Bancarios");
			lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
			lbTitulo.setBounds(147, 11, 239, 42);
		}
		return lbTitulo;
	}
	private JLabel getLbCuota() {
		if (lbCuota == null) {
			lbCuota = new JLabel("Cuota:");
			lbCuota.setBounds(10, 200, 46, 14);
		}
		return lbCuota;
	}
	private JTextField getTxCuota() {
		if (txCuota == null) {
			txCuota = new JTextField();
			txCuota.setEditable(false);
			txCuota.setBounds(66, 197, 201, 20);
			txCuota.setColumns(10);
		}
		return txCuota;
	}
	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btAceptar.setBounds(345, 325, 137, 23);
		}
		return btAceptar;
	}
	
	public void mostrar(Double cuota) {
		this.setVisible(true);
		getTxCuenta().setText("ES1320900000290350000083");
		getTxCuota().setText(cuota.toString());
	}
	private JTextField getTxCuenta() {
		if (txCuenta == null) {
			txCuenta = new JTextField();
			txCuenta.setEditable(false);
			txCuenta.setBounds(66, 133, 201, 20);
			txCuenta.setColumns(10);
		}
		return txCuenta;
	}
	private JLabel getLbCuenta() {
		if (lbCuenta == null) {
			lbCuenta = new JLabel("Cuenta:");
			lbCuenta.setBounds(10, 136, 46, 14);
		}
		return lbCuenta;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Gracias por inscribirte, por favor paga en un plazo de 48 horas en la siguiente cuenta");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(33, 63, 494, 41);
		}
		return lblNewLabel;
	}
	
	public void windowClosing( WindowEvent evt ) {
		dispose();
	}
}
