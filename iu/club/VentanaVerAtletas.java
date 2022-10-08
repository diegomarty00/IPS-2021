package iu.club;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bussiness.atleta.AtletaDto;
import bussiness.atleta.CompeticionDTO;
import bussiness.atleta.commands.InscribirListaAtletas;
import bussiness.clubes.ClubDto;
import bussiness.clubes.ClubService;
import bussiness.clubes.impl.ClubServiceImpl;
import bussiness.competicion.CompeticionService;
import bussiness.competicion.impl.CompeticionServiceImpl;
import util.BusinessException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaVerAtletas extends JFrame {

	private JPanel contentPane;
	private List<AtletaDto> dtos;
	private String nombreClub;
	private int idCompeticion;
	private JLabel lbTitulo;
	private JScrollPane scrollPane;
	private JList listaAtletas;
	private JPanel panel;
	private JButton btAceptar;
	private JButton btnCancelar;
	private DefaultListModel<String> modelo = new DefaultListModel<String>();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaVerAtletas frame = new VentanaVerAtletas();
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
	public VentanaVerAtletas(String nombreClub, int idCompeticion2, List<AtletaDto> dtos) {
		setResizable(false);
		setTitle("Ver fichero");
		this.nombreClub = nombreClub;
		this.idCompeticion = idCompeticion2;
		this.dtos = dtos;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getLbTitulo(), BorderLayout.NORTH);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.SOUTH);
	}

	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Ver fichero procesado");
			lbTitulo.setFont(new Font("Tahoma", Font.PLAIN, 21));
			lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbTitulo;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getListaAtletas());
		}
		return scrollPane;
	}

	private JList getListaAtletas() {
		if (listaAtletas == null) {
			listaAtletas = new JList();
			listaAtletas.setModel(modelo);
			for (int i = 0; i < dtos.size(); i++) {
				modelo.add(i, dtos.get(i).mostrarDatos());
			}

		}
		return listaAtletas;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getBtnCancelar());
			panel.add(getBtAceptar());
		}
		return panel;
	}

	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InscribirAtletas();
					ClubService cs = new ClubServiceImpl();
					CompeticionService co = new CompeticionServiceImpl();
					CompeticionDTO c;
					try {
						c = co.findById(idCompeticion).get();
						ClubDto cd = cs.findByNombre(nombreClub).get();
						Double deuda = c.cuota * dtos.size() + cd.totalPagar;
						cs.updateDeuda(deuda, nombreClub);
					} catch (BusinessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return btAceptar;
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelar;
	}

	private void InscribirAtletas() {
		if (!dtos.isEmpty()) {
			try {
				ClubService club = new ClubServiceImpl();
				ClubDto cd = club.findByNombre(nombreClub).get();
				new InscribirListaAtletas(dtos, idCompeticion, cd.id_club).execute();
				
				JOptionPane.showMessageDialog(null, new JLabel("Atletas inscritos correctamente", JLabel.CENTER), "Inscripcion completada ",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (BusinessException e) {
				JOptionPane.showMessageDialog(null, new JLabel("Error al añadir", JLabel.CENTER), "Error ",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
