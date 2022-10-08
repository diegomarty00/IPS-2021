package iu;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import iu.atletas.MenuAtleta;
import iu.club.InscribirPorClub;
import iu.organizador.OpcionesOrganizador;

public class MenuPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbTitulo;
	private JButton btAtleta;
	private JButton btOrganizador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try{
						
						// LAF del sistema por defecto 
						JFrame.setDefaultLookAndFeelDecorated(true);
						JDialog.setDefaultLookAndFeelDecorated(true);
						UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
						//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
						//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
					}
					catch (Exception e)
					 {
					  e.printStackTrace();
					 }
					MenuPrincipal frame = new MenuPrincipal();
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
	public MenuPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/util/Run.png")));	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Menú principal");
		setResizable(false);
		setBounds(100, 100, 552, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbTitulo());
		contentPane.add(getBtAtleta());
		contentPane.add(getBtOrganizador());
		
		JButton btnInscribirPorClub = new JButton("Inscribir por club");
		btnInscribirPorClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InscribirPorClub v = new InscribirPorClub();
				v.setVisible(true);
			}
		});
		btnInscribirPorClub.setBounds(123, 199, 272, 23);
		contentPane.add(btnInscribirPorClub);
	}
	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Menú principal");
			lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 21));
			lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lbTitulo.setBounds(136, 23, 259, 54);
		}
		return lbTitulo;
	}
	private JButton getBtAtleta() {
		if (btAtleta == null) {
			btAtleta = new JButton("Atleta");
			btAtleta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MenuAtleta mA = new MenuAtleta();
					mA.setVisible(true);
				}
			});
			btAtleta.setBounds(123, 88, 272, 23);
		}
		return btAtleta;
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
			btOrganizador.setBounds(123, 142, 272, 23);
		}
		return btOrganizador;
	}
}
