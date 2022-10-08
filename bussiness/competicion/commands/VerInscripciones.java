package bussiness.competicion.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bussiness.atleta.InscripcionDTO;
import bussiness.inscripciones.InscripcionDto;
import util.jdbc.Jdbc;

public class VerInscripciones {

	private static String consulta = "Select * from inscripcion where id_Competicion = ? and dni_atleta = ?";
	
	public static boolean inscrito(InscripcionDTO dto){

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int counter = 0;
		InscripcionDto idto = new InscripcionDto();
		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(consulta);
			pst.setInt(1, dto.id_competicion);
			pst.setString(2, dto.dni_Atleta);
			rs = pst.executeQuery();

			while (rs.next()) {
				idto.dni = rs.getString("dni_atleta");
				if (idto.dni != null)
					counter = -1;
			}
			
			Jdbc.close(rs, pst, c);

			if (counter == 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

}
