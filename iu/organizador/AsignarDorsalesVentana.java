package iu.organizador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bussiness.atleta.AtletaDto;
import bussiness.atleta.CompeticionDTO;
import bussiness.atleta.commands.AsignarDorsales;
import bussiness.inscripciones.InscripcionDto;
import bussiness.inscripciones.operaciones.FindByDni;
import bussiness.inscripciones.operaciones.FindInscripcion;
import iu.organizador.actions.VerAtletasDeCompeticionAction;
import iu.organizador.actions.VerCompeticionesAction;
import util.BusinessException;
import util.command.CommandExecutor;

import javax.swing.JLayeredPane;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class AsignarDorsalesVentana extends JFrame {

	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JPanel panel_Competiciones;
	private JLabel lblCompeticiones;
	private JScrollPane scrollPane;
	private JPanel panel_botones_Competi;
	private JButton boton_com_cancel;
	private JButton boton_Comp_Siguien;
	private JPanel panelinscribir;
	private JLabel lblCompeticiones_1;
	private JPanel panel;
	private JButton btnAsignar;
	private JLabel lblNewLabel;
	private JTextField textFieldNParticipantes;
	private JList<CompeticionesModelArticle> list;
	private DefaultListModel<CompeticionesModelArticle> modeloList;
	private CompeticionesModelArticle competi ;
	List<InscripcionDto> dtos ;
	List<InscripcionDto> asignados ;
	private JPanel panel_Atletas;
	private JLabel lblAtletasYDorsales;
	private JScrollPane scrollPane_1;
	private JList<AtletaDto> listDorsales;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AsignarDorsalesVentana frame = new AsignarDorsalesVentana();
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
	public AsignarDorsalesVentana() {
		setTitle("AsignarDorsales");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		contentPane.add(getLayeredPane_1());
	}
	private JLayeredPane getLayeredPane_1() {
		if (layeredPane == null) {
			layeredPane = new JLayeredPane();
			layeredPane.setLayout(new CardLayout(0, 0));
			layeredPane.add(getPanel_Competiciones(), "name_669877732478000");
			layeredPane.add(getPanelinscribir(), "name_669877749295800");
			layeredPane.add(getPanel_Atletas(), "name_843649679681200");
		}
		return layeredPane;
	}
	private JPanel getPanel_Competiciones() {
		if (panel_Competiciones == null) {
			panel_Competiciones = new JPanel();
			panel_Competiciones.setLayout(new BorderLayout(0, 0));
			panel_Competiciones.add(getLblCompeticiones(), BorderLayout.NORTH);
			panel_Competiciones.add(getScrollPane(), BorderLayout.CENTER);
			panel_Competiciones.add(getPanel_botones_Competi(), BorderLayout.SOUTH);
		}
		return panel_Competiciones;
	}
	private JLabel getLblCompeticiones() {
		if (lblCompeticiones == null) {
			lblCompeticiones = new JLabel("Competiciones");
			lblCompeticiones.setFont(new Font("Tahoma", Font.PLAIN, 30));
		}
		return lblCompeticiones;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getList());
		}
		return scrollPane;
	}
	private JPanel getPanel_botones_Competi() {
		if (panel_botones_Competi == null) {
			panel_botones_Competi = new JPanel();
			panel_botones_Competi.setLayout(new GridLayout(0, 2, 0, 0));
			panel_botones_Competi.add(getBoton_com_cancel());
			panel_botones_Competi.add(getBoton_Comp_Siguien());
		}
		return panel_botones_Competi;
	}
	private JButton getBoton_com_cancel() {
		if (boton_com_cancel == null) {
			boton_com_cancel = new JButton("Cancelar");
		}
		return boton_com_cancel;
	}
	private JButton getBoton_Comp_Siguien() {
		if (boton_Comp_Siguien == null) {
			boton_Comp_Siguien = new JButton("Siguiente");
			boton_Comp_Siguien.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					botonSiguienteAction();
				}
			});
			boton_Comp_Siguien.setEnabled(false);
		}
		return boton_Comp_Siguien;
	}
	
	
		private void botonSiguienteAction() {
		
			
			competi =  getList().getSelectedValue();
			getLblCompeticiones_1().setText("Competicion:"  +competi.nombre);
			
			

			try {
				 dtos =  new FindInscripcion(competi.id).execute();
			} catch (BusinessException e) {
				JOptionPane.showMessageDialog(new JFrame(), "Error al cargar el numero de Inscritos.");
			}
			getTextFieldNParticipantes().setText(String.valueOf(dtos.size()));

			CardLayout c =(CardLayout)layeredPane.getLayout();
			c.next(layeredPane);
			
		}
	
	private JPanel getPanelinscribir() {
		if (panelinscribir == null) {
			panelinscribir = new JPanel();
			panelinscribir.setLayout(new BorderLayout(0, 0));
			panelinscribir.add(getLblCompeticiones_1(), BorderLayout.NORTH);
			panelinscribir.add(getPanel(), BorderLayout.CENTER);
			panelinscribir.add(getBtnAsignar(), BorderLayout.SOUTH);
		}
		return panelinscribir;
	}
	private JLabel getLblCompeticiones_1() {
		if (lblCompeticiones_1 == null) {
			lblCompeticiones_1 = new JLabel("<Competicion>");
			lblCompeticiones_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		}
		return lblCompeticiones_1;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(getLblNewLabel())
						.addGap(18)
						.addComponent(getTextFieldNParticipantes(), GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(176, Short.MAX_VALUE))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(78)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(getLblNewLabel(), GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(getTextFieldNParticipantes(), GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(86, Short.MAX_VALUE))
			);
			panel.setLayout(gl_panel);
		}
		return panel;
	}
	private JButton getBtnAsignar() {
		if (btnAsignar == null) {
			btnAsignar = new JButton("Asignar Dorsales");
			btnAsignar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inscribirAction();
					
				}
			});
			btnAsignar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return btnAsignar;
	}
	
	
	private boolean inscribirAction() {

		try {
			List<AtletaDto>  atletas  = new AsignarDorsales(competi.id).execute();
			DefaultListModel<AtletaDto> model =	new DefaultListModel<AtletaDto>();
			model.addAll(atletas);
			getListDorsales().setModel(model );
		} catch (BusinessException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Error al asignar Dorsales.");
		}
		

		CardLayout c =(CardLayout)layeredPane.getLayout();
		c.next(layeredPane);
		
		return false;
	}
	
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Numero de Participantes: ");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		}
		return lblNewLabel;
	}
	private JTextField getTextFieldNParticipantes() {
		if (textFieldNParticipantes == null) {
			textFieldNParticipantes = new JTextField();
			textFieldNParticipantes.setFont(new Font("Tahoma", Font.PLAIN, 20));
			textFieldNParticipantes.setText("0");
			textFieldNParticipantes.setEditable(false);
			textFieldNParticipantes.setColumns(10);
		}
		return textFieldNParticipantes;
	}
	private JList<CompeticionesModelArticle> getList() {
		if (list == null) {
			list =  new JList<CompeticionesModelArticle>(getListModel());
			
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {

					getBoton_Comp_Siguien().setEnabled(true);
				}
			});
		}
		return list;
	}
	
	private DefaultListModel<CompeticionesModelArticle> getListModel() {
		List<CompeticionDTO> dtos = new VerCompeticionesAction().execute();
		List<CompeticionesModelArticle> articles = new ArrayList<CompeticionesModelArticle>();
		for (CompeticionDTO competicionDto : dtos) {
			articles.add(new CompeticionesModelArticle(competicionDto));
		}
		modeloList = new DefaultListModel<CompeticionesModelArticle>();
		modeloList.addAll(articles);
		return modeloList;
	}
	
	private JPanel getPanel_Atletas() {
		if (panel_Atletas == null) {
			panel_Atletas = new JPanel();
			panel_Atletas.setLayout(new BorderLayout(0, 0));
			panel_Atletas.add(getLblAtletasYDorsales(), BorderLayout.NORTH);
			panel_Atletas.add(getScrollPane_1());
		}
		return panel_Atletas;
	}
	private JLabel getLblAtletasYDorsales() {
		if (lblAtletasYDorsales == null) {
			lblAtletasYDorsales = new JLabel("Atletas y  Dorsales");
			lblAtletasYDorsales.setVerticalAlignment(SwingConstants.TOP);
			lblAtletasYDorsales.setFont(new Font("Tahoma", Font.PLAIN, 30));
		}
		return lblAtletasYDorsales;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getListDorsales());
		}
		return scrollPane_1;
	}
	private JList<AtletaDto> getListDorsales() {
		if (listDorsales == null) {
			listDorsales = new JList();
		}
		return listDorsales;
	}
}
