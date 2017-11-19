package br.com.embraer.flights.business.dto.convert;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.embraer.flights.business.dto.FlightDto;
import br.com.embraer.flights.model.entities.Airplane;
import br.com.embraer.flights.model.entities.Flight;
import br.com.embraer.flights.model.entities.Pilot;
import br.com.embraer.flights.model.entities.enums.FlightStatusEnum;
import br.com.embraer.flights.model.entities.utils.DateTimeUtils;

@Component
public class FlightDtoConverter extends DtoConverter<Flight, FlightDto> {


	public List<FlightDto> toDtoList(Iterable<Flight> entityList){
		return super.toDtoList(entityList, this::toDto);
	}

	public FlightDto toDto(Flight flight) {
		FlightDto dto = new FlightDto();
		dto.setId(flight.getId());
		dto.setCode(flight.getCode());
		dto.setStart(DateTimeUtils.toFormattedDateTime(flight.getStart()));
		dto.setEnd(DateTimeUtils.toFormattedDateTime(flight.getEnd()));
		dto.setStatus(flight.getStatus().name());
		dto.setAirplaneName(flight.getAirplane().getName());
		dto.setPilotName(flight.getPilot().getName());
		return dto;
	}

	public List<Flight> toFlightList(Iterable<FlightDto> dtoList){
		return super.toEntityList(dtoList, this::toFlight);
	}

	public Flight toFlight(FlightDto dto) {
		Flight flight = new Flight();
		flight.setCode(dto.getCode());
		flight.setStart(DateTimeUtils.toLocalDateTime(dto.getStart()));
		flight.setEnd(DateTimeUtils.toLocalDateTime(dto.getEnd()));
		flight.setStatus(FlightStatusEnum.valueOf(dto.getStatus()));
		Pilot pilot = new Pilot();
		pilot.setId(dto.getPilotId());
		flight.setPilot(pilot);
		Airplane airplane = new Airplane();
		airplane.setId(dto.getId());
		flight.setAirplane(airplane);
		return flight;
	}

}