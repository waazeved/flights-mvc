package br.com.embraer.flights.business.dto.convert;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.embraer.flights.business.dto.AirplaneDto;
import br.com.embraer.flights.model.entities.Airplane;

@Component
public class AirplaneDtoConverter extends DtoConverter<Airplane, AirplaneDto> {


	public List<AirplaneDto> toDtoList(Iterable<Airplane> entityList){
		return super.toDtoList(entityList, this::toDto);
	}

	public AirplaneDto toDto(Airplane airplane) {
		AirplaneDto dto = new AirplaneDto();
		dto.setId(airplane.getId());
		dto.setName(airplane.getName());
		return dto;
	}

	public List<Airplane> toAirplaneList(Iterable<AirplaneDto> dtoList){
		return super.toEntityList(dtoList, this::toAirplane);
	}

	public Airplane toAirplane(AirplaneDto dto) {
		Airplane airplane = new Airplane();
		airplane.setName(dto.getName());
		return airplane;
	}

}