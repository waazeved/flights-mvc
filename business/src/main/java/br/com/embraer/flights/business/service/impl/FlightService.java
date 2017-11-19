package br.com.embraer.flights.business.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import br.com.embraer.flights.business.dto.FlightDto;
import br.com.embraer.flights.business.dto.convert.FlightDtoConverter;
import br.com.embraer.flights.business.exception.CodeIsAlreadyInUseException;
import br.com.embraer.flights.business.exception.FlightException;
import br.com.embraer.flights.business.repository.FlightRepository;
import br.com.embraer.flights.business.service.IFlightService;
import br.com.embraer.flights.model.entities.Flight;

@Service
public class FlightService extends AbstractDataService<Flight, Long> implements IFlightService {

	private static final Logger LOGGER = LoggerFactory.getLogger(IFlightService.class);

	@Autowired
	private FlightDtoConverter converter;
	@Autowired
	private FlightRepository flightRepository;

	@Override
	public JpaRepository<Flight, Long> getRepository() {
		return flightRepository;
	}

	@Override
	public List<FlightDto> findBySearchAndIndexAndLimitOrderByStart(String search, int index, int limit) {

		try {
			search = URLDecoder.decode(search, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}

		if (search == null || search.equals("*")) {
			search = "";
		}

		search = search.toLowerCase().trim();
		Pageable pageable = new PageRequest(index, limit);
		List<Flight> flights = this.flightRepository.findBySearchOrderByStart(search, pageable);
		return this.converter.toDtoList(flights);

	}

	@Override
	public Flight save(FlightDto dto) throws FlightException, CodeIsAlreadyInUseException {

		if(dto.getId() == null){
			return create(dto);
		}
		else{
			return edit(dto);
		}

	}

	@Override
	public Flight create(FlightDto flightDto) throws CodeIsAlreadyInUseException, FlightException {

		if(flightDto == null){
			throw new FlightException("Empty data is not allowed");
		}

		if(StringUtils.isEmpty(flightDto.getCode())){
			throw new FlightException("Empty flight code is not allowed");
		}

		if(StringUtils.isEmpty(flightDto.getStart())){
			throw new FlightException("Empty flight start dateTime is not allowed");
		}

		if(StringUtils.isEmpty(flightDto.getEnd())){
			throw new FlightException("Empty flight end dateTime is not allowed");
		}

		if(flightDto.getAirplaneId() == null){
			throw new FlightException("Airplane id can't be null");
		}

		if(flightDto.getPilotId() == null){
			throw new FlightException("Pilot id can't be null");
		}

		Flight flight = this.converter.toFlight(flightDto);
		try {
			this.save(flight);
		} catch (UnexpectedRollbackException e) {
			throw new CodeIsAlreadyInUseException("This airplane code is already in use.");
		} catch (Exception e) {
			throw new FlightException("Error saving new flight.");
		}
		return flight;


	}

	@Override
	public Flight edit(FlightDto flightDto) throws CodeIsAlreadyInUseException, FlightException {
		//TODO Create the method to edit a flight
		return null;
	}




}
