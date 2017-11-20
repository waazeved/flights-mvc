package br.com.embraer.flights.business.service;

import java.util.List;

import br.com.embraer.flights.business.dto.PilotDto;
import br.com.embraer.flights.business.exception.FlightException;
import br.com.embraer.flights.model.entities.Pilot;

public interface IPilotService extends IDataService<Pilot, Long>{

	Pilot create(PilotDto dto) throws FlightException;

	Pilot edit(PilotDto dto) throws FlightException;

	Pilot save(PilotDto dto) throws FlightException;

	List<PilotDto> findAllDto();
}
