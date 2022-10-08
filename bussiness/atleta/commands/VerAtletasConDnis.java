package bussiness.atleta.commands;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bussiness.atleta.AtletaDto;
import util.jdbc.Jdbc;

public class VerAtletasConDnis {

	private List<String> dnis;

	public VerAtletasConDnis(List<String> dnis) {
		this.dnis = dnis;
	}

	private String consulta = "Select * from Inscripcion where dni_atleta = ?";

	public List<AtletaDto> execute()  {

		List<AtletaDto> result = new ArrayList<AtletaDto>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			for (String dni : dnis) {
				pst = c.prepareStatement(consulta);
				pst.setString(1, dni);
				rs = pst.executeQuery();
				
				AtletaDto dto = new AtletaDto();

				dto.DNI = rs.getString("DNI_atleta");
				dto.nombre = rs.getString("nombre");
				dto.apellido = rs.getString("apellidos");
				dto.genero = rs.getString("sexo");
	
	

				result.add(dto);
				
				
			}

			Jdbc.close(rs, pst, c);

		} catch (Exception e) {
			
		}
		return result;

	}

}
