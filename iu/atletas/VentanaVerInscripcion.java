package iu.atletas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bussiness.BusinessFactory;
import bussiness.atleta.AtletaDto;
import bussiness.atleta.AtletaService;
import bussiness.atleta.InscripcionDTO;
import iu.MenuPrincipal;
import util.BusinessException;

public class VentanaVerInscripcion extends JFrame {

	private JPanel contentPane;
	private JLabel lblLabelidentificacion;
	private JPanel panelBotones;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JTextField textFieldIndentifier;
	private JButton btButtonContinuar;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblNewLabel;
	private JPanel panel;
	private JPanel panel2;
	private JLabel lblCompeticion;
	private JScrollPane scrollPane;
	private JTable table;

	private DefaultTableModel inscripcionModel ;
	private Optional<AtletaDto> atleta;
	
	AtletaService service =  BusinessFactory.forAtletaService();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaVerInscripcion frame = new VentanaVerInscripcion();
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
	public VentanaVerInscripcion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/util/Run.png")));	
		setResizable(false);
		setTitle("Ver Inscripciones");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPanel(), "name_845033180339700");
		contentPane.add(getPanel2(), "name_845086028640100");
	}

	private JLabel getLblLabelidentificacion() {
		if (lblLabelidentificacion == null) {
			lblLabelidentificacion = new JLabel("Identificate:");
			lblLabelidentificacion.setFont(new Font("Tahoma", Font.PLAIN, 26));
		}
		return lblLabelidentificacion;
	}
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			GroupLayout gl_panelBotones = new GroupLayout(panelBotones);
			gl_panelBotones.setHorizontalGroup(
				gl_panelBotones.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelBotones.createSequentialGroup()
						.addGap(25)
						.addGroup(gl_panelBotones.createParallelGroup(Alignment.LEADING)
							.addComponent(getLblNewLabel(), GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
							.addGroup(gl_panelBotones.createSequentialGroup()
								.addComponent(getBtButtonContinuar())
								.addContainerGap(316, Short.MAX_VALUE))
							.addGroup(gl_panelBotones.createSequentialGroup()
								.addGroup(gl_panelBotones.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(Alignment.LEADING, gl_panelBotones.createSequentialGroup()
										.addComponent(getRdbtnNewRadioButton(), GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(getRdbtnNewRadioButton_1(), GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
									.addComponent(getTextFieldIndentifier(), Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
								.addContainerGap())))
			);
			gl_panelBotones.setVerticalGroup(
				gl_panelBotones.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelBotones.createSequentialGroup()
						.addGap(25)
						.addComponent(getLblNewLabel())
						.addGap(18)
						.addGroup(gl_panelBotones.createParallelGroup(Alignment.BASELINE)
							.addComponent(getRdbtnNewRadioButton())
							.addComponent(getRdbtnNewRadioButton_1()))
						.addGap(18)
						.addComponent(getTextFieldIndentifier(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
						.addComponent(getBtButtonContinuar())
						.addGap(28))
			);
			panelBotones.setLayout(gl_panelBotones);
		}
		return panelBotones;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("DNI");
			rdbtnNewRadioButton.setSelected(true);
			rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Email");
			rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}
	private JTextField getTextFieldIndentifier() {
		if (textFieldIndentifier == null) {
			textFieldIndentifier = new JTextField();
			textFieldIndentifier.setColumns(10);
		}
		return textFieldIndentifier;
	}
	private JButton getBtButtonContinuar() {
		if (btButtonContinuar == null) {
			btButtonContinuar = new JButton("Continuar");
			btButtonContinuar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
				
						try {
							if(getRdbtnNewRadioButton().isSelected()) {
								atleta =service.comprobarAtleta(getTextFieldIndentifier().getText(), true);
							
							}else {
								atleta = service.comprobarAtleta(getTextFieldIndentifier().getText(), false);
							}
							
							if(atleta.isPresent()) {
								addCompeticiones();
							}else {
								 JOptionPane.showMessageDialog(null, "No encontrado");
							}
							
						} catch (BusinessException e1) {
							 JOptionPane.showMessageDialog(null, "Operación fallida");
						}
		
					
					
				}
			});
			btButtonContinuar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return btButtonContinuar;
	}
	
	
	private void addCompeticiones() {
		
		try {
			CardLayout c =(CardLayout)contentPane.getLayout();
			c.next(contentPane);
			
			 List<InscripcionDTO> inscripciones= 	service.getInscripcionesDeAtleta(atleta.get().DNI);
			 for (InscripcionDTO ins : inscripciones) {
				 String fecha="NAN";
				 if( ins.fecha_Inscripcion!=null) {
					 fecha= ins.fecha_Inscripcion.toString();
				 }
				
				 
				 String row[] = {ins.nombre_competion,ins.estado,fecha};
				inscripcionModel.addRow(row);
			}
			
			
		} catch (BusinessException e) {
			 JOptionPane.showMessageDialog(null, "Operación fallida");
		}
		
	}
	
	
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Selecciona el metodo: ");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblNewLabel;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getLblLabelidentificacion(), BorderLayout.NORTH);
			panel.add(getPanelBotones());
		}
		return panel;
	}
	private JPanel getPanel2() {
		if (panel2 == null) {
			panel2 = new JPanel();
			panel2.setLayout(new BorderLayout(0, 0));
			panel2.add(getLblCompeticion(), BorderLayout.NORTH);
			panel2.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panel2;
	}
	private JLabel getLblCompeticion() {
		if (lblCompeticion == null) {
			lblCompeticion = new JLabel("Competiciones:");
			lblCompeticion.setFont(new Font("Tahoma", Font.PLAIN, 26));
		}
		return lblCompeticion;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		String names[] = {"Nombre","Estado Inscripcion","Fecha Inscripcion"};

		if (table == null) {
			inscripcionModel = new DefaultTableModel(names,0);
			table = new JTable(inscripcionModel);
		}
		return table;
	}
}
