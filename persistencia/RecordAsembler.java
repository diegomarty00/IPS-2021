package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bussiness.atleta.AtletaDto;
import persistencia.atleta.AtletaRecord;
import persistencia.competicion.CompeticionRecord;
import persistencia.inscripcion.InscripcionRecord;



public class RecordAsembler {

	private static InscripcionRecord resultSetToInscripcionRecord2(ResultSet rs) throws SQLException {
		InscripcionRecord value = new InscripcionRecord();
		value.id_competicion = rs.getInt("id_competicion");
		value.dni_Atleta = rs.getString("dni_Atleta");
		value.estado = rs.getString("estado");
		value.fecha_Inscripcion = rs.getDate("fecha_inscripcion");
		value.categoria = rs.getString("categoria");
		value.cantidadDevolver = rs.getDouble("cantidad_devolver");
		value.dorsal = rs.getInt("dorsal");

		
		
		return value;
	}
	
	private static InscripcionRecord resultSetToInscripcionRecord(ResultSet rs) throws SQLException {
		InscripcionRecord value = new InscripcionRecord();
		value.id_competicion = rs.getInt("id_competicion");
		value.dni_Atleta = rs.getString("dni_Atleta");
		value.estado = rs.getString("estado");
		value.fecha_Inscripcion = rs.getDate("fecha_inscripcion");
		value.categoria = rs.getString("categoria");
		value.nombre_competicion = rs.getString("nombre_competicion");
		value.cantidadDevolver = rs.getDouble("cantidad_devolver");
		value.dorsal = rs.getInt("dorsal");

		
		
		return value;
	}
	public static List<InscripcionRecord> toInscripcionRecordList(ResultSet rs) throws SQLException {
		List<InscripcionRecord> res = new ArrayList<>();
		while(rs.next()) {
			res.add( resultSetToInscripcionRecord(rs));
		}
		return res;
	}
	
	public static List<InscripcionRecord> toInscripcionRecordList2(ResultSet rs) throws SQLException {
		List<InscripcionRecord> res = new ArrayList<>();
		while(rs.next()) {
			res.add( resultSetToInscripcionRecord2(rs));
		}
		return res;
	}
	
	public static Optional<InscripcionRecord> toToInscripcionRecord(ResultSet m) throws SQLException {
		if (m.next()) {
			return Optional.of(resultSetToInscripcionRecord(m));
		}
		else 	
			return Optional.ofNullable(null);
	}
	
	private static CompeticionRecord resultSetToCompeticionRecord(ResultSet rs) throws SQLException {
		CompeticionRecord value = new CompeticionRecord();
		value.id_competicion = rs.getInt("id_competicion");
		value.nombre = rs.getString("nombre_competicion");
		value.tipo = rs.getString("tipo");
		value.distancia = rs.getInt("distancia");
		value.fecha_inicio_inscripcion = rs.getDate("fecha_inicio_inscripcion").toLocalDate();
		value.plazas = rs.getInt("numero_plazas");
		value.cuota=rs.getDouble("cuota");
		value.fecha=rs.getDate("fecha_competicion").toLocalDate();
		value.fecha_fin_inscripcion=rs.getDate("fecha_fin_inscripcion").toLocalDate();
		value.dorsales_resrvados = rs.getInt("Dorsales_reservados");
		
		
		return value;
	}
	
	public static Optional<CompeticionRecord> toCompeticionRecord(ResultSet m) throws SQLException {
		if (m.next()) {
			return Optional.of(resultSetToCompeticionRecord(m));
		}
		else 	
			return Optional.ofNullable(null);
	}
	
	public static List<CompeticionRecord> toCompeticionRecordList(ResultSet rs) throws SQLException {
		List<CompeticionRecord> res = new ArrayList<>();
		while(rs.next()) {
			res.add( resultSetToCompeticionRecord(rs));
		}
		return res;
	}

	public static Optional<AtletaRecord> toAtletaRecord(ResultSet rs) throws SQLException {
		if (rs.next()) {
			return Optional.of(resultSetToAltetaRecord(rs));
		}
		else 	
			return Optional.ofNullable(null);
	}
	private static AtletaRecord resultSetToAltetaRecord(ResultSet rs) throws SQLException {
		AtletaRecord value = new AtletaRecord();
		//value.id = rs.getString("id_atleta");
		value.nombre = rs.getString("nombre");
		value.apellidos = rs.getString("apellidos");
		value.dni = rs.getString("dni_atleta");
		value.sexo = rs.getString("sexo");
	
		value.fechaNacimieto = rs.getDate("fecha_nac").toLocalDate();
		value.correo=rs.getString("correo");

		
		
		return value;
	}
	public static List<AtletaRecord> toAtletaList(ResultSet rs) throws SQLException {
		List<AtletaRecord> res = new ArrayList<>();
		while(rs.next()) {
			res.add( resultSetToAltetaRecord(rs));
		}
		return res;
	}
	public static AtletaRecord fromDto(AtletaDto atletaDto) {
		AtletaRecord value = new AtletaRecord();
		//value.id = rs.getString("id_atleta");
		value.nombre =atletaDto.nombre;
		value.apellidos = atletaDto.apellido;
		value.dni =atletaDto.DNI;
		value.sexo = atletaDto.genero;
	
		value.fechaNacimieto = atletaDto.fechaNacimiento;
		value.correo=atletaDto.correo;
		value.id_Club=atletaDto.id_club;
		return value;
	}
	
	

	
	
}
