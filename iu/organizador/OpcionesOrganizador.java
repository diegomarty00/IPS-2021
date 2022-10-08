package iu.organizador;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import bussiness.BusinessFactory;
import bussiness.inscripciones.InscripcionService;
import iu.MenuPrincipal;
import util.BusinessException;

public class OpcionesOrganizador extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpcionesOrganizador frame = new OpcionesOrganizador();
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
	public OpcionesOrganizador() {
		setTitle("Organizador");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/util/Run.png")));	
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 566, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbTitulo = new JLabel("Opciones organizador");
		lbTitulo.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(144, 11, 236, 48);
		contentPane.add(lbTitulo);

		JButton btTramitarInscripciones = new JButton("Tramitar Inscripciones");
		btTramitarInscripciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leerFichero();
			}
		});
		btTramitarInscripciones.setBounds(144, 240, 216, 23);
		contentPane.add(btTramitarInscripciones);
		
		JButton btCrearCompeticion = new JButton("Crear competición");
		btCrearCompeticion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearCompeticion c = new CrearCompeticion();
				c.setVisible(true);
			}
		});
		btCrearCompeticion.setBounds(144, 86, 216, 23);
		contentPane.add(btCrearCompeticion);
		
		JButton btVerAtletas = new JButton("Ver atletas de una competición");
		btVerAtletas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerEstadoInscripcion v = new VerEstadoInscripcion();
				v.setVisible(true);
			}
		});
		btVerAtletas.setBounds(144, 136, 216, 23);
		contentPane.add(btVerAtletas);
		
		JButton btClasificaciones = new JButton("Ver clasificaciones");
		btClasificaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					VentanaClasificacion v = new VentanaClasificacion();
					v.setVisible(true);
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null, new	JLabel("Actualmente no hay clasificaciones disponibles", JLabel.CENTER), 
							"Error - No hay clasificaciones",  JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btClasificaciones.setBounds(144, 187, 216, 23);
		contentPane.add(btClasificaciones);
		
		JButton btnAsignarDorsales = new JButton("Asignar Dorsales");
		btnAsignarDorsales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AsignarDorsalesVentana v = new AsignarDorsalesVentana();
				v.setVisible(true);
			}
		});
		btnAsignarDorsales.setBounds(144, 287, 216, 23);
		contentPane.add(btnAsignarDorsales);
	}

	private void leerFichero() {
		JFileChooser archivo = new JFileChooser();
		archivo.addChoosableFileFilter(new FileNameExtensionFilter("Ficheros de texto o csv separados por punto y coma", "csv", "txt"));
		archivo.showOpenDialog(this);
		File fichero = archivo.getSelectedFile();
		InscripcionService is = BusinessFactory.forInscripcionService();

		if (fichero != null) {
			try {
				is.tramitarInscripciones(fichero);
				JOptionPane.showMessageDialog(new JFrame(), "Fichero procesado correctamente", "Información",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (BusinessException e) {
				JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(), "Ha ocurrido un error procesando su fichero", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
}
