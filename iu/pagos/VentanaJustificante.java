package iu.pagos;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import iu.MenuPrincipal;

public class VentanaJustificante extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbTitulo;
	private JLabel lbDNI;
	private JTextField txDNI;
	private JLabel lbCompeticion;
	private JTextField txCompeticion;
	private JLabel lbFecha;
	private JTextField txFecha;
	private JLabel lbCuota;
	private JTextField txCuota;
	private JButton btAceptar;

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
	public VentanaJustificante() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/util/Run.png")));	
		setResizable(false);
		setTitle("Justificante de pago");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 553, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbTitulo());
		contentPane.add(getLbDNI());
		contentPane.add(getTxDNI());
		contentPane.add(getLbCompeticion());
		contentPane.add(getTxCompeticion());
		contentPane.add(getLbFecha());
		contentPane.add(getTxFecha());
		contentPane.add(getLbCuota());
		contentPane.add(getTxCuota());
		contentPane.add(getBtAceptar());
	}

	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Justificante de pago");
			lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
			lbTitulo.setBounds(147, 11, 239, 42);
		}
		return lbTitulo;
	}
	private JLabel getLbDNI() {
		if (lbDNI == null) {
			lbDNI = new JLabel("DNI:");
			lbDNI.setBounds(10, 82, 40, 14);
		}
		return lbDNI;
	}
	private JTextField getTxDNI() {
		if (txDNI == null) {
			txDNI = new JTextField();
			txDNI.setEditable(false);
			txDNI.setBounds(87, 79, 201, 20);
			txDNI.setColumns(10);
		}
		return txDNI;
	}
	private JLabel getLbCompeticion() {
		if (lbCompeticion == null) {
			lbCompeticion = new JLabel("Competición");
			lbCompeticion.setBounds(10, 128, 83, 14);
		}
		return lbCompeticion;
	}
	private JTextField getTxCompeticion() {
		if (txCompeticion == null) {
			txCompeticion = new JTextField();
			txCompeticion.setEditable(false);
			txCompeticion.setBounds(87, 125, 201, 20);
			txCompeticion.setColumns(10);
		}
		return txCompeticion;
	}
	private JLabel getLbFecha() {
		if (lbFecha == null) {
			lbFecha = new JLabel("Fecha:");
			lbFecha.setBounds(10, 176, 76, 14);
		}
		return lbFecha;
	}
	private JTextField getTxFecha() {
		if (txFecha == null) {
			txFecha = new JTextField();
			txFecha.setEditable(false);
			txFecha.setBounds(87, 173, 201, 20);
			txFecha.setColumns(10);
		}
		return txFecha;
	}
	private JLabel getLbCuota() {
		if (lbCuota == null) {
			lbCuota = new JLabel("Cuota:");
			lbCuota.setBounds(10, 226, 46, 14);
		}
		return lbCuota;
	}
	private JTextField getTxCuota() {
		if (txCuota == null) {
			txCuota = new JTextField();
			txCuota.setEditable(false);
			txCuota.setBounds(87, 223, 201, 20);
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
	
	@SuppressWarnings("deprecation")
	public void mostrar(String dni, String nombre_competicion, Date fecha, Double cuota) {
		this.setVisible(true);
		getTxDNI().setText(dni);
		getTxCompeticion().setText(nombre_competicion);
		getTxFecha().setText(fecha.toLocaleString());
		getTxCuota().setText(cuota.toString());
	}
	
	public void windowClosing( WindowEvent evt ) {
		dispose();
	}
}
