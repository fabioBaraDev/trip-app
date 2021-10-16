package br.com.trips.service.impl;

import br.com.trips.dto.TripDto;
import br.com.trips.service.Service;
import br.com.trips.validators.Validator;

public class ServiceFactory {

	public Service build(TripDto tripDto) throws Exception {
		if (Validator.isSearchByCity(tripDto)) {
			return new ServiceCity();
		}
		if (Validator.isSearchByCountry(tripDto)) {
			return new ServiceCountry();
		}
		if (Validator.isSearchByPeriod(tripDto)) {
			return new ServicePeriod();
		}

		throw new Exception("URL Invalida");
	}

}
