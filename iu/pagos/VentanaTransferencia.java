package iu.pagos;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import bussiness.BusinessFactory;
import bussiness.atleta.CompeticionDTO;
import bussiness.inscripciones.DatosPagoDTO;
import iu.MenuPrincipal;
import iu.organizador.actions.VerCompeticionesAction;
import util.BusinessException;

public class VentanaTransferencia extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbDNI;
	private JTextField txDni;
	private JLabel lbTitulo;
	private JLabel lbDescripcion;
	private JButton btAceptar;
	private JButton btCancelar;
	private JLabel lbNombreCompeticion;
	@SuppressWarnings("rawtypes")
	private JComboBox cbCompeticiones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaTransferencia frame = new VentanaTransferencia();
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
	public VentanaTransferencia() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/util/Run.png")));	
		setResizable(false);
		setTitle("Pagar por transferencia");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 536, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbDNI());
		contentPane.add(getTxDni());
		contentPane.add(getLbTitulo());
		contentPane.add(getLbDescripcion());
		contentPane.add(getBtAceptar());
		contentPane.add(getBtCancelar());
		contentPane.add(getLbNombreCompeticion());
		contentPane.add(getCbCompeticiones());
	}

	private JLabel getLbDNI() {
		if (lbDNI == null) {
			lbDNI = new JLabel("DNI:");
			lbDNI.setBounds(20, 153, 40, 14);
		}
		return lbDNI;
	}

	private JTextField getTxDni() {
		if (txDni == null) {
			txDni = new JTextField();
			txDni.setBounds(137, 150, 210, 20);
			txDni.setColumns(10);
		}
		return txDni;
	}

	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Pagar por transferencia");
			lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 21));
			lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lbTitulo.setBounds(86, 11, 307, 74);
		}
		return lbTitulo;
	}

	private JLabel getLbDescripcion() {
		if (lbDescripcion == null) {
			lbDescripcion = new JLabel("Para pagar por transferencia introduzca su dni y el nombre de la competicion");
			lbDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lbDescripcion.setBounds(20, 96, 453, 14);
		}
		return lbDescripcion;
	}

	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (getTxDni().getText().length() == 0) {
						JOptionPane.showMessageDialog(new JFrame(), "Por favor complete los campos",
								"Complete los campos", JOptionPane.INFORMATION_MESSAGE);
					} else {
						
						try {
							DatosPagoDTO dto = new DatosPagoDTO();
							VentanaDatos v = new VentanaDatos();
							BusinessFactory.forAtletaService().pagarConTransferencia(getTxDni().getText(), (String) cbCompeticiones.getSelectedItem());
							dto = BusinessFactory.forInscripcionService().generarDatosPago(getTxDni().getText(), (String) cbCompeticiones.getSelectedItem());
							v.mostrar(dto.cuota);
						} catch (BusinessException be) {
							JOptionPane.showMessageDialog(new JFrame(),
									be.getMessage(),
									"ERROR", JOptionPane.ERROR_MESSAGE);
						}

					}

				}
			});
			btAceptar.setBounds(400, 313, 89, 23);
		}
		return btAceptar;
	}

	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btCancelar.setBounds(297, 313, 89, 23);
		}
		return btCancelar;
	}

	public void mostrar() {
		this.setVisible(true);
	}
	private JLabel getLbNombreCompeticion() {
		if (lbNombreCompeticion == null) {
			lbNombreCompeticion = new JLabel("Nombre competicion:");
			lbNombreCompeticion.setBounds(10, 197, 129, 14);
		}
		return lbNombreCompeticion;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox getCbCompeticiones() {
		if (cbCompeticiones == null) {
			cbCompeticiones = new JComboBox();
			cbCompeticiones.setBounds(149, 194, 324, 21);
			

			DefaultComboBoxModel model = new DefaultComboBoxModel<>();
			List<CompeticionDTO> competiciones = new VerCompeticionesAction().execute();
			for (CompeticionDTO c : competiciones) {
				model.addElement(c.nombre);
			}
			cbCompeticiones.setModel(model);
		}
		return cbCompeticiones;
	}
}
