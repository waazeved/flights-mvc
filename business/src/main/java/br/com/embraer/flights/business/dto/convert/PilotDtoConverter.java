package br.com.embraer.flights.business.dto.convert;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.embraer.flights.business.dto.PilotDto;
import br.com.embraer.flights.model.entities.Pilot;

@Component
public class PilotDtoConverter extends DtoConverter<Pilot, PilotDto> {


	public List<PilotDto> toDtoList(Iterable<Pilot> entityList){
		return super.toDtoList(entityList, this::toDto);
	}

	public PilotDto toDto(Pilot pilot) {
		PilotDto dto = new PilotDto();
		dto.setId(pilot.getId());
		dto.setName(pilot.getName());
		return dto;
	}

	public List<Pilot> toPilotList(Iterable<PilotDto> dtoList){
		return super.toEntityList(dtoList, this::toPilot);
	}

	public Pilot toPilot(PilotDto dto) {
		Pilot pilot = new Pilot();
		pilot.setName(dto.getName());
		return pilot;
	}

}