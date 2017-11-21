package br.com.embraer.flights.business.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.embraer.flights.business.dto.PilotDto;
import br.com.embraer.flights.business.dto.convert.PilotDtoConverter;
import br.com.embraer.flights.business.exception.FlightException;
import br.com.embraer.flights.business.repository.PilotRepository;
import br.com.embraer.flights.business.service.IPilotService;
import br.com.embraer.flights.model.entities.Pilot;

@Service
public class PilotService extends AbstractDataService<Pilot, Long> implements IPilotService{

	@Autowired
	private PilotRepository pilotRepository;
	@Autowired
	private PilotDtoConverter converter;

	@Override
	public JpaRepository<Pilot, Long> getRepository() {
		return this.pilotRepository;
	}

	@Override
	public List<PilotDto> findAllDto(){
		List<Pilot> pilots = this.findAll();
		return this.converter.toDtoList(pilots);
	}

	@Override
	public Pilot save(PilotDto dto) throws FlightException {

		if(dto.getId() == null){
			return create(dto);
		}
		else{
			return edit(dto);
		}

	}

	@Override
	public Pilot create(PilotDto dto) throws FlightException {

		if(dto == null){
			throw new FlightException("Empty data is not allowed");
		}

		if(StringUtils.isEmpty(dto.getName()) || dto.getName().trim().isEmpty()){
			throw new FlightException("Empty pilot's name is not allowed");
		}

		Pilot pilot = this.converter.toPilot(dto);

		try {
			return this.save(pilot);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FlightException("Error saving new pilot.");
		}

	}

	@Override
	public Pilot edit(PilotDto dto) throws FlightException {
		//TODO Create the method to edit a pilot
		return null;
	}

}
