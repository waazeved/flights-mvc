package br.com.embraer.flights.business.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.embraer.flights.business.dto.AirplaneDto;
import br.com.embraer.flights.business.dto.convert.AirplaneDtoConverter;
import br.com.embraer.flights.business.exception.FlightException;
import br.com.embraer.flights.business.exception.NameIsAlreadyInUseException;
import br.com.embraer.flights.business.repository.AirplaneRepository;
import br.com.embraer.flights.business.service.IAirplaneService;
import br.com.embraer.flights.model.entities.Airplane;

@Service
public class AirplaneService extends AbstractDataService<Airplane, Long> implements IAirplaneService {

	@Autowired
	private AirplaneRepository airplaneRepository;
	@Autowired
	private AirplaneDtoConverter converter;

	@Override
	public JpaRepository<Airplane, Long> getRepository() {
		return airplaneRepository;
	}


	@Override
	public Airplane save(AirplaneDto dto) throws FlightException, NameIsAlreadyInUseException {

		if(dto.getId() == null){
			return create(dto);
		}
		else{
			return edit(dto);
		}

	}

	@Override
	public Airplane create(AirplaneDto dto) throws FlightException, NameIsAlreadyInUseException {

		if(dto == null){
			throw new FlightException("Empty data is not allowed");
		}

		if(StringUtils.isEmpty(dto.getName())){
			throw new FlightException("Empty airplane's name is not allowed");
		}

		Airplane airplane = this.converter.toAirplane(dto);

		try {
			this.save(airplane);
		} catch (DataIntegrityViolationException e) {
			throw new NameIsAlreadyInUseException("This airplane's name is already in use.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new FlightException("Error saving new airplane.");
		}

		return airplane;

	}

	@Override
	public Airplane edit(AirplaneDto dto) throws FlightException, NameIsAlreadyInUseException {
		//TODO Create the method to edit a airplane
		return null;
	}




}
