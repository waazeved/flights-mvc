package br.com.embraer.flights.business.service.impl.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.embraer.flights.business.dto.FlightDto;
import br.com.embraer.flights.business.dto.convert.FlightDtoConverter;
import br.com.embraer.flights.business.exception.CodeIsAlreadyInUseException;
import br.com.embraer.flights.business.exception.FlightException;
import br.com.embraer.flights.business.repository.FlightRepository;
import br.com.embraer.flights.business.service.impl.FlightService;
import br.com.embraer.flights.business.utils.DateTimeUtils;
import br.com.embraer.flights.model.entities.Airplane;
import br.com.embraer.flights.model.entities.Flight;
import br.com.embraer.flights.model.entities.Pilot;
import br.com.embraer.flights.model.entities.enums.FlightStatusEnum;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FlightServiceTest {

	@InjectMocks
	private FlightService service;

	@Mock
	private FlightRepository repository;

	@Mock
	private FlightDtoConverter converter;

	private Flight flightMock;

	private FlightDto flightDtoMock;

	private Pilot pilotMock;

	private Airplane airplaneMock;

	@Before
	public void setUp() throws Exception {

		pilotMock = new Pilot();
		pilotMock.setId(1l);
		pilotMock.setName("John Galt");

		airplaneMock = new Airplane();
		airplaneMock.setId(1l);
		airplaneMock.setName("PTAAA");

		flightMock = new Flight();
		flightMock.setId(1l);
		flightMock.setCode("01");
		flightMock.setDepartureCity("Toronto");
		flightMock.setArrivalCity("São Paulo");
		flightMock.setStart(DateTimeUtils.toLocalDateTime("2017-11-21 01:00"));
		flightMock.setEnd(DateTimeUtils.toLocalDateTime("2017-11-21 01:00"));
		flightMock.setStatus(FlightStatusEnum.PENDING_CONFIRMATION);
		flightMock.setAirplane(airplaneMock);
		flightMock.setPilot(pilotMock);

		flightDtoMock = new FlightDto();
		flightDtoMock.setId(1l);
		flightDtoMock.setCode("01");
		flightDtoMock.setDepartureCity("Toronto");
		flightDtoMock.setArrivalCity("São Paulo");
		flightDtoMock.setStart("2017-11-21 01:00");
		flightDtoMock.setEnd("2017-11-21 01:00");
		flightDtoMock.setStatus(FlightStatusEnum.PENDING_CONFIRMATION.toString());
		flightDtoMock.setAirplaneId(airplaneMock.getId());
		flightDtoMock.setPilotId(pilotMock.getId());

		Mockito.doReturn(flightMock).when(repository).save(Mockito.any(Flight.class));
		Mockito.doReturn(flightMock).when(converter).toFlight(Mockito.any(FlightDto.class));
		Mockito.doReturn(mockListDto()).when(converter).toDtoList(Mockito.anyListOf(Flight.class));
	}

	@Test
	public void save() throws FlightException, CodeIsAlreadyInUseException{
		Mockito.doReturn(flightMock).when(repository).save(Mockito.any(Flight.class));
		FlightDto dto = new FlightDto();
		dto.setCode("01");
		dto.setDepartureCity("Toronto");
		dto.setArrivalCity("São Paulo");
		dto.setStart("2017-11-21 01:00");
		dto.setEnd("2017-11-21 01:00");
		dto.setStatus(FlightStatusEnum.PENDING_CONFIRMATION.toString());
		dto.setAirplaneId(airplaneMock.getId());
		dto.setPilotId(pilotMock.getId());
		Assert.assertEquals(flightMock, service.save(dto));
	}

	@Test(expected=FlightException.class)
	public void saveNullNameShouldThrowsException() throws FlightException, CodeIsAlreadyInUseException {
		FlightDto dto = new FlightDto();
		dto.setCode(null);
		service.save(dto);
	}

	@Test(expected=FlightException.class)
	public void saveEmptyNameShouldThrowsException() throws FlightException, CodeIsAlreadyInUseException {
		FlightDto dto = new FlightDto();
		dto.setCode("");
		service.save(dto);
	}

	@Test(expected=FlightException.class)
	public void saveSpaceNameShouldThrowsException() throws FlightException, CodeIsAlreadyInUseException {
		FlightDto dto = new FlightDto();
		dto.setCode("    ");
		service.save(dto);
	}

	@Test
	public void shouldReturnAllFlights() {
		when(repository.findAll()).thenReturn(mockList());
		List<FlightDto> list = service.findAll(0, 1);
		assertEquals(1, list.size());
		long id = list.get(0).getId();
		assertEquals(1l, id);
	}

	@Test
	public void shouldSearchFlights() {
		when(repository.findAll()).thenReturn(mockList());
		List<FlightDto> list = service.findBySearchAndIndexAndLimitOrderByStart("john", 0, 1);
		assertEquals(1, list.size());
		long id = list.get(0).getId();
		assertEquals(1l, id);
	}

	private List<Flight> mockList() {
		List<Flight> list = new ArrayList<>();
		list.add(flightMock);
		return list;
	}

	private List<FlightDto> mockListDto() {
		List<FlightDto> list = new ArrayList<>();
		list.add(flightDtoMock);
		return list;
	}

}
