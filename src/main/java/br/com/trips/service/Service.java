package br.com.trips.service;

import java.util.List;

import br.com.trips.dto.TripDto;
import br.com.trips.model.Trip;

public interface Service {

	public List<Trip> findTrip(TripDto dto);
}
