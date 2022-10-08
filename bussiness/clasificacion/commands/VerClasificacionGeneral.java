package bussiness.clasificacion.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bussiness.clasificacion.ClasificacionDTO;
import util.jdbc.Jdbc;

public class VerClasificacionGeneral {

	private static String consulta = "Select * from Clasificacion where id_competicion = ? order by estado asc, TIEMPO_FINAL desc";
	private static String buscar_competicion = "Select * from Competicion where id_competicion = ?";
	private static String buscar_atleta_general = "Select * from atleta where dni_atleta = ?";
	private static String buscar_nombre_club = "Select * from club where id_club = ?";
	private static String buscar_atleta_sexo = "Select * from atleta where dni_atleta = ? and sexo = ?";
	
	public static List<ClasificacionDTO> execute(int id_competicion, String sexo) {

		List<ClasificacionDTO> result = new ArrayList<ClasificacionDTO>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ClasificacionDTO dtobase = comprobarCompeticion(id_competicion);
		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(consulta);
			pst.setInt(1, id_competicion);
			rs = pst.executeQuery();

			while (rs.next()) {
				ClasificacionDTO dto = new ClasificacionDTO();
				
				dto.sexo = sexo;
				dto.id_competicion = dtobase.id_competicion;
				dto.nombre_competicion = dtobase.nombre_competicion;
				dto.tipo = dtobase.tipo;
				dto.distancia = dtobase.distancia;
				dto.fecha_competicion = dtobase.fecha_competicion;
				
				dto.id_competicion = rs.getInt("ID_COMPETICION");
				dto.tiempoFinal = rs.getTime("TIEMPO_FINAL");
				dto.tiempoInicial = rs.getTime("TIEMPO_INICIAL");
				dto.tiempos_tramos = rs.getString("TIEMPOS_TRAMOS");
				dto.estado = rs.getString("ESTADO");
				dto.dni_atleta = rs.getString("dni_atleta");
				if (dto.estado != null)
					dto.resultadoTiempo = "---";
				else
					dto = obtenerResultadoTiempo(dto);
				
				dto = buscarPersona(dto);
				if(dto.nombre_atleta !=null)
					result.add(dto);
			}
			Jdbc.close(rs, pst, c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	private static ClasificacionDTO buscarPersona(ClasificacionDTO result) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			if (result.sexo != null) {
				c = Jdbc.getConnection();
				pst = c.prepareStatement(buscar_atleta_sexo);
				pst.setString(1, result.dni_atleta);
				pst.setString(2, result.sexo);
				rs = pst.executeQuery();
			}else {
				c = Jdbc.getConnection();
				pst = c.prepareStatement(buscar_atleta_general);
				pst.setString(1, result.dni_atleta);
				rs = pst.executeQuery();
			}

			while (rs.next()) {

				result.nombre_atleta = rs.getString("Nombre");
				result.apellidos_atleta = rs.getString("apellidos");
				result.sexo = rs.getString("sexo");
				result.id_club = rs.getInt("id_club");
				result = nombreClub(result);
			}
			Jdbc.close(rs, pst, c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	private static ClasificacionDTO nombreClub(ClasificacionDTO result) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(buscar_nombre_club);
			pst.setInt(1, result.id_club);
			rs = pst.executeQuery();

			while (rs.next()) {
				result.nombre_club = rs.getString("nombre_club");
			}
			Jdbc.close(rs, pst, c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	private static ClasificacionDTO comprobarCompeticion(int id_competicion) {
		ClasificacionDTO result = new ClasificacionDTO();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(buscar_competicion);
			pst.setInt(1, id_competicion);
			rs = pst.executeQuery();

			while (rs.next()) {

				result.id_competicion = rs.getInt("ID_COMPETICION");
				result.distancia = rs.getInt("DISTANCIA");
				result.nombre_competicion = rs.getString("nombre_competicion");
				result.tipo = rs.getString("tipo");
				result.fecha_competicion = rs.getDate("fecha_competicion").toLocalDate();
			}
			Jdbc.close(rs, pst, c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	private static ClasificacionDTO obtenerResultadoTiempo(ClasificacionDTO dto) {
		String finaltiempo = dto.tiempoFinal.toLocalTime()+"";
		String[] tiempoFinal = finaltiempo.split(":");

		String inicialTiempo= dto.tiempoInicial.toLocalTime()+"";
		String[] tiempoInicial = inicialTiempo.split(":");
		int numero = 0;
		try {
			numero = Integer.parseInt(tiempoInicial[2]);
		}catch (Exception e) {
			numero = 0;
		}
		
		System.out.println(Integer.parseInt(tiempoFinal[2]));
		int horas = Integer.parseInt(tiempoFinal[0]) - Integer.parseInt(tiempoInicial[0]);
		int minutos = Integer.parseInt(tiempoFinal[1]) - Integer.parseInt(tiempoInicial[1]);
		int segundos = Integer.parseInt(tiempoFinal[2]) - numero;

		if(horas/10 == 0) {
			dto.resultadoTiempo = "0"+horas +":";
		}else {
			dto.resultadoTiempo = horas+":";
		}
		
		if(minutos/10 == 0) {
			dto.resultadoTiempo = dto.resultadoTiempo + "0"+minutos + ":";
		}else {
			dto.resultadoTiempo = dto.resultadoTiempo + minutos + ":";
		}
		
		if(segundos/10 == 0) {
			dto.resultadoTiempo = dto.resultadoTiempo + "0"+segundos;
		}else {
			dto.resultadoTiempo = dto.resultadoTiempo + segundos ;
		}

		if (dto.resultadoTiempo == "00:00:00")
			dto.resultadoTiempo = "---";
		return dto;
	}


}