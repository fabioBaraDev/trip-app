package br.com.trips.handler;

import java.io.IOException;

import br.com.trips.dto.HandlerRequest;
import br.com.trips.dto.HandlerResponse;
import br.com.trips.model.Trip;
import br.com.trips.service.impl.ServiceTrip;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateTrip implements RequestHandler<HandlerRequest, HandlerResponse> {
	
	private ServiceTrip service = new ServiceTrip();
	private Trip trip;
	
	@Override
	public HandlerResponse handleRequest(final HandlerRequest request, final Context context) {

		try {
			this.trip = new ObjectMapper().readValue(request.getBody(), Trip.class);
		} catch (IOException e) {
			return HandlerResponse.builder()
					.setStatusCode(400)
					.setRawBody("Erro inesperado")
					.build();
		}

		Trip tripRecorded = service.save(trip);
		return HandlerResponse
				.builder()
				.setStatusCode(201)
				.setObjectBody(tripRecorded)
				.build();
	}
}