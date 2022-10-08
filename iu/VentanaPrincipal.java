package iu;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import bussiness.competicion.commands.VerCompeticiones;
import iu.atletas.VentanaVerCompeticiones;
import iu.organizador.OpcionesOrganizador;
import iu.pagos.VentanaOpciones;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbTitulo;
	private JButton btPagos;
	private JButton btCompeticiones;
	private JButton btOrganizador;
	private JButton btCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		setTitle("Didaen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbTitulo());
		contentPane.add(getBtPagos());
		contentPane.add(getBtCompeticiones());
		contentPane.add(getBtOrganizador());
		contentPane.add(getBtCancelar());
	}
	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("DIDAEN");
			lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
			lbTitulo.setBounds(46, 11, 400, 52);
		}
		return lbTitulo;
	}
	private JButton getBtPagos() {
		if (btPagos == null) {
			btPagos = new JButton("Pagar Inscripciones");
			btPagos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaOpciones v = new VentanaOpciones();
					v.setVisible(true);
				}
			});
			btPagos.setBounds(83, 119, 290, 23);
		}
		return btPagos;
	}
	private JButton getBtCompeticiones() {
		if (btCompeticiones == null) {
			btCompeticiones = new JButton("Ver competiones disponibles");
			btCompeticiones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int i = 0;
					try {
						VerCompeticiones.execute();
						i = 1;
					}catch (Exception e1) {
						sinCompeticiones();
					}
					if (i == 1) {
						VentanaVerCompeticiones v = new VentanaVerCompeticiones();
						v.setVisible(true);
					}
				}
			});
			btCompeticiones.setBounds(83, 74, 290, 23);
		}
		return btCompeticiones;
	}
	
	private JButton getBtOrganizador() {
		if (btOrganizador == null) {
			btOrganizador = new JButton("Organizador");
			btOrganizador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					OpcionesOrganizador v = new OpcionesOrganizador();
					v.setVisible(true);
				}
			});
			btOrganizador.setBounds(83, 164, 290, 23);
		}
		return btOrganizador;
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
	
	private void sinCompeticiones() {
		JOptionPane.showConfirmDialog(null, new	JLabel("Lo sentimos, pero actualmente no hay"
				+ " competiciones disponibles en las que poder registrarse", JLabel.CENTER), 
				"No hay competiciones",  JOptionPane.CLOSED_OPTION);
		
	}
}
