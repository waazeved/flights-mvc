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

import br.com.embraer.flights.business.dto.PilotDto;
import br.com.embraer.flights.business.dto.convert.PilotDtoConverter;
import br.com.embraer.flights.business.exception.FlightException;
import br.com.embraer.flights.business.exception.NameIsAlreadyInUseException;
import br.com.embraer.flights.business.repository.PilotRepository;
import br.com.embraer.flights.business.service.impl.PilotService;
import br.com.embraer.flights.model.entities.Pilot;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PilotServiceTest {

	@InjectMocks
	private PilotService service;

	@Mock
	private PilotRepository repository;

	@Mock
	private PilotDtoConverter converter;

	private Pilot pilotMock;

	private PilotDto pilotDtoMock;

	@Before
	public void setUp() throws Exception {
		pilotMock = new Pilot();
		pilotMock.setId(1l);
		pilotMock.setName("John Galt");

		pilotDtoMock = new PilotDto();
		pilotDtoMock.setId(1l);
		pilotDtoMock.setName("John Galt");

		Mockito.doReturn(pilotMock).when(converter).toPilot(Mockito.any(PilotDto.class));
		Mockito.doReturn(mockListDto()).when(converter).toDtoList(Mockito.anyListOf(Pilot.class));
	}

	@Test
	public void save() throws FlightException, NameIsAlreadyInUseException{
		Mockito.doReturn(pilotMock).when(repository).save(Mockito.any(Pilot.class));
		PilotDto dto = new PilotDto();
		dto.setName("John Galt");
		Assert.assertEquals(pilotMock, service.save(dto));
	}

	@Test(expected=FlightException.class)
	public void saveNullNameShouldThrowsException() throws FlightException {
		Mockito.doReturn(pilotMock).when(repository).save(Mockito.any(Pilot.class));
		PilotDto dto = new PilotDto();
		dto.setName(null);
		service.save(dto);
	}

	@Test(expected=FlightException.class)
	public void saveEmptyNameShouldThrowsException() throws FlightException {
		Mockito.doReturn(pilotMock).when(repository).save(Mockito.any(Pilot.class));
		PilotDto dto = new PilotDto();
		dto.setName("");
		service.save(dto);
	}

	@Test(expected=FlightException.class)
	public void saveSpaceNameShouldThrowsException() throws FlightException {
		Mockito.doReturn(pilotMock).when(repository).save(Mockito.any(Pilot.class));
		PilotDto dto = new PilotDto();
		dto.setName("    ");
		service.save(dto);
	}

	@Test
	public void shouldReturnAllPilots() {
		when(repository.findAll()).thenReturn(mockList());
		List<PilotDto> list = service.findAllDto();
		assertEquals(1, list.size());
		long id = list.get(0).getId();
		assertEquals(1l, id);
	}

	private List<Pilot> mockList() {
		List<Pilot> list = new ArrayList<>();
		Pilot pilot = new Pilot();
		pilot.setId(1L);
		list.add(pilotMock);
		return list;
	}

	private List<PilotDto> mockListDto() {
		List<PilotDto> list = new ArrayList<>();
		list.add(pilotDtoMock);
		return list;
	}

}
