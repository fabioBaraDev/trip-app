package br.com.trips.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import br.com.trips.dto.HandlerRequest;
import br.com.trips.dto.HandlerResponse;
import br.com.trips.dto.TripDto;
import br.com.trips.model.Trip;
import br.com.trips.service.Service;
import br.com.trips.service.impl.ServiceFactory;

public class GetTripsByCountry implements RequestHandler<HandlerRequest, HandlerResponse> {

	private List<Trip> trips = new ArrayList<>();
	private TripDto tripDto;

	@Override
	public HandlerResponse handleRequest(HandlerRequest request, Context context) {

		try {

			tripDto = new TripDto(request);
			Service service = new ServiceFactory().build(tripDto);
			trips = service.findTrip(tripDto);

		} catch (Exception ex) {
			return HandlerResponse.builder().setStatusCode(400).setRawBody(ex.getMessage()).build();
		}

		return HandlerResponse.builder().setStatusCode(verifyResponseCode.apply(trips)).setObjectBody(trips).build();
	}

	Function<List<Trip>, Integer> verifyResponseCode = x -> {
		return x.size() == 0 ? 404 : 200;
	};
}