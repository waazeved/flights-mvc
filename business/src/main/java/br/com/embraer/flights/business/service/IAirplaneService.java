package br.com.embraer.flights.business.service;

import br.com.embraer.flights.business.dto.AirplaneDto;
import br.com.embraer.flights.business.exception.FlightException;
import br.com.embraer.flights.business.exception.NameIsAlreadyInUseException;
import br.com.embraer.flights.model.entities.Airplane;

public interface IAirplaneService extends IDataService<Airplane, Long>{

	Airplane save(AirplaneDto airplaneDto) throws FlightException, NameIsAlreadyInUseException;

	Airplane create(AirplaneDto dto) throws FlightException, NameIsAlreadyInUseException;

	Airplane edit(AirplaneDto dto) throws FlightException, NameIsAlreadyInUseException;


}
