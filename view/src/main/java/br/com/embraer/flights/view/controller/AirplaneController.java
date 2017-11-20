
package br.com.embraer.flights.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.embraer.flights.business.dto.AirplaneDto;
import br.com.embraer.flights.business.exception.FlightException;
import br.com.embraer.flights.business.exception.NameIsAlreadyInUseException;
import br.com.embraer.flights.business.service.IAirplaneService;
import br.com.embraer.flights.view.controller.response.Response;


@RestController
@RequestMapping(value = "/airplane")
public class AirplaneController {

	@Autowired
	private IAirplaneService airplaneService;


	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Response save(@RequestBody AirplaneDto airplaneDto){
			 try {
				 airplaneService.save(airplaneDto);
				return Response.success();
			} catch (NameIsAlreadyInUseException | FlightException e) {
				return Response.exception(e);
			}
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public List<AirplaneDto> findAll(){
		return this.airplaneService.findAllDto();
	}

}