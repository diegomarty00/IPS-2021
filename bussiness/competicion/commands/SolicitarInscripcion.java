package bussiness.competicion.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bussiness.atleta.CompeticionDTO;
import util.jdbc.Jdbc;

public class SolicitarInscripcion {

	private String id_competicion;

	public SolicitarInscripcion(String id_competicion) {
		this.id_competicion = id_competicion;
	}
	
	private String consulta = "Select * from Competcion";

	public List<CompeticionDTO> execute()  {

		List<CompeticionDTO> result = new ArrayList<CompeticionDTO>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(consulta);
			pst.setString(1, id_competicion);
			rs = pst.executeQuery();

			while (rs.next()) {
				CompeticionDTO dto = new CompeticionDTO();

				dto.id_competicion = rs.getInt("ID_competicion");
				dto.nombre = rs.getString("nombre_competicion");
				dto.fecha = rs.getDate("fecha_competicion").toLocalDate();
				dto.tipo = rs.getString("tipo");
				dto.distancia = rs.getInt("distancia");
				dto.cuota = rs.getDouble("cuota");
				dto.fecha_fin_inscripcion = rs.getDate("fechaFinInscripcion").toLocalDate();
				dto.plazas = rs.getInt("plazas");
				

			}
			Jdbc.close(rs, pst, c);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}

}
