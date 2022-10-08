package iu.club;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bussiness.atleta.CompeticionDTO;
import bussiness.competicion.commands.VerCompeticiones;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class VentanaSeleccionCompeticion extends JFrame {
	
	private String nombreClub;

	private JPanel contentPane;
	private DefaultListModel<CompeticionDTO> modelo = new DefaultListModel();

	private List<CompeticionDTO> competis;


	public VentanaSeleccionCompeticion(InscribirPorClub inscribirPorClub, int i) {
		setTitle("Seleccionar Competiciones");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JList list = new JList();
		scrollCompeticion.setViewportView(list);
		list.setModel(modelo);
		
		competis =  VerCompeticiones.execute();
		modelo.addAll(competis);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btAceptar = new JButton("Aceptar");
		btAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(null, new JLabel("Por favor seleccione una competicion", JLabel.CENTER), 
							"Error ",  JOptionPane.ERROR_MESSAGE);
				}
				else {
					inscribirPorClub.setId_competicion(competis.get(list.getSelectedIndex()).id_competicion   );
					inscribirPorClub.nextLayout(i);
				
					dispose();
				}
				
			}
		});
		btAceptar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(btAceptar);
		
		
	}
}