package persistencia.inscripcion.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import persistencia.PersistenceException;
import persistencia.RecordAsembler;
import persistencia.inscripcion.DatosPagoRecord;
import persistencia.inscripcion.InscripcionGateway;
import persistencia.inscripcion.InscripcionRecord;
import persistencia.inscripcion.JustificantePagoRecord;
import util.jdbc.Jdbc;

public class InscripcionGatewayImpl implements InscripcionGateway {

	private static String SQL_FIND_DNI_AND_COMP = "select * from inscripcion where dni_atleta = ? and id_competicion =? ";
	
	
	private static String SQL_FIND_DNI = "select * from inscripcion where dni_atleta = ? ";
	private static String SQL_FIND_DNI_COMPETICION = "select * from inscripcion where id_competicion = ? and dni_atleta = ? ";
	private static String SQL_PAGAR_TARJETA = "update inscripcion set estado = ? where dni_atleta=? and id_competicion = ? ";
	private static String SQL_GENERAR_JUSTIFICANTE = "select i.dni_atleta, c.nombre_competicion, i.fecha_inscripcion, c.cuota from atleta a, inscripcion i, competicion c where a.dni_atleta = i.dni_atleta and i.id_competicion = c.id_competicion and i.dni_atleta = ? and i.id_competicion = ? ";
	private static String SQL_GENERAR_DATOS_PAGO = "select c.cuota from inscripcion i, competicion c where i.id_competicion = c.id_competicion and i.dni_atleta = ? and i.id_competicion = ? ";
	private static String SQL_PAGAR_TRANSFERENCIA = "update inscripcion set estado = ? where dni_atleta=? and id_competicion = ? ";
	private static String SQL_TRAMITAR_INSCRIPCION = "update inscripcion set estado = ? where id_competicion = ? and dni_atleta = ?";
	private static String SQL_DEVOLVER_DINERO = "update inscripcion set cantidad_devolver = ? where id_competicion = ? and dni_atleta = ?";
	
	private static String FIND_INSCRIPCION_AND_COMPETICION = "SELECT * FROM INSCRIPCION i , competicion c where  i.id_competicion =c.id_competicion   and dni_atleta = ?";
	
	
	private static String ADD= "INSERT INTO INSCRIPCION ( \"DNI_ATLETA\", \"ID_COMPETICION\", \"DORSAL\", \"ESTADO\", \"FECHA_INSCRIPCION\", \"CATEGORIA\", \"CANTIDAD_DEVOLVER\" )\r\n"
			+ "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
	@Override
	public void add(InscripcionRecord t) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(ADD);

			pst.setString(1, t.dni_Atleta);
			pst.setInt(2, t.id_competicion);
			pst.setInt(3, t.dorsal);
			pst.setString(4, t.estado);
			pst.setDate(5, t.fecha_Inscripcion);
			pst.setString(6, t.categoria);
	
			pst.setDouble(7, 0);

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
	public void update(InscripcionRecord t) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<InscripcionRecord> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<InscripcionRecord> findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<InscripcionRecord> findByDni(String dni, int idCompetcion) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(SQL_FIND_DNI_AND_COMP);
			
			pst.setString(1, dni);
			pst.setInt(2, idCompetcion);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				InscripcionRecord record = new InscripcionRecord();
				record.dni_Atleta = rs.getString(1);
				record.id_competicion = rs.getInt(2);
				record.estado = rs.getString(3);
				record.fecha_Inscripcion = rs.getDate(4);
				record.categoria = rs.getString(5);
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
	public Optional<InscripcionRecord> findByDni(String dni) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(SQL_FIND_DNI);
			
			pst.setString(1, dni);
			
			rs = pst.executeQuery();
			
			if (rs.next()) {
				InscripcionRecord record = new InscripcionRecord();
				record.dni_Atleta = rs.getString(1);
				record.id_competicion = rs.getInt(2);
				record.estado = rs.getString(3);
				record.fecha_Inscripcion = rs.getDate(4);
				record.categoria = rs.getString(5);
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
	public void pagarInscripcionTarjeta(String dni, int idCompeticion) {
		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL_PAGAR_TARJETA);
			pst.setString(1, "INSCRITO");
			pst.setString(2, dni);
			pst.setInt(3, idCompeticion);
			pst.executeUpdate();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs,pst,c);
		}
	}

	@Override
	public Optional<JustificantePagoRecord> generarJustificante(String dni, int idCompeticion) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(SQL_GENERAR_JUSTIFICANTE);
			pst.setString(1, dni);
			pst.setInt(2, idCompeticion);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				JustificantePagoRecord jp = new JustificantePagoRecord();
				jp.dni_atleta = rs.getString(1);
				jp.nombre_Competicion = rs.getString(2);
				jp.fecha_Inscripcion = rs.getDate(3);
				jp.cuota = rs.getDouble(4);
				
				return Optional.of(jp);	
			}
			else {
				return Optional.empty();
			}
			
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void pagarInscripcionTransferencia(String dni, int idCompeticion) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL_PAGAR_TRANSFERENCIA);
			pst.setString(1, "PENDIENTE DE PAGO");
			pst.setString(2, dni);
			pst.setInt(3, idCompeticion);
			pst.executeUpdate();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs,pst,c);
		}
		
	}

	@Override
	public Optional<DatosPagoRecord> generarDatosPago(String dni, int idCompeticion) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(SQL_GENERAR_DATOS_PAGO);
			pst.setString(1, dni);
			pst.setInt(2, idCompeticion);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				DatosPagoRecord jp = new DatosPagoRecord();
				jp.cuota = rs.getDouble(1);
				
				return Optional.of(jp);	
			}
			else {
				return Optional.empty();
			}
			
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
	}


