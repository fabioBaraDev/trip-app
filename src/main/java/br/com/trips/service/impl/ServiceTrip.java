package br.com.trips.service.impl;

import br.com.trips.model.Trip;
import br.com.trips.repository.TripRepository;

public class ServiceTrip {

	private final TripRepository repository = new TripRepository();
	
	public Trip save(Trip trip) {
		return repository.save(trip);
	}
}
