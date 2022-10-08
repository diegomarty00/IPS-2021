package bussiness.competicion.commands;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bussiness.atleta.InscripcionDTO;
import bussiness.atleta.commands.CalculoCategoria;
import util.jdbc.Jdbc;

public class VerRegistrados {

private static String consulta = "Select * from atleta where correo = ? ";
	
	public static InscripcionDTO registrados(String correo, int id_competicion){

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		InscripcionDTO dto = new InscripcionDTO();

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(consulta);
			pst.setString(1, correo);
			rs = pst.executeQuery();

			while (rs.next()) {
				dto.dni_Atleta = rs.getString("dni_Atleta");
				dto.fecha_Inscripcion = new Date(System.currentTimeMillis());
				dto.categoria = CalculoCategoria.calculoCategoria(rs.getDate("FECHA_NAC"), id_competicion);
				dto.nombre_atleta = rs.getString("nombre");
				return dto;
			}
			Jdbc.close(rs, pst, c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
