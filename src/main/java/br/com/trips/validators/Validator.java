package br.com.trips.validators;

import br.com.trips.dto.TripDto;

public class Validator {

	public static boolean isSearchByCity(TripDto tripDto) {
		return (!tripDto.getCity().isEmpty() && (tripDto.getStarts().isEmpty() && tripDto.getEnds().isEmpty()));
	}

	public static boolean isSearchByCountry(TripDto tripDto) {
		return (!tripDto.getCountry().isEmpty()
				&& (tripDto.getStarts().isEmpty() && tripDto.getEnds().isEmpty() && tripDto.getCity().isEmpty()));
	}

	public static boolean isSearchByPeriod(TripDto tripDto) {
		return (!(tripDto.getStarts().isEmpty() && tripDto.getEnds().isEmpty()) && (tripDto.getCity().isEmpty()));
	}

}
