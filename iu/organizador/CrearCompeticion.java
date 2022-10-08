package iu.organizador;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bussiness.BusinessFactory;
import bussiness.atleta.CompeticionDTO;
import bussiness.competicion.TramoDto;
import iu.MenuPrincipal;
import iu.atletas.VentanaRegistroAplicacion;
import util.BusinessException;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class CrearCompeticion extends JFrame {

	private JPanel contentPane;
	private JLabel lblCrearComp;
	private JPanel panel;
	private JLabel lblNombre;
	private JLabel lblFecha;
	private JLabel lbltipo;
	private JLabel lblDistancia;
	private JLabel lblCuota;
	private JLabel lblPlazas;
	private JTextField textFieldNombre;
	private JTextField textField_Tipo;
	private JSpinner spinnerDistancia;
	private JSpinner spinnerCuota;
	private JSpinner spinnerFecha;
	private JSpinner spinnerPlazas;
	private JButton btnCreate;
	private JTable tablaTiemposIntermedios;
	private JScrollPane scrollPane;
	private JButton btAñadir;
	private JLabel lbTramo;
	private List<TramoDto> tramos = new ArrayList<TramoDto>();
	DefaultTableModel modelo = new DefaultTableModel();
	private JTextField txDescripcionTramo;
	private JLabel lbDorsalesReservados;
	private JSpinner spinnerDorsales;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearCompeticion frame = new CrearCompeticion();
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
	public CrearCompeticion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/util/Run.png")));	
		setTitle("CrearCompeticion");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 574, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLblCrearComp(), BorderLayout.NORTH);
		contentPane.add(getPanel(), BorderLayout.CENTER);
		contentPane.add(getBtnCreate(), BorderLayout.SOUTH);
	}

	private JLabel getLblCrearComp() {
		if (lblCrearComp == null) {
			lblCrearComp = new JLabel("Crea la Competicion:");
			lblCrearComp.setFont(new Font("Tahoma", Font.PLAIN, 26));
		}
		return lblCrearComp;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = 
					new JPanel();
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(4)
								.addComponent(getLblNombre())
								.addGap(18)
								.addComponent(getTextFieldNombre(), GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(6)
								.addComponent(getLblFecha(), GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addGap(6)
								.addComponent(getSpinnerFecha(), GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(6)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(getLbltipo())
										.addGap(47)
										.addComponent(getTextField_Tipo(), GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(getLblDistancia())
										.addGap(6)
										.addComponent(getSpinnerDistancia(), GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE))))
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(74)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(getBtAñadir(), GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
									.addComponent(getScrollPane(), GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
										.addContainerGap()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_panel.createSequentialGroup()
												.addComponent(getLbDorsalesReservados(), GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(getSpinnerDorsales()))
											.addGroup(gl_panel.createSequentialGroup()
												.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
													.addComponent(getLblPlazas(), Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
													.addComponent(getLbTramo(), Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
													.addComponent(getSpinnerPlazas(), GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
													.addComponent(getTxDescripcionTramo(), GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)))))
									.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
										.addGap(6)
										.addComponent(getLblCuota(), GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(getSpinnerCuota(), GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)))
								.addGap(59)))
						.addGap(109))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(6)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(getTextFieldNombre(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(getLblNombre()))
						.addGap(6)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(1)
								.addComponent(getLblFecha()))
							.addComponent(getSpinnerFecha(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(6)
								.addComponent(getTextField_Tipo(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(getLbltipo())))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(1)
								.addComponent(getLblDistancia()))
							.addComponent(getSpinnerDistancia(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(6)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(1)
								.addComponent(getLblCuota()))
							.addComponent(getSpinnerCuota(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(7)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(getLblPlazas())
							.addComponent(getSpinnerPlazas(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addComponent(getLbTramo())
							.addComponent(getTxDescripcionTramo(), GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(getLbDorsalesReservados())
							.addComponent(getSpinnerDorsales(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(9)
						.addComponent(getScrollPane(), GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(getBtAñadir())
						.addGap(26))
			);
			panel.setLayout(gl_panel);
		}
		return panel;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre");
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblNombre;
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha");
			lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblFecha;
	}
	private JLabel getLbltipo() {
		if (lbltipo == null) {
			lbltipo = new JLabel("Tipo");
			lbltipo.setHorizontalAlignment(SwingConstants.LEFT);
			lbltipo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lbltipo;
	}
	private JLabel getLblDistancia() {
		if (lblDistancia == null) {
			lblDistancia = new JLabel("Distancia");
			lblDistancia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblDistancia;
	}
	private JLabel getLblCuota() {
		if (lblCuota == null) {
			lblCuota = new JLabel("Cuota");
			lblCuota.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblCuota;
	}
	private JLabel getLblPlazas() {
		if (lblPlazas == null) {
			lblPlazas = new JLabel("Plazas");
			lblPlazas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblPlazas;
	}
	private JTextField getTextFieldNombre() {
		if (textFieldNombre == null) {
			textFieldNombre = new JTextField();
			textFieldNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textFieldNombre.setColumns(10);
		}
		return textFieldNombre;
	}
	private JTextField getTextField_Tipo() {
		if (textField_Tipo == null) {
			textField_Tipo = new JTextField();
			textField_Tipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textField_Tipo.setColumns(10);
		}
		return textField_Tipo;
	}
	private JSpinner getSpinnerDistancia() {
		if (spinnerDistancia == null) {
			spinnerDistancia = new JSpinner();
			spinnerDistancia.setFont(new Font("Tahoma", Font.PLAIN, 17));
			spinnerDistancia.setModel(new SpinnerNumberModel(new Integer(2), new Integer(1), null, new Integer(1)));
		}
		return spinnerDistancia;
	}
	private JSpinner getSpinnerCuota() {
		if (spinnerCuota == null) {
			spinnerCuota = new JSpinner();
			spinnerCuota.setFont(new Font("Tahoma", Font.PLAIN, 17));
			spinnerCuota.setModel(new SpinnerNumberModel(new Integer(5), new Integer(0), null, new Integer(1)));
		}
		return spinnerCuota;
	}
	private JSpinner getSpinnerFecha() {
		if (spinnerFecha == null) {
			spinnerFecha = new JSpinner();
			spinnerFecha.setFont(new Font("Tahoma", Font.PLAIN, 17));
			spinnerFecha.setModel(new SpinnerDateModel(new Date(1636326000000L), null, null, Calendar.DAY_OF_YEAR));
		}
		return spinnerFecha;
	}
	private JSpinner getSpinnerPlazas() {
		if (spinnerPlazas == null) {
			spinnerPlazas = new JSpinner();
			spinnerPlazas.setModel(new SpinnerNumberModel(new Integer(10), new Integer(1), null, new Integer(1)));
			spinnerPlazas.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return spinnerPlazas;
	}
	private JButton getBtnCreate() {
		if (btnCreate == null) {
			btnCreate = new JButton("Crear");
			btnCreate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 accionBotonCrear();
					
					
				}
			});
			btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 22));
		}
		return btnCreate;
	}
	
	
	private void accionBotonCrear() {
		CompeticionDTO dto = new CompeticionDTO();
		
		if(!getTextFieldNombre().getText().isBlank()) {
			dto.nombre =getTextFieldNombre().getText();
			if(!getTextField_Tipo().getText().isBlank()) {
				dto.tipo =getTextField_Tipo().getText();
				
		
					dto.distancia =(Integer) getSpinnerDistancia().getValue();
					dto.cuota =(Integer) getSpinnerCuota().getValue();
					dto.plazas =(Integer) getSpinnerPlazas().getValue();
					dto.fecha = convertToLocalDateViaInstant((Date) getSpinnerFecha().getValue());
					dto.fecha_inicio_inscripcion = LocalDate.now();
					dto.fecha_fin_inscripcion = LocalDate.of(2022,3,3);
					dto.id_competicion = Math.abs(dto.nombre.hashCode());
					dto.numero_tramos = tramos.size();
					dto.dorsalesReservados = (Integer) getSpinnerDorsales().getValue();
				
					CrearCategorias v = new CrearCategorias(dto,tramos);
					v.setVisible(true);
					
				
			}else {
				JOptionPane.showMessageDialog(null, "Tipo vacio");
			}
		}else {
			JOptionPane.showMessageDialog(null, "Nombre vacio");
		}
		
		
	}
	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	private JTable getTablaTiemposIntermedios() {
		if (tablaTiemposIntermedios == null) {			
			modelo.addColumn("Descripción");
			
			tablaTiemposIntermedios = new JTable(modelo);
		}
		return tablaTiemposIntermedios;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTablaTiemposIntermedios());
		}
		return scrollPane;
	}
	private JButton getBtAñadir() {
		if (btAñadir == null) {
			btAñadir = new JButton("Añadir tramo");
			btAñadir.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TramoDto dto = new TramoDto();
					dto.descripcion = getTxDescripcionTramo().getText();
					if (dto.descripcion.isBlank()) {
						JOptionPane.showMessageDialog(new JFrame(), "Debe introducir una descripción valida", "Error",  JOptionPane.ERROR_MESSAGE);
					}
					else if (estaTramoRepetido(dto.descripcion)) {
						JOptionPane.showMessageDialog(new JFrame(), "Este tramo ya ha sido creado", "Error",  JOptionPane.ERROR_MESSAGE);
					}
					else {
							dto.numeroTramo = tramos.size() + 1;
							tramos.add(dto);
							modelo.addRow(new Object[] {dto.descripcion});
					}
					
					
				
				}
			});
		}
		return btAñadir;
	}
	private JLabel getLbTramo() {
		if (lbTramo == null) {
			lbTramo = new JLabel("Tramo");
			lbTramo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lbTramo;
	}
	
	private JTextField getTxDescripcionTramo() {
		if (txDescripcionTramo == null) {
			txDescripcionTramo = new JTextField();
			txDescripcionTramo.setColumns(10);
		}
		return txDescripcionTramo;
	}
	private JLabel getLbDorsalesReservados() {
		if (lbDorsalesReservados == null) {
			lbDorsalesReservados = new JLabel("N dorsales");
			lbDorsalesReservados.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lbDorsalesReservados;
	}
	private JSpinner getSpinnerDorsales() {
		if (spinnerDorsales == null) {
			spinnerDorsales = new JSpinner();
			spinnerDorsales.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return spinnerDorsales;
	}
	
	private void añadirTramos(CompeticionDTO dto) {
		for (TramoDto tramo : tramos) {
			tramo.idCompeticion = dto.id_competicion;
			try {
				BusinessFactory.forCompeticionService().addTramo(tramo);
			}
			catch (BusinessException e) {
				JOptionPane.showMessageDialog(new JFrame(), "Error al añadir el tramo: " + tramo.numeroTramo, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private boolean estaTramoRepetido(String nombre) {
		for (TramoDto t: tramos) {
			if (t.descripcion.equals(nombre)) {
				return true;
			}
		}
		
		return false;
	}
}
