package br.com.trips.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import br.com.trips.model.Trip;

public class TripRepository {

	private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

	public Trip save(final Trip trip) {
		mapper.save(trip);
		return trip;
	}

	public List<Trip> findByPeriod(final String country, final String starts, final String ends) {

		final Map<String, AttributeValue> attributes = new HashMap<String, AttributeValue>();
		attributes.put(":val1", new AttributeValue().withS(country));
		attributes.put(":val2", new AttributeValue().withS(starts));
		attributes.put(":val3", new AttributeValue().withS(ends));

		final DynamoDBQueryExpression<Trip> query = new DynamoDBQueryExpression<Trip>()
				.withKeyConditionExpression("country = :val1 AND travelDate between :val2 and :val3")
				.withExpressionAttributeValues(attributes);

		final List<Trip> trips = mapper.query(Trip.class, query);

		return trips;
	}

	public List<Trip> findByCountry(final String country) {

		final Map<String, AttributeValue> attributes = new HashMap<String, AttributeValue>();
		attributes.put(":val1", new AttributeValue().withS(country));

		final DynamoDBQueryExpression<Trip> query = new DynamoDBQueryExpression<Trip>()
				.withKeyConditionExpression("country = :val1").withExpressionAttributeValues(attributes);

		final List<Trip> trips = mapper.query(Trip.class, query);

		return trips;
	}

	public List<Trip> findByCity(final String country, final String city) {

		final Map<String, AttributeValue> attributes = new HashMap<String, AttributeValue>();
		attributes.put(":val1", new AttributeValue().withS(country));
		attributes.put(":val2", new AttributeValue().withS(city));

		final DynamoDBQueryExpression<Trip> query = new DynamoDBQueryExpression<Trip>()
				.withConsistentRead(false)
				.withKeyConditionExpression("country = :val1")
				.withFilterExpression("contains(city,:val2)")
				.withExpressionAttributeValues(attributes);

		final List<Trip> trips = mapper.query(Trip.class, query);

		return trips;
	}
}