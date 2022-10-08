package bussiness.competicion.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bussiness.competicion.CategoriaDto;
import util.jdbc.Jdbc;

public class CrearCategoria {

	private static String ADD= "insert into CATEGORIA (ID_COMPETICION, nombre, EDAD_INICIAL, EDAD_FINAL)"
			+ " values( ?, ?, ?, ?)";

	public static void execute(CategoriaDto t) throws RuntimeException{
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(ADD);
			pst.setInt(1,  t.id_competicion);
			pst.setString(2, t.nombre);
			pst.setInt(3, t.edad_inicial);
			pst.setInt(4, t.edad_final);

			pst.executeUpdate();

			Jdbc.close(rs, pst, c);
		} catch (Exception e) {
			throw new RuntimeException();
		} 
		
	}
}