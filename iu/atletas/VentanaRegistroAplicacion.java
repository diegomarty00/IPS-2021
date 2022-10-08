package iu.atletas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import bussiness.atleta.AtletaDto;
import bussiness.atleta.commands.RegistrarAtleta;
import iu.MenuPrincipal;
import iu.atletas.utiles.meses;

public class VentanaRegistroAplicacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbTitulo;
	private JRadioButton rdbtnMujer;
	private JRadioButton rdbtnHombre;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblSexo;
	private JLabel lblCorreo;
	private JLabel lblDni;
	private JLabel lblFechaNac;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtDni;
	private JTextField txtCorreo;
	private JTextField txtApellidos;
	private JTextField txtNombre;

	private JComboBox<Integer> cbDias;
	private JComboBox<meses> cbMeses;
	private JComboBox<Integer> cbanios;
	
	private JButton btnRegistrarse;
	private JButton btnCancelar;
	
	private int dia;
	private int mes;
	private int anio;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistroAplicacion frame = new VentanaRegistroAplicacion();
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
	public VentanaRegistroAplicacion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/util/Run.png")));	
		setResizable(false);
		setTitle("Registrar atleta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 460, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbTitulo());
		contentPane.add(getRdbtnMujer());
		contentPane.add(getRdbtnHombre());
		contentPane.add(getLblNombre());
		contentPane.add(getLblApellidos());
		contentPane.add(getLblSexo());
		contentPane.add(getBtnRegistrarse());

		contentPane.add(getCbDias());
		contentPane.add(getCbMeses());
		contentPane.add(getCbAnios());
		
		contentPane.add(getLblDni());
		contentPane.add(getLblFechaNac());
		contentPane.add(getLblCorreo());
		contentPane.add(getTxtDni());
		contentPane.add(getTxtCorreo());
		contentPane.add(getTxtNombre());
		contentPane.add(getTxtApellidos());
		contentPane.add(getBtnCancelar());
	}
	
	public VentanaRegistroAplicacion(String correo) {
		this();
		getTxtCorreo().setText(correo);
	}
	
	private JButton getBtnRegistrarse() {
		if (btnRegistrarse == null) {
			btnRegistrarse = new JButton("Registrarse");
			btnRegistrarse.setBackground(Color.GREEN);
			btnRegistrarse.setBounds(208, 316, 103, 23);
			btnRegistrarse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comprobarDatos();
				}
			});
		}
		return btnRegistrarse;
	}
	
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBackground(Color.RED);
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					preguntarCerrar();
				}
			});
			btnCancelar.setBounds(329, 316, 103, 23);
		}
		return btnCancelar;
	}
	
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(107, 87, 144, 20);
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}
	
	private JTextField getTxtApellidos() {
		if (txtApellidos == null) {
			txtApellidos = new JTextField();
			txtApellidos.setBounds(111, 122, 158, 20);
			txtApellidos.setColumns(10);
		}
		return txtApellidos;
	}
	
	private JTextField getTxtCorreo() {
		if (txtCorreo == null) {
			txtCorreo = new JTextField();
			txtCorreo.setBounds(86, 262, 193, 20);
			txtCorreo.setColumns(10);
		}
		return txtCorreo;
	}
	
	private JTextField getTxtDni() {
		if (txtDni == null) {
			txtDni = new JTextField();
			txtDni.setBounds(76, 227, 96, 20);
			txtDni.setColumns(10);
		}
		return txtDni;
	}
	
	private JLabel getLblCorreo() {
		if (lblCorreo == null) {
			lblCorreo = new JLabel("Correo:");
			lblCorreo.setBounds(37, 265, 46, 14);
		}
		return lblCorreo;
	}
	
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI:");
			lblDni.setBounds(37, 230, 31, 14);
		}
		return lblDni;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JComboBox<Integer> getCbDias() {
		if (cbDias == null) {
			cbDias = new JComboBox<Integer>();
			cbDias.setModel(new DefaultComboBoxModel(generardias31()));
			cbDias.setBounds(164, 191, 50, 22);
			cbDias.setSelectedIndex(0);
			dia = 1;
			cbDias.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dia = cbDias.getSelectedIndex() + 1;
				}
			});
		}
		return cbDias;
	}

	private JComboBox<meses>  getCbMeses() {
		if (cbMeses == null) {
			cbMeses = new JComboBox<meses>();
			cbMeses.setModel(new DefaultComboBoxModel<meses>(meses.values()));
			cbMeses.setBounds(224, 191, 100, 22);
			cbMeses.setSelectedIndex(0);
			mes = 1;
			cbMeses.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mes = cbMeses.getSelectedIndex() + 1;
					checkMes();
				}
			});
		}
		return cbMeses;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JComboBox<Integer>  getCbAnios() {
		if (cbanios == null) {
			cbanios = new JComboBox<Integer>();
			cbanios.setModel(new DefaultComboBoxModel(generarAnios()));
			cbanios.setBounds(334, 191, 80, 22);
			cbanios.setSelectedIndex(0);
			anio = 2021;
			cbanios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					anio = 2021- cbanios.getSelectedIndex();
					checkMes();
				}
			});
		}
		return cbanios;
	}

	private JRadioButton getRdbtnHombre() {
		if (rdbtnHombre == null) {
			rdbtnHombre = new JRadioButton("Hombre");
			rdbtnHombre.setSelected(true);
			rdbtnHombre.setBounds(76, 155, 72, 23);
			buttonGroup.add(rdbtnHombre);
			rdbtnHombre.setActionCommand("Hombre");
		}
		return rdbtnHombre;
	}
	
	private JRadioButton getRdbtnMujer() {
		if (rdbtnMujer == null) {
			rdbtnMujer = new JRadioButton("Mujer");
			rdbtnMujer.setBounds(150, 155, 59, 23);
			buttonGroup.add(rdbtnMujer);
			rdbtnMujer.setActionCommand("Mujer");
	}
		return rdbtnMujer;
	}

	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Registro de Atleta");
			lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
			lbTitulo.setBounds(111, 21, 199, 27);
		}
		return lbTitulo;
	}
	
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(37, 90, 64, 14);
		}
		return lblNombre;
	}
	
	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setBounds(37, 125, 64, 14);
		}
		return lblApellidos;
	}
	
	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Sexo:");
			lblSexo.setBounds(37, 160, 46, 14);
		}
		return lblSexo;
	}
	private JLabel getLblFechaNac() {
		if (lblFechaNac == null) {
			lblFechaNac = new JLabel("Fecha de nacimiento: ");
			lblFechaNac.setBounds(37, 195, 126, 14);
		}
		return lblFechaNac;
	}
	
	private String[] generardias31() {
		String[] result = new String[31];
		for (int i = 0; i < 31; i++)
			result[i] = (i + 1) + "";
		return result;
	}
	
	private String[] generardias30() {
		String[] result = new String[30];
		for (int i = 0; i < 30; i++)
			result[i] = (i + 1) + "";
		return result;
	}
	
	private String[] generardias29() {
		String[] result = new String[29];
		for (int i = 0; i < 29; i++)
			result[i] = (i + 1) + "";
		return result;
	}
	
	private String[] generardias28() {
		String[] result = new String[28];
		for (int i = 0; i < 28; i++)
			result[i] = (i + 1) + "";
		return result;
	}
	
	private String[] generarAnios() {
		String[] result = new String[122];
		for (int i = 0; i < 122; i++)
			result[i] = (2021-i) + "";
		return result;
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void checkMes() {
		if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
			cbDias.setModel(new DefaultComboBoxModel(generardias31()));
		}else if (mes == 2 && checkBisiesto()) {
			cbDias.setModel(new DefaultComboBoxModel(generardias29()));
		}else if (mes == 2 && !checkBisiesto()) {
			cbDias.setModel(new DefaultComboBoxModel(generardias28()));
		}else {
			cbDias.setModel(new DefaultComboBoxModel(generardias30()));
		}
	}

	private boolean checkBisiesto() {
		if(anio % 4 == 0)
			return true;
		return false;
	}
	
	private void preguntarCerrar() {
		int result = JOptionPane.showConfirmDialog(null, new JLabel("¿Estás seguro de que quieres cancelar"
				+ " el registro?", JLabel.CENTER), 
				"Cancelar registro",  JOptionPane.YES_NO_OPTION);
		if (result == 0) {
			dispose();
		}	
	}
	
	private void comprobarDatos() {
		if (getTxtNombre().getText().length() == 0 || getTxtApellidos().getText().length() == 0 
				|| getTxtDni().getText().length() == 0 || getTxtCorreo().getText().length() == 0)
			JOptionPane.showMessageDialog(null, new JLabel("Por favor, rellene todos los campos de texto para poder"
					+ " realizar el registro correctamente", JLabel.CENTER), 
					"Error - Campos de texto vacios",  JOptionPane.ERROR_MESSAGE);
		else if (!checkEdad())
			JOptionPane.showMessageDialog(null, new JLabel("Usted no puede registrarse al ser menor"
					+ " de edad", JLabel.CENTER), 
					"Error - Solo atletas mayores de edad",  JOptionPane.ERROR_MESSAGE);
		else {
			try {
				AtletaDto dto = new AtletaDto();
				dto.DNI = getTxtDni().getText();
				dto.nombre = getTxtNombre().getText();
				dto.genero = buttonGroup.getSelection().getActionCommand();
				dto.correo = getTxtCorreo().getText();
				dto.apellido = getTxtApellidos().getText();
				dto.fechaNacimiento = LocalDate.of(anio,mes,dia);
				RegistrarAtleta.execute(dto);
				dispose();
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, new JLabel("Este usuario ya se encuentra registrado"
						, JLabel.CENTER), 
						"Error - Usuario ya registrado",  JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	private boolean checkEdad() {
		if (LocalDate.now().getYear() - anio > 18)
			return true;
		if (LocalDate.now().getYear() - anio == 18) {
			if (LocalDate.now().getMonthValue() > mes )
				return true;
			if (LocalDate.now().getMonthValue() == mes)
				if (LocalDate.now().getDayOfMonth() >= dia)
					return true;
		}
		return false;
	}
}
