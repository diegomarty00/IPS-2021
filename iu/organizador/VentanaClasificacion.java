package iu.organizador;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import bussiness.clasificacion.ClasificacionDTO;
import bussiness.clasificacion.commands.VerClasificaciones;
import iu.MenuPrincipal;

public class VentanaClasificacion extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JComboBox<String> cbResultados;
	private List<ClasificacionDTO> clasis;

	private int elementoCB;
	private final ButtonGroup buttonGroupSexo = new ButtonGroup();
	private final ButtonGroup buttonGroupClasis = new ButtonGroup();
	private JLabel lblResultados;
	private JLabel lblClasificacion;
	private JRadioButton rdbtnGeneral;
	private JRadioButton rdbtnSexo;
	private JLabel lblSexo;
	private JRadioButton rdbtnMasculina;
	private JRadioButton rdbtnFemenina;
	private JButton btnResultados;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaClasificacion frame = new VentanaClasificacion();
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
	public VentanaClasificacion() {
		getLblSexo().setVisible(false);
		getRdbtnMasculina().setVisible(false);
		getRdbtnFemenina().setVisible(false);
		clasis = VerClasificaciones.execute();
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/util/Run.png")));	
		setResizable(false);
		setTitle("Ver tabla de clasificacion");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 460, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getCbResultados());	
		contentPane.add(getLblResultados());
		contentPane.add(getLblClasificacion());
		contentPane.add(getRdbtnGeneral());
		contentPane.add(getRdbtnSexo());
		contentPane.add(getLblSexo());
		contentPane.add(getRdbtnMasculina());
		contentPane.add(getRdbtnFemenina());
		contentPane.add(getBtnResultados());
		contentPane.add(getBtnCancelar());
	}
	
	private JComboBox<String> getCbResultados() {
		if (cbResultados == null) {
			cbResultados = new JComboBox<String>();
			for(int i = 0; i < clasis.size(); i++)
				cbResultados.addItem(clasis.get(i).nombre_competicion + " - " 
						+ clasis.get(i).tipo + " - " + clasis.get(i).fecha_competicion);
			cbResultados.setBounds(50, 66, 360, 22);
			((JLabel)cbResultados.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
			cbResultados.setSelectedIndex(0);
			elementoCB = 0;
			cbResultados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					elementoCB = cbResultados.getSelectedIndex();
				}
			});
		}
		return cbResultados;
	}

	private JLabel getLblResultados() {
		if (lblResultados == null) {
			lblResultados = new JLabel("Resultados de las competicones");
			lblResultados.setFont(new Font("Tahoma", Font.BOLD, 22));
			lblResultados.setHorizontalAlignment(SwingConstants.CENTER);
			lblResultados.setBounds(10, 11, 434, 54);
		}
		return lblResultados;
	}
	private JLabel getLblClasificacion() {
		if (lblClasificacion == null) {
			lblClasificacion = new JLabel("Tipo de clasificación");
			lblClasificacion.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblClasificacion.setHorizontalAlignment(SwingConstants.CENTER);
			lblClasificacion.setBounds(6, 106, 438, 35);
		}
		return lblClasificacion;
	}
	private JRadioButton getRdbtnGeneral() {
		if (rdbtnGeneral == null) {
			rdbtnGeneral = new JRadioButton("Clasificación general");
			rdbtnGeneral.setSelected(true);
			rdbtnGeneral.setHorizontalAlignment(SwingConstants.CENTER);
			buttonGroupClasis.add(rdbtnGeneral);
			rdbtnGeneral.setBounds(0, 144, 454, 23);
			rdbtnGeneral.setActionCommand("General");
			rdbtnGeneral.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getLblSexo().setVisible(false);
					getRdbtnMasculina().setVisible(false);
					getRdbtnFemenina().setVisible(false);
				}
			});
		}
		return rdbtnGeneral;
	}
	private JRadioButton getRdbtnSexo() {
		if (rdbtnSexo == null) {
			rdbtnSexo = new JRadioButton("Clasificación por sexo");
			rdbtnSexo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getLblSexo().setVisible(true);
					getRdbtnMasculina().setVisible(true);
					getRdbtnFemenina().setVisible(true);
				}
			});
			rdbtnSexo.setHorizontalAlignment(SwingConstants.CENTER);
			rdbtnSexo.setActionCommand("Sexo");
			buttonGroupClasis.add(rdbtnSexo);
			rdbtnSexo.setBounds(0, 170, 454, 23);
		}
		return rdbtnSexo;
	}
	private JLabel getLblSexo() {
		if (lblSexo == null) {
			lblSexo = new JLabel("Categoría");
			lblSexo.setHorizontalAlignment(SwingConstants.CENTER);
			lblSexo.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblSexo.setBounds(0, 207, 454, 35);
		}
		return lblSexo;
	}
	private JRadioButton getRdbtnMasculina() {
		if (rdbtnMasculina == null) {
			rdbtnMasculina = new JRadioButton("Masculina");
			rdbtnMasculina.setSelected(true);
			rdbtnMasculina.setHorizontalAlignment(SwingConstants.CENTER);
			rdbtnMasculina.setBounds(0, 240, 454, 23);
			buttonGroupSexo.add(rdbtnMasculina);
			rdbtnMasculina.setActionCommand("Masculina");
		}
		return rdbtnMasculina;
	}
	private JRadioButton getRdbtnFemenina() {
		if (rdbtnFemenina == null) {
			rdbtnFemenina = new JRadioButton("Femenina");
			rdbtnFemenina.setHorizontalAlignment(SwingConstants.CENTER);
			buttonGroupSexo.add(rdbtnFemenina);
			rdbtnFemenina.setBounds(0, 266, 454, 23);
			rdbtnFemenina.setActionCommand("Femenina");
		}
		return rdbtnFemenina;
	}
	private JButton getBtnResultados() {
		if (btnResultados == null) {
			btnResultados = new JButton("Resultados");
			btnResultados.setBackground(Color.GREEN);
			btnResultados.setBounds(207, 310, 103, 23);
			btnResultados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (buttonGroupClasis.getSelection().getActionCommand() == "General")
						try {
							VentanaTablaClasificacion frame = new VentanaTablaClasificacion(clasis.get(elementoCB), "General");
							frame.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					else {
						if (buttonGroupSexo.getSelection().getActionCommand() == "Masculina")
							try {
								clasis.get(elementoCB).sexo = "Hombre";
								VentanaTablaClasificacion frame = new VentanaTablaClasificacion(clasis.get(elementoCB), "Masculina");
								frame.setVisible(true);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						else
							try {
								clasis.get(elementoCB).sexo = "Mujer";
								VentanaTablaClasificacion frame = new VentanaTablaClasificacion(clasis.get(elementoCB), "Femenina");
								frame.setVisible(true);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
					}
				}
			});
		}
		return btnResultados;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBackground(Color.RED);
			btnCancelar.setBounds(328, 310, 103, 23);
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelar;
	}
}
