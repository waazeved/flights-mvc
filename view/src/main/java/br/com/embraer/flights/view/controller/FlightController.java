
package br.com.embraer.flights.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.embraer.flights.business.dto.FlightDto;
import br.com.embraer.flights.business.exception.CodeIsAlreadyInUseException;
import br.com.embraer.flights.business.exception.FlightException;
import br.com.embraer.flights.business.service.IFlightService;
import br.com.embraer.flights.view.controller.response.Response;


@RestController
@RequestMapping(value = "/flight")
public class FlightController {

	@Autowired
	private IFlightService flightService;



	@RequestMapping(value = "/search/{search}/{index}/{limit}", method = RequestMethod.GET)
	public List<FlightDto> search(@PathVariable("search") String search, @PathVariable("index") int index, @PathVariable("limit") int limit){
		return this.flightService.findBySearchAndIndexAndLimitOrderByStart(search, index, limit);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Response save(@RequestBody FlightDto flightDto){
			try {
				flightService.save(flightDto);
				return Response.success();
			} catch (CodeIsAlreadyInUseException | FlightException e) {
				return Response.exception(e);
			}
	}

}