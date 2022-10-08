package iu.organizador;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bussiness.atleta.AtletaDto;
import bussiness.atleta.CompeticionDTO;
import iu.MenuPrincipal;
import iu.organizador.actions.VerAtletasDeCompeticionAction;
import iu.organizador.actions.VerCompeticionesAction;

public class VerClasificacion extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = 1L;

	private JLayeredPane layeredPane;
	private JPanel panel_Competiciones;
	private JPanel panel_Atletas;
	private JList<CompeticionesModelArticle> list;
	private JPanel panel_botones_Competi;
	private JButton boton_Comp_Siguien;
	private JButton boton_com_cancel;
	private DefaultListModel<CompeticionesModelArticle> modeloList;
	private DefaultTableModel atletamodeloList ;
	
	private JLabel Label_atletas;
	private JScrollPane scrollPane_atletas;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerClasificacion frame = new VerClasificacion();
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
	public VerClasificacion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/util/Run.png")));	
		setResizable(false);
		setTitle("Ver clasificación - Selección de competición");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 699, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

	
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(getLayeredPane());
	}

	public JLayeredPane getLayeredPane() {
		if (layeredPane == null) {
			layeredPane = new JLayeredPane();
			layeredPane.setLayout(new CardLayout(0, 0));
			layeredPane.add(getPanel_Competiciones(), "name_91163845033700");
			layeredPane.add(getPanel_Atletas(), "name_91153845033700");
		}
		return layeredPane;
	}

	private JPanel getPanel_Competiciones() {
		if (panel_Competiciones == null) {
			panel_Competiciones = new JPanel();
			panel_Competiciones.setLayout(new BorderLayout(0, 0));

			JLabel lblCompeticiones = new JLabel("Competiciones");
			lblCompeticiones.setFont(new Font("Tahoma", Font.PLAIN, 30));
			panel_Competiciones.add(lblCompeticiones, BorderLayout.NORTH);

			JScrollPane scrollPane = new JScrollPane();
			panel_Competiciones.add(scrollPane, BorderLayout.CENTER);
			scrollPane.setViewportView(getList());
			panel_Competiciones.add(getPanel_botones_Competi(), BorderLayout.SOUTH);
		}
		return panel_Competiciones;
	}

	private JPanel getPanel_Atletas() {
		if (panel_Atletas == null) {
			panel_Atletas = new JPanel();
			panel_Atletas.setLayout(new BorderLayout(0, 0));
			panel_Atletas.add(getLabel_atletas(), BorderLayout.NORTH);
			panel_Atletas.add(getScrollPane_atletas(), BorderLayout.CENTER);
		}
		return panel_Atletas;
	}

	private JList<CompeticionesModelArticle> getList() {
		if (list == null) {
		

			list = new JList<CompeticionesModelArticle>(getListModel());
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
			if (competicionDto.fecha.isBefore(LocalDate.now()))
				articles.add(new CompeticionesModelArticle(competicionDto));
		}
		modeloList = new DefaultListModel<CompeticionesModelArticle>();
		modeloList.addAll(articles);
		return modeloList;
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
		
		
		
		CompeticionesModelArticle competi =  getList().getSelectedValue();
		
		List<AtletaDto> atletasDtos = new VerAtletasDeCompeticionAction(competi.getId()).execute();
		
		
		List<AtletaModelArticle> lista = new ArrayList<AtletaModelArticle>();
		
	
		if(atletasDtos!= null) {
			
			for (AtletaDto atletaDto : atletasDtos) {
				AtletaModelArticle article = new AtletaModelArticle(atletaDto);
				lista.add(article);
				atletamodeloList.addRow(article.toList());
			}
			
		}

		
		
		
		
		CardLayout c =(CardLayout)layeredPane.getLayout();
		c.next(layeredPane);
		
	}
	

	private JButton getBoton_com_cancel() {
		if (boton_com_cancel == null) {
			boton_com_cancel = new JButton("Cancelar");
			boton_com_cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return boton_com_cancel;
	}
	private JLabel getLabel_atletas() {
		if (Label_atletas == null) {
			Label_atletas = new JLabel("Atletas");
			Label_atletas.setFont(new Font("Tahoma", Font.PLAIN, 30));
		}
		return Label_atletas;
	}
	private JScrollPane getScrollPane_atletas() {
		if (scrollPane_atletas == null) {
			scrollPane_atletas = new JScrollPane();
			scrollPane_atletas.setViewportView(getTable());
		}
		return scrollPane_atletas;
	}
	private JTable getTable() {
		String names[] = {"DNI","Nombre","Apellido" ,"Genero","Nacimiento","Categoria","Fecha Inscripcion","Estado Inscripcion"};

		if (table == null) {
			atletamodeloList = new DefaultTableModel(names,0);
			table = new JTable(atletamodeloList);
		}
		return table;
	}
}
