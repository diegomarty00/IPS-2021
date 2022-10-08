package iu.organizador;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bussiness.clasificacion.ClasificacionDTO;
import bussiness.clasificacion.commands.VerClasificacionGeneral;
import iu.MenuPrincipal;

public class VentanaTablaClasificacion extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private List<ClasificacionDTO> clasis;

	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel inscripcionModel;

	private String titulo;
	private JLabel lbTitulo;
	
	private String tiempoPrimero;
	private JPanel panel;


	/**
	 * Create the frame.
	 * @param tipo 
	 */
	public VentanaTablaClasificacion(ClasificacionDTO dto, String tipo) {
		tiempoPrimero = "";
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/util/Run.png")));	
		clasis = VerClasificacionGeneral.execute(dto.id_competicion, dto.sexo);

		burbuja();
		getTable();
		int counter = 0;
		
		for (ClasificacionDTO clasi : clasis) {
			 counter++;
			 String tramos[] = clasi.tiempos_tramos.split("-");
			 int tam = 7 + tramos.length;
			 String row[] = new String[tam];
			 row[0] = ""+counter;
			 row[1] = clasi.nombre_atleta + " "+ clasi.apellidos_atleta;
			 row[2] = clasi.sexo;
			 row[3] = clasi.nombre_club;
			 row[4] = calcularRitmo(clasi);
			 row[row.length-2] = clasi.resultadoTiempo;
			 row[row.length-1] = calcularDiferencia(clasi);
			 
			 for (int i = 5; i < 5+tramos.length; i++) {
				 row[i] = tramos[i-5];
			 }
			 
			inscripcionModel.addRow(row);
		}
		titulo = "Clasificacion " + tipo;
		setTitle(dto.nombre_competicion + " - " + dto.tipo + " - " +dto.fecha_competicion);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 779, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPanel());
	}
	
	private String calcularDiferencia(ClasificacionDTO clasi) {
		if (tiempoPrimero == "") {
			tiempoPrimero = clasi.resultadoTiempo;
			return "---";
		}
		String rs="";
		String ms="";
		String[] tiempo = clasi.resultadoTiempo.split(":");
		double horas = Integer.parseInt(tiempo[0]) * 60;
		double min = (Integer.parseInt(tiempo[1]) + horas) * 60;
		double seg = Integer.parseInt(tiempo[2]) + min;
		
		tiempo = tiempoPrimero.split(":");
		horas = Integer.parseInt(tiempo[0]) * 60;
		min = (Integer.parseInt(tiempo[1]) + horas) * 60;
		seg = seg - (Integer.parseInt(tiempo[2]) + min);
		
		if (seg < 60) {
			if (seg<10)
				return "00:00:0"+(int)seg;
			return "00:00:"+(int)seg;
		}else {
			int m = (int) (seg/60);
			seg = seg%60;
			rs = ""+(int)seg;
			if (seg<10)
				rs = "0"+(int)seg;
			if (m < 60) {
				if(m<10)
					return "00:0"+m+":"+rs;
				return "00:"+m+":"+rs;
			}else {
				int h = (int) (m/60);
				m = m%60;
				ms = ""+(int)m;
				if (m<10)
					ms = "0"+(int)m;
				if (h<10)
					return "0"+h+":"+ms+":"+rs;
				return h+":"+ms+":"+rs;
			}
		}
	}

	private String calcularRitmo(ClasificacionDTO clasi) {
		String[] tiempo = clasi.resultadoTiempo.split(":");
		double horas =  Double.parseDouble(tiempo[0]) * 60;
		double seg = Double.parseDouble(tiempo[2]) / 60;
		double min =  Double.parseDouble(tiempo[1]) + seg + horas;
		
		double result = min/(clasi.distancia/1000);
		result = Math.round(result * 100);
		result = result/100;
		return result + " min/km";
	}

	public void burbuja () 
	{
		int n= clasis.size();
		for (int i=0; i<=n-2; i++) 
			for (int j=n-1; j>i;j--)
				if (clasis.get(j).resultadoTiempo.compareTo(clasis.get(j-1).resultadoTiempo) < 0)
					intercambiar(j,j-1);
	} 
	
	private void intercambiar (int i, int j)
	{
		String t;
		t= clasis.get(i).resultadoTiempo; 
		clasis.get(i).resultadoTiempo = clasis.get(j).resultadoTiempo; 
		clasis.get(j).resultadoTiempo = t;
		
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getLbTitulo(), BorderLayout.NORTH);
			panel.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panel;
	}
	
	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel(titulo);
			lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
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
		
		String tramos[] = clasis.get(0).tiempos_tramos.split("-");
		int tam = 7 + tramos.length;
		String names[] = new String[tam];
		names[0] = "Posición";
		names[1] = "Atleta";
		names[2] = "Sexo";
		names[3] = "Club";
		names[4] = "Ritmo carrera";
		names[names.length-2] = "Tiempo";
		names[names.length-1] = "Dif. resp. 1º";
		
		for (int i = 5; i < 5+tramos.length; i++) {
			names[i] = "Tramo "+(i-4);
		}
		
		if (table == null) {
			inscripcionModel = new DefaultTableModel(names,0);
			table = new JTable(inscripcionModel);
		}
		return table;
	}
}
