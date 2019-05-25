package br.com.app.zup.xyinc.controller;

import java.util.regex.Pattern;

import javax.validation.ValidationException;

import org.hibernate.internal.util.StringHelper;

public abstract class CoordenadasValidations {


	private Pattern caracterPattern = Pattern.compile("[a-zA-Z]");
	
	
	/**
	 * @param latitudeParam
	 * @param longitudeParam
	 * @param distanciaMaxParam
	 */
	public void validarParametros(String latitudeParam, String longitudeParam, String distanciaMaxParam) {
		this.validarParametros(latitudeParam, longitudeParam);
		
		if(StringHelper.isEmpty(distanciaMaxParam)) {
			throw new ValidationException("Distancia não informada");
		}
		
		if(caracterPattern.matcher(distanciaMaxParam).find()) {
			throw new ValidationException("Somente numeros deve ser informados para a Distancia");
		}
		if((Integer.parseInt(distanciaMaxParam)) <= 0) {
			throw new ValidationException("Distancia deve ser um valor inteiro maior que zero");
		}		
	}

	/**
	 * @param latitudeParam
	 * @param longitudeParam
	 */
	public void validarParametros(String latitudeParam, String longitudeParam) {
		if(StringHelper.isEmpty(latitudeParam)) {
			throw new ValidationException("Latitude não informada");
		}
		if(caracterPattern.matcher(latitudeParam).find()) {
			throw new ValidationException("Somente numeros deve ser informados para a Latitude");
		}
		if((Integer.parseInt(latitudeParam)) <= 0) {
			throw new ValidationException("Latitude deve ser um valor inteiro maior que zero");
		}
		
		if(StringHelper.isEmpty(longitudeParam)) {
			throw new ValidationException("Longitude não informada");
		}
		if(caracterPattern.matcher(longitudeParam).find()) {
			throw new ValidationException("Somente numeros deve ser informados para a Longitude");
		}
		if((Integer.parseInt(longitudeParam)) <= 0) {
			throw new ValidationException("Longitude deve ser um valor inteiro maior que zero");
		}
	}

}
