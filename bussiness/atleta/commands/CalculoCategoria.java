package bussiness.atleta.commands;



import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;

import bussiness.atleta.AtletaDto;
import bussiness.inscripciones.InscripcionDto;
import util.jdbc.Jdbc;

public class CalculoCategoria {
	private static String consulta = "Select * from categoria where id_competicion = ?";
	/**
	 * A partir de la fecha de nacimiento del atleta se le asigna una categor�a en
	 * funci�n su edad y el sexo. Las categor�as son espec�ficas para cada
	 * competici�n (no se configurar�n en esta HU), aunque en algunos casos se
	 * comparten por varias competiciones. Cada categor�a est� determinada por una
	 * edad de inicio y fin, y pueden ser diferentes entre hombres y mujeres.
	 * Ejemplo: Veterano A Masculino (VETAM) - Entre 35 y 39 a�os.
	 * @throws Exception 
	 * 
	 */

	
    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    
    public static String calculoCategoria(Date fecha, int id_competicion) {
    	Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		LocalDate fechaLocal = fecha.toLocalDate();
		int edad = calculateAge(fechaLocal,LocalDate.now());
		
		System.out.println(edad);
		System.out.println("---------------------");
		InscripcionDto idto = new InscripcionDto();
		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(consulta);
			pst.setInt(1, id_competicion);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				idto.edad_final = rs.getInt("edad_final");
				idto.edad_inicial = rs.getInt("edad_inicial");
				idto.categoria = rs.getString("NOMBRE");
				System.out.println(idto.categoria);
				System.out.println(idto.edad_final);
				System.out.println(idto.edad_inicial);
				System.out.println("---------------------");
				if (edad >= idto.edad_inicial && edad <= idto.edad_final)
					return idto.categoria;
			}
			Jdbc.close(rs, pst, c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
	
    public static String calculoCategoriaViejo(Date fecha, String genero) {
    	String result = "Categoría: ";
    	LocalDate fechaLocal = fecha.toLocalDate();
    	
    	int edad = calculateAge(fechaLocal,LocalDate.now());
    	if (edad < 18)
    		return "Sub 18 - " + genero;
    	if (edad >= 100)
    		return "Jubilado - " + genero;
    	for (int i = 15; i < 100; i=i+5) {
    		if(edad >= i) 
    			result = "Sub " + i + " - " + genero;
    	}
    	return result;
    }
    
	public static String calculoCategoria(AtletaDto atleta) throws Exception {
		String re ="";
		
		LocalDate fecha = atleta.fechaNacimiento;
		String genero = atleta.genero;

		
		int edad = calculateAge(fecha,LocalDate.now());

	
		
		
		
		//Otro metodo para cambiar las categorias
		if(edad < 14) {
			re.concat("NI�OA");
		}else if(edad >= 14 && edad <21) {
			re.concat("JOVENA");
		}else if(edad >= 21 && edad <35) {
			re.concat("ADULTOA");
		}else if(edad >= 21 && edad <35) {
			re.concat("VETA");
		}else {
			re.concat("VIEJOA");
		}
		
		if(genero=="hombre") {
			re.concat("M");
		}else if(genero=="mujer"){
			re.concat("F");
		}
		else {
			throw new Exception("Error");
		}		
	
		
		return re;
	}


}
