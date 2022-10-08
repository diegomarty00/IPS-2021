package bussiness.atleta.commands;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bussiness.atleta.AtletaDto;
import util.jdbc.Jdbc;

public class RegistrarAtleta {

	private static String ADD= "insert into Atleta (dni_atleta, nombre, apellidos, "
			+ "fecha_nac, sexo, correo) values(?, ?, ?, ?, ?, ?)";

	public static void execute(AtletaDto t) throws RuntimeException{
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(ADD);
			pst.setString(1,  t.DNI);
			pst.setString(2, t.nombre);
			pst.setString(3, t.apellido);
			pst.setDate(4, Date.valueOf( t.fechaNacimiento));
			pst.setString(5, t.genero);
			pst.setString(6, t.correo);

			pst.executeUpdate();

			Jdbc.close(rs, pst, c);
		} catch (Exception e) {
			throw new RuntimeException();
		} 
		
	}
}
