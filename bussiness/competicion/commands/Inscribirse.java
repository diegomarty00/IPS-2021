package bussiness.competicion.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bussiness.atleta.InscripcionDTO;
import util.jdbc.Jdbc;

public class Inscribirse {
	
	private static String actualizar = "UPDATE Competicion set numero_plazas = ? where id_competicion = ?";
	private static String inscribir = "insert into inscripcion values (?, ?, null, 'NO PAGADO', ?, ?, 0)";
	
	
	public static InscripcionDTO execute(InscripcionDTO inscripcion, int plazas)  {

		Connection c = null;
		PreparedStatement pst1, pst2 = null;
		InscripcionDTO dto = new InscripcionDTO();
		
		try {
			c = Jdbc.getConnection();
			pst1 = c.prepareStatement(inscribir);
			pst1.setString(1, inscripcion.dni_Atleta);
			pst1.setInt(2, inscripcion.id_competicion);
			pst1.setDate(3, inscripcion.fecha_Inscripcion);
			pst1.setString(4, inscripcion.categoria);
			pst1.executeUpdate();
			
			Jdbc.close(pst1);
			
			pst2 = c.prepareStatement(actualizar);
			pst2.setInt(1, plazas-1);
			pst2.setInt(2,inscripcion.id_competicion);
			pst2.executeUpdate();
			
			Jdbc.close(pst2);
			Jdbc.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;

	}

}
