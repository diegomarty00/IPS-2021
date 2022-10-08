	package iu.atletas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import iu.MenuPrincipal;
import iu.pagos.VentanaOpciones;

public class MenuAtleta extends JFrame {

	private JPanel contentPane;
	private JLabel lbTitulo;
	private JButton btCompeticiones;
	private JButton btInscripciones;
	private JButton btPagar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAtleta frame = new MenuAtleta();
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
	public MenuAtleta() {
		setTitle("Menú atleta");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/util/Run.png")));	
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 509, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbTitulo());
		contentPane.add(getBtCompeticiones());
		contentPane.add(getBtInscripciones());
		contentPane.add(getBtPagar());
	}

	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Menú atleta");
			lbTitulo.setFont(new Font("Tahoma", Font.PLAIN, 21));
			lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lbTitulo.setBounds(137, 11, 215, 69);
		}
		return lbTitulo;
	}
	private JButton getBtCompeticiones() {
		if (btCompeticiones == null) {
			btCompeticiones = new JButton("Ver competiciones e inscribirse");
			btCompeticiones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaVerCompeticiones v = new VentanaVerCompeticiones();
					v.setVisible(true);
				}
			});
			btCompeticiones.setBounds(110, 91, 265, 23);
		}
		return btCompeticiones;
	}
	private JButton getBtInscripciones() {
		if (btInscripciones == null) {
			btInscripciones = new JButton("Ver inscripciones");
			btInscripciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaVerInscripcion v = new VentanaVerInscripcion();
					v.setVisible(true);
				}
			});
			btInscripciones.setBounds(110, 147, 265, 23);
		}
		return btInscripciones;
	}
	private JButton getBtPagar() {
		if (btPagar == null) {
			btPagar = new JButton("Pagar inscripción");
			btPagar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaOpciones v = new VentanaOpciones();
					v.setVisible(true);
				}
			});
			btPagar.setBounds(110, 213, 265, 23);
		}
		return btPagar;
	}
}
