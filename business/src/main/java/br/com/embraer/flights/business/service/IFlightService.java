package br.com.embraer.flights.business.service;

import java.util.List;

import br.com.embraer.flights.business.dto.FlightDto;
import br.com.embraer.flights.business.exception.CodeIsAlreadyInUseException;
import br.com.embraer.flights.business.exception.FlightException;
import br.com.embraer.flights.model.entities.Flight;


public interface IFlightService extends IDataService<Flight, Long> {

	List<FlightDto> findBySearchAndIndexAndLimitOrderByStart(String search, int index, int limit);

	Flight create(FlightDto flightDto) throws CodeIsAlreadyInUseException, FlightException;

	Flight edit(FlightDto flightDto) throws CodeIsAlreadyInUseException, FlightException;

	Flight save(FlightDto dto) throws FlightException, CodeIsAlreadyInUseException;
}
