package br.com.trips.dto;

import java.util.Map;
import java.util.function.BiFunction;

public class TripDto {

	private String country;
	private String city;
	private String starts;
	private String ends;

	public TripDto(HandlerRequest request) {
		Map<String, String> queryParameters = request.getQueryStringParameters();
		Map<String, String> pathParameters = request.getPathParameters();
		
		this.country = isValidParameter.apply(pathParameters, "country");
		this.city = isValidParameter.apply(queryParameters, "city"); 
		this.starts = isValidParameter.apply(queryParameters, "starts"); 
		this.ends = isValidParameter.apply(queryParameters, "ends"); 
	}

	private BiFunction<Map<String, String>, String, String> isValidParameter = 
			(parameters, entity) -> parameters != null && parameters.containsKey(entity) ? parameters.get(entity) : "";

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getStarts() {
		return starts;
	}

	public String getEnds() {
		return ends;
	}

}
