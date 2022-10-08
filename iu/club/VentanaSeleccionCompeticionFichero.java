package iu.club;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import bussiness.BusinessFactory;
import bussiness.atleta.AtletaDto;
import bussiness.atleta.CompeticionDTO;
import bussiness.clubes.ClubService;
import bussiness.clubes.impl.ClubServiceImpl;
import bussiness.competicion.commands.VerCompeticiones;
import bussiness.inscripciones.InscripcionService;
import util.BusinessException;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;

public class VentanaSeleccionCompeticionFichero extends JFrame {
	
	private String nombreClub;
	private int idCompeticion;

	private JPanel contentPane;
	private DefaultListModel<CompeticionDTO> modelo = new DefaultListModel();
	private List<AtletaDto> dtos;
	JList list;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaSeleccionCompeticion frame = new VentanaSeleccionCompeticion();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public VentanaSeleccionCompeticionFichero(String nombreClub) {
		setTitle("Seleccionar Competiciones");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.nombreClub = nombreClub;
		setBounds(100, 100, 724, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lbTituloVentana = new JLabel("Seleccione la competicion");
		lbTituloVentana.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lbTituloVentana.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbTituloVentana, BorderLayout.NORTH);
		
		JScrollPane scrollCompeticion = new JScrollPane();
		contentPane.add(scrollCompeticion, BorderLayout.CENTER);
		
		list = new JList();
		scrollCompeticion.setViewportView(list);
		list.setModel(modelo);
		modelo.addAll(VerCompeticiones.execute());
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btAceptar = new JButton("Aceptar");
		btAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(null, new JLabel("Por favor seleccione una competicion", JLabel.CENTER), 
							"Error ",  JOptionPane.ERROR_MESSAGE);
				}
				else {
					leerFichero();
				}
				
			}
		});
		
		JButton btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btCancelar);
		panel.add(btAceptar);
	}
	
	private void leerFichero() {
		JFileChooser archivo = new JFileChooser();
		archivo.addChoosableFileFilter(new FileNameExtensionFilter("Ficheros de texto o csv separados por punto y coma", "csv", "txt"));
		archivo.showOpenDialog(this);
		File fichero = archivo.getSelectedFile();
		ClubService cs = new ClubServiceImpl();

		if (fichero != null) {
			try {
				dtos = cs.leerFichero(fichero);
				CompeticionDTO cdto = (CompeticionDTO) list.getSelectedValue();
				idCompeticion = cdto.id_competicion;
				VentanaVerAtletas v = new VentanaVerAtletas(nombreClub,idCompeticion, dtos);
				v.setVisible(true);
			} catch (BusinessException e) {
				JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
}