//FIND_INSCRIPCION_AND_COMPETICION
	@Override
	public List<InscripcionRecord> findAllByDni(String dni) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(FIND_INSCRIPCION_AND_COMPETICION);
			pst.setString(1, dni);
			rs = pst.executeQuery();

			return RecordAsembler.toInscripcionRecordList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}
	

	@Override
	public void tramitarInscripcion(int idCompeticion, String dni) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL_TRAMITAR_INSCRIPCION);
			pst.setString(1, "INSCRITO");
			pst.setInt(2, idCompeticion);
			pst.setString(3, dni);
			pst.executeUpdate();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs,pst,c);
		}
		
	}

	@Override
	public void anularInscripcion(int idCompeticion, String dni) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL_TRAMITAR_INSCRIPCION);
			pst.setString(1, "ANULADA");
			pst.setInt(2, idCompeticion);
			pst.setString(3, dni);
			pst.executeUpdate();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs,pst,c);
		}
		
	}

	@Override
	public void devolverDinero(int idCompeticion, String dni, double cantidad) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL_DEVOLVER_DINERO);
			pst.setDouble(1, cantidad);
			pst.setInt(2, idCompeticion);
			pst.setString(3, dni);
			pst.executeUpdate();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs,pst,c);
		}
		
	}

	@Override
	public Optional<InscripcionRecord> findByDniYCompeticion(int idCompeticion, String dni) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(SQL_FIND_DNI_COMPETICION);
			
			pst.setInt(1, idCompeticion);
			pst.setString(2, dni);
			
			rs = pst.executeQuery();
			
			if (rs.next()) {
				InscripcionRecord record = new InscripcionRecord();
				record.dni_Atleta = rs.getString(1);
				record.id_competicion = rs.getInt(2);
				record.dorsal = rs.getInt(2);
				record.estado = rs.getString(4);
				record.fecha_Inscripcion = rs.getDate(5);
				record.categoria = rs.getString(6);
				record.cantidadDevolver = rs.getDouble(7);
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
	public List<InscripcionRecord> findInscripcionesByDni(String dni) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<InscripcionRecord> inscripciones = new ArrayList<InscripcionRecord>();
		
		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(SQL_FIND_DNI);
			
			pst.setString(1, dni);
			
			rs = pst.executeQuery();
			
			while (rs.next()) {
				InscripcionRecord record = new InscripcionRecord();
				record.dni_Atleta = rs.getString(1);
				record.id_competicion = rs.getInt(2);
				record.estado = rs.getString(3);
				record.fecha_Inscripcion = rs.getDate(4);
				record.categoria = rs.getString(5);
				record.cantidadDevolver = rs.getDouble(6);
				inscripciones.add(record);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
		
		return inscripciones;
	}
	private static String SQL_FIND_COMPETICION = "select * from inscripcion where id_competicion = ? ";
	
	@Override
	public List<InscripcionRecord> findByCompeticion(int idCompeticion) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL_FIND_COMPETICION);
			
	
			pst.setInt(1, idCompeticion);
			rs = pst.executeQuery();
			
			return RecordAsembler.toInscripcionRecordList2(rs);
	
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		finally {
			Jdbc.close(rs, pst,c);
		}
	}
	private static String ASIGNAR_DORSAL = "UPDATE INSCRIPCION SET DORSAL= ? where dni_atleta = ? and id_competicion=? ";
	
	@Override
	public void asignarDorsal(String dni_atleta,int id_competicion, int dorsal) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(ASIGNAR_DORSAL);
			pst.setInt(1, dorsal);
			pst.setString(2, dni_atleta);
			pst.setInt(3, id_competicion);
			
			pst.executeUpdate();
			
		
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		finally {
			Jdbc.close(rs, pst,c);
		}
		
	}

	
}
