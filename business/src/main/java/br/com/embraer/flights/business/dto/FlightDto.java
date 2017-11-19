package br.com.embraer.flights.business.dto;

import lombok.Data;

@Data
public class FlightDto {

	private Long id;
	private String code;
	private String departureCity;
	private String arrivalCity;
	private String start;
	private String end;
	private String status;
	private String pilotName;
	private String airplaneName;
	private Integer pilotId;
	private Integer airplaneId;


}
