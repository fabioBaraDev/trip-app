package br.com.trips.dto;

import java.util.Map;

public class TripDto {

	private String country;
	private String city;
	private String starts;
	private String ends;
	
	public TripDto(HandlerRequest request) {
		Map<String, String> parameters = request.getQueryStringParameters();
		
		this.country = (parameters == null) ? "" : parameters.get("city");
		this.city = parameters.get("city");
		this.starts = parameters.get("starts");
		this.ends = parameters.get("ends");
	}

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
