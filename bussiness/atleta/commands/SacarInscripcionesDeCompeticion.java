package bussiness.atleta.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bussiness.atleta.AtletaDto;
import util.jdbc.Jdbc;

public class SacarInscripcionesDeCompeticion {
	private int competicion;

	public SacarInscripcionesDeCompeticion(int competicion2) {
		this.competicion = competicion2;
	}

	private String consulta = "Select * from Inscripcion i join atleta a on i.dni_atleta=a.dni_atleta where i.ID_COMPETICION = ? ";

	public List<AtletaDto> execute() {

		List<AtletaDto> result = new ArrayList<AtletaDto>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		AtletaDto dto;
		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(consulta);
			pst.setInt(1, competicion);
			rs = pst.executeQuery();

			while (rs.next()) {
				
				dto = new AtletaDto();
				
				dto.DNI= rs.getString("DNI_atleta");
				dto.estadoInscripcion=rs.getString("estado");
				dto.fechaDeInscripcion=rs.getDate( "FECHA_INSCRIPCION").toLocalDate();
				dto.categoria=rs.getString("Categoria");
				dto.nombre = rs.getString("nombre");
				dto.apellido = rs.getString("apellidos");
				dto.genero = rs.getString("sexo");
				result.add(dto);

			}
			Jdbc.close(rs, pst, c);

		} catch (Exception e) {

			System.out.print("a");
		}
		return result;

	}
}
