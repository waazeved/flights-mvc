
package br.com.embraer.flights.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.embraer.flights.business.dto.PilotDto;
import br.com.embraer.flights.business.exception.FlightException;
import br.com.embraer.flights.business.exception.NameIsAlreadyInUseException;
import br.com.embraer.flights.business.service.IPilotService;
import br.com.embraer.flights.view.controller.response.Response;


@RestController
@RequestMapping(value = "/pilot")
public class PilotController {

	@Autowired
	private IPilotService pilotService;


	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Response save(@RequestBody PilotDto pilotDto){
			 try {
				 pilotService.save(pilotDto);
				return Response.success();
			} catch (NameIsAlreadyInUseException | FlightException e) {
				return Response.exception(e);
			}
	}

}