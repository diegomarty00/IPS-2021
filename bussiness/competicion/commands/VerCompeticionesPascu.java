package bussiness.competicion.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bussiness.atleta.CompeticionDTO;
import util.jdbc.Jdbc;

public class VerCompeticionesPascu {

	private static String consulta = "Select * from Competicion order by FECHA_COMPETICION";
	
	public static List<CompeticionDTO> execute(){

		List<CompeticionDTO> result = new ArrayList<CompeticionDTO>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(consulta);
			rs = pst.executeQuery();

			
			
			while (rs.next()) {
				CompeticionDTO dto = new CompeticionDTO();

				dto.id_competicion = rs.getInt("ID_COMPETICION");
				dto.nombre = rs.getString("NOMBRE_COMPETICION");
				dto.fecha = rs.getDate("FECHA_COMPETICION").toLocalDate();
				dto.tipo = rs.getString("TIPO");
				dto.distancia = rs.getInt("DISTANCIA");
				dto.cuota = rs.getDouble("CUOTA");
				dto.fecha_inicio_inscripcion = rs.getDate("FECHA_INICIO_INSCRIPCION").toLocalDate();
				dto.fecha_fin_inscripcion = rs.getDate("FECHA_FIN_INSCRIPCION").toLocalDate();
				dto.plazas = rs.getInt("NUMERO_PLAZAS");
				
				
				result.add(dto);
				if(dto.fecha_inicio_inscripcion != null && dto.fecha_fin_inscripcion !=null) {
					LocalDate finIns = dto.fecha_fin_inscripcion;
					LocalDate iniIns = dto.fecha;
					LocalDate actual = LocalDate.now();
				
					if (comprobarFecha(iniIns, actual, finIns)) {
						
					}
						// result.add(dto);
				}
			}
			Jdbc.close(rs, pst, c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
	public static boolean comprobarFecha(LocalDate iniFec, LocalDate actual, LocalDate finFec) {
		if (iniFec.getYear() <= actual.getYear() && finFec.getYear() >= actual.getYear())
			if (iniFec.getMonthValue() <= actual.getMonthValue() && finFec.getMonthValue() >= actual.getMonthValue())
				if (iniFec.getDayOfMonth() <= actual.getDayOfMonth() && finFec.getDayOfMonth() >= actual.getDayOfMonth())
					return true;
		return false;
		
	}

}
