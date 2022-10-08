package iu.organizador;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bussiness.BusinessFactory;
import bussiness.atleta.CompeticionDTO;
import bussiness.competicion.CategoriaDto;
import bussiness.competicion.TramoDto;
import bussiness.competicion.commands.CrearCategoria;
import iu.MenuPrincipal;
import util.BusinessException;

import java.awt.Color;

public class CrearCategorias extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel inscripcionModel;
	private List<String> nombres = new ArrayList<String>();
	private List<Integer> edadesIniciales = new ArrayList<Integer>();
	private List<Integer> edadesFinales = new ArrayList<Integer>();
	private String titulo;
	private JLabel lbTitulo;
	
	private boolean errorCategoria;
	private JPanel panel;
	private CompeticionDTO dto;
	private List<CategoriaDto> categorias = new ArrayList<CategoriaDto>();
	private JButton btnAñadirCategoria;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private JButton btnCrearCompetición;
	private List<TramoDto> tramos;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearCategorias frame = new CrearCategorias(new CompeticionDTO(), new ArrayList<TramoDto>());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param arrayList 
	 */
	public CrearCategorias(CompeticionDTO dto, List<TramoDto> tramos) {
		this.dto = dto;
		this.tramos = tramos;
		
		titulo = "Configuración de categorias ";
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 627, 310);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/util/Run.png")));	
		setResizable(false);
		setTitle("Crear competición - "+titulo);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanel());
		contentPane.add(getBtnAñadirCategoria());
		contentPane.add(getBtnEliminar());
		contentPane.add(getBtnCancelar());
		contentPane.add(getBtnCrearCompetición());
		
		inscripcionModel.addColumn("Nombre Categoria");
		inscripcionModel.addColumn("Edad Inicial");
		inscripcionModel.addColumn("Edad Final");
		inscripcionModel.addRow(new Object[]{"Juvenil", "18", "29"});
		inscripcionModel.addRow(new Object[]{"Senior", "30", "49"});
		inscripcionModel.addRow(new Object[]{"Veterano", "50", "64"});
		inscripcionModel.addRow(new Object[]{"Jubilado", "65", "120"});
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(10, 10, 447, 198);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getLbTitulo(), BorderLayout.NORTH);
			panel.add(getScrollPane(), BorderLayout.CENTER);

		}
		return panel;
	}
	
	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Categorias");
			lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
			lbTitulo.setBounds(111, 21, 199, 27);
		}
		return lbTitulo;
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
			inscripcionModel = new DefaultTableModel();
			table = new JTable(inscripcionModel);
		}
		return table;
	}
	private JButton getBtnAñadirCategoria() {
		if (btnAñadirCategoria == null) {
			btnAñadirCategoria = new JButton("Añadir (+)");
			btnAñadirCategoria.setBackground(Color.GREEN);
			btnAñadirCategoria.setBounds(479, 62, 116, 23);
			btnAñadirCategoria.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inscripcionModel.addRow(new Object[]{"", "", ""});
				}
			});
		}
		return btnAñadirCategoria;
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar (-)");
			btnEliminar.setBackground(Color.RED);
			btnEliminar.setBounds(479, 116, 116, 23);
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inscripcionModel.removeRow(inscripcionModel.getRowCount()-1);
				}
			});
		}
		return btnEliminar;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBackground(Color.RED);
			btnCancelar.setBounds(231, 219, 177, 37);
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelar;
	}
	
	private boolean comprobarNombres(String valueAt) {
		for (int i = 0; i < nombres.size()-1; i++) {
			if (nombres.get(i).equals(valueAt)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean comprobarEdades() {
		if (edadesIniciales.get(0)!=18 || edadesFinales.get(edadesFinales.size()-1)!=120)
			return false;
		for (int i=0; i < edadesIniciales.size()-1; i++)
			if (edadesIniciales.get(i+1)-1 != edadesFinales.get(i)) {
				return false;
			}
		return true;
	}
	
	private JButton getBtnCrearCompetición() {
		if (btnCrearCompetición == null) {
			btnCrearCompetición = new JButton("Crear competición");
			btnCrearCompetición.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(inscripcionModel.getRowCount() == 0) {
						categoriasPorDefecto();
					}else {
						try {
							errorCategoria = false;
							nombres = new ArrayList<String>();
							edadesFinales = new ArrayList<Integer>();
							edadesIniciales = new ArrayList<Integer>();
							for(int i=0; i<inscripcionModel.getRowCount(); i++) {
								nombres.add(inscripcionModel.getValueAt(i, 0).toString());
								edadesIniciales.add(Integer.parseInt(inscripcionModel.getValueAt(i, 1).toString()));
								edadesFinales.add(Integer.parseInt(inscripcionModel.getValueAt(i, 2).toString()));
								if(!comprobarNombres((String) inscripcionModel.getValueAt(i, 0))) {
									JOptionPane.showMessageDialog(null, new	JLabel("Por favor, comprueba que las categorias creadas"
											+ " tengan un nombre distinto, actualmente hay repetidos.", JLabel.CENTER), 
											"Error - Nombre categoría repetido",  JOptionPane.ERROR_MESSAGE);
									errorCategoria = true;
									break;
								}
								if (inscripcionModel.getValueAt(i, 0).toString().trim().length() == 0) {
									mensajeErrorVacio();
									errorCategoria = true;
									break;
								}
							}
							if (!comprobarEdades()) {
								JOptionPane.showMessageDialog(null, new	JLabel("Comprueba las edades de las categorias. Recuerda"
										+ " que es necesario cubrir toda la franja de edades desde los 18 hasta los 120 años."
										+ " Recuerda también que para una misma edada no hay dos categorias disponibles.", JLabel.CENTER), 
										"Error - Edades mal asignadas",  JOptionPane.ERROR_MESSAGE);
								errorCategoria = true;
							}
							
							if (!errorCategoria) {
								try {
									CategoriaDto cat = new CategoriaDto();
									for(int i=0; i<inscripcionModel.getRowCount(); i++) {
										cat.id_competicion = dto.id_competicion;
										cat.nombre = inscripcionModel.getValueAt(i, 0).toString();
										cat.edad_inicial = Integer.parseInt(inscripcionModel.getValueAt(i, 1).toString());
										cat.edad_final = Integer.parseInt(inscripcionModel.getValueAt(i, 2).toString());
										CrearCategoria.execute(cat);
									}
									BusinessFactory.forCompeticionService().addCompeticion(dto);
									añadirTramos(dto);
									
									JOptionPane.showMessageDialog(null, "Se añadio la competicion "+dto.nombre);
									
								} catch (BusinessException ex) {
									JOptionPane.showMessageDialog(null, "Error al añadir competicion");
								}
							}
						}catch(Exception esException) {
							mensajeErrorVacio();
						}
					}
				}

				private void mensajeErrorVacio() {
					JOptionPane.showMessageDialog(null, new	JLabel("Por favor, rellene todos los campos, hay algunos vacios."
							+ " Si no vas a usar más categorias, por favor, elimina las celdas. Si te quedas sin celdas,"
							+ " se crearán las categorías por defecto.", JLabel.CENTER), 
							"Error - Campos vacios",  JOptionPane.ERROR_MESSAGE);
				}
			});
			btnCrearCompetición.setBackground(Color.GREEN);
			btnCrearCompetición.setBounds(418, 219, 177, 37);
		}
		return btnCrearCompetición;
	}
	
	private void categoriasPorDefecto() {
		int result = JOptionPane.showConfirmDialog(null, new	JLabel("¿Está seguro que no quiere crear categorias personalizadas?.\n"
				+ "Se asignarán las categorias por defecto en ese caso", JLabel.CENTER), 
				"Confirmación - ¿Quiere las categorias por defecto?",  JOptionPane.YES_NO_OPTION);
		if (result == 0) {
			inscripcionModel.addRow(new Object[]{"Juvenil", "18", "29"});
			inscripcionModel.addRow(new Object[]{"Senior", "30", "49"});
			inscripcionModel.addRow(new Object[]{"Veterano", "50", "64"});
			inscripcionModel.addRow(new Object[]{"Jubilado", "65", "120"});
			
			try {
				CategoriaDto cat = new CategoriaDto();
				for(int i=0; i<inscripcionModel.getRowCount(); i++) {
					cat.id_competicion = dto.id_competicion;
					cat.nombre = inscripcionModel.getValueAt(i, 0).toString();
					cat.edad_inicial = Integer.parseInt(inscripcionModel.getValueAt(i, 1).toString());
					cat.edad_final = Integer.parseInt(inscripcionModel.getValueAt(i, 2).toString());
					CrearCategoria.execute(cat);
				}
				BusinessFactory.forCompeticionService().addCompeticion(dto);
				añadirTramos(dto);
				
				JOptionPane.showMessageDialog(null, "Se añadio la competicion "+dto.nombre);
				
			} catch (BusinessException ex) {
				JOptionPane.showMessageDialog(null, "Error al añadir competicion");
			}
		}
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
}
