package br.com.trips.validators;

import java.util.Map;

import br.com.trips.dto.HandlerRequest;

public class Validator {

	public static boolean isSearchByCity(HandlerRequest request) {
		Map<String, String> parameters = request.getQueryStringParameters();

		return (parameters.containsKey("city") && !(parameters.containsKey("starts") || parameters.containsKey("ends")
				|| parameters.containsKey("country")));
	}

	public static boolean isSearchByCountry(HandlerRequest request) {
		Map<String, String> parameters = request.getQueryStringParameters();

		return (parameters.containsKey("country") && !(parameters.containsKey("starts")
				|| parameters.containsKey("ends") || parameters.containsKey("city")));
	}

	public static boolean isSearchByPeriod(HandlerRequest request) {
		Map<String, String> parameters = request.getQueryStringParameters();

		return ((parameters.containsKey("starts") && parameters.containsKey("ends"))
				&& !(parameters.containsKey("country") || parameters.containsKey("city")));
	}

}
