package bussiness.clasificacion.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bussiness.clasificacion.ClasificacionDTO;
import util.jdbc.Jdbc;

public class VerClasificaciones {

	private static String consulta = "Select distinct id_competicion from Clasificacion";
	private static String buscar_competicion = "Select * from Competicion where id_competicion = ?";

	public static List<ClasificacionDTO> execute() {

		List<ClasificacionDTO> result = new ArrayList<ClasificacionDTO>();

		Connection c, c_competicion = null;
		PreparedStatement pst, pst_competicion = null;
		ResultSet rs, rs_competicion = null;

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(consulta);
			rs = pst.executeQuery();

			while (rs.next()) {
				ClasificacionDTO dto = new ClasificacionDTO();

				dto.id_competicion = rs.getInt("ID_COMPETICION");

				c_competicion = Jdbc.getConnection();
				pst_competicion = c_competicion.prepareStatement(buscar_competicion);
				pst_competicion.setInt(1, dto.id_competicion);
				rs_competicion = pst_competicion.executeQuery();
				
				while (rs_competicion.next()) {
					dto.nombre_competicion = rs_competicion.getString("NOMBRE_COMPETICION");
					dto.fecha_competicion = rs_competicion.getDate("FECHA_COMPETICION").toLocalDate();

					dto.tipo = rs_competicion.getString("TIPO");

					result.add(dto);
				}
				Jdbc.close(rs_competicion, pst_competicion, c_competicion);
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
				if (iniFec.getDayOfMonth() <= actual.getDayOfMonth()
						&& finFec.getDayOfMonth() >= actual.getDayOfMonth())
					return true;
		return false;

	}

}