package persistencia.competicion.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import persistencia.PersistenceException;
import persistencia.RecordAsembler;
import persistencia.competicion.CompeticionGateway;
import persistencia.competicion.CompeticionRecord;
import persistencia.competicion.TramoRecord;
import util.jdbc.Jdbc;

public class CompleticionGatewayImpl implements CompeticionGateway{

	
	private static String ADD= "insert into Competicion(id_competicion, nombre_competicion, "
			+ "fecha_competicion, tipo, distancia, cuota,numero_tramos,fecha_fin_inscripcion,"
			+ "numero_plazas, fecha_inicio_inscripcion, dorsales_reservados) values(?, ?, ?, ?, ?, ?,?,?,?, ?, ?)";
	
	private static String ADD_TRAMO = "insert into Tramo(id_competicion, id_tramo, descripcion) values(?,?,?)"; 
	
	private static String SQL_FIND_BY_NOMBRE = "select * from competicion where nombre_competicion = ? ";
	
	@Override
	public void add(CompeticionRecord t) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(ADD);
			pst.setInt(1,  t.id_competicion);
			pst.setString(2, t.nombre);
			pst.setDate(3, Date.valueOf( t.fecha));
			pst.setString(4, t.tipo);
			pst.setInt(5, t.distancia);
			pst.setDouble(6, t.cuota);
			pst.setInt(7, t.numero_tramos);

			pst.setDate(8, Date.valueOf( t.fecha_fin_inscripcion));
			pst.setInt(9, t.plazas);
			
			pst.setDate(10, Date.valueOf( t.fecha_inicio_inscripcion));
			pst.setInt(11, t.dorsalesReservados);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CompeticionRecord t) {
		// TODO Auto-generated method stub
		
	}
	private static String FIND_BY_ID= "select * from Competicion where id_competicion = ?";
	@Override
	public Optional<CompeticionRecord> findById(int id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(FIND_BY_ID);

			pst.setInt(1,Integer.valueOf( id));

			rs = pst.executeQuery();

			return RecordAsembler.toCompeticionRecord(rs);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst,c);
		}
	}

	
	
	private String findAll ="select * from Competicion";
	@Override
	public List<CompeticionRecord> findAll() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(findAll);
			rs = pst.executeQuery();
			
			

			return RecordAsembler.toCompeticionRecordList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}
	
	@Override
	public Optional<CompeticionRecord> findByNombre(String nombreCompeticion) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(SQL_FIND_BY_NOMBRE);
			
			pst.setString(1, nombreCompeticion);
			
			rs = pst.executeQuery();
			
			if (rs.next()) {
				CompeticionRecord record = new CompeticionRecord();
				record.id_competicion = rs.getInt(1);
				record.nombre = rs.getString(2);
				record.fecha = rs.getDate(3).toLocalDate();
				record.tipo = rs.getString(4);
				record.distancia = rs.getInt(5);
				record.cuota = rs.getDouble(6);
				record.fecha_fin_inscripcion = rs.getDate(7).toLocalDate();
				record.tramos = rs.getInt(7);
				record.fecha_fin_inscripcion = rs.getDate(8).toLocalDate();
				record.plazas = rs.getInt(9);
				record.fecha_inicio_inscripcion = rs.getDate(10).toLocalDate();
				record.dorsales_resrvados = rs.getInt(11);
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

	@Override
	public void addTramo(TramoRecord record) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(ADD_TRAMO);
			pst.setInt(1, record.idCompeticion);
			pst.setInt(2, record.numeroTramo);
			pst.setString(3, record.descripcion);
			
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		
		
	}

}
