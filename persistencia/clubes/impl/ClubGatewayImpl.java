package persistencia.clubes.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bussiness.atleta.AtletaDto;
import persistencia.PersistenceException;
import persistencia.RecordAsembler;
import persistencia.clubes.ClubRecord;
import persistencia.competicion.CompeticionRecord;
import util.jdbc.Jdbc;

public class ClubGatewayImpl implements persistencia.clubes.ClubGateway {
	
	private static String FIND_ALL = "select nombre_club from club";
	private static String UPDATE_DEUDA = "update club set deuda = ? where nombre_club = ?";
	private static String FIND_BY_NOMBRE = "select * from club where nombre_club = ?";

	@Override
	public void add(ClubRecord t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ClubRecord t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<ClubRecord> findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClubRecord> findAll() {
		return null;
	}

	@Override
	public List<String> findAllByNombre() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> nombres = new ArrayList();
		try {
			c = Jdbc.getCurrentConnection();



			pst = c.prepareStatement(FIND_ALL);

			rs = pst.executeQuery();
			
			while (rs.next()) {
				nombres.add(rs.getString(1));
			}
			
			return nombres;

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void updateDeuda(Double deuda, String nombre) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(UPDATE_DEUDA);
			pst.setDouble(1, deuda);
			pst.setString(2, nombre);
			pst.executeUpdate();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs,pst,c);
		}
		
	}

	@Override
	public Optional<ClubRecord> findByNombre(String nombre) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(FIND_BY_NOMBRE);
			
			pst.setString(1, nombre);
			
			rs = pst.executeQuery();
			
			if (rs.next()) {
				ClubRecord record = new ClubRecord();
				record.nombreClub = rs.getString("nombre_club");
				record.totalPagar = rs.getDouble("deuda");
				record.id_club = rs.getInt("id_club");
				return Optional.of(record);
			}
			else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
	}

}
