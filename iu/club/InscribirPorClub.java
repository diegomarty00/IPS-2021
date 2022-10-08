package iu.club;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bussiness.atleta.AtletaDto;
import bussiness.atleta.CompeticionDTO;
import bussiness.atleta.commands.InscribirListaAtletas;
import bussiness.atleta.commands.RegistrarAtleta;
import iu.organizador.CompeticionesModelArticle;
import iu.organizador.actions.VerCompeticionesAction;
import persistencia.PersistenceFactory;
import persistencia.competicion.CompeticionGateway;
import bussiness.clubes.ClubDto;
import bussiness.clubes.ClubService;
import bussiness.clubes.impl.ClubServiceImpl;
import bussiness.competicion.CompeticionService;
import bussiness.competicion.impl.CompeticionServiceImpl;
import util.BusinessException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JTextArea;

public class InscribirPorClub extends JFrame {

	private JPanel contentPane;
	private JPanel panelSeleccionClub;
	private JPanel panelAgregarAtletas;
	private JPanel panelAgregarArchivo;
	private JPanel panelFinal;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnPorArchivo;
	private JLabel lblMano;
	private JPanel panel_1;
	private JButton btnNewButton_1;
	private JPanel panel_3;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel contentPane_1;
	private JRadioButton rdbtnMujer;
	private JRadioButton rdbtnHombre;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblSexo;
	private JButton btnAñadir;
	private JLabel lblDni;
	private JLabel lblFechaNac;
	private JLabel lblCorreo;
	private JTextField textFieldDNI;
	private JTextField textFieldApellidos;
	private JTextField textFieldCorreo;
	private JTextField textFieldNombre;
	private JButton btnCancelar;
	private JSpinner spinnerFecha;
	private JPanel panel_2;
	DefaultListModel<String> modelo = new DefaultListModel();
	
	
	private Object club;
	private DefaultListModel<CompeticionesModelArticle> modeloList;
	private int id_competicion;
	public void setId_competicion(int id_competicion) {
		this.id_competicion = id_competicion;
	}
	private List<AtletaDto> atletas = new ArrayList<AtletaDto>();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JScrollPane scrollClubes;
	private JList listaClubes;
	private JLabel lblAtletasInscritos;
	private JScrollPane scrollPane_1;
	private JList list;
	private String nombreClub;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InscribirPorClub frame = new InscribirPorClub();
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
	public InscribirPorClub() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPanelSeleccionClub(), "name_894685440385300");
		contentPane.add(getPanelAgregarAtletas(), "name_894701970546600");
		contentPane.add(getPanelAgregarArchivo(), "name_894718870570000");
		contentPane.add(getPanelFinal(), "name_894737938033200");

	}

	private JPanel getPanelSeleccionClub() {
		if (panelSeleccionClub == null) {
			panelSeleccionClub = new JPanel();
			panelSeleccionClub.setLayout(new BorderLayout(0, 0));
			panelSeleccionClub.add(getPanel(), BorderLayout.SOUTH);
			panelSeleccionClub.add(getLblNewLabel(), BorderLayout.NORTH);
			panelSeleccionClub.add(getScrollClubes(), BorderLayout.CENTER);
		}
		return panelSeleccionClub;
	}
	private JPanel getPanelAgregarAtletas() {
		if (panelAgregarAtletas == null) {
			panelAgregarAtletas = new JPanel();
			panelAgregarAtletas.setLayout(new BorderLayout(0, 0));
			panelAgregarAtletas.add(getLblMano(), BorderLayout.NORTH);
			panelAgregarAtletas.add(getPanel_1(), BorderLayout.CENTER);
			panelAgregarAtletas.add(getBtnNewButton_1(), BorderLayout.SOUTH);
		}
		return panelAgregarAtletas;
	}
	private JPanel getPanelAgregarArchivo() {
		if (panelAgregarArchivo == null) {
			panelAgregarArchivo = new JPanel();
			panelAgregarArchivo.setLayout(new BorderLayout(0, 0));
		}
		return panelAgregarArchivo;
	}
	private JPanel getPanelFinal() {
		if (panelFinal == null) {
			panelFinal = new JPanel();
			panelFinal.setLayout(new BorderLayout(0, 0));
			panelFinal.add(getLblAtletasInscritos(), BorderLayout.NORTH);
			panelFinal.add(getScrollPane_1(), BorderLayout.CENTER);
		}
		return panelFinal;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Selecciona tu Club");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		}
		return lblNewLabel;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			panel.add(getBtnPorArchivo());
			panel.add(getBtnNewButton());
		}
		return panel;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("A mano");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					accionBoton(1);
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return btnNewButton;
	}
	private JButton getBtnPorArchivo() {
		if (btnPorArchivo == null) {
			btnPorArchivo = new JButton("Por Archivo");
			btnPorArchivo.addActionListener(new ActionListener() {
			

				public void actionPerformed(ActionEvent e) {
					
					if (listaClubes.getSelectedValue() == null) {
						JOptionPane.showMessageDialog(null, new JLabel("Por favor seleccione un club", JLabel.CENTER), 
								"Error ",  JOptionPane.ERROR_MESSAGE);
					}
					else {
						VentanaSeleccionCompeticionFichero v = new VentanaSeleccionCompeticionFichero(listaClubes.getSelectedValue().toString());
						v.setVisible(true);
					}
					
				}
			});
			btnPorArchivo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return btnPorArchivo;
	}
	
	private void accionBoton(int i) {
		VentanaSeleccionCompeticion v = new VentanaSeleccionCompeticion(this,i);
		if (listaClubes.getSelectedValue() == null) {
			JOptionPane.showMessageDialog(null, new JLabel("Por favor seleccione un club", JLabel.CENTER), 
					"Error ",  JOptionPane.ERROR_MESSAGE);
		}
		else {
			nombreClub = listaClubes.getSelectedValue().toString();
			club =listaClubes.getSelectedValue();
			v.setVisible(true);
		}
		
	}
	
	
	private JLabel getLblMano() {
		if (lblMano == null) {
			lblMano = new JLabel("Agrega los Atletas");
			lblMano.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblMano;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			GroupLayout gl_panel_1 = new GroupLayout(panel_1);
			gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addComponent(getContentPane_1(), GroupLayout.PREFERRED_SIZE, 712, Short.MAX_VALUE)
					.addComponent(getPanel_3(), GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(getContentPane_1(), GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(getPanel_3(), GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);
			panel_1.setLayout(gl_panel_1);
		}
		return panel_1;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("Inscribir todos");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					botonInsctibirAction();
				}
			});
			btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return btnNewButton_1;
	}
	
	private void botonInsctibirAction() {
		if(!atletas.isEmpty()) {
			try {
				CompeticionService cs = new CompeticionServiceImpl();			
				CompeticionDTO cdto = cs.findById(id_competicion).get();
				ClubService club = new ClubServiceImpl();
				ClubDto cd = club.findByNombre(nombreClub).get();
				List<AtletaDto> atletasIns = new InscribirListaAtletas(atletas, id_competicion, cd.id_club).execute();
				Double deuda = cdto.cuota * atletas.size() + cd.totalPagar;
				club.updateDeuda(deuda, nombreClub);
				
				setTextAreaAtletas(atletasIns);
				nextLayout(2);
			} catch (BusinessException e) {
				JOptionPane.showMessageDialog(null, new JLabel("Error al añadir", JLabel.CENTER), 
						"Error ",  JOptionPane.ERROR_MESSAGE);
			}
			
			
			
		}
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(new BorderLayout(0, 0));
			panel_3.add(getScrollPane());
		}
		return panel_3;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"DNI", "Nombre", "Apellidos", "Correo", "Sexo", "Fecha"
				}
			));
		}
		return table;
	}
	private JPanel getContentPane_1() {
		if (contentPane_1 == null) {
			contentPane_1 = new JPanel();
			contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane_1.setLayout(new GridLayout(2, 7, 0, 0));
			contentPane_1.add(getLblDni());
			contentPane_1.add(getLblNombre());
			contentPane_1.add(getLblApellidos());
			contentPane_1.add(getLblCorreo());
			contentPane_1.add(getLblSexo());
			contentPane_1.add(getLblFechaNac());
			contentPane_1.add(getBtnAñadir());
			contentPane_1.add(getTextFieldDNI());
			contentPane_1.add(getTextFieldNombre());
			contentPane_1.add(getTextFieldApellidos());
			contentPane_1.add(getTextFieldCorreo());
			contentPane_1.add(getPanel_2());
			contentPane_1.add(getSpinnerFecha());
			contentPane_1.add(getBtnCancelar());
		}
		return contentPane_1;
	}
	private JRadioButton getRdbtnMujer() {
		if (rdbtnMujer == null) {
			rdbtnMujer = new JRadioButton("M");
			buttonGroup.add(rdbtnMujer);
			rdbtnMujer.setActionCommand("Mujer");
		}
		return rdbtnMujer;
	}
	private JRadioButton getRdbtnHombre() {
		if (rdbtnHombre == null) {
			rdbtnHombre = new JRadioButton("H");
			buttonGroup.add(rdbtnHombre);
			rdbtnHombre.setSelected(true);
			rdbtnHombre.setActionCommand("Hombre");
		}
		return rdbtnHombre;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblNombre;
	}
	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblApellidos;
	}
	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Sexo:");
			lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblSexo;
	}
	private JButton getBtnAñadir() {
		if (btnAñadir == null) {
			btnAñadir = new JButton("Añadir");
			btnAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					AccionAñadir();
					
				}
			});
			btnAñadir.setBackground(Color.GREEN);
		}
		return btnAñadir;
	}
	
	private void AccionAñadir() {
		if (getTextFieldNombre().getText().length() == 0 || getTextFieldApellidos().getText().length() == 0 
				|| getTextFieldDNI().getText().length() == 0 || getTextFieldCorreo().getText().length() == 0)
			JOptionPane.showMessageDialog(null, new JLabel("Por favor, rellene todos los campos de texto para poder"
					+ " realizar el registro correctamente", JLabel.CENTER), 
					"Error - Campos de texto vacios",  JOptionPane.ERROR_MESSAGE);
		else if (!checkEdad((Date) getSpinnerFecha().getValue()))
			JOptionPane.showMessageDialog(null, new JLabel("Usted no puede registrarse al ser menor"
					+ " de edad", JLabel.CENTER), 
					"Error - Solo atletas mayores de edad",  JOptionPane.ERROR_MESSAGE);
		else {
			try {
				AtletaDto dto = new AtletaDto();
				dto.DNI = getTextFieldDNI().getText();
				dto.nombre = getTextFieldNombre().getText();
				dto.genero = buttonGroup.getSelection().getActionCommand();
				dto.correo = getTextFieldCorreo().getText();
				dto.apellido = getTextFieldApellidos().getText();
				dto.fechaNacimiento =  ((Date) getSpinnerFecha().getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
				
				atletas.add(dto);
				actualizarTabla();
			
				
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, new JLabel("Este usuario ya se encuentra registrado"
						, JLabel.CENTER), 
						"Error - Usuario ya registrado",  JOptionPane.ERROR_MESSAGE);
			}
		}
	}
		
		private boolean checkEdad(Date anio) {
			if (anio.getYear() > 18)
				return true;

			return false;
		}
	
	
	private void actualizarTabla() {
		
		String names[] = {"DNI","Nombre","Apellido" ,"Correo","Genero","Nacimiento"};
		DefaultTableModel atletamodeloList = new DefaultTableModel(names,0);
		for (AtletaDto atl : atletas) {
			atletamodeloList.addRow(atl.toList());
		}	
		 getTable().setModel(atletamodeloList);
	}
		
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI:");
			lblDni.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblDni;
	}
	private JLabel getLblFechaNac() {
		if (lblFechaNac == null) {
			lblFechaNac = new JLabel("Fecha de nacimiento: ");
			lblFechaNac.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblFechaNac;
	}
	private JLabel getLblCorreo() {
		if (lblCorreo == null) {
			lblCorreo = new JLabel("Correo:");
			lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblCorreo;
	}
	private JTextField getTextFieldDNI() {
		if (textFieldDNI == null) {
			textFieldDNI = new JTextField();
			textFieldDNI.setColumns(10);
		}
		return textFieldDNI;
	}
	private JTextField getTextFieldApellidos() {
		if (textFieldApellidos == null) {
			textFieldApellidos = new JTextField();
			textFieldApellidos.setColumns(10);
		}
		return textFieldApellidos;
	}
	private JTextField getTextFieldCorreo() {
		if (textFieldCorreo == null) {
			textFieldCorreo = new JTextField();
			textFieldCorreo.setColumns(10);
		}
		return textFieldCorreo;
	}
	private JTextField getTextFieldNombre() {
		if (textFieldNombre == null) {
			textFieldNombre = new JTextField();
			textFieldNombre.setColumns(10);
		}
		return textFieldNombre;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Eliminar Selec");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarAction();
				}
			});
			btnCancelar.setBackground(Color.RED);
		}
		return btnCancelar;
	}
	
	private void eliminarAction() {
		
		int i = getTable().getSelectedRow();
		atletas.remove(i);
		
		 actualizarTabla();
		
		
	}
	
	private JSpinner getSpinnerFecha() {
		if (spinnerFecha == null) {
			spinnerFecha = new JSpinner();
			spinnerFecha.setModel(new SpinnerDateModel(new Date(1638313200000L), null, null, Calendar.DAY_OF_YEAR));
		}
		return spinnerFecha;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new GridLayout(0, 2, 0, 0));
			panel_2.add(getRdbtnHombre());
			panel_2.add(getRdbtnMujer());
		}
		return panel_2;
	}
	

	private JScrollPane getScrollClubes() {
		if (scrollClubes == null) {
			scrollClubes = new JScrollPane();
			scrollClubes.setViewportView(getListaClubes());
		}
		return scrollClubes;
	}
	private JList getListaClubes() {
		ClubService cs = new ClubServiceImpl();
		if (listaClubes == null) {
			listaClubes = new JList();
			listaClubes.setModel(modelo);
			try {
				modelo.addAll(cs.findClubesByNombre());
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listaClubes;
	}
	
	public void nextLayout(int veces) {
		CardLayout c =(CardLayout)contentPane.getLayout();
		for (int i = 0; i < veces; i++) {
			c.next(contentPane);
		}
		
	}
	
	private JLabel getLblAtletasInscritos() {
		if (lblAtletasInscritos == null) {
			lblAtletasInscritos = new JLabel("Atletas inscritos");
			lblAtletasInscritos.setFont(new Font("Tahoma", Font.PLAIN, 26));
		}
		return lblAtletasInscritos;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getList());
		}
		return scrollPane_1;
	}
	
	private void setTextAreaAtletas(List<AtletaDto> lista) {
		DefaultListModel<String> modelo = new DefaultListModel();
		list.setModel(modelo);
		int i =0;
		for (AtletaDto atletaDto : lista) {
			modelo.add(i,atletaDto.toListaTexto());
			i++;
		}
		
	}
	private JList getList() {
		if (list == null) {
			list = new JList();
		}
		return list;
	}
}
