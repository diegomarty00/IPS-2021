package iu.atletas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import bussiness.atleta.CompeticionDTO;
import bussiness.atleta.InscripcionDTO;
import bussiness.competicion.commands.Inscribirse;
import bussiness.competicion.commands.VerCompeticiones;
import bussiness.competicion.commands.VerInscripciones;
import bussiness.competicion.commands.VerRegistrados;
import iu.MenuPrincipal;
import iu.organizador.CompeticionesModelArticle;
import iu.organizador.actions.VerCompeticionesAction;

import javax.swing.JScrollPane;
import javax.swing.JList;

public class VentanaVerCompeticiones extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbTitulo;
	private JLabel lbFechaCarrera;
	private JTextField txFechaCarrera;
	private JLabel lbTipo;
	private JTextField txTipo;
	private JLabel lbCuota;
	private JTextField txCuota;
	private JButton btInscribirse;
	private JTextField txDistancia;
	private JLabel lbInicioInscripcion;
	private JLabel lbFinInscripcion;
	private JLabel lbDistancia;
	private JTextField txFinInscripcion;
	private JLabel lbPlazas;
	private JTextField txPlazas;
	private List<CompeticionDTO> compes;
	private int elementoCB;
	private JTextField txCorreo;
	private JLabel lblCorreo;
	private JTextField txInicioInscripcion;
	private JList<CompeticionDTO> list;
	private DefaultListModel<CompeticionDTO> modeloList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaVerCompeticiones frame = new VentanaVerCompeticiones();
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
	public VentanaVerCompeticiones() {
		compes = VerCompeticiones.execute();
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/util/Run.png")));	
		setResizable(false);
		setTitle("Ver competiciones abiertas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 747, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbTitulo());
		contentPane.add(getLbFechaCarrera());
		contentPane.add(getTxFechaCarrera());
		contentPane.add(getLbTipo());
		contentPane.add(getTxTipo());
		contentPane.add(getLbCuota());
		contentPane.add(getTxCuota());
		contentPane.add(getBtInscribirse());
		contentPane.add(getLbInicioInscripcion());
		contentPane.add(getLbDistancia());
		contentPane.add(getLbFinInscripcion());

		contentPane.add(getTxInicioInscripcion());
		contentPane.add(getTxFinInscripcion());
		contentPane.add(getLbPlazas());
		contentPane.add(getTxDistancia());
		contentPane.add(getTxPlazas());
		contentPane.add(getTxCorreo());
		contentPane.add(getLblCorreo());
		contentPane.add(getList());
		getList().setSelectedIndex(0);
		actualiza();
	}
	
	private JLabel getLbDistancia() {
		if (lbDistancia == null) {
			lbDistancia = new JLabel("Distancia:");
			lbDistancia.setHorizontalAlignment(SwingConstants.LEFT);
			lbDistancia.setBounds(275, 242-40, 195, 14);
		}
		return lbDistancia;
	}

	private JTextField getTxDistancia() {
		if (txDistancia == null) {
			txDistancia = new JTextField();
			txDistancia.setEditable(false);
			txDistancia.setColumns(10);
			txDistancia.setBounds(275, 267-40, 102, 20);
		}else {
			txDistancia.setText(getList().getSelectedValue().distancia + " metros");
		}
		return txDistancia;
	}
	
	
	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Competiciones abiertas");
			lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
			lbTitulo.setBounds(131, 0, 445, 42);
		}
		return lbTitulo;
	}
	private JLabel getLbFechaCarrera() {
		if (lbFechaCarrera == null) {
			lbFechaCarrera = new JLabel("Fecha carrera:");
			lbFechaCarrera.setHorizontalAlignment(SwingConstants.LEFT);
			lbFechaCarrera.setBounds(24, 111-40, 201, 14);
		}
		return lbFechaCarrera;
	}
	private JTextField getTxFechaCarrera() {
		if (txFechaCarrera == null) {
			txFechaCarrera = new JTextField();
			txFechaCarrera.setEditable(false);
			txFechaCarrera.setBounds(24, 139-40, 201, 20);
			txFechaCarrera.setColumns(10);
		}else {
			txFechaCarrera.setText(getList().getSelectedValue().fecha + "");
		}
		return txFechaCarrera;
	}
	private JLabel getLbTipo() {
		if (lbTipo == null) {
			lbTipo = new JLabel("Tipo:");
			lbTipo.setBounds(275, 111-40, 201, 14);
		}
		return lbTipo;
	}
	private JTextField getTxTipo() {
		if (txTipo == null) {
			txTipo = new JTextField();
			txTipo.setEditable(false);
			txTipo.setBounds(275, 139-40, 102, 20);
			txTipo.setColumns(10);
		}else {
			txTipo.setText(getList().getSelectedValue().tipo + "");
		}
		return txTipo;
	}

	private JTextField getTxCuota() {
		if (txCuota == null) {
			txCuota = new JTextField();
			txCuota.setEditable(false);
			txCuota.setBounds(275, 202-40, 102, 20);
			txCuota.setColumns(10);
		}else {
			txCuota.setText(getList().getSelectedValue().cuota + " €");
		}
		return txCuota;
	}
	private JButton getBtInscribirse() {
		if (btInscribirse == null) {
			btInscribirse = new JButton("Inscribirse");
			btInscribirse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getTxCorreo().getText().length() != 0 && getTxPlazas().getText() != "0") {
						InscripcionDTO inscripcion = VerRegistrados.registrados(getTxCorreo().getText(), getList().getSelectedValue().id_competicion);
						if (inscripcion != null) {
							inscripcion.id_competicion = getList().getSelectedValue().id_competicion;
							if (VerInscripciones.inscrito(inscripcion)) {
								Inscribirse.execute(inscripcion, getList().getSelectedValue().plazas);
								JOptionPane.showConfirmDialog(null, new	JLabel("Felicidades "+ inscripcion.nombre_atleta +
										", te has inscrito hoy, "+ inscripcion.fecha_Inscripcion +", a " + getList().getSelectedValue().nombre + 
										" y participas en la cateroría de " + inscripcion.categoria +
										". Recuerde que ha de pagar los " + getList().getSelectedValue().cuota +
										"€ de la cuota.",JLabel.CENTER), 
										"Confirmación de inscripción",  JOptionPane.CLOSED_OPTION);
								dispose();	
							}else {
								JOptionPane.showMessageDialog(null, new	JLabel("Lo sentimos "+ inscripcion.nombre_atleta +
										", ya estabas inscrito en " + getList().getSelectedValue().nombre + 
										" en la cateroría de " + inscripcion.categoria, JLabel.CENTER), 
										"Error - Ya estaba inscrito",  JOptionPane.ERROR_MESSAGE);
							}
						}else{
							registrarAtleta();
						}
					} else {
						JOptionPane.showMessageDialog(null, new JLabel("Por favor, relleña el campo del correo para inscribirte", JLabel.CENTER), 
								"Alerta - Falta correo", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btInscribirse.setBounds(275, 314, 137, 23);
		}
		return btInscribirse;
	}
	
	private void actualiza() {
		getTxFinInscripcion();
		getTxCuota();
		getTxDistancia();
		getTxFechaCarrera();
		getTxInicioInscripcion();
		getTxPlazas();
		getTxTipo();
	}
	
	private JLabel getLbInicioInscripcion() {
		if (lbInicioInscripcion == null) {
			lbInicioInscripcion = new JLabel("Inicio inscripci\u00F3n:");
			lbInicioInscripcion.setHorizontalAlignment(SwingConstants.LEFT);
			lbInicioInscripcion.setBounds(24, 177-40, 201, 14);
		}
		return lbInicioInscripcion;
	}
	private JLabel getLbFinInscripcion() {
		if (lbFinInscripcion == null) {
			lbFinInscripcion = new JLabel("Fin Inscripci\u00F3n:");
			lbFinInscripcion.setHorizontalAlignment(SwingConstants.LEFT);
			lbFinInscripcion.setBounds(24, 242-40, 201, 14);
		}
		return lbFinInscripcion;
	}
	
	
	private JTextField getTxFinInscripcion() {
		if (txFinInscripcion == null) {
			txFinInscripcion = new JTextField();
			txFinInscripcion.setEditable(false);
			txFinInscripcion.setColumns(10);
			txFinInscripcion.setBounds(24, 267-40, 201, 20);
		}else {
			txFinInscripcion.setText(getList().getSelectedValue().fecha_fin_inscripcion + "");
		}
		return txFinInscripcion;
	}
	
	private JTextField getTxInicioInscripcion() {
		if (txInicioInscripcion == null) {
			txInicioInscripcion = new JTextField();
			txInicioInscripcion.setEditable(false);
			txInicioInscripcion.setColumns(10);
			txInicioInscripcion.setBounds(24, 202-40, 201, 20);
		}else {
			txInicioInscripcion.setText(getList().getSelectedValue().fecha_inicio_inscripcion + "");
		}
		return txInicioInscripcion;
	}
	
	private JLabel getLbCuota() {
		if (lbCuota == null) {
			lbCuota = new JLabel("Cuota:");
			lbCuota.setBounds(275, 177-40, 201, 14);
		}
		return lbCuota;
	}
	
	private JLabel getLbPlazas() {
		if (lbPlazas == null) {
			lbPlazas = new JLabel("Plazas:");
			lbPlazas.setHorizontalAlignment(SwingConstants.LEFT);
			lbPlazas.setBounds(227, 301-40, 59, 14);
		}
		return lbPlazas;
	}

	private JTextField getTxCorreo() {
		if (txCorreo == null) {
			txCorreo = new JTextField();
			txCorreo.setColumns(10);
			txCorreo.setBounds(24, 320-40, 181, 20);
		}
		return txCorreo;
	}
	private JLabel getLblCorreo() {
		if (lblCorreo == null) {
			lblCorreo = new JLabel("Correo");
			lblCorreo.setHorizontalAlignment(SwingConstants.LEFT);
			lblCorreo.setBounds(24, 301-40, 135, 14);
		}
		return lblCorreo;
	}
	private JTextField getTxPlazas() {
		if (txPlazas == null) {
			txPlazas = new JTextField();
			txPlazas.setEditable(false);
			txPlazas.setColumns(10);
			txPlazas.setBounds(227, 320-40, 59, 20);
		}else {
			txPlazas.setText(getList().getSelectedValue().plazas + "");
		}
		return txPlazas;
	}
	
	private void registrarAtleta() {
		int result = JOptionPane.showConfirmDialog(null, new	JLabel("Lo sentimos, el correo que ha proporcionado ("+
				getTxCorreo().getText() + ") no se encuentra en la base de datos.\n¿Le gustaría"
						+ " registrarse en la aplicacion para poder inscribirse a está competición?", JLabel.CENTER), 
				"Atleta no encontrado - ¿Quiere registrarse?",  JOptionPane.YES_NO_OPTION);
		if (result == 0) {
			VentanaRegistroAplicacion v = new VentanaRegistroAplicacion(getTxCorreo().getText());
			v.setVisible(true);
		}
		
	}
	private JList<CompeticionDTO> getList() {
		if (list == null) {
			list = new JList<CompeticionDTO>(getListModel());
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {

					actualiza();
				}
			});
			list.setBounds(446, 47, 258, 287);
			}
		return list;
	}

	private DefaultListModel<CompeticionDTO> getListModel() {
		modeloList = new DefaultListModel<CompeticionDTO>();
		modeloList.addAll(compes);
		return modeloList;
	}
}
