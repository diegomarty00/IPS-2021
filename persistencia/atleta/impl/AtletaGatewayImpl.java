package persistencia.atleta.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import persistencia.PersistenceException;
import persistencia.RecordAsembler;
import persistencia.atleta.AtletaGateway;
import persistencia.atleta.AtletaRecord;
import util.jdbc.Jdbc;


public class AtletaGatewayImpl implements AtletaGateway{

	
	private static String ADD= "INSERT INTO ATLETA( DNI_ATLETA, NOMBRE, APELLIDOS, FECHA_NAC, SEXO, CORREO, ID_CLUB )"
			+ "VALUES ( ?, ?, ?,? ,?, ?,? )";
	@Override
	public void add(AtletaRecord t) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(ADD);

			pst.setString(1, t.dni);
			pst.setString(2, t.nombre);
			pst.setString(3, t.apellidos);
			pst.setDate(4, Date.valueOf(t.fechaNacimieto) );
			pst.setString(5, t.sexo);
			pst.setString(6, t.correo);
			pst.setInt(7, t.id_Club);

			pst.executeUpdate();

			
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst,c);
		}
		
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(AtletaRecord t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<AtletaRecord> findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AtletaRecord> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	private static String FIND_BY_ID= "select * from Atleta where dni_atleta = ?";
	@Override
	public Optional<AtletaRecord> getByDni(String dni) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(FIND_BY_ID);

			pst.setString(1, dni);

			rs = pst.executeQuery();

			return RecordAsembler.toAtletaRecord(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst,c);
		}
	}
	private static String FIND_BY_EMAIL= "select * from Atleta where correo = ?";
	@Override
	public Optional<AtletaRecord> getByEmail(String email) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(FIND_BY_EMAIL);

			pst.setString(1, email);

			rs = pst.executeQuery();

			return RecordAsembler.toAtletaRecord(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}



	private static String FIND_BY_COMPETICION_ID= "SELECT * FROM ATLETA a left join inscripcion i on i.dni_atleta= a.dni_atleta and i.id_competicion = ?";
	@Override
	public List<AtletaRecord> getByCompeticion_id(int id_competicion) {

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();



			pst = c.prepareStatement(FIND_BY_COMPETICION_ID);

			pst.setInt(1, id_competicion);

			rs = pst.executeQuery();
			
			return RecordAsembler.toAtletaList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	private static String UPDATE_DORSAL= "update dorsal from Inscripcion where dorsal =?,atleta_id =?";
	@Override
	public void setDorsal(int dorsal, String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AtletaRecord> findByCompeticionId(int competicion_id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
