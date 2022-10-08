package iu.pagos;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import bussiness.BusinessFactory;
import bussiness.atleta.CompeticionDTO;
import bussiness.inscripciones.JustificantePagoDTO;
import iu.MenuPrincipal;
import iu.organizador.actions.VerCompeticionesAction;
import util.BusinessException;

public class VentanaTarjeta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbTitulo;
	private JLabel lbNumeroTarjeta;
	private JTextField txNumeroTarjeta;
	private JLabel lbFechaCaducidad;
	private JFormattedTextField txFecha;
	private JLabel lbCVC;
	private JTextField txCVC;
	private JButton btConfirmar;
	private JButton btCancelar;
	private JLabel lbDNI;
	private JTextField txDNI;
	@SuppressWarnings("rawtypes")
	private JComboBox cbCompeticiones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaTarjeta frame = new VentanaTarjeta();
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
	public VentanaTarjeta() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/util/Run.png")));	
		setResizable(false);
		setTitle("Pagar con tarjeta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 513, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbTitulo());
		contentPane.add(getLbNumeroTarjeta());
		contentPane.add(getTxNumeroTarjeta());
		contentPane.add(getLbFechaCaducidad());
		contentPane.add(getTxFecha());
		contentPane.add(getLbCVC());
		contentPane.add(getTxCVC());
		contentPane.add(getBtConfirmar());
		contentPane.add(getBtCancelar());
		contentPane.add(getLbDNI());
		contentPane.add(getTxDNI());
		
		JLabel lbNombreCompeticion = new JLabel("Nombre competicion:");
		lbNombreCompeticion.setBounds(10, 132, 131, 14);
		contentPane.add(lbNombreCompeticion);
		contentPane.add(getCbCompeticiones());
		
		
		
	}

	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Pagar con tarjeta");
			lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 21));
			lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lbTitulo.setBounds(67, 11, 367, 38);
		}
		return lbTitulo;
	}

	private JLabel getLbNumeroTarjeta() {
		if (lbNumeroTarjeta == null) {
			lbNumeroTarjeta = new JLabel("Número de tarjeta:");
			lbNumeroTarjeta.setBounds(10, 175, 121, 14);
		}
		return lbNumeroTarjeta;
	}

	private JTextField getTxNumeroTarjeta() {
		if (txNumeroTarjeta == null) {
			txNumeroTarjeta = new JTextField();
			txNumeroTarjeta.setBounds(135, 172, 121, 20);
			txNumeroTarjeta.setColumns(10);
		}

		KeyHandlerNumTarjeta kh = new KeyHandlerNumTarjeta();
		txNumeroTarjeta.addKeyListener(kh);

		return txNumeroTarjeta;
	}

	private JLabel getLbFechaCaducidad() {
		if (lbFechaCaducidad == null) {
			lbFechaCaducidad = new JLabel("Fecha de caducidad:");
			lbFechaCaducidad.setBounds(10, 216, 121, 14);
		}
		return lbFechaCaducidad;
	}

	private JFormattedTextField getTxFecha() {
		DateFormat df = new SimpleDateFormat("MM/yyyy");
		if (txFecha == null) {
			txFecha = new JFormattedTextField(df);
			txFecha.setBounds(135, 213, 121, 20);
			txFecha.setColumns(10);
		}

		txFecha.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| (c == KeyEvent.VK_SLASH))) {
					e.consume();
				}
			}
		});
		return txFecha;
	}

	private JLabel getLbCVC() {
		if (lbCVC == null) {
			lbCVC = new JLabel("CVC:");
			lbCVC.setBounds(10, 253, 31, 14);
		}
		return lbCVC;
	}

	private JTextField getTxCVC() {
		if (txCVC == null) {
			txCVC = new JTextField();
			txCVC.setBounds(135, 250, 121, 20);
			txCVC.setColumns(10);
		}
		KeyHandlerCVC kh = new KeyHandlerCVC();
		txCVC.addKeyListener(kh);
		return txCVC;
	}

	private JButton getBtConfirmar() {
		if (btConfirmar == null) {
			btConfirmar = new JButton("Aceptar");
			btConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (getTxNumeroTarjeta().getText().length() == 0 || getTxFecha().getText().length() == 0
							|| getTxCVC().getText().length() == 0) {
						JOptionPane.showMessageDialog(new JFrame(), "Por favor complete los campos",
								"Complete los campos", JOptionPane.INFORMATION_MESSAGE);

					} else if (!comprobarFecha(getTxFecha().getText())) {
						JOptionPane.showMessageDialog(new JFrame(), "La tarjeta está caducada", "Tarjeta caducada",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						try {
							JustificantePagoDTO dto = new JustificantePagoDTO();
							VentanaJustificante v = new VentanaJustificante();
							BusinessFactory.forAtletaService().pagarConTarjeta(getTxDNI().getText(), (String) cbCompeticiones.getSelectedItem());
							dto = BusinessFactory.forInscripcionService().generarJustificante(getTxDNI().getText(), (String) cbCompeticiones.getSelectedItem());
							v.mostrar(dto.dni_atleta, dto.nombre_Competicion, dto.fecha_Inscripcion, dto.cuota);
						} catch (BusinessException be) {
							JOptionPane.showMessageDialog(new JFrame(),
									be.getMessage(),
									"ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
					
				}
			});
			btConfirmar.setBounds(382, 317, 89, 23);
		}
		return btConfirmar;
	}

	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btCancelar.setBounds(283, 317, 89, 23);
		}
		return btCancelar;
	}

	class KeyHandlerNumTarjeta extends KeyAdapter {

		@Override
		public void keyTyped(KeyEvent e) {
			compruebaTecla(e);
		}

	}

	class KeyHandlerCVC extends KeyAdapter {

		@Override
		public void keyTyped(KeyEvent e) {

			if (!Character.isDigit(e.getKeyChar()) || getTxCVC().getText().length() >= 3) {
				e.consume();
			}

		}

	}

	void compruebaTecla(KeyEvent e) {
		char teclaPulsada = e.getKeyChar();

		if (!Character.isDigit(teclaPulsada)) {
			e.consume();
		}

	}

	boolean comprobarFecha(String fecha) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/yyyy");
		if (YearMonth.now(ZoneId.systemDefault()).isAfter(YearMonth.parse(fecha, df))) {
			return false;
		} else {
			return true;
		}

	}

	private JLabel getLbDNI() {
		if (lbDNI == null) {
			lbDNI = new JLabel("DNI:");
			lbDNI.setBounds(10, 86, 31, 14);
		}
		return lbDNI;
	}

	private JTextField getTxDNI() {
		if (txDNI == null) {
			txDNI = new JTextField();
			txDNI.setBounds(135, 83, 121, 20);
			txDNI.setColumns(10);
		}
		return txDNI;
	}
	
	public void windowClosing( WindowEvent evt ) {
		dispose();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JComboBox getCbCompeticiones() {
		if (cbCompeticiones == null) {
			cbCompeticiones = new JComboBox<String>();
			cbCompeticiones.setBounds(151, 129, 221, 21);
			
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
