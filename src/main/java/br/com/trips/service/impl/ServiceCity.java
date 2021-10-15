package br.com.trips.service.impl;

import java.util.List;

import br.com.trips.dto.TripDto;
import br.com.trips.model.Trip;
import br.com.trips.repository.TripRepository;
import br.com.trips.service.Service;

public class ServiceCity implements Service {
	
	private final TripRepository repository = new TripRepository();
	
	public List<Trip> findTrip(TripDto dto) {
		return this.repository.findByCity(dto.getCountry(), dto.getCity());
	}
}

