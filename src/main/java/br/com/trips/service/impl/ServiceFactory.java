package br.com.trips.service.impl;

import br.com.trips.dto.HandlerRequest;
import br.com.trips.service.Service;
import br.com.trips.validators.Validator;

public class ServiceFactory {

	public Service build(HandlerRequest request) throws Exception {
		
		if (Validator.isSearchByCity(request)) {
			return new ServiceCity();
		}
		if (Validator.isSearchByCountry(request)) {
			return new ServiceCountry();
		}
		if (Validator.isSearchByPeriod(request)) {
			return new ServicePeriod();
		}

		throw new Exception("URL Invalida");
	}

}
