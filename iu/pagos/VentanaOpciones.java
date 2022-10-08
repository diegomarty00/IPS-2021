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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import iu.MenuPrincipal;

public class VentanaOpciones extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbTitulo;
	private JButton btTarjeta;
	private JButton btTransferencia;
	private JButton btCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaOpciones frame = new VentanaOpciones();
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
	public VentanaOpciones() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/util/Run.png")));	
		setResizable(false);
		setTitle("Pagar Inscripcion");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 508, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbTitulo());
		contentPane.add(getBtTarjeta());
		contentPane.add(getBtTransferencia());
		contentPane.add(getBtCancelar());
	}
	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("¿Cómo desea pagar su inscripción?");
			lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
			lbTitulo.setBounds(46, 11, 400, 52);
		}
		return lbTitulo;
	}
	private JButton getBtTarjeta() {
		if (btTarjeta == null) {
			btTarjeta = new JButton("Con tarjeta de crédito");
			btTarjeta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaTarjeta v = new VentanaTarjeta();
					v.setVisible(true);
				}
			});
			btTarjeta.setBounds(83, 74, 290, 23);
		}
		return btTarjeta;
	}
	private JButton getBtTransferencia() {
		if (btTransferencia == null) {
			btTransferencia = new JButton("Por transferencia bancaria");
			btTransferencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaTransferencia vt = new VentanaTransferencia();
					vt.mostrar();
				}
			});
			btTransferencia.setBounds(83, 119, 290, 23);
		}
		return btTransferencia;
	}
	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btCancelar.setBounds(302, 261, 89, 23);
		}
		return btCancelar;
	}
	
	public void windowClosing( WindowEvent evt ) {
		dispose();
	}
}
