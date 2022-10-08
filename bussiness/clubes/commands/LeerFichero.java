package bussiness.clubes.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bussiness.atleta.AtletaDto;
import util.BusinessException;
import util.command.Command;

public class LeerFichero implements Command<List<AtletaDto>> {
	
	private File archivo;
	
	

	public LeerFichero(File archivo) {
		super();
		this.archivo = archivo;
	}



	@Override
	public List<AtletaDto> execute() throws BusinessException {
		BufferedReader fichero = null;
		String linea;
		String[] datos = null;
		List<AtletaDto> dtos = new ArrayList<>();

		try {
			fichero = new BufferedReader(new FileReader(archivo));
			linea = fichero.readLine();

			while (linea != null) {
				datos = linea.split(";");
				String dniAtleta = datos[0];
				String nombre = datos[1];
				String apellidos = datos[2];
				LocalDate fecha = LocalDate.parse(datos[3]);
				String sexo = datos[4];
				String email = datos[5];
				AtletaDto dto = new AtletaDto();
				dto.DNI = dniAtleta;
				dto.nombre = nombre;
				dto.apellido = apellidos;
				dto.fechaNacimiento = fecha;
				dto.genero = sexo;
				dto.categoria = "SIN CATEGORIA"; //Aqui iria el calcular categoria
				dto.correo = email;
				linea = fichero.readLine();
				dtos.add(dto);

			}
			
			fichero.close();
		} catch (FileNotFoundException e) {
			throw new BusinessException("Fichero no encontrado");
		} catch (IOException e) {
			throw new BusinessException("Error en el procesamiento del fichero");
		}

		return dtos;
	}

}
